package org.avalon.lark;

import org.avalon.lark.common.cache.ConfigCache;
import org.avalon.lark.common.database.crud.DMLManager;
import org.avalon.lark.common.utility.ConfigUtils;
import org.avalon.lark.common.utility.SpringUtils;
import org.avalon.lark.systemadmin.controller.AuthorityController;
import org.avalon.lark.systemadmin.dto.UserDto;
import org.avalon.lark.systemadmin.entity.User;
import org.avalon.lark.test.TestPo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class LarkApplicationTests {

    @Test
    void contextLoads() {
        AuthorityController authorityController = SpringUtils.getBean(AuthorityController.class);
        User user = new User();
        user.setLoginId("test2");
        user.setPassword("123456");
        UserDto userDto = new UserDto(user);
        authorityController.doLogin(userDto);


    }

}
