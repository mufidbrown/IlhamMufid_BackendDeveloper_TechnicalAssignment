package com.mufid.controller;

import com.mufid.base.ApiResponse;
import com.mufid.shared.constant.MessageConstant;
import com.mufid.dto.store.StoreRequest;
import com.mufid.dto.store.StoreResponse;
import com.mufid.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody StoreRequest request) {
        StoreResponse response = storeService.create(request);
        return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_CREATED, response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody StoreRequest request) {
        StoreResponse response = storeService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_UPDATED, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        storeService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_DELETED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
        StoreResponse response = storeService.getById(id);
        return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_RETRIEVED, response));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllActive() {
        List<StoreResponse> response = storeService.getAllActive();
        return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_RETRIEVED, response));
    }

    @GetMapping("/search/province")
    public ResponseEntity<ApiResponse> searchByProvince(@RequestParam String provinceName) {
        List<StoreResponse> response = storeService.searchByProvince(provinceName);
        return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_RETRIEVED, response));
    }

    @GetMapping("/search/province-whitelist")
    public ResponseEntity<ApiResponse> searchByProvinceIncludingWhitelist(@RequestParam String provinceName) {
        List<StoreResponse> response = storeService.searchByProvinceIncludingWhitelist(provinceName);
        return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_RETRIEVED, response));
    }

    @GetMapping("/whitelist")
    public ResponseEntity<ApiResponse> getWhitelistStores() {
        List<StoreResponse> response = storeService.getWhitelistStores();
        return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_RETRIEVED, response));
    }

    @GetMapping("/search/name")
    public ResponseEntity<ApiResponse> searchByName(@RequestParam String name) {
        List<StoreResponse> response = storeService.searchByName(name);
        return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_RETRIEVED, response));
    }
}