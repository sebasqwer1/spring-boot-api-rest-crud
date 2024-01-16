package com.demo.demo.Features.Param.User.Models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NoDataResponse<T> {
    @Schema(description = "estado", type = "String")
    private String status;

    private T data;

    @Schema(description = "mensaje", type = "String")
    private String message;
}
