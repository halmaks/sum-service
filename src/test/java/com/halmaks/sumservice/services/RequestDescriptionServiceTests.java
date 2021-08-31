package com.halmaks.sumservice.services;

import com.halmaks.sumservice.config.RequestDescriptionConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RequestDescriptionServiceTests {
    private RequestDescriptionConfig config;
    private RequestDescriptionService descriptionService;

    @BeforeEach
    private void init() {
        config = new RequestDescriptionConfig();
        config.setDescriptions(Map.of(
                0, "OK",
                1, "NOT FOUND",
                2, "ALREADY EXISTS",
                3, "ERROR",
                4, "CLIENT ERROR",
                5, "BAD REQUEST")
        );
        descriptionService = new RequestDescriptionService(config);
    }

    @Nested
    public class GetDescriptionByCode {
        @Test
        public void shouldReturnOkWhenCode0() {
            final var actual = descriptionService.getDescriptionByCode(0);
            assertEquals("OK", actual);
        }

        @Test
        public void shouldReturnNotFoundWhenCode1() {
            final var actual = descriptionService.getDescriptionByCode(1);
            assertEquals("NOT FOUND", actual);
        }

        @Test
        public void shouldReturnAlreadyExistsWhenCode2() {
            final var actual = descriptionService.getDescriptionByCode(2);
            assertEquals("ALREADY EXISTS", actual);
        }

        @Test
        public void shouldReturnErrorWhenCode3() {
            final var actual = descriptionService.getDescriptionByCode(3);
            assertEquals("ERROR", actual);
        }

        @Test
        public void shouldReturnClientErrorWhenCode4() {
            final var actual = descriptionService.getDescriptionByCode(4);
            assertEquals("CLIENT ERROR", actual);
        }

        @Test
        public void shouldReturnBadRequestWhenCode5() {
            final var actual = descriptionService.getDescriptionByCode(5);
            assertEquals("BAD REQUEST", actual);
        }
    }
}
