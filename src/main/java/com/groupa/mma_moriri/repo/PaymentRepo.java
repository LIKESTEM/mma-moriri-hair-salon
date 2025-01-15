package com.groupa.mma_moriri.repo;

import com.groupa.mma_moriri.model.Appointment;
import com.groupa.mma_moriri.model.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
    public Payment findByAppRefNo(String appRefNo);
    @Query("SELECT p FROM Payment p WHERE p.fanOffId = ?1")
    public List<Payment> getByFanOffId(Long fanOffId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Payment p WHERE p.appRefNo = ?1")
    public void deleteByAppRefNo(String appRefNo);
}
