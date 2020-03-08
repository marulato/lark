package org.avalon.lark.systemadmin.dto;

import org.avalon.lark.common.base.BaseDto;
import org.avalon.lark.common.base.BasePo;
import org.avalon.lark.common.validation.Validator;
import org.avalon.lark.systemadmin.validator.UserValidator;

import java.util.Date;

@Validator(validatorClass = UserValidator.class)
public class UserDto extends BaseDto {

    private long userId;
    private String loginId;
    private String userName;
    private String password;
    private String email;
    private String status;
    private Date activateAt;
    private Date deactivateAt;
    private String needChangePwd;
    private Date pwdLastChangeDt;
    private Date lastLoginAt;
    private int failedAttempts;

    //additional fields
    private String ipAddress;
    private String client;
    private String newPassword;
    private String reEnterNewPassword;

    public UserDto(){
        super(null);
    }

    public UserDto(BasePo po) {
        super(po);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getActivateAt() {
        return activateAt;
    }

    public void setActivateAt(Date activateAt) {
        this.activateAt = activateAt;
    }

    public Date getDeactivateAt() {
        return deactivateAt;
    }

    public void setDeactivateAt(Date deactivateAt) {
        this.deactivateAt = deactivateAt;
    }

    public String getNeedChangePwd() {
        return needChangePwd;
    }

    public void setNeedChangePwd(String needChangePwd) {
        this.needChangePwd = needChangePwd;
    }

    public Date getPwdLastChangeDt() {
        return pwdLastChangeDt;
    }

    public void setPwdLastChangeDt(Date pwdLastChangeDt) {
        this.pwdLastChangeDt = pwdLastChangeDt;
    }

    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReEnterNewPassword() {
        return reEnterNewPassword;
    }

    public void setReEnterNewPassword(String reEnterNewPassword) {
        this.reEnterNewPassword = reEnterNewPassword;
    }
}
