package com.example.Integro.Service.implementation;

import com.example.Integro.DTO.Entrada.Dentist.DentistEntrance;
import com.example.Integro.DTO.Entrada.Modified.Dentist.DentistModified;
import com.example.Integro.DTO.Salida.Dentist.DentistExit;
import com.example.Integro.Entity.Dentist.Dentist;
import com.example.Integro.Exceptions.BadRequestException;
import com.example.Integro.Exceptions.ResourceNotFoundException;
import com.example.Integro.Repository.IDentistRepository;
import com.example.Integro.Service.IDentistService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistService implements IDentistService {


    private final Logger LOGGER = LoggerFactory.getLogger(DentistService.class);
    private final IDentistRepository dentistRepository;
    private final ModelMapper modelMapper;


    public DentistService(IDentistRepository dentistRepository, ModelMapper modelMapper) {
        this.dentistRepository = dentistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DentistExit> listDentists() {
        List<DentistExit> dentists = dentistRepository.findAll().stream()
                .map(this::outputDtoToEntity).toList();

        LOGGER.info("Listado de todos los odontologos: {}", dentists);

        return dentists;
    }

    @Override
    public DentistExit registerDentist(DentistEntrance dentistEntrance) throws BadRequestException {

       Dentist dentistSaved = dentistRepository.save(inputDtoToEntity(dentistEntrance));
       DentistExit dentistExit = outputDtoToEntity(dentistSaved);

       LOGGER.info("Dentist guardado: {}", dentistExit);
       return dentistExit;
    }

    @Override
    public DentistExit searchDentistById(Long id) {
        Dentist dentistsearch = dentistRepository.findById(id).orElse(null);

        DentistExit dentistExit = null;

        if ( dentistsearch!= null) {
            dentistExit = outputDtoToEntity(dentistsearch);
            LOGGER.info("Dentist encontrado: {}", dentistExit);
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return dentistExit;
    }


    @Override
    public void removeDentist(Long id) throws ResourceNotFoundException {
        if (searchDentistById(id) != null) {
            dentistRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el odontologo con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el odontologo con id " + id);
        }
    }

    @Override
    public DentistExit updateDentist(DentistModified dentistModified) throws ResourceNotFoundException {
        Dentist dentistReceived = modifiedDtoToEntity(dentistModified);
        Dentist dentistAUpdate = dentistRepository.findById(dentistModified.getId()).orElse(null);
        DentistExit dentistExitDiscount = null;

        if (dentistAUpdate != null) {

            dentistAUpdate = dentistReceived;
            dentistRepository.save(dentistAUpdate);

            dentistExitDiscount = outputDtoToEntity(dentistAUpdate);

            LOGGER.warn("Dentist actualizado: {}", dentistExitDiscount);

        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el odontologo no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos ya que el odontologo no se encuentra registrado");
        }


        return dentistExitDiscount;
    }

    private Dentist inputDtoToEntity (DentistEntrance dentistEntrance){
        return modelMapper.map(dentistEntrance, Dentist.class);
    }

    private DentistExit outputDtoToEntity(Dentist dentist){
        return modelMapper.map(dentist, DentistExit.class);
    }

    private Dentist modifiedDtoToEntity(DentistModified dentistModified){
        return modelMapper.map(dentistModified, Dentist.class);
    }


}
