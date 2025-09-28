package com.mufid.service.store;

import com.mufid.dto.store.StoreRequest;
import com.mufid.dto.store.StoreResponse;
import com.mufid.entity.bean.Branch;
import com.mufid.entity.bean.Store;
import com.mufid.repository.BranchRepository;
import com.mufid.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository; // biar bisa validasi branch

    private StoreResponse mapToResponse(Store store) {
        StoreResponse res = new StoreResponse();
        res.setId(store.getId());
        res.setName(store.getName());
        res.setCode(store.getCode());
        res.setAddress(store.getAddress());
        res.setPhone(store.getPhone());
        res.setManagerName(store.getManagerName());
        res.setIsWhitelist(store.getIsWhitelist());
        res.setBranchId(store.getBranch().getId());
        res.setBranchName(store.getBranch().getName());
        res.setProvinceName(store.getBranch().getProvince().getName());
        return res;
    }

    @Override
    public StoreResponse create(StoreRequest request) {
        if (storeRepository.existsByCodeAndIsDeletedFalse(request.getCode())) {
            throw new RuntimeException("Store code already exists");
        }

        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        Store store = new Store();
        store.setName(request.getName());
        store.setCode(request.getCode());
        store.setAddress(request.getAddress());
        store.setPhone(request.getPhone());
        store.setManagerName(request.getManagerName());
        store.setIsWhitelist(request.getIsWhitelist());
        store.setBranch(branch);

        Store saved = storeRepository.save(store);
        return mapToResponse(saved);
    }

    @Override
    public StoreResponse update(Integer id, StoreRequest request) {
        Store store = storeRepository.findActiveById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        store.setName(request.getName());
        store.setCode(request.getCode());
        store.setAddress(request.getAddress());
        store.setPhone(request.getPhone());
        store.setManagerName(request.getManagerName());
        store.setIsWhitelist(request.getIsWhitelist());
        store.setBranch(branch);

        Store updated = storeRepository.save(store);
        return mapToResponse(updated);
    }

    @Override
    public void delete(Integer id) {
        Store store = storeRepository.findActiveById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        store.setIsDeleted(true);
        store.setIsActive(false);
        storeRepository.save(store);
    }

    @Override
    public StoreResponse getById(Integer id) {
        return storeRepository.findActiveById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Store not found"));
    }

    @Override
    public List<StoreResponse> getAllActive() {
        return storeRepository.findAllActive()
                .stream().map(this::mapToResponse).toList();
    }

    @Override
    public List<StoreResponse> searchByProvince(String provinceName) {
        return storeRepository.findActiveByProvinceName(provinceName)
                .stream().map(this::mapToResponse).toList();
    }

    @Override
    public List<StoreResponse> searchByProvinceIncludingWhitelist(String provinceName) {
        return storeRepository.findActiveByProvinceNameIncludingWhitelist(provinceName)
                .stream().map(this::mapToResponse).toList();
    }

    @Override
    public List<StoreResponse> getWhitelistStores() {
        return storeRepository.findAllWhitelistStores()
                .stream().map(this::mapToResponse).toList();
    }

    @Override
    public List<StoreResponse> searchByName(String name) {
        return storeRepository.findActiveByNameContaining(name)
                .stream().map(this::mapToResponse).toList();
    }
}