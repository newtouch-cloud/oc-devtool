package com.geeke.dc.dao;

import com.geeke.common.data.PageRequest;
import com.geeke.common.persistence.CrudDao;
import com.geeke.dc.entity.DcTemplate;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 模板管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface DcTemplateDao extends CrudDao<DcTemplate> {
    /**
     * 查看所有模板包含文件信息
     * @param pageRequest
     * @return
     */
    List<DcTemplate> listTemplateAndFile(PageRequest pageRequest);
}
