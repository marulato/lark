package org.avalon.lark.common.database.sql;

import org.avalon.lark.common.database.annotation.Id;
import org.avalon.lark.common.database.annotation.NotColumn;
import org.avalon.lark.common.utility.ArrayUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeanUtils {

    private static PropertyConverter converter = PropertyConverter.getInstance();

    public static Field[] getFields(Object bean) {
        if (bean != null) {
            Class beanType = bean.getClass();
            Field[] fields = beanType.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
            }
            return fields;
        }
        return new Field[0];
    }

    public static String[] getPropertyNames(Object bean) {
        int notColumn = 0;
        Field[] fields = getFields(bean);
        String[] names = new String[fields.length];
        if (fields.length > 0) {
            for (int i = 0; i < fields.length; i++) {
                if(fields[i].isAnnotationPresent(NotColumn.class) || "serialVersionUID".equals(fields[i].getName())
                        || "TABLE_NAME".equals(fields[i].getName()) || "AUDIT_TABLE_NAME".equals(fields[i].getName())) {
                    notColumn ++;
                    continue;
                }
                names[i] = fields[i].getName();
            }
        }
        return ArrayUtils.removeEmpty(names);
    }

    public static String[] getIdPropNames(Object bean) {
        Field[] fields = getFields(bean);
        StringBuilder props = new StringBuilder();
        for(Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                props.append(field.getName());
                props.append(" ");
            }
        }
        props.deleteCharAt(props.lastIndexOf(" "));
        return props.toString().split(" ");
    }

    public static List<String> getIdPropNamesList(Object bean) {
        return new ArrayList<>(Arrays.asList(getIdPropNames(bean)));
    }
}
