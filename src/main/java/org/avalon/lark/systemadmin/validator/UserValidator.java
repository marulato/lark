package org.avalon.lark.systemadmin.validator;

import org.avalon.lark.common.validation.BaseValidator;
import org.avalon.lark.common.validation.ValidationResult;
import org.avalon.lark.systemadmin.dto.UserDto;
import org.avalon.lark.systemadmin.entity.User;

public class UserValidator extends BaseValidator {
    @Override
    public ValidationResult doValidate(Object object) {
        ValidationResult result = new ValidationResult();
        if (object instanceof UserDto) {
            UserDto userDto = (UserDto) object;
            result.addErrorMsg("dick", "鸡巴");
        }
        return result;
    }
}
