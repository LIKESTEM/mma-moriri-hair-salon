package com.groupa.mma_moriri.repo;

import com.groupa.mma_moriri.model.ConfirmedAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmedAppointmentRepo extends JpaRepository<ConfirmedAppointment, Long> {
    public ConfirmedAppointment findByAppointId(String appointId);
}
