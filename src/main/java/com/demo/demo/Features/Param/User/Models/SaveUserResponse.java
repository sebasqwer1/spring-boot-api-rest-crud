package com.demo.demo.Features.Param.User.Models;

import com.demo.demo.Entities.CityModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SaveUserResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombre")
    private String firsName;

    @JsonProperty("apellido")
    private String lastName;

    @JsonProperty("correo")
    private String email;

}
