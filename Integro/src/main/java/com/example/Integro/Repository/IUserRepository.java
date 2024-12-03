package com.example.Integro.Repository;

import com.example.Integro.Entity.Loggin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

}
