package com.example.Integro.Controller.Patient;

import com.example.Integro.DTO.Entrada.Modified.Patient.PatientModified;
import com.example.Integro.DTO.Entrada.Patient.PatientEntrance;
import com.example.Integro.DTO.Salida.Patient.PatientExit;

import com.example.Integro.Exceptions.ResourceNotFoundException;
import com.example.Integro.Service.implementation.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paciente")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    //POST
  @Operation(summary = "Registrar nuevo paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Paciente registrado con exito",
                content = {@Content (mediaType = "application/json",
                    schema = @Schema(implementation = PatientExit.class ))}),
            @ApiResponse(responseCode = "400",description = "Bad request",
                content = @Content),
            @ApiResponse(responseCode = "500",description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/registrar")
    public ResponseEntity<PatientExit> registerPatient(@Valid @RequestBody PatientEntrance patientEntrance){
       return new ResponseEntity<>(patientService.registerPatient(patientEntrance), HttpStatus.CREATED);
  }


 // GETT listar todos los pacientes
    @Operation(summary = "Listado de todos los paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " Listado de los pacientes",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PatientExit.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                content = @Content),
            @ApiResponse(responseCode = "500",description = "Server Error",
                content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<PatientExit>> listAll(){
        return new ResponseEntity<>(patientService.listPatient(),HttpStatus.OK);
    }


  // GETT Buscar Patient Por ID
    @Operation(summary = "Buscar paciente por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Paciente obtenido correctamente",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PatientExit.class))}),
            @ApiResponse(responseCode = "400",description = "Id invalido",
                content = @Content),
            @ApiResponse(responseCode = "404",description = "Paciente No encontrado",
                content = @Content),
            @ApiResponse(responseCode = "500",description = "Server error",
                content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PatientExit> searchPatientById(@PathVariable Long id){
        return new ResponseEntity<>(patientService.searchPatientById(id),HttpStatus.OK);
    }

    //PUT
    @Operation(summary = "Actualizar Paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente actualizado Correctamente",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PatientExit.class))}),
            @ApiResponse(responseCode = "400",description = "Bad Request",
                content = @Content),
            @ApiResponse(responseCode = "404", description = "Paciente No encnontrado",
                content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error",
                content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar")
    public ResponseEntity<PatientExit> updatePatient(@Valid @RequestBody PatientModified patientModified) throws ResourceNotFoundException {
        return new ResponseEntity<>(patientService.updatePatient(patientModified),HttpStatus.OK);
    }


    //DELETE
    @Operation(summary = "Eliminacion De Un Paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente eliminado correctamente",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PatientExit.class))}),
            @ApiResponse(responseCode = "400", description = "Id invalido",
                content = @Content),
            @ApiResponse(responseCode = "404",description = "Paciente no encontrado",
                content = @Content),
            @ApiResponse(responseCode = "500",description = "Server Error",
                content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?>deletePatient(@PathVariable Long id) throws ResourceNotFoundException{
        patientService.removePatient(id);
        return new ResponseEntity<>("Paciente eliminado correctamente",HttpStatus.NO_CONTENT);
    }

}
