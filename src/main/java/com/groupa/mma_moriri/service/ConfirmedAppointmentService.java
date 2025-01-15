package com.groupa.mma_moriri.service;

import com.groupa.mma_moriri.model.ConfirmedAppointment;
import com.groupa.mma_moriri.repo.ConfirmedAppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfirmedAppointmentService {
    @Autowired
    private ConfirmedAppointmentRepo repo;

    public boolean addConfirmedApp(ConfirmedAppointment confirmedApp) {
        repo.save(confirmedApp);
        return true;
    }

    public List<ConfirmedAppointment> getAllConfirmedAppointments() {
        return repo.findAll();
    }
    public List<ConfirmedAppointment> getConfirmedByUsername(String username) {
        return repo.findAll();
    }
    public ConfirmedAppointment getOneConfirmedAppointment(String appointId) {
        return repo.findByAppointId(appointId);
    }

}
