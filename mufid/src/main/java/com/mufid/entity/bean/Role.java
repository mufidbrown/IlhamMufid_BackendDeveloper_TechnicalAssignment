package com.mufid.entity.bean;

import com.mufid.entity.RoleEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "roles", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class Role extends RoleEntity {
}

