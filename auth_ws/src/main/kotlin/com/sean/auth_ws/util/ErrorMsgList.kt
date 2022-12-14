package com.sean.auth_ws.util

enum class ErrorMsgList(val value: String) {
    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
    PASSWORD_MISMATCH("Password mismatch"),
    ACCOUNT_EXIST("Email already registered"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    NO_RECORD_FOUND("Record with provided id is not found"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified");
}