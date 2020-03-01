package org.avalon.lark.systemadmin.service;

import org.avalon.lark.common.consts.AppConsts;
import org.avalon.lark.common.database.crud.DMLManager;
import org.avalon.lark.common.utility.ConfigUtils;
import org.avalon.lark.common.utility.DateUtils;
import org.avalon.lark.common.utility.EncryptionUtils;
import org.avalon.lark.common.utility.StringUtils;
import org.avalon.lark.systemadmin.dao.RbacDao;
import org.avalon.lark.systemadmin.dto.UserDto;
import org.avalon.lark.systemadmin.entity.LoginHistory;
import org.avalon.lark.systemadmin.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserMaintenanceService {
    @Autowired
    private RbacDao rbacDao;

    public void updateUserAfterLogin(UserDto userDto, boolean successful) {
        if (userDto != null) {
            User user = getUserByLoginId(userDto.getLoginId());
            LoginHistory loginHistory = initLoginHistory(user);
            loginHistory.setLoginAttemptAt(DateUtils.now());
            if (successful) {
                user.setLastLoginAt(loginHistory.getLoginAttemptAt());
                user.setFailedAttempts(0);
                if (AppConsts.ACCT_STATUS_LOCKED.equals(user.getStatus())) {
                    user.setStatus(AppConsts.ACCT_STATUS_ACTIVE);
                }
                loginHistory.setIp(userDto.getIpAddress());
                loginHistory.setClient(userDto.getClient());
                loginHistory.setIsSuccessful(AppConsts.YES);
            } else {
                user.setFailedAttempts(user.getFailedAttempts() + 1);
                loginHistory.setIsSuccessful(AppConsts.NO);
                int times = Integer.parseInt(ConfigUtils.get("lark.security.login.failedtimes"));
                if (user.getFailedAttempts() >= times && times > 0) {
                    user.setStatus(AppConsts.ACCT_STATUS_LOCKED);
                }
            }
            updateUser(user);
            DMLManager.insert(loginHistory);
        }
    }

    public void recordUnknownUser(UserDto webUser) {
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setClient(webUser.getClient());
        loginHistory.setIp(webUser.getIpAddress());
        loginHistory.setIsSuccessful(AppConsts.NO);
        loginHistory.setLoginAttemptAt(new Date());
        loginHistory.setLoginId(webUser.getLoginId());
        DMLManager.insert(loginHistory);
    }

    public void changePassword(UserDto userDto) {
        User user = getUserByLoginId(userDto.getLoginId());
        if (user != null) {
            String newPwd = userDto.getNewPassword();
            user.setPassword(EncryptionUtils.encryptPassword(newPwd));
            user.setNeedChangePwd(AppConsts.NO);
            updateUser(user);
        }
    }

    public User getUserByLoginId(String loginId) {
        if (StringUtils.isNotBlank(loginId)) {
            return rbacDao.getUser(loginId);
        }
        return null;
    }

    public boolean exists(String loginId) {
        User user = rbacDao.getUser(loginId);
        return user != null;
    }

    public boolean isUserEffective(User user) {
        if (user != null) {
            return DateUtils.isBetween(DateUtils.now(), user.getActivateAt(), user.getDeactivateAt());
        }
        return false;
    }

    public LoginHistory initLoginHistory(User user) {
        LoginHistory loginHistory = new LoginHistory();
        if (user != null) {
            loginHistory.setUserId(user.getUserId());
            loginHistory.setLoginId(user.getLoginId());
        }
        return loginHistory;
    }

    public LoginHistory getLastLoginHistory(String loginId) {
        return rbacDao.getLastLoginHistory(loginId);
    }

    public void updateUser(User user) {
        if (user != null) {
            if (user.getUserId() > 0) {
                DMLManager.update(user);
            } else {
                DMLManager.insert(user);
            }
        }
    }
}
