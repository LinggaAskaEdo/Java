package com.edelwish.preference;

public class ConstantPreference
{
    private  ConstantPreference()
    {
        //Do Nothing
    }

    public static final int RESPONSE_CODE_OK = 200;
    public static final String RESPONSE_MESSAGE_OK = "Request was successful";

    public static final int RESPONSE_CODE_NOT_FOUND = 404;
    public static final String RESPONSE_MESSAGE_NOT_FOUND = "Resource/object could not be found";

    public static final int RESPONSE_CODE_BAD_REQUEST = 400;
    public static final String RESPONSE_MESSAGE_BAD_REQUEST = "Syntax error, e.g. request is missing required parameters/attributes or parameter values are of incorrect type";

    public static final int RESPONSE_CODE_CONFLICT = 409;
    public static final String RESPONSE_MESSAGE_CONFLICT = "Data already exist";

    public static final int RESPONSE_CODE_INTERNAL_SERVER_ERROR = 500;
    public static final String RESPONSE_MESSAGE_INTERNAL_SERVER_ERROR = "Something went wrong on our side";

    public static final String MISSING_OBJECT = "Missing Required Object";
}