package com.geeke.dc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.utils.StringUtils;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class DcSchemeEx extends DcScheme {

    private static final long serialVersionUID = 57593538386163538L;

    public DcSchemeEx() {}

    /**
     * 是否所有的子表都是关联表或者一对一子表
     * @return
     */
    public Boolean getMainChildTableExists() {
        Iterator var1 = this.getDcTableList().iterator();

        int count = 0;
        while (var1.hasNext()) {
            DcTable table = (DcTable) var1.next();
            if (!StringUtils.equalsIgnoreCase("1", table.getIsMainTable()) && (StringUtils.equalsIgnoreCase("1", table.getIsAssociation()) || StringUtils.equalsIgnoreCase("1", table.getIsOneToOne()))) {
                count ++;
            }
        }
        if (count == this.getDcTableList().size() - 1) return true;

        return false;
    }
}
