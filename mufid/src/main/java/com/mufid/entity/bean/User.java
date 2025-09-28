package com.mufid.entity.bean;

import com.mufid.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "path", "is_deleted" }) })
public class User extends UserEntity {
}

