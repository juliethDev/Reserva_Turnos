package com.example.Integro.Service;

import com.example.Integro.DTO.Entrada.Loggin.RoleEntrance;
import com.example.Integro.DTO.Entrada.Modified.Loggin.RoleModified;
import com.example.Integro.DTO.Salida.Loggin.RoleExit;
import com.example.Integro.Entity.Loggin.Role;
import com.example.Integro.Exceptions.BadRequestException;
import com.example.Integro.Exceptions.ResourceNotFoundException;

import java.util.List;

public interface IRoleService {
    List<RoleExit> listRole();

    RoleExit searchRoleById(Long id);

    RoleExit registerRole(RoleEntrance roleEntrance)  throws BadRequestException;

    RoleExit updateRole(RoleModified roleModified) throws ResourceNotFoundException;

    void removeRole(Long id) throws ResourceNotFoundException;


}
