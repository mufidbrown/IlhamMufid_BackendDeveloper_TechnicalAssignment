package com.mufid.logging.service;


import com.mufid.logging.entity.LogEntity;

public interface LogService {
    void saveLogToDatabase(LogEntity logEntity);
}
