package com.groupa.mma_moriri.web;

import com.groupa.mma_moriri.model.Appointment;
import com.groupa.mma_moriri.model.Financial_Officer;
import com.groupa.mma_moriri.model.HairStyle;
import com.groupa.mma_moriri.model.Payment;
import com.groupa.mma_moriri.service.AppointmentService;
import com.groupa.mma_moriri.service.FinancialOfficerService;
import com.groupa.mma_moriri.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PaymentController {
    @Autowired
    private PaymentService service;
    @Autowired
    private AppointmentService appService;
    @Autowired
    private FinancialOfficerService fanOffService;

    @PostMapping("AddPaymentOptional")
    public String addPayment(@RequestParam("appRefNo") String appRefNo,
                             HttpSession session) {
        String msg = "fail_app_page";

        Timestamp dateTime = new Timestamp(System.currentTimeMillis());

        Payment payment = new Payment(null, false,
        null, null, appRefNo, null, dateTime);

        if(service.addPayment(payment)) {
            msg = "success_app_page";
        }

        session.setAttribute("msg", msg);

        return "success";
    }

    @GetMapping("UploadPaymentDetailsPage")
    public String getPaymentDetailsPage() {
        return "upload_payment_detail";
    }

    @PostMapping("AddPaymentAllRequired")
    public String addPaymentAllRequired(@RequestParam String appRefNo,
                                        @RequestParam MultipartFile pdfFile,
                                        @RequestParam double totAmtPaid,
                                        HttpSession session) {
        String msg = "fail_app_page";
        Timestamp dateTime = new Timestamp(System.currentTimeMillis());

        try {
            if (!pdfFile.isEmpty()) {
                byte[] receipt = pdfFile.getBytes();

                Optional<Appointment> appointment = appService.getByRefNo(appRefNo);

                if (appointment.isPresent()) {
                    Payment payment = new Payment(receipt, false, null, null,
                            appRefNo, totAmtPaid, dateTime);

                    if (service.addPayment(payment)) {
                        msg = "The payment was added successfully.";
                    } else {
                        msg = "Failed to add payment. Please try again.";
                    }
                } else {
                    msg = "Appointment with reference number " + appRefNo + " not found.";
                }
            } else {
                msg = "PDF file is empty. Please select a PDF file.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            msg = "Error processing PDF file. Please try again.";
        }

        session.setAttribute("msg", msg);

        return "success";
    }

    @GetMapping("UpdatePaymentDetailPage")
    public String getUpdatePaymentDetailPage() {
        return "update_payment_detail";
    }

    @PostMapping("UpdatePayment")
    public String updatePayment(@RequestParam("appRefNo") String appRefNo,
                                @RequestParam("pdfFile") MultipartFile pdfFile,
                                @RequestParam("totAmtPaid") double totAmtPaid,
                                HttpSession session) {
        String msg = "fail_app_page";
        Timestamp dateTime = new Timestamp(System.currentTimeMillis());

        try {
            if (!pdfFile.isEmpty()) {
                byte[] receipt = pdfFile.getBytes();

                Optional<Appointment> appointment = appService.getByRefNo(appRefNo);

                if (appointment.isPresent()) {
                    Payment payment = new Payment(receipt, false, null, null,
                            appRefNo, totAmtPaid, dateTime);

                    if (service.updateReceipt(appRefNo, receipt, totAmtPaid)) {
                        msg = "The payment receipt was added successfully.";
                    } else {
                        msg = "Failed to add payment receipt. Please try again.";
                    }
                } else {
                    msg = "Appointment with reference number " + appRefNo + " not found.";
                }
            } else {
                msg = "PDF file is empty. Please select a PDF file.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            msg = "Error processing PDF file. Please try again.";
        }

        session.setAttribute("msg", msg);

        return "success";
    }

    @PostMapping("UpdatePaymentByFanOff")
    public String updatePaymentByFanOff(@RequestParam("fanOffId") Long fanOffId,
                                        @RequestParam("status") boolean status,
                                        @RequestParam("statusDesc") String statusDesc,
                                        HttpSession session) {
        String msg = "fail_app_page";
        String appRefNo = (String) session.getAttribute("appRefNo");
        Payment payment = new Payment();

        if(service.updateStatusByFanOff(appRefNo, fanOffId, status, statusDesc)) {
            Optional<Appointment> appointment = appService.getByRefNo(appRefNo);
            appointment.get().setActive(status);
            appService.addAppointment(appointment.get());
            msg = "success_app_page";
        }

        session.setAttribute("msg", msg);

        return "success";
    }

    @PostMapping("GetCustomerPayment")
    public String getCustomerPayment(@RequestParam String appRefNo, HttpSession session) {
        Payment payment = service.findByRefNo(appRefNo);
        session.setAttribute("appRefNo", appRefNo);
        String username = (String) session.getAttribute("username");
        Financial_Officer fanOff = fanOffService.getFinancialOfficerByUsername(username);
        session.setAttribute("fanOffId", fanOff.getFan_off_id());
        session.setAttribute("payment", payment);
        return "selected_appointment_payment";
    }

    @GetMapping("GetAllPayments")
    public String getAllPayments(HttpSession session) {
        List<Payment> payments = service.getAllPayments();
        session.setAttribute("payments", payments);
        return "financial_officer_home";
    }

    @GetMapping("AllPayments")
    public String getPayments(HttpSession session) {
        List<Payment> payments = service.getAllPayments();

        List<Payment> filteredPayments = new ArrayList<>();

        for (Payment pay : payments) {
            if (pay.isStatus()) {
                filteredPayments.add(pay);
                session.setAttribute("filteredPayments", filteredPayments);
            }
        }
        return "payments";
    }

}

