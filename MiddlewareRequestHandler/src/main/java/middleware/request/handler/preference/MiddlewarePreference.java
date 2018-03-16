package middleware.request.handler.preference;

public class MiddlewarePreference
{
    //role
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_USER = "user";

    //table
    public static final String TABLE_REQUEST = "MIDDLEWARE_REQUEST_HANDLER.REQUEST";
    public static final String TABLE_ROLE = "MIDDLEWARE_REQUEST_HANDLER.ROLE";

    //column from table REQUEST
    public static final String COLUMN_REQUEST_ID = "REQUEST_ID";
    public static final String COLUMN_REQUEST_CONTENT = "REQUEST_CONTENT";
    public static final String COLUMN_REQUEST_UUID = "REQUEST_UUID";
    public static final String COLUMN_REQUEST_STATUS = "REQUEST_STATUS";
    public static final String COLUMN_REQUEST_ROLE = "REQUEST_ROLE";
    public static final String COLUMN_REQUEST_MESSAGE = "REQUEST_MESSAGE";

    //column from table ROLE
    public static final String ROLE_ID = "ROLE_ID";
    public static final String ROLE_NAME = "ROLE_NAME";
    public static final String ROLE_DESC = "ROLE_DESC";
}