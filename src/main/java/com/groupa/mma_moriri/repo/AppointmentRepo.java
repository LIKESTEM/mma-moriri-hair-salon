package com.groupa.mma_moriri.repo;

import com.groupa.mma_moriri.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.lang.Integer;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, String> {
    public List<Appointment> findByCustId(Long custId);
    @Query("SELECT a FROM Appointment a WHERE a.stylistId = ?1")
    public List<Appointment> getByStylistId(Long stylistId);

}
