package com.example.Integro.DTO.Entrada.Dentist;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistEntrance {

    @NotNull(message = "La matricula no puede ser nula")
    @NotBlank(message = "Debe especificarse la matricula del odontologo")
    @Pattern(regexp = "^[A-Z]{2}-\\d{1,3}\\d*$")
    @Size(min = 10, message = "El campo debe tener mínimo 10 caracteres")
    private String tuition;

    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres")
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del odontologo")
    private String name;

    @Size(max = 50, message = "El apellido debe tener hasta 50 caracteres")
    @NotNull(message = "El apellido no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del odontologo")
    private String lastName;


    public DentistEntrance() {
    }

    public DentistEntrance(String tuition, String name, String lastName) {
        this.tuition = tuition;
        this.name = name;
        this.lastName = lastName;
    }

    public String getTuition() {
        return tuition;
    }

    public void setTuition(String tuition) {
        this.tuition = tuition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
