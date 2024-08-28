package com.geeke.dc.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.dc.entity.DcTable;
import com.geeke.dc.service.SchemaTableService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统表控件Controller
 * @author lys
 * @version 2019-09-13
 */
@RestController
@RequestMapping(value = "/dc/schemaTable")
public class SchemaTableController extends BaseController {

	@Autowired
	private SchemaTableService schemaTableService;
	
	@GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
		DcTable entity = schemaTableService.get(id);
        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }
	
	@PostMapping(value = {"list", ""})
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<DcTable> result = schemaTableService.listPage(searchParams.getParams(), searchParams.getOffset(), searchParams.getLimit(), searchParams.getOrderby());
        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @DS("#header.project_id")
    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<DcTable> result = schemaTableService.listAll(searchParams.getParams(), searchParams.getOrderby());
        return ResponseEntity.ok(ResultUtil.successJson(result));
    }   

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody DcTable entity) {
    	String id = schemaTableService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }
  
    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody DcTable entity) {
        int rows = schemaTableService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

	
}