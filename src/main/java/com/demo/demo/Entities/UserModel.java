package com.demo.demo.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "usuario")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("nombre")
    private String firsName;

    @JsonProperty("apellido")
    private String lastName;

    @JsonProperty("correo")
    private String email;

    @ManyToOne
    @JsonProperty("ciudad")
    @JoinColumn(name = "city_id")
    private CityModel cityId;

}
