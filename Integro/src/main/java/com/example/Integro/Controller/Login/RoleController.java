package com.example.Integro.Controller.Login;

import com.example.Integro.DTO.Entrada.Dentist.DentistEntrance;
import com.example.Integro.DTO.Entrada.Loggin.RoleEntrance;
import com.example.Integro.DTO.Entrada.Modified.Dentist.DentistModified;
import com.example.Integro.DTO.Entrada.Modified.Loggin.RoleModified;
import com.example.Integro.DTO.Salida.Dentist.DentistExit;
import com.example.Integro.DTO.Salida.Loggin.RoleExit;
import com.example.Integro.DTO.Salida.Loggin.UserExit;
import com.example.Integro.Exceptions.BadRequestException;
import com.example.Integro.Exceptions.ResourceNotFoundException;
import com.example.Integro.Service.implementation.RoleService;
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
@RequestMapping("/api/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    //POST
    @Operation(summary = "Registro de un nuevo rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rol guardado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleExit.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registrar")
    public ResponseEntity<RoleExit> registerRole(@Valid @RequestBody RoleEntrance roleEntrance) throws BadRequestException {
        return new ResponseEntity<>(roleService.registerRole(roleEntrance), HttpStatus.CREATED);
    }


    //PUT
    @Operation(summary = "Actualización de un rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role actualizado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserExit.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Role no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar")
    public ResponseEntity<RoleExit> updateRole(@Valid @RequestBody RoleModified roleModified) throws ResourceNotFoundException {
        return new ResponseEntity<>(roleService.updateRole(roleModified), HttpStatus.OK);
    }


    //GET
    @Operation(summary = "Búsqueda de un Role por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleExit.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Role no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<RoleExit> getRoleById(@PathVariable Long id) {
        return new ResponseEntity<>(roleService.searchRoleById(id), HttpStatus.OK);
    }


    @Operation(summary = "Listado de todos los Roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de Roles obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleExit.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<RoleExit>> listRole() {
        return new ResponseEntity<>(roleService.listRole(), HttpStatus.OK);
    }


    //DELETE
    @Operation(summary = "Eliminación de un rol por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Rol eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> removeRole(@PathVariable Long id) throws ResourceNotFoundException {
        roleService.removeRole(id);
        return new ResponseEntity<>("Role eliminado correctamente", HttpStatus.NO_CONTENT);
    }


}
