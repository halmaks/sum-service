package com.halmaks.sumservice.models;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class Response {
    @NotNull
    Integer code;

    @NotNull
    @NotBlank
    String description;
}
