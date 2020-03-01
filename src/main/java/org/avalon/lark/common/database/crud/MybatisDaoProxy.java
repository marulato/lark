package org.avalon.lark.common.database.crud;

import org.apache.ibatis.annotations.*;
import org.avalon.lark.common.base.BaseBean;
import org.avalon.lark.common.base.BasePo;
import org.avalon.lark.common.database.sql.SimpleSQLGenerator;
import java.util.List;

@Mapper
public interface MybatisDaoProxy {

    @InsertProvider(type = SimpleSQLGenerator.class, method = "insert")
    void insert(BasePo po);

    @UpdateProvider(type = SimpleSQLGenerator.class, method = "update")
    void update(BasePo po);

    @DeleteProvider(type = SimpleSQLGenerator.class, method = "delete")
    void delete(BasePo po);
}
