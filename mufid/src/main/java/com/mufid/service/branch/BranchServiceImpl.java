package com.mufid.service.branch;

import com.mufid.dto.branch.BranchRequest;
import com.mufid.dto.branch.BranchResponse;
import com.mufid.entity.bean.Branch;
import com.mufid.entity.bean.Province;
import com.mufid.repository.BranchRepository;
import com.mufid.repository.ProvinceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final ProvinceRepository provinceRepository;

    public BranchServiceImpl(BranchRepository branchRepository, ProvinceRepository provinceRepository) {
        this.branchRepository = branchRepository;
        this.provinceRepository = provinceRepository;
    }

    @Override
    public BranchResponse create(BranchRequest request) {
        Province province = provinceRepository.findById(request.getProvinceId())
                .orElseThrow(() -> new EntityNotFoundException("Province not found"));

        Branch branch = new Branch();
        branch.setName(request.getName());
        branch.setCode(request.getCode());
        branch.setAddress(request.getAddress());
        branch.setPhone(request.getPhone());
        branch.setEmail(request.getEmail());
        branch.setProvince(province);

        Branch saved = branchRepository.save(branch);
        return mapToResponse(saved);
    }

    @Override
    public BranchResponse update(Integer id, BranchRequest request) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));

        Province province = provinceRepository.findById(request.getProvinceId())
                .orElseThrow(() -> new EntityNotFoundException("Province not found"));

        branch.setName(request.getName());
        branch.setCode(request.getCode());
        branch.setAddress(request.getAddress());
        branch.setPhone(request.getPhone());
        branch.setEmail(request.getEmail());
        branch.setProvince(province);

        Branch updated = branchRepository.save(branch);
        return mapToResponse(updated);
    }

    @Override
    public void delete(Integer id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
        branchRepository.delete(branch);
    }

    @Override
    public BranchResponse getById(Integer id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
        return mapToResponse(branch);
    }

    @Override
    public List<BranchResponse> getAll() {
        return branchRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private BranchResponse mapToResponse(Branch entity) {
        return new BranchResponse(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getAddress(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getProvince() != null ? entity.getProvince().getName() : null
        );
    }
}
