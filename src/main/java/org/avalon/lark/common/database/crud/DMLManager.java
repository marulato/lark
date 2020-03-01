package org.avalon.lark.common.database.crud;

import com.google.common.collect.Lists;
import org.avalon.lark.common.base.BasePo;
import org.avalon.lark.common.utility.SpringUtils;
import java.util.ArrayList;
import java.util.List;

public final class DMLManager {

    private static MybatisDaoProxy proxy = SpringUtils.getBean(MybatisDaoProxy.class);

    public static void insert(BasePo po) {
        proxy.insert(po);
    }

    public static void update(BasePo po) {
        proxy.update(po);
    }

    public static void delete(BasePo po) {
        proxy.delete(po);
    }
}
