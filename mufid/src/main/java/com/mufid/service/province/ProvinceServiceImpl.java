package com.mufid.service.province;

import com.mufid.dto.province.ProvinceRequest;
import com.mufid.dto.province.ProvinceResponse;
import com.mufid.entity.bean.Province;
import com.mufid.repository.BranchRepository;
import com.mufid.repository.ProvinceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;

    public ProvinceServiceImpl(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public ProvinceResponse create(ProvinceRequest request) {
        Province province = new Province();
        province.setName(request.getName());
        province.setCode(request.getCode());
        province.setDescription(request.getDescription());

        Province saved = provinceRepository.save(province);
        return mapToResponse(saved);
    }

    @Override
    public ProvinceResponse update(Integer id, ProvinceRequest request) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Province not found"));

        province.setName(request.getName());
        province.setCode(request.getCode());
        province.setDescription(request.getDescription());

        Province updated = provinceRepository.save(province);
        return mapToResponse(updated);
    }

    @Override
    public void delete(Integer id) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Province not found"));
        provinceRepository.delete(province);
    }

    @Override
    public ProvinceResponse getById(Integer id) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Province not found"));
        return mapToResponse(province);
    }

    @Override
    public List<ProvinceResponse> getAll() {
        return provinceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ProvinceResponse mapToResponse(Province entity) {
        return new ProvinceResponse(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription()
        );
    }
}
