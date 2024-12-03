package com.example.Integro.Repository;

import com.example.Integro.Entity.Loggin.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {

}