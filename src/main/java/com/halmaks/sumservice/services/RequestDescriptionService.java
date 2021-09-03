package com.halmaks.sumservice.services;

import com.halmaks.sumservice.config.RequestDescriptionConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestDescriptionService {
    private final RequestDescriptionConfig config;

    public String getDescriptionByCode(final int code) {
        return config.getDescriptions().get(code);
    }
}
