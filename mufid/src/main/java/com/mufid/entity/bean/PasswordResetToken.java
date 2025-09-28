package com.mufid.entity.bean;

import com.mufid.entity.PasswordResetTokenEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken extends PasswordResetTokenEntity {
}
