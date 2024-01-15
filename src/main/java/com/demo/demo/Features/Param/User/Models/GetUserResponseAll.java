package com.demo.demo.Features.Param.User.Models;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class GetUserResponseAll {
    private Long id;

    @JsonProperty("nombre")
    private String firsName;

    @JsonProperty("apellido")
    private String lastName;

    @JsonProperty("correo")
    private String email;

    @JsonProperty("ciudad")
    private String ciudadDescripcion;

}
