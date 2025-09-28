package com.mufid.entity;

import com.mufid.base.BaseEntity;
import com.mufid.entity.bean.Branch;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@MappedSuperclass
public class ProvinceEntity extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "code", nullable = false, unique = true, length = 10)
    private String code;

    @Column(name = "description", length = 500)
    private String description;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Branch> branches;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}