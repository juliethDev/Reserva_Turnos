package com.example.Integro.DTO.Entrada.Shift;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftEntrance {

    @NotNull(message = "El paciente no puede ser nulo")
    private Long patientId;

    @NotNull(message = "El odontologo no puede ser nulo")
    private Long dentistId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha y hora del turno")
    private LocalDateTime dateAndTime;


    public ShiftEntrance() {
    }

    public ShiftEntrance(Long patientId, Long dentistId, LocalDateTime dateAndTime) {
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.dateAndTime = dateAndTime;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDentistId() {
        return dentistId;
    }

    public void setDentistId(Long dentistId) {
        this.dentistId = dentistId;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "ShiftEntrance{" +
                "patientId=" + patientId +
                ", dentistId=" + dentistId +
                ", dateAndTime=" + dateAndTime +
                '}';
    }
}
