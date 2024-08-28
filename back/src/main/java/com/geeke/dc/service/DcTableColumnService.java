package com.geeke.dc.service;

import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.dc.dao.DcTableColumnDao;
import com.geeke.dc.entity.DcTableColumn;
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
 * 业务表字段Service
 * @author
 * @version
 */

@Service("dcTableColumnService")
@Transactional(readOnly = true)
public class DcTableColumnService extends CrudService<DcTableColumnDao, DcTableColumn> {}
