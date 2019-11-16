package id.springboot.sql2o.model;

import java.util.Date;

public class User
{
    private String id;
    private int version;
    private Date createdDate;
    private Date lastModifiedDate;
    private boolean isAccountExpired;
    private boolean isAccountLocked;
    private String clientId;
    private String cuid;
    private Date dateOfBirth;
    private String email; //null
    private boolean isEnabled;
    private boolean isFirstLogin;
    private String fullname; //null
    private String loginType;
    private boolean isPasswordExpired;
    private String phone;
    private String pinCode;
    private String userId;
    private boolean isDummy;
    private int loginAttempt;
    private int appsVersion;
    private String phoneRegistered; //null
    private Date dobRegistered; //null
    private String mobId; //null

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean isAccountExpired() {
        return isAccountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        isAccountExpired = accountExpired;
    }

    public boolean isAccountLocked() {
        return isAccountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        isAccountLocked = accountLocked;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        isFirstLogin = firstLogin;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public boolean isPasswordExpired() {
        return isPasswordExpired;
    }

    public void setPasswordExpired(boolean passwordExpired) {
        isPasswordExpired = passwordExpired;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isDummy() {
        return isDummy;
    }

    public void setDummy(boolean dummy) {
        isDummy = dummy;
    }

    public int getLoginAttempt() {
        return loginAttempt;
    }

    public void setLoginAttempt(int loginAttempt) {
        this.loginAttempt = loginAttempt;
    }

    public int getAppsVersion() {
        return appsVersion;
    }

    public void setAppsVersion(int appsVersion) {
        this.appsVersion = appsVersion;
    }

    public String getPhoneRegistered() {
        return phoneRegistered;
    }

    public void setPhoneRegistered(String phoneRegistered) {
        this.phoneRegistered = phoneRegistered;
    }

    public Date getDobRegistered() {
        return dobRegistered;
    }

    public void setDobRegistered(Date dobRegistered) {
        this.dobRegistered = dobRegistered;
    }

    public String getMobId() {
        return mobId;
    }

    public void setMobId(String mobId) {
        this.mobId = mobId;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", isAccountExpired=" + isAccountExpired +
                ", isAccountLocked=" + isAccountLocked +
                ", clientId='" + clientId + '\'' +
                ", cuid='" + cuid + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", isEnabled=" + isEnabled +
                ", isFirstLogin=" + isFirstLogin +
                ", fullname='" + fullname + '\'' +
                ", loginType='" + loginType + '\'' +
                ", isPasswordExpired=" + isPasswordExpired +
                ", phone='" + phone + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", userId='" + userId + '\'' +
                ", isDummy=" + isDummy +
                ", loginAttempt=" + loginAttempt +
                ", appsVersion=" + appsVersion +
                ", phoneRegistered='" + phoneRegistered + '\'' +
                ", dobRegistered=" + dobRegistered +
                ", mobId='" + mobId + '\'' +
                '}';
    }
}