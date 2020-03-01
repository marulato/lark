package org.avalon.lark.common.database.sql;

import org.avalon.lark.common.base.AppContext;
import org.avalon.lark.common.base.BasePo;
import org.avalon.lark.common.base.BaseBean;
import org.avalon.lark.common.utility.AppUtils;

import java.util.Date;
import java.util.List;

public abstract class AbstractSQLGenerator {

    private PropertyConverter converter = PropertyConverter.getInstance();

    protected String placeholder(String property) {
        StringBuilder holder = new StringBuilder();
        holder.append("#{").append(property).append("}");
        return holder.toString();
    }

    protected String insertColumns(BasePo po) {
        StringBuilder column = new StringBuilder();
        if (po != null) {
            column.append("(");
            String[] propNames = BeanUtils.getPropertyNames(po);
            for (String propName : propNames) {
                column.append(converter.getColumn(propName));
                column.append(", ");
            }
            if (po.needAuditColumns()) {
                column.append("CREATED_AT, ");
                column.append("CREATED_BY, ");
                column.append("UPDATED_AT, ");
                column.append("UPDATED_BY, ");
            }
            column.delete(column.length() - 2, column.length());
            column.append(")");
        }
        return column.toString();
    }

    protected String insertValues(BasePo po) {
        StringBuilder values = new StringBuilder();
        if (po != null) {
            values.append("(");
            String[] propNames = BeanUtils.getPropertyNames(po);
            for (String propName : propNames) {
                values.append(placeholder(propName));
                values.append(", ");
            }
            if (po.needAuditColumns()) {
                handleInsertAuditColumns(po);
                values.append("#{createdAt}, ");
                values.append("#{createdBy}, ");
                values.append("#{updatedAt}, ");
                values.append("#{updatedBy}, ");
            }
            values.delete(values.length() - 2, values.length());
            values.append(")");
        }
        return values.toString();
    }

    protected String insertPlaceholderValues(BasePo po) {
        StringBuilder values = new StringBuilder();
        if (po != null) {
            values.append("(");
            String[] propNames = BeanUtils.getPropertyNames(po);
            for (int i = 0; i < propNames.length; i++) {
                values.append("?, ");
            }
            if (po.needAuditColumns()) {
                values.append("#{createdAt}, ");
                values.append("#{createdBy}, ");
                values.append("#{updatedAt}, ");
                values.append("#{updatedBy}, ");
            }

            values.delete(values.length() - 2, values.length());
            values.append(")");
        }
        return values.toString();
    }


    protected String batchPlaceholderValues(List<? extends BasePo> rowMappers) {
        StringBuilder values = new StringBuilder();
        for (BasePo rowMapper : rowMappers) {
            values.append(insertPlaceholderValues(rowMapper));
            values.append(", ");
        }
        values.delete(values.length() - 2, values.length());
        return values.toString();
    }

    protected String setValues(BasePo rowMapper) {
        StringBuilder set = new StringBuilder();
        String[] propNames = BeanUtils.getPropertyNames(rowMapper);
        List<String> ids = BeanUtils.getIdPropNamesList(rowMapper);
        set.append("SET ");
        for (String propName : propNames) {
            if (ids.contains(propName)) {
                continue;
            }
            set.append(converter.getColumn(propName));
            set.append(" = ");
            set.append(placeholder(propName));
            set.append(", ");
        }
        if (rowMapper.needAuditColumns()) {
            handleUpdateAuditColumns(rowMapper);
            set.append("CREATED_AT = #{createdAt}, ");
            set.append("CREATED_BY = #{createdBy}, ");
            set.append("UPDATED_AT = #{updatedAt}, ");
            set.append("UPDATED_BY = #{updatedBy}, ");
        }

        set.delete(set.length() - 2, set.length());
        return set.toString();
    }

    protected String primaryKeyStatement(BasePo rowMapper) {
        StringBuilder where = new StringBuilder();
        String[] idProp = BeanUtils.getIdPropNames(rowMapper);
        for (String id : idProp) {
            where.append(converter.getColumn(id));
            where.append(" = ");
            where.append(placeholder(id));
            where.append(" AND ");
        }
        where.delete(where.lastIndexOf(" AND "), where.length());
        return where.toString();
    }

    protected String primaryKeyPlaceholderStatement(BasePo rowMapper) {
        StringBuilder where = new StringBuilder();
        String[] idProp = BeanUtils.getIdPropNames(rowMapper);
        for (String id : idProp) {
            where.append(converter.getColumn(id));
            where.append(" = ");
            where.append("?");
            where.append(" AND ");
        }
        where.delete(where.lastIndexOf(" AND "), where.length());
        return where.toString();
    }

    private void handleInsertAuditColumns(BasePo po) {
        po.setAudit(AppUtils.getAppContext().getLoginId(), new Date());
    }

    private void handleUpdateAuditColumns(BasePo po) {
        po.setUpdateAudit(AppUtils.getAppContext().getLoginId(), new Date());
    }

    public abstract String insert(BasePo po);

    public abstract String update(BasePo po);

    public abstract String delete(BasePo po);
}
