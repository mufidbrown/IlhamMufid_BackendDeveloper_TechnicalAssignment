package com.mufid.model;

import java.time.LocalDateTime;
import java.util.List;

public class WorkflowStepModel {
    private String stepCode;
    private String stepName;
    private String description;
    private Integer order;
    private String status; // PENDING, APPROVED, REJECTED, SKIPPED
    private Long approverId;
    private String approverName;
    private String approverRole;
    private LocalDateTime actionDate;
    private String comments;
    private String action; // APPROVE, REJECT, DELEGATE, etc.
    private Boolean isRequired;
    private Boolean allowDelegation;
    private Boolean allowSkip;
    private Integer maxDays; // Maximum days to complete this step
    private List<Long> alternateApprovers;
    private String escalationRule;

    public WorkflowStepModel() {
        this.status = "PENDING";
        this.isRequired = true;
        this.allowDelegation = false;
        this.allowSkip = false;
    }

    public WorkflowStepModel(String stepCode, String stepName, Integer order) {
        this();
        this.stepCode = stepCode;
        this.stepName = stepName;
        this.order = order;
    }

    // Builder pattern methods
    public WorkflowStepModel step(String stepCode, String stepName) {
        this.stepCode = stepCode;
        this.stepName = stepName;
        return this;
    }

    public WorkflowStepModel order(Integer order) {
        this.order = order;
        return this;
    }

    public WorkflowStepModel approver(Long approverId, String approverName, String approverRole) {
        this.approverId = approverId;
        this.approverName = approverName;
        this.approverRole = approverRole;
        return this;
    }

    public WorkflowStepModel status(String status) {
        this.status = status;
        return this;
    }

    public WorkflowStepModel action(String action, String comments) {
        this.action = action;
        this.comments = comments;
        this.actionDate = LocalDateTime.now();
        return this;
    }

    public WorkflowStepModel required(Boolean isRequired) {
        this.isRequired = isRequired;
        return this;
    }

    public WorkflowStepModel delegation(Boolean allowDelegation) {
        this.allowDelegation = allowDelegation;
        return this;
    }

    public WorkflowStepModel skip(Boolean allowSkip) {
        this.allowSkip = allowSkip;
        return this;
    }

    public WorkflowStepModel maxDays(Integer maxDays) {
        this.maxDays = maxDays;
        return this;
    }

    public WorkflowStepModel alternates(List<Long> alternateApprovers) {
        this.alternateApprovers = alternateApprovers;
        return this;
    }

    // Utility methods
    public boolean isPending() {
        return "PENDING".equalsIgnoreCase(status);
    }

    public boolean isCompleted() {
        return "APPROVED".equalsIgnoreCase(status) || "REJECTED".equalsIgnoreCase(status);
    }

    public boolean isOverdue() {
        if (maxDays == null || actionDate != null) return false;
        // This would need the step start date to calculate properly
        return false; // Placeholder implementation
    }

    public boolean canBeDelegated() {
        return allowDelegation && isPending();
    }

    public boolean canBeSkipped() {
        return allowSkip && !isRequired;
    }

    // Getters and Setters
    public String getStepCode() { return stepCode; }
    public void setStepCode(String stepCode) { this.stepCode = stepCode; }

    public String getStepName() { return stepName; }
    public void setStepName(String stepName) { this.stepName = stepName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getOrder() { return order; }
    public void setOrder(Integer order) { this.order = order; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getApproverId() { return approverId; }
    public void setApproverId(Long approverId) { this.approverId = approverId; }

    public String getApproverName() { return approverName; }
    public void setApproverName(String approverName) { this.approverName = approverName; }

    public String getApproverRole() { return approverRole; }
    public void setApproverRole(String approverRole) { this.approverRole = approverRole; }

    public LocalDateTime getActionDate() { return actionDate; }
    public void setActionDate(LocalDateTime actionDate) { this.actionDate = actionDate; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public Boolean getIsRequired() { return isRequired; }
    public void setIsRequired(Boolean isRequired) { this.isRequired = isRequired; }

    public Boolean getAllowDelegation() { return allowDelegation; }
    public void setAllowDelegation(Boolean allowDelegation) { this.allowDelegation = allowDelegation; }

    public Boolean getAllowSkip() { return allowSkip; }
    public void setAllowSkip(Boolean allowSkip) { this.allowSkip = allowSkip; }

    public Integer getMaxDays() { return maxDays; }
    public void setMaxDays(Integer maxDays) { this.maxDays = maxDays; }

    public List<Long> getAlternateApprovers() { return alternateApprovers; }
    public void setAlternateApprovers(List<Long> alternateApprovers) { this.alternateApprovers = alternateApprovers; }

    public String getEscalationRule() { return escalationRule; }
    public void setEscalationRule(String escalationRule) { this.escalationRule = escalationRule; }
}
