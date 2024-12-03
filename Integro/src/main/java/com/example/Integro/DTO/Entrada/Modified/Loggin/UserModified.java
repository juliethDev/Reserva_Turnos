package com.example.Integro.DTO.Entrada.Modified.Loggin;

import com.example.Integro.DTO.Entrada.Loggin.RoleEntrance;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModified {

    @NotNull(message = "Debe proveerse el id del usuario que se desea modificar")
    private Long id;

    @Size(min = 2, max = 10, message = "El nombre de usuario debe tener hasta 10 caracteres")
    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre de usuario")
    private String username;

    @Size(min = 2, max = 20, message = "El password debe tener hasta 20 caracteres")
    @NotNull(message = "El password no puede ser nulo")
    @NotBlank(message = "Debe especificarse el password")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "La contraseña debe contener al menos una letra mayúscula, una letra minúscula, un número y un carácter especial."
    )
    private String password;

    @NotNull(message = "El rol no puede ser nulo")
    @Valid
    private RoleEntrance rol;

    public UserModified() {
    }

    public UserModified(Long id, String username, String password, RoleEntrance rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntrance getRol() {
        return rol;
    }

    public void setRol(RoleEntrance rol) {
        this.rol = rol;
    }
}