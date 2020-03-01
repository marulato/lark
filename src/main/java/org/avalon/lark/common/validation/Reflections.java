package org.avalon.lark.common.validation;

import org.avalon.lark.common.exception.IllegalMethodException;
import org.avalon.lark.common.exception.MessagesNotMatchException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reflections {

    static boolean isValidateAnnoPresents(Field field) {
        return field != null && field.isAnnotationPresent(Validate.class);
    }

    static Field[] getFieldsWithValidateAnno(Object object) {
        List<Field> list = new ArrayList<>();
        if (object != null) {
            Class<?> classtype = object.getClass();
            Field[] allFields = classtype.getDeclaredFields();
            for(Field field : allFields) {
                if (isValidateAnnoPresents(field)) {
                    list.add(field);
                }
            }
        }
        return list.toArray(new Field[list.size()]);
}

    static Map<Field, Map<Method, String>> getFieldValidationMethods(Object object)  {
        Map<Field, Map<Method, String>> map = new HashMap<>();
        Field[] fields = getFieldsWithValidateAnno(object);
        if (fields != null) {
            Class<?> classtype = object.getClass();
            for (Field field : fields) {
                Validate validate = field.getAnnotation(Validate.class);
                String[] methodNames = validate.withMethods();
                String[] messages = validate.messages();
                if (methodNames.length != messages.length)
                    throw new MessagesNotMatchException("Field: " + field.getName() + " -> Methods do not match messages");
                Map<Method, String> methodMsgMap = new HashMap<>();
                try {
                    for (int i = 0; i < methodNames.length; i++) {
                        Method method = classtype.getDeclaredMethod(methodNames[i], field.getType());
                        methodMsgMap.put(method, messages[i]);
                    }
                    map.put(field, methodMsgMap);
                } catch (NoSuchMethodException e) {
                    throw new IllegalMethodException("Method Not Found or Method Args Not Supported" );
                }
            }
        }
        return map;
    }


}
