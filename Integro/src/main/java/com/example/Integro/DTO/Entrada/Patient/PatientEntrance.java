package com.example.Integro.DTO.Entrada.Patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PatientEntrance {

    @Size(min = 2, max = 50, message = "El nombre debe tener hasta 50 caracteres")
    @NotNull(message = "El nombre del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del paciente")
    private String name;

    @Size(min = 2, max = 50, message = "El apellido debe tener hasta 50 caracteres")
    @NotNull(message = "El apellido del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del paciente")
    private String lastName;


    @NotNull(message = "Debe especificarse el dni del paciente")
    @Digits(integer = 8, fraction = 0, message = "El número debe tener como máximo 8 dígitos")
    private int dni;

    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate entryDate;


    @NotNull(message = "El domicilio del paciente no puede ser nulo")
    @Valid
    private AddressEntrance addressEntrance;


    public PatientEntrance() {
    }

    public PatientEntrance(String name, String lastName, int dni, LocalDate entryDate, AddressEntrance addressEntrance) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.entryDate = entryDate;
        this.addressEntrance = addressEntrance;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public AddressEntrance getAddressEntrance() {
        return addressEntrance;
    }

    public void setAddressEntrance(AddressEntrance addressEntrance) {
        this.addressEntrance = addressEntrance;
    }
}
