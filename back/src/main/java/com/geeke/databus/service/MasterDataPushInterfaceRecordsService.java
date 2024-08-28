package com.geeke.databus.service;

import com.geeke.common.constants.ActionConstants;
import com.geeke.common.data.Page;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.databus.dao.MasterDataPushInterfaceRecordsDao;
import com.geeke.databus.entity.MasterDataPushInterfaceRecords;
import com.geeke.utils.Reflections;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 主数据批次分发记录Service
 * @author
 * @version
 */

@Service("masterDataPushInterfaceRecordsService")
@Transactional(readOnly = true)
public class MasterDataPushInterfaceRecordsService
    extends CrudService<MasterDataPushInterfaceRecordsDao, MasterDataPushInterfaceRecords> {}
