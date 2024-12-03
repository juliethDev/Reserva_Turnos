package com.example.Integro.DTO.Salida.Patient;

import java.time.LocalDate;

public class PatientExit {

    private Long id;

    private String name;

    private String lastName;

    private int dni;

    private LocalDate entryDate;

    private AddressExit addressExit;


    public PatientExit() {
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

    @Override
    public String toString() {
        return "PatientExit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dni=" + dni +
                ", entryDate=" + entryDate +
                ", addressExit=" + addressExit +
                '}';
    }
}
