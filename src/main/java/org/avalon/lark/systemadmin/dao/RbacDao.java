package org.avalon.lark.systemadmin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.avalon.lark.systemadmin.entity.LoginHistory;
import org.avalon.lark.systemadmin.entity.User;

@Mapper
public interface RbacDao {

    public User getUser(String loginId);

    public LoginHistory getLastLoginHistory(String loginId);
}
