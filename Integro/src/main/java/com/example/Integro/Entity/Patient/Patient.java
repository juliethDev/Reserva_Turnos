package com.example.Integro.Entity.Patient;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "PATIENT", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private int dni;

    private LocalDate entryDate;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name= "home_Id",referencedColumnName = "id")
    private Home home;


    public Patient() {
    }

    public Patient(String name, String lastName, int dni, LocalDate entryDate, Home home) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.entryDate = entryDate;
        this.home = home;
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

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}
