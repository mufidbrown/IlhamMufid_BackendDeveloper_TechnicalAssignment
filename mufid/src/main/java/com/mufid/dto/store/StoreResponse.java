package com.mufid.dto.store;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {
    private Integer id;
    private String name;
    private String code;
    private String address;
    private String phone;
    private String managerName;
    private Boolean isWhitelist;
    private Integer branchId;
    private String branchName;
    private String provinceName;
    private Boolean isActive;
}