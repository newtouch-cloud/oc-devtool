package com.geeke.dc.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.common.data.Parameter;
import com.geeke.dc.entity.DcTable;
import com.geeke.dc.entity.DcTableColumn;
import com.geeke.dc.service.DcTableService;
import com.geeke.dc.utils.DcUtils;
import com.geeke.sys.controller.BaseController;
import com.geeke.sys.entity.DictItem;
import com.geeke.sys.service.DictItemService;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后端方案Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/dc/dcTable")
public class DcTableController extends BaseController {

    @Autowired
    private DcTableService dcTableService;

    @Autowired
    private DictItemService dictItemService;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        DcTable entity = dcTableService.get(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<DcTable> result = dcTableService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<DcTable> result = dcTableService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody DcTable entity) {
        String id = dcTableService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody DcTable entity) {
        int rows = dcTableService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<DcTable> entitys) {
        List<String> ids = dcTableService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<DcTable> entitys) {
        List<String> ids = dcTableService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<DcTable> entitys) {
        int rows = dcTableService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping({ "listTableColumn" })
    public ResponseEntity<JSONObject> listTableColumn(@RequestBody SearchParams searchParams) {
        List<DcTableColumn> result = dcTableService.listTableColumn(searchParams.getParams(), searchParams.getOrderby());
        List<DictItem> dtList = this.getItemList();
        DcUtils.initColumnField(result, dtList);
        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    private List<DictItem> getItemList() {
        List<Parameter> params = Lists.newArrayList();
        String[] strings = new String[] { "5006", "5002", "5003" };
        params.add(new Parameter("dict_type_id", "in", Arrays.asList(strings)));
        return this.dictItemService.listAll(params, (String) null);
    }
}
