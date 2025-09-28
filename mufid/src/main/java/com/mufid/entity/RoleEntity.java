package com.mufid.entity;

import com.mufid.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class RoleEntity extends BaseEntity implements Serializable {

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "type")
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
