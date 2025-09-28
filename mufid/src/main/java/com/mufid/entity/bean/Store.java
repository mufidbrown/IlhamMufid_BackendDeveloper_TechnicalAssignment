package com.mufid.entity.bean;


import com.mufid.entity.StoreEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "stores", uniqueConstraints = { @UniqueConstraint(columnNames = { "code", "is_deleted" }) })
public class Store extends StoreEntity {
}
