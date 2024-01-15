package com.demo.demo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Schema(description = "the common response")
public class Response<T>  implements Serializable {

    @Schema(description = "estado", type = "String")
    private String status;

    private T data;

    @Schema(description = "mensaje", type = "String")
    private String message;

}
