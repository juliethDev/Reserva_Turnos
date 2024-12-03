package com.example.Integro.Service.implementation;

import com.example.Integro.Entity.Loggin.User;
import com.example.Integro.Repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserDetailService implements UserDetailsService{

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(" Nombre de usuario no existe"));

        Set<SimpleGrantedAuthority> autorizacion = user.getRol().stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getusername(),
                user.getpassword(),
                autorizacion
        );
    }
}
