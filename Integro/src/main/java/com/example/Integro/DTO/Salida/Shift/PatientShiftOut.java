package com.example.Integro.DTO.Salida.Shift;


import com.example.Integro.DTO.Salida.Patient.AddressExit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientShiftOut {

    private Long id;

    private String name;

    private String lastName;

    private int dni;

    private LocalDate entryDate;

    private AddressExit addressExit;


    public PatientShiftOut() {
    }

    public PatientShiftOut(Long id, String name, String lastName, int dni, LocalDate entryDate, AddressExit addressExit) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.entryDate = entryDate;
        this.addressExit = addressExit;
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

    public AddressExit getAddressExit() {
        return addressExit;
    }

    public void setAddressExit(AddressExit addressExit) {
        this.addressExit = addressExit;
    }
}
