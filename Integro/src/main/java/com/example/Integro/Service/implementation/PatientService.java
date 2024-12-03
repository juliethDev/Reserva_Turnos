package com.example.Integro.Service.implementation;

import com.example.Integro.DTO.Entrada.Modified.Patient.PatientModified;
import com.example.Integro.DTO.Entrada.Patient.PatientEntrance;
import com.example.Integro.DTO.Salida.Patient.PatientExit;
import com.example.Integro.Entity.Patient.Patient;
import com.example.Integro.Exceptions.ResourceNotFoundException;
import com.example.Integro.Repository.IPatientRepository;
import com.example.Integro.Service.IPatientService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements IPatientService {
    private final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);

    private final IPatientRepository patientRepository;

    private final ModelMapper modelMapper;

    public PatientService(IPatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public List<PatientExit> listPatient() {
        List<PatientExit> patients = patientRepository.findAll().stream()
                .map(this::outputDtoToEntity).toList();

        LOGGER.info("Listado de todos los pacientes:{}", patients);
        return patients;
    }

    @Override
    public PatientExit searchPatientById(Long id) {
        Patient patientSearch = patientRepository.findById(id).orElse(null);

        PatientExit patientExit = null;

        if (patientSearch != null) {
            patientExit = outputDtoToEntity(patientSearch);
            LOGGER.info("Patient encontrado:{}", patientExit);
        } else {
            LOGGER.error("El id no se encuentra registrado en la base de datos ");
        }
        return patientExit;
    }

    @Override
    public PatientExit registerPatient(PatientEntrance patientEntrance) {
        Patient patientSaved = patientRepository.save(inputDtoToEntity(patientEntrance));

        PatientExit patientExit = outputDtoToEntity(patientSaved);

        LOGGER.info("Patient guardado: {}", patientExit);
        return patientExit;
    }

    @Override
    public PatientExit updatePatient(PatientModified patientModified) throws ResourceNotFoundException {
        Patient patientReceived = modifiedDtoToEntity(patientModified);
        Patient patientAModify = patientRepository.findById(patientReceived.getId()).orElse(null);

        PatientExit patientExit = null;

        if (patientAModify != null) {
            patientAModify = patientReceived;

            patientRepository.save(patientAModify);

            patientExit = outputDtoToEntity(patientAModify);
            LOGGER.warn("Patient actualizado:{}", patientExit);
        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el paciente no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos ya que el paciente no se encuentra registrado");
        }

        return patientExit;
    }

    @Override
    public void removePatient(Long id) throws ResourceNotFoundException {
        if (searchPatientById(id) != null) {
            patientRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con id:{}", id);
        } else {
            LOGGER.error("No se ha encontrado el paciente con id{}", id);
            throw new ResourceNotFoundException("No se ha encontrado el paciente con id " + id);
        }
    }

    private void configureMapping() {
        // Mapeo explícito para `domicilio` entre PatientEntrance y Patient
        modelMapper.typeMap(PatientEntrance.class, Patient.class)
                .addMappings(mapper -> mapper.map(PatientEntrance::getAddressEntrance, Patient::setHome));

        // Mapeo explícito para `domicilio` entre Patient y PatientExit
        modelMapper.typeMap(Patient.class, PatientExit.class)
                .addMappings(mapper -> mapper.map(Patient::getHome, PatientExit::setAddressExit));

        // Mapeo explícito para `domicilio` entre PatientModified y Patient
        modelMapper.typeMap(PatientModified.class, Patient.class)
                .addMappings(mapper -> mapper.map(PatientModified::getAddressEntrance, Patient::setHome));
    }


    // Método para convertir PatientEntrance a Patient (DTO a Entidad)
    private Patient inputDtoToEntity(PatientEntrance patientEntrance) {
        return modelMapper.map(patientEntrance, Patient.class);
    }

    // Método para convertir Patient a PatientExit (Entidad a DTO)
    private PatientExit outputDtoToEntity(Patient patient) {
        return modelMapper.map(patient, PatientExit.class);
    }

    // Método para convertir PatientModified a Patient (DTO a Entidad)
    private Patient modifiedDtoToEntity(PatientModified patientModified) {
        return modelMapper.map(patientModified, Patient.class);
    }
}
