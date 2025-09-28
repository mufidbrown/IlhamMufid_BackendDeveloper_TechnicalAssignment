package com.mufid.shared.constant;

public class AppConstant {

    // API Paths
    public static final String API_V1 = "/api/v1";
    public static final String AUTH_PATH = "/auth";
    public static final String PROVINCES_PATH = "/provinces";
    public static final String BRANCHES_PATH = "/branches";
    public static final String STORES_PATH = "/stores";
    public static final String WHITELIST_PATH = "/whitelist";

    // Security
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";

    // Database
    public static final String SOFT_DELETE_QUERY = "isDeleted = false";
    public static final String ACTIVE_QUERY = "isActive = true AND isDeleted = false";

    // Validation Messages
    public static final String REQUIRED_FIELD = "Field is required";
    public static final String INVALID_EMAIL = "Invalid email format";
    public static final String INVALID_LENGTH = "Invalid field length";

    // Success Messages
    public static final String SUCCESS_CREATE = "Resource created successfully";
    public static final String SUCCESS_UPDATE = "Resource updated successfully";
    public static final String SUCCESS_DELETE = "Resource deleted successfully";

    // Error Messages
    public static final String ERROR_NOT_FOUND = "Resource not found";
    public static final String ERROR_UNAUTHORIZED = "Unauthorized access";
    public static final String ERROR_FORBIDDEN = "Access forbidden";
    public static final String ERROR_VALIDATION = "Validation failed";

    // Pagination
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

}
