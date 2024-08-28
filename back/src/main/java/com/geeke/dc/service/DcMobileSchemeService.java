package com.geeke.dc.service;

import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.dc.dao.DcMobileSchemeDao;
import com.geeke.dc.entity.DcMobileScheme;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 移动端方案Service
 * @author
 * @version
 */

@Service("dcMobileSchemeService")
@Transactional(readOnly = true)
public class DcMobileSchemeService extends CrudService<DcMobileSchemeDao, DcMobileScheme> {}
