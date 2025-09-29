package com.mufid.model;

import java.time.LocalDateTime;
import java.util.Map;

public class NotificationModel {
    private String title;
    private String message;
    private String type; // INFO, SUCCESS, WARNING, ERROR
    private String priority; // LOW, MEDIUM, HIGH, URGENT
    private String category; // SYSTEM, LEAVE, PAYROLL, ATTENDANCE, etc.
    private Long recipientId;
    private String recipientType; // USER, ROLE, DEPARTMENT, ALL
    private Boolean isRead;
    private Boolean requireAction;
    private String actionUrl;
    private String actionLabel;
    private Map<String, Object> data;
    private LocalDateTime createdAt;
    private LocalDateTime readAt;
    private LocalDateTime expiresAt;
    private String icon;
    private String color;

    public NotificationModel() {
        this.createdAt = LocalDateTime.now();
        this.isRead = false;
        this.requireAction = false;
        this.type = "INFO";
        this.priority = "MEDIUM";
    }

    public NotificationModel(String title, String message, String type) {
        this();
        this.title = title;
        this.message = message;
        this.type = type;
    }

    // Builder pattern methods
    public NotificationModel title(String title) {
        this.title = title;
        return this;
    }

    public NotificationModel message(String message) {
        this.message = message;
        return this;
    }

    public NotificationModel type(String type) {
        this.type = type;
        return this;
    }

    public NotificationModel priority(String priority) {
        this.priority = priority;
        return this;
    }

    public NotificationModel category(String category) {
        this.category = category;
        return this;
    }

    public NotificationModel recipient(Long recipientId, String recipientType) {
        this.recipientId = recipientId;
        this.recipientType = recipientType;
        return this;
    }

    public NotificationModel action(String actionUrl, String actionLabel) {
        this.actionUrl = actionUrl;
        this.actionLabel = actionLabel;
        this.requireAction = true;
        return this;
    }

    public NotificationModel expires(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public NotificationModel withData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    // Utility methods
    public boolean isExpired() {
        return expiresAt != null && LocalDateTime.now().isAfter(expiresAt);
    }

    public boolean isUrgent() {
        return "URGENT".equalsIgnoreCase(priority);
    }

    public boolean isError() {
        return "ERROR".equalsIgnoreCase(type);
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Long getRecipientId() { return recipientId; }
    public void setRecipientId(Long recipientId) { this.recipientId = recipientId; }

    public String getRecipientType() { return recipientType; }
    public void setRecipientType(String recipientType) { this.recipientType = recipientType; }

    public Boolean getIsRead() { return isRead; }
    public void setIsRead(Boolean isRead) { this.isRead = isRead; }

    public Boolean getRequireAction() { return requireAction; }
    public void setRequireAction(Boolean requireAction) { this.requireAction = requireAction; }

    public String getActionUrl() { return actionUrl; }
    public void setActionUrl(String actionUrl) { this.actionUrl = actionUrl; }

    public String getActionLabel() { return actionLabel; }
    public void setActionLabel(String actionLabel) { this.actionLabel = actionLabel; }

    public Map<String, Object> getData() { return data; }
    public void setData(Map<String, Object> data) { this.data = data; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getReadAt() { return readAt; }
    public void setReadAt(LocalDateTime readAt) { this.readAt = readAt; }

    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}
