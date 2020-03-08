package org.avalon.lark.systemadmin.validator;

import org.avalon.lark.common.validation.BaseValidator;
import org.avalon.lark.common.validation.ValidationResult;
import org.avalon.lark.systemadmin.dto.UserDto;

public class UserValidator extends BaseValidator {
    @Override
    public ValidationResult doValidate(Object object) {
        ValidationResult result = new ValidationResult();
        if (object instanceof UserDto) {
            UserDto userDto = (UserDto) object;
            if (!"123".equalsIgnoreCase(userDto.getUserName())) {
                result.addErrorMsg("userName", "鸡巴");
                result.addErrorMsg("password", "diao");
            }
        }
        return result;
    }
}
