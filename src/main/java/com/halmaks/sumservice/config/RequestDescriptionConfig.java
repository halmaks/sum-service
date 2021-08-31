package com.halmaks.sumservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "services.response")
@Component
public class RequestDescriptionConfig {
    private Map<Integer, String> descriptions;
}
