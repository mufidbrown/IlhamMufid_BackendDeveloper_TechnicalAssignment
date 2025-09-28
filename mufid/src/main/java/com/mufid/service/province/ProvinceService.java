package com.mufid.service.province;

import com.mufid.dto.province.ProvinceRequest;
import com.mufid.dto.province.ProvinceResponse;

import java.util.List;

public interface ProvinceService {
    ProvinceResponse create(ProvinceRequest request);
    ProvinceResponse update(Integer id, ProvinceRequest request);
    void delete(Integer id);
    ProvinceResponse getById(Integer id);
    List<ProvinceResponse> getAll();
}
