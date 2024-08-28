package com.geeke.micro.service;

import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.micro.dao.MicroAppDao;
import com.geeke.micro.dao.MicroVersionDao;
import com.geeke.micro.entity.MicroApp;
import com.geeke.micro.entity.MicroVersion;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 应用版本管理Service
 * @author
 * @version
 */

@Service("microVersionService")
@Transactional(readOnly = true)
public class MicroVersionService extends CrudService<MicroVersionDao, MicroVersion> {

    @Autowired
    private MicroAppDao microAppDao;

    public MicroVersion getAppByKey(String appKey) {
        MicroApp app = microAppDao.getAppByKey(appKey);
        List<Parameter> parameters = Lists.newArrayList();
        parameters.add(new Parameter("app_id","=",app.getId()));
        PageRequest request = new PageRequest(parameters);
        List<MicroVersion> microVersions = dao.listAll(request);
        return CollectionUtils.isEmpty(microVersions)?new MicroVersion():microVersions.get(0);
    }
}
