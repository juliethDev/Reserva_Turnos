package com.example.Integro.Repository;

import com.example.Integro.Entity.Shift.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShiftRepository extends JpaRepository<Shift,Long> {
}
