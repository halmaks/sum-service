package com.halmaks.sumservice.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Value;

@Value
public class SumResponse {
    int sum;

    @JsonUnwrapped
    Response response;
}
