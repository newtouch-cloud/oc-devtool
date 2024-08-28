package com.geeke.dc.service;

import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.dc.dao.DcTemplateDao;
import com.geeke.dc.entity.DcTemplate;
import com.geeke.sys.service.SysFileService;
import com.geeke.utils.IdGen;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 模板管理Service
 * @author
 * @version
 */

@Service("dcTemplateService")
@Transactional(readOnly = true)
public class DcTemplateService extends CrudService<DcTemplateDao, DcTemplate> {

    @Autowired
    private SysFileService sysFileService;

    @Transactional(readOnly = false)
    public DcTemplate save(DcTemplate dcTemplate, MultipartFile[] fileIdUploads, String[] deleteIds)
        throws java.io.IOException {
        if (StringUtils.isEmpty(dcTemplate.getFileId())) {
            dcTemplate.setFileId(IdGen.uuid());
        }
        // 保存附件
        sysFileService.changeAndSaveSysFileList(fileIdUploads, dcTemplate.getFileId());

        // 根据附件ID删除附件信息
        if (null != deleteIds && deleteIds.length > 0) {
            sysFileService.delete(deleteIds);
        }

        DcTemplate dcTemplateTemp = super.save(dcTemplate);

        return dcTemplateTemp;
    }

    /**
     * 查看所有模板包含文件信息
     * @param params
     * @param orderby
     * @return
     */
    public List<DcTemplate> listTemplateAndFile(List<Parameter> params, String orderby) {
        PageRequest pageRequest = new PageRequest(params, orderby);
        return this.dao.listTemplateAndFile(pageRequest);
    }
}
