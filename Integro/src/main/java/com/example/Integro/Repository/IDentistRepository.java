package com.example.Integro.Repository;

import com.example.Integro.Entity.Dentist.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDentistRepository extends JpaRepository<Dentist,Long> {
}
