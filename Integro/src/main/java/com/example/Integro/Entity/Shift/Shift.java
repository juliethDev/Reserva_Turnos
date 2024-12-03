package com.example.Integro.Entity.Shift;


import com.example.Integro.Entity.Dentist.Dentist;
import com.example.Integro.Entity.Patient.Patient;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="SHIFT",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dentist_ID")
    private Dentist dentist;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime dateAndTime;

    public Shift() {
    }

    public Shift(Long id, Dentist dentist, Patient patient, LocalDateTime dateAndTime) {
        this.id = id;
        this.dentist = dentist;
        this.patient = patient;
        this.dateAndTime = dateAndTime;
    }

    public Long getId() {
        return id;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
