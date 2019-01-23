package id.co.homecredit.cc.pin.preference;

public class ConstantPreference
{
    public static final String RESPONSE_CODE_OK = "200";
    public static final String RESPONSE_MESSAGE_OK = "Request was successful";

    public static final String RESPONSE_CODE_NO_CONTENT = "204";
    public static final String RESPONSE_MESSAGE_NO_CONTENT = "We accepted your request but there is nothing to return (e.g. response is empty)";

    public static final String RESPONSE_CODE_BAD_REQUEST = "400";
    public static final String RESPONSE_MESSAGE_BAD_REQUEST = "Syntax error, e.g. request is missing required parameters/attributes or parameter values are of incorrect type";

    public static final String RESPONSE_CODE_SERVICE_UNAVAILABLE = "503";
    public static final String RESPONSE_MESSAGE_SERVICE_UNAVAILABLE = "There is planned service outage. We should specify response headers or error object with\n" +
            "more details on service outage";

    public static final String RESPONSE_CODE_ERROR = "404";
    public static final String RESPONSE_MESSAGE_CUID_NOT_EXIST = "CUID tidak dapat ditemukan";

    public static final String RESPONSE_SUCCESS = "SUCCESS";
    public static final String CARD_STATUS_ACTIVE = "ACTIVE";

    //error code when generate session
    public static final String SESSION_RESPONSE_CONNECTION_ID_ALREADY_USED = "CONNECTION_ID_ALREADY_USED";
    public static final String SESSION_RESPONSE_HSM_COMMUNICATION_FAILED = "HSM_COMMUNICATION_FAILED";
    public static final String SESSION_RESPONSE_INVALID_APPLICATION_ID = "INVALID_APPLICATION_ID";
    public static final String SESSION_RESPONSE_OTHER_ERROR = "OTHER_ERROR";

    //error message when generate session
    public static final String RESPONSE_MESSAGE_SESSION_CONNECTION_ID_ALREADY_USED = "Connection ID sudah digunakan";
    public static final String RESPONSE_MESSAGE_SESSION_HSM_COMMUNICATION_FAILED = "Terjadi masalah dengan modul HSM, RSA key tidak berhasil dihasilkan";
    public static final String RESPONSE_MESSAGE_SESSION_INVALID_APPLICATION_ID = "Application ID tidak dikenal oleh CMS system";
    public static final String RESPONSE_MESSAGE_SESSION_OTHER_ERROR = "Terjadi kesalahan pada sistem";

    //error code when set pin
    public static final String SET_PIN_CARD_NOT_FOUND = "CARD_NOT_FOUND";
    public static final String SET_PIN_CONNECTION_ID_NOT_FOUND = "CONNECTION_ID_NOT_FOUND";
    public static final String SET_PIN_INVALID_APPLICATION_ID = "INVALID_APPLICATION_ID";
    public static final String SET_PIN_NO_FREE_SLOT_IN_HSM_FOR_APPLICATION_ID = "NO_FREE_SLOT_IN_HSM_FOR_APPLICATION_ID";
    public static final String SET_PIN_KEY_LOADING_FAILED = "KEY_LOADING_FAILED";
    public static final String SET_PIN_KEY_IMPORT_FAILED = "KEY_IMPORT_FAILED";
    public static final String SET_PIN_HSM_TRANSLATION_FAILED = "HSM_TRANSLATION_FAILED";
    public static final String SET_PIN_PAN_DECIPHERING_FAILED = "PAN_DECIPHERING_FAILED";
    public static final String SET_PIN_PROCESSOR_COMMUNICATION_FAILED = "PROCESSOR_COMMUNICATION_FAILED";
    public static final String SET_PIN_WRONG_PIN_LENGTH = "WRONG_PIN_LENGTH";
    public static final String SET_PIN_OTHER_ERROR = "OTHER_ERROR";

    //error message when set pin
    public static final String RESPONSE_MESSAGE_SET_PIN_CARD_NOT_FOUND = "Card with PAN last 4 digits was not found in the system. If it was received";
    public static final String RESPONSE_MESSAGE_SET_PIN_CONNECTION_ID_NOT_FOUND = "Connection ID was not found in system. That means that there was no session " +
            "key generated for connection ID";
    public static final String RESPONSE_MESSAGE_SET_PIN_INVALID_APPLICATION_ID = "Application ID in request is not known by CMS";
    public static final String RESPONSE_MESSAGE_SET_PIN_NO_FREE_SLOT_IN_HSM_FOR_APPLICATION_ID = "All slots in HSM that are reserved for specific application ID are locked";
    public static final String RESPONSE_MESSAGE_SET_PIN_KEY_LOADING_FAILED = "Private RSA key was not properly loaded to HSM";
    public static final String RESPONSE_MESSAGE_SET_PIN_KEY_IMPORT_FAILED = "Session ZPK encrypted by RSA public key was not properly imported to HSM";
    public static final String RESPONSE_MESSAGE_SET_PIN_HSM_TRANSLATION_FAILED = "Trouble with HSM module, PIN was not correctly translated";
    public static final String RESPONSE_MESSAGE_SET_PIN_PAN_DECIPHERING_FAILED = "Trouble with HSM module, PAN was not properly deciphered";
    public static final String RESPONSE_MESSAGE_SET_PIN_PROCESSOR_COMMUNICATION_FAILED = "Trouble with card processor, PIN change was not propagated to card processor";
    public static final String RESPONSE_MESSAGE_SET_PIN_WRONG_PIN_LENGTH = "PIN length is incorrect";
    public static final String RESPONSE_MESSAGE_SET_PIN_OTHER_ERROR = "In case of other unspecified error";

    public static final String DOB_DATE_FORMAT = "ddMMyy";

    public static final String APPLICATION_ID_IVR = "IVR";
}