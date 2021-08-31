package com.halmaks.sumservice.services;

import com.halmaks.sumservice.config.RequestDescriptionConfig;
import org.springframework.stereotype.Service;

@Service
public class RequestDescriptionService {
    private final RequestDescriptionConfig config;

    public RequestDescriptionService(final RequestDescriptionConfig config) {
        this.config = config;
    }

    public String getDescriptionByCode(final int code) {
        return config.getDescriptions().get(code);
    }
}
