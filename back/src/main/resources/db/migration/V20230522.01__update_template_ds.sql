
/* 模板去掉@ds引用 */
UPDATE dc_template  
SET json = replace(json, '{{if project && project.multilTen == ''1''}}\\nimport com.baomidou.dynamic.datasource.annotation.DS;\\n{{/if}}', '') ;
UPDATE dc_template  
SET json = replace(json, '{{if project && project.multilTen == ''1''}}\\n@DS(\\"#header.tenant_id\\")\\n{{/if}}', '') ;

UPDATE dc_scheme  
SET template_json = replace(template_json, '{{if project && project.multilTen == ''1''}}\\nimport com.baomidou.dynamic.datasource.annotation.DS;\\n{{/if}}', '') ;
UPDATE dc_scheme  
SET template_json = replace(template_json, '{{if project && project.multilTen == ''1''}}\\n@DS(\\"#header.tenant_id\\")\\n{{/if}}', '') ;
