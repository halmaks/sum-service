package com.halmaks.sumservice.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRemoveDto {
    @NotNull
    @NotBlank
    private String name;
}
