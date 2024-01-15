package com.demo.demo.Features.Param.User.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SaveUserRequest {
    @JsonProperty("nombre")
    @NotBlank(message = "nombre de usuario es requerido")
    private String firsName;

    @JsonProperty("apellido")
    @NotBlank(message = "apellido de usuario es requerido")
    private String lastName;

    @JsonProperty("correo")
    @NotBlank(message = "correo de usuario es requerido")
    private String email;
    @JsonProperty("ciudad")
    @Min(1)
    @NotNull()
    private Long ciudad;

}
