package org.avalon.lark.systemadmin.service;

import org.avalon.lark.common.base.AppContext;
import org.avalon.lark.common.consts.AppConsts;
import org.avalon.lark.common.utility.AppUtils;
import org.avalon.lark.common.utility.DateUtils;
import org.avalon.lark.common.utility.EncryptionUtils;
import org.avalon.lark.systemadmin.dao.RbacDao;
import org.avalon.lark.systemadmin.dto.UserDto;
import org.avalon.lark.systemadmin.entity.LoginHistory;
import org.avalon.lark.systemadmin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class AuthorityService {
    @Autowired
    private RbacDao rbacDao;
    @Autowired
    private UserMaintenanceService userMaintenanceSvc;

    public int login(UserDto webUser) {
        int loginStatus = Integer.MIN_VALUE;
        if (webUser != null) {
            if (!userMaintenanceSvc.exists(webUser.getLoginId())) {
                loginStatus = AppConsts.LOGIN_USER_NOT_EXIST;
                userMaintenanceSvc.recordUnknownUser(webUser);
            } else {
                User user = userMaintenanceSvc.getUserByLoginId(webUser.getLoginId());
                if ((AppConsts.ACCT_STATUS_ACTIVE.equals(user.getStatus()) && userMaintenanceSvc.isUserEffective(user))) {
                    loginStatus = checkLogin(webUser, user);
                } else if (AppConsts.ACCT_STATUS_LOCKED.equals(user.getStatus()) && userMaintenanceSvc.isUserEffective(user)) {
                    LoginHistory lastLogin = userMaintenanceSvc.getLastLoginHistory(user.getLoginId());
                    if (lastLogin != null && DateUtils.getHoursBetween(new Date(), lastLogin.getLoginAttemptAt()) >= 24) {
                        loginStatus = checkLogin(webUser, user);
                    }
                } else {
                    loginStatus = AppConsts.LOGIN_ACCESS_DENIED;
                    userMaintenanceSvc.updateUserAfterLogin(webUser, false);
                }
            }
        }
        return loginStatus;
    }

    private int checkLogin(UserDto webUser, User user) {
        int loginStatus = Integer.MIN_VALUE;
        boolean pwdMatches = EncryptionUtils.matchPassword(webUser.getPassword(), user.getPassword());
        if (pwdMatches) {
            loginStatus = AppConsts.LOGIN_SUCCESSFULLY;
            userMaintenanceSvc.updateUserAfterLogin(webUser, true);
        } else {
            loginStatus = AppConsts.LOGIN_PWD_MISMATCH;
            userMaintenanceSvc.updateUserAfterLogin(webUser, false);
        }
        return loginStatus;
    }

    public void updateUser() {

    }
}
