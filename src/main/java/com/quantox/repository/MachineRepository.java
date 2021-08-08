package com.quantox.repository;

import com.quantox.data.Machine;
import com.quantox.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine,Long> {

    @Query("Select m from Machine m where m.uid = :machineId")
    Machine getByUid(@Param("machineId") String machineId);

    @Query("Select m from Machine m where m.active = true")
    List<Machine> searchActiveMachine();
}
