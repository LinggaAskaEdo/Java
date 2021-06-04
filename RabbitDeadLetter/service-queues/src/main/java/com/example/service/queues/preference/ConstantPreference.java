package com.example.service.queues.preference;

public class ConstantPreference
{
    private  ConstantPreference()
    {
        //Do Nothing
    }

    public static final int RESPONSE_CODE_BAD_REQUEST = 400;
    public static final String RESPONSE_MESSAGE_BAD_REQUEST = "Syntax error, e.g. request is missing required parameters/attributes or parameter values are of incorrect type";

    public static final int RESPONSE_CODE_INTERNAL_SERVER_ERROR = 500;
    public static final String RESPONSE_MESSAGE_INTERNAL_SERVER_ERROR = "Something went wrong on our side";
}