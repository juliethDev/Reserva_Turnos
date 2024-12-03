package com.example.Integro.Service;

import com.example.Integro.DTO.Entrada.Dentist.DentistEntrance;
import com.example.Integro.DTO.Entrada.Modified.Dentist.DentistModified;
import com.example.Integro.DTO.Salida.Dentist.DentistExit;
import com.example.Integro.Exceptions.BadRequestException;
import com.example.Integro.Exceptions.ResourceNotFoundException;

import java.util.List;

public interface IDentistService {

    List<DentistExit> listDentists();

    DentistExit registerDentist(DentistEntrance dentistEntrance) throws BadRequestException;

    DentistExit searchDentistById(Long id);

    void removeDentist(Long id) throws ResourceNotFoundException;

    DentistExit updateDentist(DentistModified dentistModified) throws ResourceNotFoundException;


}
