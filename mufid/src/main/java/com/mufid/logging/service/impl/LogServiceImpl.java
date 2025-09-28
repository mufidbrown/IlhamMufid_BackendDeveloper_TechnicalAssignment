package com.mufid.logging.service.impl;

import com.mufid.logging.entity.LogEntity;
import com.mufid.logging.repository.LogRepository;
import com.mufid.logging.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    /**
     * Save  {@link LogEntity} entry ke database.
     *
     * @param logEntity  {@link LogEntity} entity yang akan disimpan.
     */
    @Override
    public void saveLogToDatabase(LogEntity logEntity) {
        logEntity.setTime(LocalDateTime.now());
        logRepository.save(logEntity);
    }
}

