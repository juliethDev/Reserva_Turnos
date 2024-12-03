package com.example.Integro.Service;


import com.example.Integro.DTO.Entrada.Modified.Patient.PatientModified;
import com.example.Integro.DTO.Entrada.Patient.PatientEntrance;
import com.example.Integro.DTO.Salida.Patient.PatientExit;
import com.example.Integro.Exceptions.BadRequestException;
import com.example.Integro.Exceptions.ResourceNotFoundException;


import java.util.List;

public interface IPatientService {
    List<PatientExit> listPatient();

    PatientExit searchPatientById(Long id);

    PatientExit registerPatient(PatientEntrance patientEntrance)  throws BadRequestException;

    PatientExit updatePatient(PatientModified patientModified) throws ResourceNotFoundException;

    void removePatient(Long id) throws ResourceNotFoundException;

}
