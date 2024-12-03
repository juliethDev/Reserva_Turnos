package com.example.Integro.Service.implementation;

import com.example.Integro.DTO.Entrada.Modified.Shift.ShiftModified;
import com.example.Integro.DTO.Entrada.Shift.ShiftEntrance;
import com.example.Integro.DTO.Salida.Dentist.DentistExit;
import com.example.Integro.DTO.Salida.Patient.PatientExit;
import com.example.Integro.DTO.Salida.Shift.DentistShiftOut;
import com.example.Integro.DTO.Salida.Shift.PatientShiftOut;
import com.example.Integro.DTO.Salida.Shift.ShiftOut;
import com.example.Integro.Entity.Dentist.Dentist;
import com.example.Integro.Entity.Patient.Patient;
import com.example.Integro.Entity.Shift.Shift;
import com.example.Integro.Exceptions.BadRequestException;
import com.example.Integro.Exceptions.ResourceNotFoundException;
import com.example.Integro.Repository.IShiftRepository;
import com.example.Integro.Service.IShiftService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftService implements IShiftService {

    private final Logger LOGGER = LoggerFactory.getLogger(ShiftService.class);

    private final ModelMapper modelMapper;
    private final IShiftRepository shiftRepository;
    private  final DentistService dentistService;

    private final PatientService patientService;

    public ShiftService(ModelMapper modelMapper, IShiftRepository shiftRepository, DentistService dentistService, PatientService patientService) {
        this.modelMapper = modelMapper;
        this.shiftRepository = shiftRepository;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }

    @Override
    public List<ShiftOut> listShift() {

        List<ShiftOut> shifts = shiftRepository.findAll().stream()
                .map(this::entityEntry ).toList();

        LOGGER.info("Listado de todos los pacientes:{}",shifts);

        return shifts;
    }

    @Override
    public ShiftOut searchShiftById(Long id) {
        Shift shiftSearchById = shiftRepository.findById(id).orElse(null);

        ShiftOut shiftExit = null;
        if (shiftSearchById != null){

            shiftExit = entityEntry(shiftSearchById);
            LOGGER.info("Shift encontrado:{}",shiftExit);
        }else{
            LOGGER.error("El id no se encuentra registrado en la base de datos ");

        }
        return shiftExit;
    }

    @Override
    public ShiftOut registerShift(ShiftEntrance shiftEntrance) throws BadRequestException {

        ShiftOut shiftExit = null;

        PatientExit patientExit = patientService.searchPatientById(shiftEntrance.getPatientId());

        DentistExit dentistExit = dentistService.searchDentistById(shiftEntrance.getDentistId());


        String patientNoEnBdd = "El paciente no se encuentra en nuestra base de datos";
        String dentistNoEnBdd = "El odontologo no se encuentra en nuestra base de datos";


        if (patientExit == null || dentistExit == null) {
            if (patientExit == null && dentistExit == null) {
                LOGGER.error("El paciente y el odontologo no se encuentran en nuestra base de datos");
                throw new BadRequestException("El paciente y el odontologo no se encuentran en nuestra base de datos");
            } else if (patientExit == null) {
                LOGGER.error(patientNoEnBdd);
                throw new BadRequestException(patientNoEnBdd);
            } else {
                LOGGER.error(dentistNoEnBdd);
                throw new BadRequestException(dentistNoEnBdd);
            }
        }else {
            Shift shiftNew = shiftRepository.save(modelMapper.map(shiftEntrance, Shift.class));

            shiftExit = entityEntry(shiftNew);

        }

        return shiftExit;
    }

    @Override
    public ShiftOut updateShift(ShiftModified shiftModified) throws ResourceNotFoundException {

        Shift shiftAModify = shiftRepository.findById(shiftModified.getId()).orElse(null);
        ShiftOut shiftExit = null;

        if (shiftAModify !=null){
            shiftAModify.setPatient(modelMapper.map(patientService.searchPatientById(shiftModified.getPatientId()), Patient.class));
            shiftAModify.setDentist(modelMapper.map(dentistService.searchDentistById(shiftModified.getDentistId()), Dentist.class));
            shiftAModify.setDateAndTime(shiftModified.getDateAndTime());

            shiftRepository.save(shiftAModify);

            shiftExit = entityEntry(shiftAModify);
            LOGGER.warn("Patient actualizado:{}",shiftExit);
        }else{
            LOGGER.error("No fue posible actualizar los datos ya que el paciente no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos ya que el paciente no se encuentra registrado");

        }


        return shiftExit;
    }

    @Override
    public void removeShift(Long id) throws ResourceNotFoundException {

        if (searchShiftById(id) != null){
            shiftRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el Shift con id:{}",id);
        }else{
            LOGGER.error("no se ha encontrado el Shift con id{}",id);
            throw new ResourceNotFoundException("No se ha encontrado el Shift con id " + id);

        }

    }


    private PatientShiftOut patientShiftExitATShiftExit(Long id){
        return modelMapper.map(patientService.searchPatientById(id), PatientShiftOut.class);
    }

    private DentistShiftOut dentistShiftExitATShiftExit(Long id){
        return modelMapper.map(dentistService.searchDentistById(id), DentistShiftOut.class);
    }

    private ShiftOut entityEntry(Shift shift){
        ShiftOut shiftExit = modelMapper.map(shift, ShiftOut.class);
            shiftExit.setPatientShiftOut(patientShiftExitATShiftExit(shift.getPatient().getId()));
            shiftExit.setDentistShiftOut(dentistShiftExitATShiftExit(shift.getDentist().getId()));

        return shiftExit;
    }

}
