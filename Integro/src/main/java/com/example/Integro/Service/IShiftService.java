package com.example.Integro.Service;

import com.example.Integro.DTO.Entrada.Modified.Shift.ShiftModified;
import com.example.Integro.DTO.Entrada.Shift.ShiftEntrance;
import com.example.Integro.DTO.Salida.Shift.ShiftOut;
import com.example.Integro.Exceptions.BadRequestException;
import com.example.Integro.Exceptions.ResourceNotFoundException;

import java.util.List;

public interface IShiftService {

    List<ShiftOut> listShift();

    ShiftOut searchShiftById(Long id);

    ShiftOut registerShift(ShiftEntrance turnoEntrada) throws BadRequestException;

    ShiftOut updateShift(ShiftModified turnoModificado) throws ResourceNotFoundException;

    void removeShift(Long id) throws ResourceNotFoundException;

 }
