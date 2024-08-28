package com.geeke.dbtype.service;

import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.dbtype.dao.SysDbTypeDao;
import com.geeke.dbtype.entity.SysDbType;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Maps;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据库类型管理Service
 * @author
 * @version
 */

@Service("sysDbTypeService")
@Transactional(readOnly = true)
public class SysDbTypeService extends CrudService<SysDbTypeDao, SysDbType> {}
