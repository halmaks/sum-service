package com.halmaks.sumservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = "/db/test-data.sql", config = @SqlConfig(commentPrefix = "--#"))
public class ItemControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnCode0DescriptionOkWhenThereIsNoItemNamedFive() throws Exception {
        final var request = post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"five\",\"value\":5}");
        final var response = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertEquals("{\"code\":0,\"description\":\"OK\"}", response);
    }

    @Test
    public void shouldReturnCode2DescriptionAlreadyExistsWhenItemNamedOneIsPresent() throws Exception {
        final var request = post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"one\",\"value\":1}");
        final var response = mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertEquals("{\"code\":2,\"description\":\"ALREADY EXISTS\"}", response);
    }

    @Test
    public void shouldReturnCode0AndDescriptionOkWhenItemNamedOneIsPresent() throws Exception {
        final var request = post("/remove")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"one\"}");
        final var response = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertEquals("{\"code\":0,\"description\":\"OK\"}", response);
    }

    @Test
    public void shouldReturnCode1AndDescriptionNotFoundWhenThereIsNoItemNamedFive() throws Exception {
        final var request = post("/remove")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"five\"}");
        final var response = mvc.perform(request)
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertEquals("{\"code\":1,\"description\":\"NOT FOUND\"}", response);
    }

    @Test
    public void shouldReturnCode0AndDescriptionOkAndSum3WhenThereAreItemsWithCurrentNames() throws Exception {
        final var request = post("/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"first\":\"one\",\"second\":\"two\"}");
        final var response = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertEquals("{\"sum\":3,\"code\":0,\"description\":\"OK\"}", response);
    }

    @Test
    public void shouldReturnCode1AndDescriptionNotFoundWhenThereIsNoItemNamedSix() throws Exception {
        final var request = post("/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"first\":\"one\",\"second\":\"six\"}");
        final var response = mvc.perform(request)
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        assertEquals("{\"code\":1,\"description\":\"NOT FOUND\"}", response);
    }
}
