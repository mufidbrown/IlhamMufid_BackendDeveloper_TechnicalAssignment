package com.mufid.entity.bean;

import com.mufid.entity.BranchEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "branchs", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class Branch extends BranchEntity {
}
