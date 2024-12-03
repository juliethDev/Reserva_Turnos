package com.example.Integro.Controller.Shift;

import com.example.Integro.DTO.Entrada.Modified.Shift.ShiftModified;
import com.example.Integro.DTO.Entrada.Shift.ShiftEntrance;
import com.example.Integro.DTO.Salida.Shift.ShiftOut;
import com.example.Integro.Exceptions.BadRequestException;
import com.example.Integro.Exceptions.ResourceNotFoundException;
import com.example.Integro.Service.implementation.ShiftService;
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

@RequestMapping("api/turno")
public class ShiftController {

    private ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    //Post

    @Operation(summary = "Registrar nuevo turno")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Turno registrado satisfactoriamente",
                    content = {@Content(mediaType = "application/json",
                        schema = @Schema(implementation = ShiftOut.class))}),
                @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
                @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
        })
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/registrar")
    public ResponseEntity<ShiftOut> registerShift(@Valid @RequestBody ShiftEntrance shiftEntrance) throws BadRequestException {
        return new ResponseEntity<>(shiftService.registerShift(shiftEntrance), HttpStatus.CREATED);
    }


    // Get listar todos los turnos
    @Operation(summary = "Listar todos los Turnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Listado de Turnos",
                content = {@Content (mediaType = "application/json",
                    schema = @Schema(implementation = ShiftOut.class))}),
            @ApiResponse(responseCode = "400",description = "Bad request",
                content = @Content),
            @ApiResponse(responseCode = "500",description = "Server error",
                content = @Content)
    })
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<ShiftOut>> listAll(){
        return new ResponseEntity<>(shiftService.listShift(),HttpStatus.OK);
    }

    // GETT
    @Operation(summary = "Buscar turno por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description="Turno obtenido con exito",
                content = {@Content (mediaType = "application/json",
                        schema = @Schema(implementation = ShiftOut.class))}),
            @ApiResponse(responseCode = "400",description = "Id invalido",
                    content = @Content),
            @ApiResponse(responseCode = "404",description = "Turno No encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500",description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ShiftOut> searchShiftById(@PathVariable Long id){
        return new ResponseEntity<>(shiftService.searchShiftById(id),HttpStatus.OK);
    }


    // PUT
    @Operation(summary = "Actualizar Turno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Turno actualizado correctamente",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ShiftOut.class))}),
            @ApiResponse(responseCode = "400", description = "Id invalido",
                content = @Content),
            @ApiResponse(responseCode = "404",description = "Turno No encontrado",
                content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error",
                content = @Content)
    })
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/actualizar")
    public ResponseEntity<ShiftOut> updateShift(@Valid @RequestBody ShiftModified shiftModified) throws ResourceNotFoundException {
        return new ResponseEntity<>(shiftService.updateShift(shiftModified),HttpStatus.OK);
    }

    //DELETE
    @Operation(summary = "Eliminacion de un Turno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Turno Eliminado correctamente",
                content = {@Content (mediaType ="application/json",
                    schema = @Schema(implementation = ShiftOut.class))}),
            @ApiResponse(responseCode = "400", description = "Id invalido",
                content = @Content),
            @ApiResponse(responseCode = "404",description = "Turno no encontrado",
                content = @Content),
            @ApiResponse(responseCode = "500",description = "Server Error",
                content = @Content)
    })
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?>removeShift(@PathVariable Long id) throws ResourceNotFoundException{
        shiftService.removeShift(id);
        return new ResponseEntity<>("Turno eliminado de forma correcta", HttpStatus.NO_CONTENT);
    }
}
