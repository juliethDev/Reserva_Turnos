package com.example.Integro.DTO.Entrada.Modified.Loggin;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleModified {

    @NotNull(message = "Debe proveerse el id del rol que se desea modificar")
    private Long id;

    @Size(min = 2, max = 50, message = "El nombre debe tener hasta 50 caracteres")
    @NotNull(message = "El nombre del rol no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del rol")
    private String name;

    public RoleModified() {
    }

    public RoleModified(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
