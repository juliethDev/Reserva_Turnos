package com.example.Integro.Service;

import com.example.Integro.DTO.Entrada.Loggin.UserEntrance;
import com.example.Integro.DTO.Entrada.Modified.Loggin.UserModified;
import com.example.Integro.DTO.Entrada.Modified.Patient.PatientModified;
import com.example.Integro.DTO.Entrada.Patient.PatientEntrance;
import com.example.Integro.DTO.Salida.Loggin.UserExit;
import com.example.Integro.DTO.Salida.Patient.PatientExit;
import com.example.Integro.Exceptions.BadRequestException;
import com.example.Integro.Exceptions.ResourceNotFoundException;

import java.util.List;

public interface IUserService {

    List<UserExit> listUser();

    UserExit searchUserById(Long id);

    UserExit registerUser(UserEntrance userEntrance)  throws BadRequestException;

    UserExit updateUser(UserModified userModified) throws ResourceNotFoundException;

    void removeUser(Long id) throws ResourceNotFoundException;

}
