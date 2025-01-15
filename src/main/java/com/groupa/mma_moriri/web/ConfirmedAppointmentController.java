package com.groupa.mma_moriri.web;

import com.groupa.mma_moriri.model.*;
import com.groupa.mma_moriri.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
public class ConfirmedAppointmentController {
    @Autowired
    private ConfirmedAppointmentService service;
    @Autowired
    private AdminService adminService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    CustomerService customerService;
    @PostMapping("AddConfirmedApp")
    public String addConfirmedApp(@RequestParam("appRefNo") String appRefNo,
                                  @RequestParam("username") String username,
                                  @RequestParam("status") boolean status, HttpSession session) {

        Timestamp dateTime = new Timestamp(System.currentTimeMillis());
        Payment payment = paymentService.findByRefNo(appRefNo);
        Admin admin = adminService.getAdminByUsername(username);

        if (admin == null) {
          session.setAttribute("msg","No admin details found!");
            return "success";
        }

        if (payment == null) {
            session.setAttribute("msg","No payment details found!");
            return "success";
        }

        List<ConfirmedAppointment> confirmedApps = service.getAllConfirmedAppointments();

        for (ConfirmedAppointment cn : confirmedApps) {
            if (cn.getAppointId().equals(appRefNo)) {
                session.setAttribute("msg","Appointment has already been confirmed!");
                return "success";
            }
        }

        ConfirmedAppointment confirmedApp = new ConfirmedAppointment(appRefNo,
                Math.toIntExact(admin.getAdmin_id()), dateTime, status);
        service.addConfirmedApp(confirmedApp);
        session.setAttribute("msg","The appointment has been confirmed successfully.");
        return "success";
    }



    @GetMapping("GetOneConfirmedAppointment")
    public ConfirmedAppointment getOneConfirmedAppointment(@PathVariable("appointId") String appointId) {
        return service.getOneConfirmedAppointment(appointId);
    }

    @GetMapping("GetAllConfirmedAppointments")
    public List<ConfirmedAppointment> getAllConfirmedAppointments() {
        return service.getAllConfirmedAppointments();
    }

    @GetMapping("GetClientsConfirmedAppointments")
    public String getClientsConfirmedAppointments(HttpSession session) {
        String username = (String) session.getAttribute("username");

        if (username != null) {
            Optional<Customer> customer = Optional
                    .ofNullable(customerService.getCustomerByUsername(username));

            List<Appointment> confirmed = appointmentService.getByCustomerId(customer
                    .get().getCust_id());
            session.setAttribute("confirmed", confirmed);
        }
        return "clientsConfirmed";
    }

}
