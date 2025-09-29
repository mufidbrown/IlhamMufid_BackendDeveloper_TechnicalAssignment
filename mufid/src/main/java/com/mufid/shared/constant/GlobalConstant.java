package com.mufid.shared.constant;

public final class GlobalConstant {

    // Application Constants
    public static final String APPLICATION_NAME = "Enterprise Monolith System";
    public static final String APPLICATION_VERSION = "1.0.0";

    // Date Formats
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss";

    // Cache Names
    public static final String CACHE_APP_PARAMETERS = "app-parameters";
    public static final String CACHE_USER_PERMISSIONS = "user-permissions";
    public static final String CACHE_SYSTEM_CONFIG = "system-config";

    // Pagination
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;
    public static final String DEFAULT_SORT_FIELD = "id";
    public static final String DEFAULT_SORT_DIRECTION = "ASC";

    // Status
    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_INACTIVE = "INACTIVE";
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_APPROVED = "APPROVED";
    public static final String STATUS_REJECTED = "REJECTED";

    // System Users
    public static final String SYSTEM_USER = "SYSTEM";
    public static final String ANONYMOUS_USER = "ANONYMOUS";

    private GlobalConstant() {
        throw new IllegalStateException("Utility class");
    }
}

