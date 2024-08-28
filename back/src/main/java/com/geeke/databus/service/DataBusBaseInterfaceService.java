package com.geeke.databus.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.geeke.common.data.Parameter;
import com.geeke.databus.aspect.Loggable;
import com.geeke.databus.entity.*;
import com.geeke.databus.enums.DataModel;
import com.geeke.databus.enums.PushStatus;
import com.geeke.micro.entity.MicroVersion;
import com.geeke.micro.service.MicroVersionService;
import com.geeke.utils.DateUtils;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.constants.ErrorEnum;
import com.newtouch.cloud.common.dependency.remote.rest.DynamicRestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service("dataBusBaseInterfaceService")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DataBusBaseInterfaceService {

    public static final String MASTER_DATA = "data";
    private static final String UNIVERSAL_ADDRESS = "/synchronizeData";
    public static final String DATA_CREATOR = "system";

    private final ProductionRelationService productionRelationService;
    private final MasterDataBatchRecordsService masterDataBatchRecordsService;
    private final MasterDataJsonService masterDataJsonService;
    private final UniversalInitializationService universalInitializationService;
    private final MasterDataTypeService masterDataTypeService;

    private final MasterDataPushRecordsService masterDataPushRecordsService;
    private final MasterDataPushInterfaceRecordsService masterDataPushInterfaceRecordsService;
    private final MicroVersionService microVersionService;
    private final DynamicRestTemplate restTemplate;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public JSONObject receive(String tenantId, String sourceAppId, String dataTypeCode, JSONObject request) {
        //变量准备
        JSONArray masterDataArray = request.getJSONArray(MASTER_DATA);
        String nowDateTime = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        MasterDataType masterDataType;
        List<MasterDataType> masterDataTypes;

        //查询主数据
        masterDataTypes = masterDataTypeService.listAll(ListUtil.of(
            new Parameter("code", "=", dataTypeCode)
        ), "");
        if (CollUtil.isEmpty(masterDataTypes) || masterDataTypes.size() != 1) {
            return ResultUtil.errorJson(ErrorEnum.E_90003, "主数据未查询到");
        }
        masterDataType = masterDataTypes.get(0);

        //查询是否有生产权限  已保证能继续后续流程
        List<ProductionRelation> produceRelations = productionRelationService.listAll(ListUtil.of(
            new Parameter("tenant_id", "=", tenantId),
            new Parameter("master_data_type_id", "=", masterDataType.getId()),
            new Parameter("data_model", "=", DataModel.produce.getCode()),
            new Parameter("app_id", "=", sourceAppId)
        ), "");
        if (CollUtil.isEmpty(produceRelations)) {
            return ResultUtil.errorJson(ErrorEnum.E_90003, "生产消费关系未查询到");
        }

        //字段校验，校验是否必填，数据类型，数据长度
        if (CollUtil.isEmpty(masterDataTypes) || masterDataTypes.size() < 1) {
            return ResultUtil.errorJson(ErrorEnum.E_90003, "主数据信息未查询到，请检查参数");
        }
        List<StandardField> standardFieldList = masterDataType.getStandardFieldList();
        for (StandardField standardField : standardFieldList) {
            boolean flg = true;
            //必填字段校验,size校验
            if (Integer.valueOf(1).equals(standardField.getDataKey())) {
                flg = masterDataArray.size() == masterDataArray
                    .stream()
                    .map(x -> (JSONObject) JSON.toJSON(x))
                    .filter(x -> ObjUtil.isNotNull(x.get(standardField.getFiledCode())))
                    .filter(x -> standardField.checkBasicData(x.get(standardField.getFiledCode())))
                    .count();
            }
            if (!flg) {
                return ResultUtil.errorJson(ErrorEnum.E_90003, "当前批数据中，某单个数据体缺少" + standardField.getFiledCode() + "必填字段");
            }
        }


        //获取消费者app_id
        List<ProductionRelation> productionRelations = productionRelationService.listAll(ListUtil.of(
            new Parameter("tenant_id", "=", tenantId),
            new Parameter("master_data_type_id", "=", masterDataType.getId()),
            new Parameter("data_model", "=", DataModel.consume.getCode())
        ), "");
        String targetAppIds = productionRelations.stream().map(ProductionRelation::getAppId).collect(Collectors.joining(","));


        //组装主表
        MasterDataBatchRecords masterDataBatchRecords = new MasterDataBatchRecords();
        masterDataBatchRecords.setDataBatch(UUID.randomUUID().toString());
        masterDataBatchRecords.setTenantId(tenantId);
        masterDataBatchRecords.setMasterDataTypeId(masterDataType.getId());
        masterDataBatchRecords.setSourceAppId(sourceAppId);
        masterDataBatchRecords.setReceiveDate(nowDateTime);
        masterDataBatchRecords.setTargetAppId(targetAppIds);
        masterDataBatchRecords.setPushStatus(PushStatus.NOT_PUSH.getCode());
        masterDataBatchRecords.setLastSendTime(nowDateTime);
        masterDataBatchRecords.setCreateBy(DATA_CREATOR);
        masterDataBatchRecords.setUpdateBy(DATA_CREATOR);
        masterDataBatchRecords.preInsert();
        masterDataBatchRecordsService.bulkInsert(ListUtil.of(masterDataBatchRecords));


        //组装从表masterDataJson
        MasterDataJson masterDataJson = new MasterDataJson();
        masterDataJson.setMasterDataBatchRecords(new MasterDataBatchRecords(masterDataBatchRecords.getId()));
        masterDataJson.setDataJson(JSONUtil.toJsonStr(request));
        masterDataJson.setAnalysis(MasterDataJson.Analysis.NOT_ANALYSIS.getCode());
        masterDataJson.setCreateBy(DATA_CREATOR);
        masterDataJson.setUpdateBy(DATA_CREATOR);
        masterDataJson.preInsert();
        masterDataBatchRecords.getMasterDataJsonList().add(masterDataJson);
        masterDataJsonService.bulkInsert(ListUtil.of(masterDataJson));

        //数据发送现已增加 异步调用发送与解析  定时轮询发送与解析已完成
        ThreadUtil.execute(() -> this.distribute(masterDataBatchRecords));
        ThreadUtil.execute(universalInitializationService::parsingJsonData);
        return ResultUtil.successJson("接收成功");
    }

    @Loggable(value = "数据总线分发service")
    public MasterDataPushRecords distribute(MasterDataBatchRecords masterDataBatchRecords) {
        //获取相关参数 开始处理业务逻辑 准备参数
        //准备好原始json数据
        log.info("收到接收数据,分发数据开始,执行时间:{}", DateUtil.now());
        log.info("分发数据的参数为:{}", JSONUtil.toJsonStr(masterDataBatchRecords));
        List<MasterDataJson> masterDataJsonList = masterDataBatchRecords.getMasterDataJsonList();
        List<String> jsonList = masterDataJsonList.stream().map(MasterDataJson::getDataJson).collect(Collectors.toList());
        //准备获取app的url
        String targetAppIds = masterDataBatchRecords.getTargetAppId();
        log.info("目标App的ID:{}", targetAppIds);
        for (String targetAppId : targetAppIds.split(StrUtil.COMMA)) {
            ThreadUtil.execute(() -> this.sendHandle(masterDataBatchRecords, jsonList, targetAppId));
        }
        return null;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void sendHandle(MasterDataBatchRecords masterDataBatchRecords, List<String> jsonList, String targetAppId) {
        MicroVersion microVersion = microVersionService.get(targetAppId);
        log.info("目标APP为：{}", JSONUtil.toJsonStr(microVersion));
        if (Objects.isNull(microVersion)) {
            throw new RuntimeException("没有获取到相关应用");
        }
        //通过微服务名称 + url 调用 微服务名称规则是 appKey + 版本号
        String appKey = microVersion.getApp().getAppKey();
        String versionNumber = microVersion.getName();
        String serviceId = appKey + "-" +  versionNumber.replace(".","-");
        log.info("目标APP的ServiceId为：{}", JSONUtil.toJsonStr(serviceId));
        //设置请求头租户id,然后自动切换相关租户的数据库入库
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("tenant_id", masterDataBatchRecords.getTenantId());
        log.info("设置请求头,放入租户信息,租户ID为:{}", masterDataBatchRecords.getTenantId());
        log.info("开始调用目标APP的接收数据接口:{}", UNIVERSAL_ADDRESS);
        JSONObject result = restTemplate.post(serviceId, UNIVERSAL_ADDRESS, JSONObject.class, jsonList, headers);
        //准备好保存的数据
        log.info("调用目标APP发送数据接口的返回结果为:{}", JSONUtil.toJsonStr(result));
        MasterDataPushRecords masterDataPushRecords = new MasterDataPushRecords();
        MasterDataType masterDataType = new MasterDataType();
        masterDataType.setId(masterDataBatchRecords.getMasterDataTypeId());
        //判断请求结果
        if (result.getInteger("code") == 100) {
            //请求成功
            //保存推送批次
            masterDataPushRecords.setDataKey(masterDataBatchRecords.getMasterDataTypeId());
            masterDataPushRecords.setMasterDataType(masterDataType);
            //推送成功 code 为2
            masterDataPushRecords.setPushStatus("2");
            masterDataPushRecords.setLastSendTime(masterDataBatchRecords.getLastSendTime());
            masterDataPushRecords.setTargetAppId(masterDataBatchRecords.getTargetAppId());
            masterDataPushRecords.setTenantId(masterDataBatchRecords.getTenantId());
            masterDataPushRecords.setMasterDataBatchRecords(masterDataBatchRecords);
            masterDataPushRecordsService.save(masterDataPushRecords);
            log.info("保存本次成功的推送批次数据:{}", JSONUtil.toJsonStr(masterDataPushRecords));
            //保存推送记录
            MasterDataPushInterfaceRecords record = new MasterDataPushInterfaceRecords();
            record.setPushStatus("2");
            record.setPushData(JSONUtil.toJsonStr(jsonList));
            record.setPushDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            record.setMasterDataPushRecords(masterDataPushRecords);
            record.setReturnData(result.getString("data"));
            masterDataPushInterfaceRecordsService.save(record);
            log.info("保存本次成功的推送记录:{}", JSONUtil.toJsonStr(record));
        } else {
            //请求失败 //更新数据库表
            //保存推送批次
            masterDataPushRecords.setDataKey(masterDataBatchRecords.getMasterDataTypeId());
            masterDataPushRecords.setMasterDataType(masterDataType);
            //推送失败 code 为3
            masterDataPushRecords.setPushStatus("3");
            masterDataPushRecords.setLastSendTime(masterDataBatchRecords.getLastSendTime());
            masterDataPushRecords.setTargetAppId(masterDataBatchRecords.getTargetAppId());
            masterDataPushRecords.setTenantId(masterDataBatchRecords.getTenantId());
            masterDataPushRecords.setMasterDataBatchRecords(masterDataBatchRecords);
            //失败原因
            masterDataPushRecords.setPushFailInfo(result.getString("data"));
            //todo 失败次数
            masterDataPushRecords.setPushFailSize(1);
            masterDataPushRecordsService.save(masterDataPushRecords);
            log.info("保存本次失败的推送批次数据:{}", JSONUtil.toJsonStr(masterDataPushRecords));
            //保存推送记录
            MasterDataPushInterfaceRecords record = new MasterDataPushInterfaceRecords();
            record.setPushStatus("3");
            record.setPushData(JSONUtil.toJsonStr(jsonList));
            record.setPushDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            record.setMasterDataPushRecords(masterDataPushRecords);
            record.setReturnData(result.getString("data"));
            record.setPushFailInfo(result.getString("data"));
            masterDataPushInterfaceRecordsService.save(record);
            log.info("保存本次失败的推送记录:{}", JSONUtil.toJsonStr(record));
        }
    }

}
