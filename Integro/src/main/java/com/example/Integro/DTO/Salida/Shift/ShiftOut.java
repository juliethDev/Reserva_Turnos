package com.example.Integro.DTO.Salida.Shift;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ShiftOut {

    private Long id;

    private PatientShiftOut patientShiftOut;

    private DentistShiftOut dentistShiftOut;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateAndTime;


    public ShiftOut() {

    }

    public ShiftOut(Long id, PatientShiftOut patientShiftOut, DentistShiftOut dentistShiftOut, LocalDateTime dateAndTime) {
        this.id = id;
        this.patientShiftOut = patientShiftOut;
        this.dentistShiftOut = dentistShiftOut;
        this.dateAndTime = dateAndTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientShiftOut getPatientShiftOut() {
        return patientShiftOut;
    }

    public void setPatientShiftOut(PatientShiftOut patientShiftOut) {
        this.patientShiftOut = patientShiftOut;
    }

    public DentistShiftOut getDentistShiftOut() {
        return dentistShiftOut;
    }

    public void setDentistShiftOut(DentistShiftOut dentistShiftOut) {
        this.dentistShiftOut = dentistShiftOut;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "ShiftOut{" +
                "id=" + id +
                ", patientShiftOut=" + patientShiftOut +
                ", dentistShiftOut=" + dentistShiftOut +
                ", dateAndTime=" + dateAndTime +
                '}';
    }
}
