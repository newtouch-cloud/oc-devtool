package com.geeke.databus.service;

import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.databus.dao.MasterDataDetailDao;
import com.geeke.databus.entity.MasterDataDetail;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 主数据解析数据Service
 * @author
 * @version
 */

@Service("masterDataDetailService")
@Transactional(readOnly = true)
public class MasterDataDetailService extends CrudService<MasterDataDetailDao, MasterDataDetail> {}
