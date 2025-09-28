package com.mufid.entity.bean;

import com.mufid.entity.TokenEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "tokens")
public class Token extends TokenEntity {

}

