package com.groupa.mma_moriri.web;

import com.groupa.mma_moriri.model.Appointment;
import com.groupa.mma_moriri.model.Customer;
import com.groupa.mma_moriri.model.HairStyle;
import com.groupa.mma_moriri.model.Stylist;
import com.groupa.mma_moriri.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Controller
public class ManageAppointmentsController {
    @Autowired
    private AppointmentService appService;
    @Autowired
    private CustomerService customerService;;
    @Autowired
    private StylistService stylistService;
    @Autowired
    private HairStyleService hsService;
    @Autowired
    private PaymentService pmtService;

    @PostMapping("CreateAppointmentDetails")
    public String createAppointmentDetails(@RequestParam("stylistId") Long stylist_id,
                                  HttpSession session) {
        Optional<Stylist> stylist = stylistService.getStylistById(Math.toIntExact(stylist_id));
        String stylistName = stylist.get().getFirstname() + " " + stylist.get().getLastname();

        String hairstyleName = (String) session.getAttribute("hairstyleName");
        HairStyle hairStyle = hsService.getByName(hairstyleName);

        String nextPage = "success";

        if(hairStyle != null) {
            Long style_id = hairStyle.getStyle_id();

            String customerUsername = (String) session.getAttribute("username");

            String msg = "The appointment details was not created";
            List<Appointment> appointments = appService.getAllAppointments();

            Timestamp date = new Timestamp(System.currentTimeMillis());

            int morningStartHours = 8, eveningStartHours = 17;
            int hours = date.getHours();

            if(hours >= morningStartHours && hours < eveningStartHours) {
                if(!appointments.isEmpty()) {
                    if(appointments.getLast().getDateTime().getMonth() == date.getMonth() &&
                            appointments.getLast().getDateTime().getDate() == date.getDate() &&
                            appointments.getLast().getDateTime().getDay() == date.getDay() &&
                            appointments.getLast().getDateTime().getYear() == date.getYear()) {
                        date = appointments.getLast().getDateTime();
                        date = Timestamp.valueOf(date.toLocalDateTime().plusMinutes(60));
                    } else {
                        date = appointments.getLast().getDateTime();
                        date = Timestamp.valueOf(date.toLocalDateTime().plusDays(1));
                    }
                }
            } else {
                if(!appointments.isEmpty()) {
                    date = Timestamp.valueOf(date.toLocalDateTime().plusHours(15));
                }
            }

            Customer customer = customerService.getCustomerByUsername(customerUsername);
            boolean active = false;

            if(customer != null) {
                String refNo = "" + (1 + ((int) (Math.random()*900))) +
                        customer.getCust_id() + stylist_id + style_id;
                int maxCount = 0;
                Optional<Appointment> accepTestApp = appService.getByRefNo(refNo);

                while(accepTestApp.isPresent() != false && maxCount < 10) {
                    refNo = "" + (1 + ((int) (Math.random()*100))) +
                            customer.getCust_id() + stylist_id + style_id;
                    accepTestApp = appService.getByRefNo(refNo);
                    maxCount++;
                }

                Appointment appointment = new Appointment(refNo, ((Timestamp) date),
                        customer.getCust_id(), stylist_id, Math.toIntExact(style_id), active);

                if(appointments.isEmpty() != true) {
                    Integer count = appService.getStylistCount(stylist_id);

                    if(count <= 8 && maxCount < 10) {
                        session.setAttribute("stylistName", stylistName);
                        session.setAttribute("appointment", appointment);
                        nextPage = "finalise_appointment_creation";
                    }else {
                        session.setAttribute("msg", msg);
                    }
                } else {
                    session.setAttribute("msg", msg);
                }
            } else {
                session.setAttribute("msg", "Please login or create an account if you don't one.");
                nextPage = "login_page";
            }
        } else {
            session.setAttribute("msg", "Please login or create an account if you don't one.");
            nextPage = "login_page";
        }

        return nextPage;
    }

    @GetMapping("SaveSucceeded")
    public String saveAppointmentDetails(HttpSession session) {
        Appointment app = (Appointment) session.getAttribute("appointment");
        String nextPage = "success";

        if(appService.addAppointment(app)) {
            String refNo = app.getRefNo();
            session.setAttribute("refNo", refNo);
            nextPage = "appointment_created";
        } else {
            String msg = "The appointment was not created. Please try again.";
            session.setAttribute("msg", msg);
        }

        return nextPage;
    }

    @PostMapping("RescheduleAppointmentPage")
    public String rescheduleAppointment(@RequestParam("refNo") String refNo,
                                        HttpSession session) {
        String nextPage = "success";
        Optional<Appointment> app = appService.getByRefNo(refNo);

        if(app.isPresent()) {
            Timestamp date = app.get().getDateTime();

            date = Timestamp.valueOf(date.toLocalDateTime().plusDays(6));

            int morningStartHours = 8, eveningStartHours = 17;
            int hours = date.getHours();

            if(hours >= morningStartHours && hours < eveningStartHours) {
                app.get().setDateTime(date);
                Long stylistId = app.get().getStylistId();

                if(stylistId != null) {
                    String stylistName = stylistService.getStylistById(Math.toIntExact(stylistId))
                            .get().getFirstname() + " " +
                            stylistService.getStylistById(Math.toIntExact(stylistId))
                                    .get().getLastname();

                    Long hairstyleId = Long.valueOf(app.get().getStyleId());
                    String hairstyleName = hsService.getByHairstyleId(hairstyleId).get().getName();

                    session.setAttribute("stylistName", stylistName);
                    session.setAttribute("hairstyleName", hairstyleName);
                    session.setAttribute("appointment", app.get());
                    nextPage = "confirm_reschedule_app_page";
                } else {
                    session.setAttribute("msg", "The appointment was not " +
                            "rescheduled.");
                }
            }

        } else {
            session.setAttribute("msg", "Invalid reference number. " +
                    "Please enter a valid reference number.");
        }

        return nextPage;
    }

    @GetMapping("SaveRescheduledAppointment")
    public String saveRescheduledAppointment(HttpSession session) {
        Appointment appointment = (Appointment) session.getAttribute("appointment");
        String msg = "The appointment has not been rescheduled.";

        if(appService.reschedule(appointment)) {
            msg = "The appointment has been rescheduled successfully.";
        }

        session.setAttribute("msg", msg);

        return "success";
    }

    @PostMapping("DeleteAppointment")
    public String cancelAppointment(@RequestParam("refNo") String refNo,
                                    HttpSession session) {
        String nextPage = "success";
        Optional<Appointment> appointment = appService.getByRefNo(refNo);

        if(appointment.isPresent()) {
            pmtService.makeRefNoNullById(refNo);
            appService.cancel(refNo);
            session.setAttribute("msg", "The appointment has been deleted.");

        } else {
            session.setAttribute("msg", "Failed to delete appointment");
        }


        return nextPage;
    }

    @GetMapping("GetAppointmentByRefNo")
    public Appointment getAppointmentByRefNo(@RequestParam("refNo") String refNo) {
        return appService.getByRefNo(refNo).get();
    }

}
