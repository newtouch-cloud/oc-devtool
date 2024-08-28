package com.geeke.dc.utils;

import com.geeke.dc.entity.*;
import com.geeke.sys.entity.DictItem;
import com.geeke.sys.utils.SessionUtils;
import com.geeke.utils.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DcUtils {
    private static Logger logger = LoggerFactory.getLogger(DcUtils.class);

    public DcUtils() {
    }

    public static void initColumnField(List<DcTableColumn> columnList, List<DictItem> itemList) {
        if (columnList != null) {
            Iterator var2 = columnList.iterator();

            while(true) {
                DcTableColumnEx column;
                do {
                    if (!var2.hasNext()) {
                        return;
                    }
                    column = (DcTableColumnEx)var2.next();
                } while(StringUtils.isNotBlank(column.getId()));

                column.setIsEdit("0");
                column.setIsInsert("1");
                column.setJavaType(getDictItem(itemList, "5006", "String"));
                column.setCustomType(new DcTableEx());
                column.setJavaField(StringUtils.toCamelCase(column.getName()));
                if (StringUtils.isBlank(column.getComments())) {
                    column.setComments(column.getName());
                }

                if (!StringUtils.startsWithIgnoreCase(column.getJdbcType(), "CHAR") && !StringUtils.startsWithIgnoreCase(column.getJdbcType(), "VARCHAR") && !StringUtils.startsWithIgnoreCase(column.getJdbcType(), "NARCHAR")) {
                    if (!StringUtils.startsWithIgnoreCase(column.getJdbcType(), "DATETIME") && !StringUtils.startsWithIgnoreCase(column.getJdbcType(), "TIMESTAMP")) {
                        if (StringUtils.startsWithIgnoreCase(column.getJdbcType(), "DATE")) {
                            column.setJavaType(getDictItem(itemList, "5006", "String"));
                           // column.setJavaType(getDictItem(itemList, "5001", "java.util.Date"));
                        } else if (StringUtils.startsWithIgnoreCase(column.getJdbcType(), "TIME")) {
                            column.setJavaType(getDictItem(itemList, "5006", "String"));
                           // column.setJavaType(getDictItem(itemList, "5001", "java.util.Date"));
                        } else if (StringUtils.startsWithIgnoreCase(column.getJdbcType(), "DOUBLE")) {
                            column.setJavaType(getDictItem(itemList, "5006", "Double"));
                        } else if (StringUtils.startsWithIgnoreCase(column.getJdbcType(), "FLOAT")) {
                            column.setJavaType(getDictItem(itemList, "5006", "Float"));
                        } else if (!StringUtils.startsWithIgnoreCase(column.getJdbcType(), "BIGINT") && !StringUtils.startsWithIgnoreCase(column.getJdbcType(), "INT") && !StringUtils.startsWithIgnoreCase(column.getJdbcType(), "MEDIUMINT") && !StringUtils.startsWithIgnoreCase(column.getJdbcType(), "SMALLINT") && !StringUtils.startsWithIgnoreCase(column.getJdbcType(), "TINYINT")) {
                            if (StringUtils.startsWithIgnoreCase(column.getJdbcType(), "DECIMAL")) {
                                column.setJavaType(getDictItem(itemList, "5006", "java.math.BigDecimal"));
                            }
                        } else {
                            String[] ss = StringUtils.split(StringUtils.substringBetween(column.getJdbcType(), "(", ")"), ",");
                            if (ss != null && ss.length == 2 && Integer.parseInt(ss[1]) > 0) {
                                column.setJavaType(getDictItem(itemList, "5006", "Double"));
                            } else if (ss != null && ss.length == 1 && Integer.parseInt(ss[0]) <= 16) {
                                column.setJavaType(getDictItem(itemList, "5006", "Integer"));
                            } else {
                                column.setJavaType(getDictItem(itemList, "5006", "String"));
                            }
                        }
                    } else {
                        column.setJavaType(getDictItem(itemList, "5006", "String"));
                       // column.setJavaType(getDictItem(itemList, "5001", "java.util.Date"));
                    }
                } else {
                    column.setJavaType(getDictItem(itemList, "5006", "String"));
                }

                if (!StringUtils.equalsIgnoreCase(column.getName(), "id") && !StringUtils.equalsIgnoreCase(column.getName(), "create_by") && !StringUtils.equalsIgnoreCase(column.getName(), "create_date") && !StringUtils.equalsIgnoreCase(column.getName(), "del_flag")) {
                    column.setIsEdit("1");
                }

                if (StringUtils.equalsIgnoreCase(column.getName(), "remarks")) {
                    column.setJavaType(getDictItem(itemList, "5006", "Mutilple"));
                }

                if (StringUtils.equalsIgnoreCase(column.getName(), "create_date") || StringUtils.equalsIgnoreCase(column.getName(), "update_date")) {
                    column.setJavaType(getDictItem(itemList, "5006", "Date"));
                }

                if (!StringUtils.equalsIgnoreCase(column.getName(), "name") && !StringUtils.equalsIgnoreCase(column.getName(), "title")) {
                    if (!StringUtils.equalsIgnoreCase(column.getName(), "remarks") && !StringUtils.equalsIgnoreCase(column.getName(), "content")) {
                        if (StringUtils.equalsIgnoreCase(column.getName(), "parent_id")) {
                            column.setJavaType(getDictItem(itemList, "5006", "This"));
                            column.setJavaField("parent");
                        }
                    }
                }

                if (StringUtils.equalsIgnoreCase(column.getName(), "is_locked")) {
                    column.setIsEdit("1");
                    column.setIsInsert("1");
                }

                if (StringUtils.equalsIgnoreCase(column.getName(), "proc_inst")) {
                    column.setIsEdit("1");
                    column.setIsInsert("1");
                }

                if (StringUtils.equalsIgnoreCase(column.getName(), "proc_status")) {
                    column.setIsEdit("1");
                    column.setIsInsert("1");
                    column.setJavaField(column.getJavaField());
                    column.setJavaType(getDictItem(itemList, "5006", "Custom"));
//                    GenScheme scheme = new GenScheme();
//
//                    scheme.setId("6006");
//                    scheme.setName("字典项控件");
                    // TODO
                  //  column.setCustomType("sys_dict_type");
                }
            }
        }
    }

    private static DictItem getDictItem(List<DictItem> itemList, String typeId, String value) {
        if (!StringUtils.isBlank(typeId) && !StringUtils.isBlank(value) && itemList != null) {
            Iterator var3 = itemList.iterator();

            DictItem item;
            do {
                if (!var3.hasNext()) {
                    return null;
                }

                item = (DictItem)var3.next();
            } while(!value.equals(item.getValue()) || !typeId.equals(item.getDictType().getId()));

            return item;
        } else {
            return null;
        }
    }
}
