package com.mufid.shared.constant;

public final class MessageConstant {

    // Success Messages
    public static final String SUCCESS_REGISTERED = "Registration successful";
    public static final String SUCCESS_OPERATION = "Operation completed successfully";
    public static final String SUCCESS_CREATED = "Data created successfully";
    public static final String SUCCESS_UPDATED = "Data updated successfully";
    public static final String SUCCESS_DELETED = "Data deleted successfully";
    public static final String SUCCESS_LOGOUT = "Logout successfully";
    public static final String SUCCESS_RETRIEVED = "Data retrieved successfully";

    // Error Messages
    public static final String ERROR_GENERAL = "An error occurred while processing your request";
    public static final String ERROR_VALIDATION = "Validation error occurred";
    public static final String ERROR_NOT_FOUND = "Data not found";
    public static final String ERROR_ALREADY_EXISTS = "Data already exists";
    public static final String ERROR_UNAUTHORIZED = "Unauthorized access";
    public static final String ERROR_FORBIDDEN = "Access denied";
    public static final String ERROR_BAD_REQUEST = "Invalid request";
    public static final String ERROR_INTERNAL_SERVER = "Internal server error";

    // Field Validation Messages
    public static final String FIELD_REQUIRED = "This field is required";
    public static final String FIELD_INVALID_FORMAT = "Invalid format";
    public static final String FIELD_TOO_SHORT = "Field value is too short";
    public static final String FIELD_TOO_LONG = "Field value is too long";
    public static final String FIELD_INVALID_EMAIL = "Invalid email format";
    public static final String FIELD_INVALID_PHONE = "Invalid phone number format";

    // Business Logic Messages
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String INVALID_CREDENTIALS = "Invalid username or password";
    public static final String ACCOUNT_LOCKED = "Account is locked";
    public static final String ACCOUNT_EXPIRED = "Account has expired";
    public static final String PASSWORD_EXPIRED = "Password has expired";

    private MessageConstant() {
        throw new IllegalStateException("Utility class");
    }
}
