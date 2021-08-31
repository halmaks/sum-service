package com.halmaks.sumservice.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class SumDto {
    @NotNull
    @NotBlank
    String first;

    @NotNull
    @NotBlank
    String second;
}
