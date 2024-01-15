package com.demo.demo.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
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

    @Transient
    @JsonProperty("descripcion_ciudad")
    private String ciudadDescripcion;

    @PostLoad
    private void load() {
        if (cityId != null) {
            ciudadDescripcion = cityId.getDescription();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CityModel getCityId() {
        return cityId;
    }

    public void setCityId(CityModel cityId) {
        this.cityId = cityId;
    }

    public String getCiudadDescripcion() {
        return ciudadDescripcion;
    }

    public void setCiudadDescripcion(String ciudadDescripcion) {
        this.ciudadDescripcion = ciudadDescripcion;
    }

}
