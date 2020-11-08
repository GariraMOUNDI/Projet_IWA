package org.projet_iwa.auth.api.model;

public enum UserResponseType {
    USER_CREATED,
    USER_LOGGED_IN,
    USER_EXIST,
    USER_NOT_EXIST,
    INCORRECT_PASSWORD,
    UPDATED,
    VALID_USER,
    FIELD_NULL,
    BAD_EMAIL,
    BAD_PHONE_NUMBER,
    BAD_CONFIRMATION,
    FIELD_LENGTH
}
