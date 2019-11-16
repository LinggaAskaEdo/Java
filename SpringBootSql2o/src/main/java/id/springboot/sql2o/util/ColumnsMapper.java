package id.springboot.sql2o.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ColumnsMapper
{
    public Map<String, String> generateMapper()
    {
        Map<String, String> mappings = new HashMap<>();
        mappings.put("CREATED_DATE", "createdDate");
        mappings.put("LAST_MODIFIED_DATE", "lastModifiedDate");
        mappings.put("IS_ACCOUNT_EXPIRED", "isAccountExpired");
        mappings.put("IS_ACCOUNT_LOCKED", "isAccountLocked");
        mappings.put("CLIENT_ID", "clientId");
        mappings.put("DATE_OF_BIRTH", "dateOfBirth");
        mappings.put("IS_ENABLED", "isEnabled");
        mappings.put("IS_FIRST_LOGIN", "isFirstLogin");
        mappings.put("LOGIN_TYPE", "loginType");
        mappings.put("IS_PASSWORD_EXPIRED", "isPasswordExpired");
        mappings.put("PIN_CODE", "pinCode");
        mappings.put("USER_ID", "userId");
        mappings.put("IS_DUMMY", "isDummy");
        mappings.put("LOGIN_ATTEMPT", "loginAttempt");
        mappings.put("APPS_VERSION", "appsVersion");
        mappings.put("PHONE_REGISTERED", "phoneRegistered");
        mappings.put("DOB_REGISTERED", "dobRegistered");
        mappings.put("MOB_ID", "mobId");

        return mappings;
    }
}