package com.example.Integro.Controller.Login;


import com.example.Integro.DTO.Entrada.Loggin.UserEntrance;
import com.example.Integro.DTO.Entrada.Modified.Loggin.UserModified;
import com.example.Integro.DTO.Salida.Loggin.UserExit;
import com.example.Integro.Exceptions.BadRequestException;
import com.example.Integro.Exceptions.ResourceNotFoundException;
import com.example.Integro.Service.implementation.UserService;
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
@RequestMapping("/api/usuarios")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //POST
    @Operation(summary = "Registro de un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario guardado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserExit.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registrar")
    public ResponseEntity<UserExit> registerUser(@Valid @RequestBody UserEntrance userEntrance) throws BadRequestException {
        return new ResponseEntity<>(userService.registerUser(userEntrance), HttpStatus.CREATED);
    }


    //PUT
    @Operation(summary = "Actualización de un Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserExit.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar")
    public ResponseEntity<UserExit> updateUser(@Valid @RequestBody UserModified userModified) throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.updateUser(userModified), HttpStatus.OK);
    }


    //GET
    @Operation(summary = "Búsqueda de un Usuario por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserExit.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserExit> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.searchUserById(id), HttpStatus.OK);
    }


    @Operation(summary = "Listado de todos los Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de Usuario obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserExit.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<UserExit>> listUsers() {
        return new ResponseEntity<>(userService.listUser(), HttpStatus.OK);
    }


    //DELETE
    @Operation(summary = "Eliminación de un Usuario por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> removeUser(@PathVariable Long id) throws ResourceNotFoundException {
        userService.removeUser(id);
        return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.NO_CONTENT);
    }


}
