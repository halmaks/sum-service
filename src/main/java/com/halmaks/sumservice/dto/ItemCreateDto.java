package com.halmaks.sumservice.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class ItemCreateDto {
    @NotNull
    @NotBlank
    String name;

    @NotNull
    Integer value;
}
