package com.mufid.entity;



import com.mufid.base.BaseEntity;
import com.mufid.entity.bean.Role;
import com.mufid.entity.bean.User;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class TokenEntity extends BaseEntity implements Serializable {

    @Transient
    private String statusText;

    @Transient
    private String roleText;

    @OneToOne
    @Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false, nullable = false)
    private User user;

    @OneToOne
    @Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    @Column(name = "token", updatable = false, nullable = false, length = 64)
    private String token;


    @Column(name = "login_at", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginAt;

    @Column(name = "logout_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutAt;

    @Column(name = "last_active_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActiveAt;

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getRoleText() {
        return roleText;
    }

    public void setRoleText(String roleText) {
        this.roleText = roleText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Date loginAt) {
        this.loginAt = loginAt;
    }

    public Date getLogoutAt() {
        return logoutAt;
    }

    public void setLogoutAt(Date logoutAt) {
        this.logoutAt = logoutAt;
    }

    public Date getLastActiveAt() {
        return lastActiveAt;
    }

    public void setLastActiveAt(Date lastActiveAt) {
        this.lastActiveAt = lastActiveAt;
    }

}
