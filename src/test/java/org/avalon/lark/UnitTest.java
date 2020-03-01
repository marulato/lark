package org.avalon.lark;

import org.avalon.lark.common.consts.SystemConsts;
import org.avalon.lark.common.docgen.TemplateMessageProvider;
import org.avalon.lark.common.utility.EncryptionUtils;
import org.avalon.lark.common.validation.Validate;
import org.avalon.lark.common.validation.Validation;
import org.avalon.lark.common.validation.ValidationResult;
import org.avalon.lark.systemadmin.dto.UserDto;
import org.avalon.lark.systemadmin.entity.User;
import org.junit.Test;



public class UnitTest {

    @Test
    public void Test2() throws Exception {
        UserDto userDto = new UserDto(new User());
        userDto.setIpAddress("yyy");
        ValidationResult result =Validation.validate(userDto);
        System.out.println(result.getErrorMsgMap());
        System.out.println(Validation.validateWithBoundedMethods(userDto));
    }

    @Test
    public void test1() {
        String serverPath = TemplateMessageProvider.class.getResource("").toString().replaceAll("%20", " ").substring(5);
        System.out.println(serverPath);
        System.out.println(SystemConsts.getClassPath());
    }


}
