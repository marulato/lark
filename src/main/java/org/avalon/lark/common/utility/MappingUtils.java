package org.avalon.lark.common.utility;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MappingUtils {

    public static <T> T getBean(Map<String, String> param, Class<T> type) throws Exception {
        if (param != null && type != null) {
            T instance = type.getConstructor().newInstance();
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                String value = param.get(name);
                if (value != null) {
                    field.setAccessible(true);
                    Class fieldType = field.getType();
                    if (fieldType == String.class) {
                        field.set(instance, value);
                    } else if (fieldType == int.class || fieldType == Integer.class) {
                        field.setInt(instance, Integer.parseInt(value));
                    } else if (fieldType == double.class || fieldType == Double.class) {
                        field.setDouble(instance, Double.parseDouble(value));
                    } else if (fieldType == long.class || fieldType == Long.class) {
                        field.setLong(instance, Long.parseLong(value));
                    } else if (fieldType == short.class || fieldType == Short.class) {
                        field.setShort(instance, Short.parseShort(value));
                    } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                        field.setBoolean(instance, StringUtils.parseBoolean(value));
                    } else if (fieldType == Date.class) {
                        field.set(instance, DateUtils.parseDate(value));
                    } else if (fieldType == byte.class || fieldType == Byte.class) {
                        field.setByte(instance, Byte.parseByte(value));
                    } else if (fieldType == char.class || fieldType == Character.class) {
                        field.setChar(instance, value.toCharArray()[0]);
                    }
                }
            }
            return instance;
        }
        return null;
    }

    public static <T> Map<String, String> getMap(T obj) throws Exception {
        Map<String, String> map = new HashMap<>();
        if (obj != null) {
            Class type = obj.getClass();
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), String.valueOf(field.get(obj)));
            }
        }
        return map;
    }
}
