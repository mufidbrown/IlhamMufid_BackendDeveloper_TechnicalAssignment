package com.mufid.service.store;

import com.mufid.dto.store.StoreRequest;
import com.mufid.dto.store.StoreResponse;
import java.util.List;

public interface StoreService {

    StoreResponse create(StoreRequest request);

    StoreResponse update(Integer id, StoreRequest request);

    void delete(Integer id);

    StoreResponse getById(Integer id);

    List<StoreResponse> getAllActive();

    List<StoreResponse> searchByProvince(String provinceName);

    List<StoreResponse> searchByProvinceIncludingWhitelist(String provinceName);

    List<StoreResponse> getWhitelistStores();

    List<StoreResponse> searchByName(String name);
}
