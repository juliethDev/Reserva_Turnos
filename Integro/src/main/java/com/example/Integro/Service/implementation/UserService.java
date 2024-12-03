package com.example.Integro.Service.implementation;

import com.example.Integro.DTO.Entrada.Dentist.DentistEntrance;
import com.example.Integro.DTO.Entrada.Loggin.UserEntrance;
import com.example.Integro.DTO.Entrada.Modified.Dentist.DentistModified;
import com.example.Integro.DTO.Entrada.Modified.Loggin.UserModified;
import com.example.Integro.DTO.Salida.Dentist.DentistExit;
import com.example.Integro.DTO.Salida.Loggin.UserExit;
import com.example.Integro.Entity.Dentist.Dentist;
import com.example.Integro.Entity.Loggin.User;
import com.example.Integro.Exceptions.BadRequestException;
import com.example.Integro.Exceptions.ResourceNotFoundException;
import com.example.Integro.Repository.IDentistRepository;
import com.example.Integro.Repository.IUserRepository;
import com.example.Integro.Service.IUserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(IUserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserExit> listUser() {

        List<UserExit> users = userRepository.findAll().stream()
                .map(this::outputDtoToEntity).toList();

        LOGGER.info("Listado de todos los usuarios: {}", users);

        return users;
    }

    @Override
    public UserExit searchUserById(Long id) {
        User usersearch = userRepository.findById(id).orElse(null);

        UserExit userExit = null;

        if ( usersearch != null) {
            userExit = outputDtoToEntity(usersearch);
            LOGGER.info("Dentist encontrado: {}", userExit);
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return userExit;
    }

    @Override
    public UserExit registerUser(UserEntrance userEntrance) throws BadRequestException {

        User userSaved = userRepository.save(inputDtoToEntity(userEntrance));
        UserExit userExit = outputDtoToEntity(userSaved);

        LOGGER.info("Usuario guardado: {}", userExit);

        return userExit;

    }

    @Override
    public UserExit updateUser(UserModified userModified) throws ResourceNotFoundException {

        User userReceived = modifiedDtoToEntity(userModified);
        User userAUpdate = userRepository.findById(userModified.getId()).orElse(null);
        UserExit userExitDiscount = null;

        if (userAUpdate != null) {

            userAUpdate = userReceived;
            userRepository.save(userAUpdate);

            userExitDiscount = outputDtoToEntity(userAUpdate);

            LOGGER.warn("Usuario actualizado: {}", userExitDiscount);

        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el usuario no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos ya que el usuario no se encuentra registrado");
        }


        return userExitDiscount;

    }

    @Override
    public void removeUser(Long id) throws ResourceNotFoundException {

        if (searchUserById(id) != null) {
            userRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el usuario con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el usuario con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el usuario con id " + id);
        }

    }

    private User inputDtoToEntity (UserEntrance userEntrance){
        return modelMapper.map(userEntrance, User.class);
    }

    private UserExit outputDtoToEntity(User user){
        return modelMapper.map(user, UserExit.class);
    }

    private User modifiedDtoToEntity(UserModified userModified){
        return modelMapper.map(userModified, User.class);
    }


}
