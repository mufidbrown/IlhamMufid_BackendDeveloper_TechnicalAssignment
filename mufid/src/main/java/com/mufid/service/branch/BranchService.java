package com.mufid.service.branch;

import com.mufid.dto.branch.BranchRequest;
import com.mufid.dto.branch.BranchResponse;

import java.util.List;

public interface BranchService {
    BranchResponse create(BranchRequest request);
    BranchResponse update(Integer id, BranchRequest request);
    void delete(Integer id);
    BranchResponse getById(Integer id);
    List<BranchResponse> getAll();
}
