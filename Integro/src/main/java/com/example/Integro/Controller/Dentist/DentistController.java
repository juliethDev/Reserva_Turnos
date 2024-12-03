package com.example.Integro.Controller.Dentist;

import com.example.Integro.DTO.Entrada.Dentist.DentistEntrance;
import com.example.Integro.DTO.Entrada.Modified.Dentist.DentistModified;
import com.example.Integro.DTO.Salida.Dentist.DentistExit;
import com.example.Integro.Exceptions.BadRequestException;
import com.example.Integro.Exceptions.ResourceNotFoundException;
import com.example.Integro.Service.implementation.DentistService;
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
@RequestMapping("/api/odontologos")
public class DentistController {

    private DentistService dentistService;

    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }


    //POST
    @Operation(summary = "Registro de un nuevo odontólogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Odontólogo guardado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DentistExit.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registrar")
    public ResponseEntity<DentistExit> registerDentist(@Valid @RequestBody DentistEntrance dentistEntrance) throws BadRequestException {
        return new ResponseEntity<>(dentistService.registerDentist(dentistEntrance), HttpStatus.CREATED);
    }

    //PUT
    @Operation(summary = "Actualización de un Odontólogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo actualizado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DentistExit.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar")
    public ResponseEntity<DentistExit> updateDentist(@Valid @RequestBody DentistModified dentistModified) throws ResourceNotFoundException {
        return new ResponseEntity<>(dentistService.updateDentist(dentistModified), HttpStatus.OK);
    }

    //GET
    @Operation(summary = "Búsqueda de un odontólogo por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DentistExit.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<DentistExit> getDentistById(@PathVariable Long id) {
        return new ResponseEntity<>(dentistService.searchDentistById(id), HttpStatus.OK);
    }


    @Operation(summary = "Listado de todos los odontólogos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de odontólogos obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DentistExit.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<DentistExit>> listDentists() {
        return new ResponseEntity<>(dentistService.listDentists(), HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminación de un Odontólogo por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Odontólogo eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> removeDentist(@PathVariable Long id) throws ResourceNotFoundException {
        dentistService.removeDentist(id);
        return new ResponseEntity<>("Odontólogo eliminado correctamente", HttpStatus.NO_CONTENT);
    }

}
