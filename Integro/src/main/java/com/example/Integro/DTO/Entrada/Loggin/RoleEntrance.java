package com.example.Integro.DTO.Entrada.Loggin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleEntrance {

    @Size(min = 2, max = 50, message = "El nombre debe tener hasta 50 caracteres")
    @NotNull(message = "El nombre del rol no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del rol")
    private String name;

    public RoleEntrance() {
    }

    public RoleEntrance(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
