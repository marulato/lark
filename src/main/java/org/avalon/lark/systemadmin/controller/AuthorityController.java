package org.avalon.lark.systemadmin.controller;

import org.avalon.lark.systemadmin.dto.UserDto;
import org.avalon.lark.systemadmin.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorityController {

    private AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @Transactional
    @RequestMapping("/web/login")
    public void doLogin(UserDto webUser) {
        authorityService.login(webUser);
    }
}
