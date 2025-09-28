package com.mufid.controller;

import com.mufid.base.ApiResponse;
import com.mufid.shared.constant.MessageConstant;
import com.mufid.dto.province.ProvinceRequest;
import com.mufid.dto.province.ProvinceResponse;
import com.mufid.service.province.ProvinceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody ProvinceRequest request) {
        ProvinceResponse response = provinceService.create(request);
        return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_CREATED, response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody ProvinceRequest request) {
        ProvinceResponse response = provinceService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_UPDATED, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        provinceService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_DELETED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
        ProvinceResponse response = provinceService.getById(id);
        return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_RETRIEVED, response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<ProvinceResponse> responses = provinceService.getAll();
        return ResponseEntity.ok(ApiResponse.success(MessageConstant.SUCCESS_RETRIEVED, responses));
    }
}
