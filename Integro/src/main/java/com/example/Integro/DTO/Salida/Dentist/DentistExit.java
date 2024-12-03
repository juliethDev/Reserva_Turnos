package com.example.Integro.DTO.Salida.Dentist;

public class DentistExit {

    private Long id;
    private String tuition;
    private String name;
    private String lastName;


    public DentistExit() {
    }

    public DentistExit(Long id, String tuition, String name, String lastName) {
        this.id = id;
        this.tuition = tuition;
        this.name = name;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
