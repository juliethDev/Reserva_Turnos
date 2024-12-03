package com.example.Integro.Service.implementation;


import com.example.Integro.DTO.Entrada.Loggin.RoleEntrance;
import com.example.Integro.DTO.Entrada.Modified.Loggin.RoleModified;
import com.example.Integro.DTO.Salida.Loggin.RoleExit;
import com.example.Integro.Entity.Loggin.Role;
import com.example.Integro.Exceptions.BadRequestException;
import com.example.Integro.Exceptions.ResourceNotFoundException;
import com.example.Integro.Repository.IRoleRepository;
import com.example.Integro.Service.IRoleService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService implements IRoleService {

    private final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

    private final IRoleRepository roleRepository;

    private final ModelMapper modelMapper;


    public RoleService(IRoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public List<RoleExit> listRole() {
        List<RoleExit> roles = roleRepository.findAll().stream()
                .map(this::outputDtoToEntity).toList();

        LOGGER.info("Listado de todos los roles: {}", roles);

        return roles;
    }

    @Override
    public RoleExit searchRoleById(Long id) {

        Role rolesearch = roleRepository.findById(id).orElse(null);

        RoleExit roleExit = null;

        if (roleExit != null){
            roleExit = outputDtoToEntity(rolesearch);
            LOGGER.info("Roles encontrados:{}",roleExit);
        }else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return roleExit;
    }

    @Override
    public RoleExit registerRole(RoleEntrance roleEntrance) throws BadRequestException {

        Role roleSaved = roleRepository.save(inputDtoToEntity(roleEntrance));
        RoleExit roleExit = outputDtoToEntity(roleSaved);

        LOGGER.info("role guardado: {}", roleExit);
        return roleExit;
    }

    @Override
    public RoleExit updateRole(RoleModified roleModified) throws ResourceNotFoundException {

        Role roleReceived = modifiedDtoToEntity(roleModified);
        Role roleAUpdate  = roleRepository.findById(roleModified.getId()).orElse(null);
        RoleExit roleExitDiscount = null;

        if (roleAUpdate != null){

            roleAUpdate = roleReceived;
            roleRepository.save(roleAUpdate);

            roleExitDiscount = outputDtoToEntity(roleAUpdate);

            LOGGER.warn("role actualizado: {}", roleExitDiscount);

        }else{
            LOGGER.error("No fue posible actualizar los datos ya que el rol no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos ya que el rol no se encuentra registrado");

        }

        return roleExitDiscount;
    }

    @Override
    public void removeRole(Long id) throws ResourceNotFoundException {

        if (searchRoleById(id) != null) {
            roleRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el rol con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el rol con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el rol con id " + id);
        }

    }

    private Role inputDtoToEntity (RoleEntrance roleEntrance){
        return modelMapper.map(roleEntrance, Role.class);
    }

    private RoleExit outputDtoToEntity(Role role) {
        return modelMapper.map(role,RoleExit.class);
    }

    private Role modifiedDtoToEntity(RoleModified roleModified){
        return modelMapper.map(roleModified, Role.class);
    }

}

