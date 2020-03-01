package org.avalon.lark.common.database.sql;


import org.avalon.lark.common.base.BasePo;
import java.util.List;

public class SimpleSQLGenerator extends AbstractSQLGenerator {
    @Override
    public String insert(BasePo po) {
        StringBuilder insert = new StringBuilder();
        if (po != null) {
            insert.append("INSERT INTO ").append(po.getTableName()).append(" ");
            insert.append(insertColumns(po)).append(" VALUES ");
            insert.append(insertValues(po));
        }
        return insert.toString();
    }

    @Override
    public String update(BasePo po) {
        StringBuilder update = new StringBuilder();
        if (po != null) {
            update.append("UPDATE ").append(po.getTableName()).append(" ");
            update.append(setValues(po));
            update.append(" WHERE ");
            update.append(primaryKeyStatement(po));
        }
        return update.toString();
    }

    @Override
    public String delete(BasePo po) {
        StringBuilder delete = new StringBuilder();
        if (po != null) {
            delete.append("DELETE FROM ").append(po.getTableName());
            delete.append(" WHERE ").append(primaryKeyStatement(po));
        }
        return delete.toString();
    }
}
