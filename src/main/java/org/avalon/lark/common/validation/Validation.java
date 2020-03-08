package org.avalon.lark.common.validation;

import org.avalon.lark.common.exception.IllegalReturnTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class Validation {

    private Validation() {
    }

    private static final Logger log = LoggerFactory.getLogger(Validate.class);

    public static ValidationResult validate(Object object) throws Exception {
        if (object != null) {
            Class type = object.getClass();
            if (type.isAnnotationPresent(Validator.class)) {
                Validator validator = (Validator) type.getAnnotation(Validator.class);
                Class validatorClass = validator.validatorClass();
                Object validatorInstance = validatorClass.getConstructor().newInstance();
                if (validatorInstance instanceof BaseValidator) {
                    BaseValidator baseValidator = (BaseValidator) validatorInstance;
                    return baseValidator.doValidate(object);
                }
            } else {
                Map<String, List<String>> map = validateWithBoundedMethods(object);
            }
        }
        return null;
    }

    public static Map<String, List<String>> validateWithBoundedMethods(Object object) {
        Map<String, List<String>> errorMap = new HashMap<>();
        Map<Field, Map<Method, String>> fieldMethodsMap = Reflections.getFieldValidationMethods(object);
        Set<Field> fieldSet = fieldMethodsMap.keySet();
        for (Field field : fieldSet) {
            field.setAccessible(true);
            Map<Method, String> methodMsgMap = fieldMethodsMap.get(field);
            Set<Method> methodSet = methodMsgMap.keySet();
            for (Method method : methodSet) {
                method.setAccessible(true);
                Object result = null;
                try {
                    result = method.invoke(object, field.get(object));
                } catch (Exception e) {
                    log.error("Can not happen", e);
                }
                if (!(result instanceof Boolean)) {
                    throw new IllegalReturnTypeException(result.getClass().getTypeName() + " is not supported");
                }
                boolean validationResult = (boolean) result;
                if (!validationResult) {
                    List<String> msgList = errorMap.get(field.getName());
                    if (msgList == null) {
                        msgList = new ArrayList<>();
                        errorMap.put(field.getName(), msgList);
                    }
                    msgList.add(methodMsgMap.get(method));
                }
            }
        }
        return errorMap;
    }

    public static boolean isValidEntity(Object object) {
        return validateWithBoundedMethods(object).isEmpty();
    }


}
