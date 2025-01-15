package com.groupa.mma_moriri.service;

import com.groupa.mma_moriri.model.Appointment;
import com.groupa.mma_moriri.repo.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepo repo;

    public boolean addAppointment(Appointment appointment) {
        repo.save(appointment);
        return true;
    }

    public List<Appointment> getAllAppointments() {
        return repo.findAll();
    }

    public List<Appointment> getAllAppointmentsInTheCurrentDate() {
        List<Appointment> allAppointments = repo.findAll();
        List<Appointment> currApps = new ArrayList<>();

        Appointment app = allAppointments.getLast();
        Date date = app.getDateTime();

        for(int count = 0; count < allAppointments.size(); count++) {
            if(date == allAppointments.get(count).getDateTime()) {
                currApps.add(allAppointments.get(count));
            }
        }

        return currApps;
    }

    public boolean reschedule(Appointment app) {
        boolean isSaved = false;

        if(repo.save(app) != null) {
            isSaved = true;
        }

        return isSaved;
    }

    public boolean cancel(String ref_no) {
        boolean isCancelled = false;
        Optional<Appointment> testApp = repo.findById(ref_no);
        Appointment appointment = testApp.get();

        if(appointment != null) {
            repo.delete(appointment);
            isCancelled = true;
        }

        return isCancelled;
    }

    public List<Appointment> getByCustomerId(Long cust_id) {
        return repo.findByCustId(cust_id);
    }

    public List<Appointment> getByStylistId(Long stylist_id) {
        return repo.getByStylistId(stylist_id);
    }

    public Optional<Appointment> getByRefNo(String refNo) {
        return repo.findById(refNo);
    }

    public Integer getStylistCount(Long stylist_id) {
        List<Appointment> allAppointments = repo.findAll();
        Integer numOfWorked = 0;


        LocalDateTime currDate = LocalDateTime.now();
        int currDay = currDate.getDayOfMonth();
        int currMonth = currDate.getMonthValue();
        int currYear = currDate.getYear();

        for(int count = 0; count < allAppointments.size(); count++) {
            Appointment app = allAppointments.get(count);
            Date date = app.getDateTime();

            int dbDay = date.getDay();
            int dbMonth = date.getMonth();
            int dbYear = date.getYear();

            boolean isValid = false;

            if(currDay == dbDay && currMonth == dbMonth && currYear == dbYear) {
                isValid = true;
            }

            if(allAppointments.get(count).getStyleId() == Math.toIntExact(stylist_id) && isValid) {
                numOfWorked += 1;
            }

        }

        return numOfWorked;
    }

    public void makeIdNullById(Long custId) {
        List<Appointment> apps = (List<Appointment>) repo.findByCustId(custId);

        for(Appointment app: apps) {
            app.setCustId(null);
            repo.save(app);
        }
    }

    public void makeIdNullByStylistId(Long stylistId) {
        List<Appointment> apps = repo.getByStylistId(stylistId);

        for(Appointment app: apps) {
            app.setStylistId(null);
            repo.save(app);
        }
    }
}
