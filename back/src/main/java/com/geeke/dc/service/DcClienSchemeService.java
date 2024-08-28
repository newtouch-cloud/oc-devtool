package com.geeke.dc.service;

import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.dc.dao.DcClienSchemeDao;
import com.geeke.dc.entity.DcClienScheme;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Maps;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前端方案Service
 * @author
 * @version
 */

@Service("dcClienSchemeService")
@Transactional(readOnly = true)
public class DcClienSchemeService extends CrudService<DcClienSchemeDao, DcClienScheme> {}
