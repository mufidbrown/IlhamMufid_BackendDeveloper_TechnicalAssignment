package com.mufid.entity;



import com.mufid.base.BaseEntity;
import com.mufid.entity.bean.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
@MappedSuperclass
public class PasswordResetTokenEntity extends BaseEntity implements Serializable {
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "expiry_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @Column(name = "used", nullable = false)
    private boolean used;


    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }

    public boolean isUsed() { return used; }
    public void setUsed(boolean used) { this.used = used; }

    public boolean isExpired() {
        return new Date().after(this.expiryDate);
    }
}
