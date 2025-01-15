package com.groupa.mma_moriri.web;

import com.groupa.mma_moriri.model.*;
import com.groupa.mma_moriri.service.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;


@Controller
public class ManageUserAccountController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private StylistService stylistService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private FinancialOfficerService fanOffService;
    @Autowired
    private PaymentService pmtService;
    @Autowired
    private AppointmentService appService;

    @GetMapping("Signup")
    public String getSignupPage() {
        return "signup";
    }

    @PostMapping("AddUser")
    public String getLoginPageAfterSignup(HttpServletRequest request) {
        return "login";
    }

    @PostMapping("CustomerHomePage")
    public String validateLoginCredentials(@RequestParam("username") String username,
                                                           @RequestParam("password") String password,
                                                           @RequestParam("usertype") String userType,
                                                           HttpSession session,
                                                           HttpServletRequest request) {
        String msg = "Your password or username is incorrect. Please try again!";

        if(userType.equals("admin")) {
            Optional<Admin> admin = Optional
                    .ofNullable(adminService.getAdminByUsername(username));

            if(admin.isPresent()) {
                if(password.equals(admin.get().getPassword())) {
                    session.setAttribute("username", username);
                    session.setAttribute("names", admin.get().getFirstname() + " " +
                            admin.get().getLastname());
                    List<Appointment> list = appService.getAllAppointments();
                    session.setAttribute("list", list);
                    session.setAttribute("usertype", userType);
                    return "admin_home";
                }
            }

        } else if(userType.equals("customer")) {
            Optional<Customer> customer = Optional
                    .ofNullable(customerService.getCustomerByUsername(username));

            if(customer.isPresent()) {
                if(password.equals(customer.get().getPassword())) {
                    session.setAttribute("username", username);
                    session.setAttribute("names", customer.get().getFirstname() + " " +
                            customer.get().getLastname());
                    List<Appointment> list = appService.getByCustomerId(customer
                            .get().getCust_id());
                    session.setAttribute("list", list);
                    session.setAttribute("usertype", userType);
                    return "client_home";
                }
            }

        } else if(userType.equals("financial_officer")) {
            Optional<Financial_Officer> fanOff = Optional
                    .ofNullable(fanOffService.getFinancialOfficerByUsername(username));

            if(fanOff.isPresent()) {
                if(password.equals(fanOff.get().getPassword())) {
                    session.setAttribute("username", username);
                    session.setAttribute("names", fanOff.get().getFirstname() + " " +
                            fanOff.get().getLastname());
                    session.setAttribute("payments", pmtService.getAllPayments());
                    session.setAttribute("usertype", userType);
                    return "financial_officer_home";
                }
            }

        } else if(userType.equals("stylist")) {
            Optional<Stylist> stylist = Optional.ofNullable(stylistService
                    .getStylistByUsername(username));

            if(stylist.isPresent()) {
                if(password.equals(stylist.get().getPassword())) {
                    session.setAttribute("username", username);
                    session.setAttribute("names", stylist.get().getFirstname() + " " +
                            stylist.get().getLastname());
                    List<Appointment> list = appService.getByStylistId(stylist
                            .get().getStylist_id());
                    session.setAttribute("list", list);
                    session.setAttribute("usertype", userType);
                    return "hairdresser_home";
                }
            }
        }

        request.setAttribute("msg", msg);

        return "login_page";
    }
    @PostMapping("CreateUser")
    public String createUser(@RequestParam("userType") String userType,
                             @RequestParam("firstname") String firstname,
                             @RequestParam("lastname") String lastname,
                             @RequestParam("phone_number") long phone_number,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession session) {
        String msg = "Successful";
        try {

            switch (userType) {
                case "admin":
                    Admin admin = new Admin(firstname, lastname, phone_number, username, password);
                    if (adminService.addAmin(admin) != null) {
                        msg = "The admin is successfully created.";
                    }
                    break;
                case "customer":
                    Customer customer = new Customer(firstname, lastname, phone_number, username, password);
                    if (customerService.addCustomer(customer) != null) {
                        msg = "The customer is successfully created.";
                    }
                    break;
                case "finOff":
                    Financial_Officer financialOfficer = new Financial_Officer(firstname, lastname, phone_number, username, password);
                    if (fanOffService.addFinancialOfficer(financialOfficer) != null) {
                        msg = "The financial officer is successfully created.";
                    }
                    break;
                case "stylist":
                    Stylist stylist = new Stylist(firstname, lastname, phone_number, username, password, null);
                    if (stylistService.addStylist(stylist)) {
                        msg = "The stylist is successfully created.";
                    }
                    break;
                default:
                    msg = "Unsuccessful";
                    break;
            }
        } catch (Exception e) {
            msg = "Error during user creation: " + e.getMessage();
        }

        session.setAttribute("msg", msg);
        return "success";
    }

    @GetMapping("GetCustomerRecentHomePage")
    public String getCustomerRecentHomePage(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String userType = (String) session.getAttribute("usertype");

        if(userType.equals("admin")) {
            Optional<Admin> admin = Optional
                    .ofNullable(adminService.getAdminByUsername(username));

            if(admin.isPresent()) {
                session.setAttribute("username", username);
                session.setAttribute("names", admin.get().getFirstname() + " " +
                        admin.get().getLastname());
                List<Appointment> list = appService.getAllAppointments();
                session.setAttribute("list", list);
                return "admin_home";
            }

        } else if(userType.equals("customer")) {
            Optional<Customer> customer = Optional
                    .ofNullable(customerService.getCustomerByUsername(username));

            if(customer.isPresent()) {
                session.setAttribute("username", username);
                session.setAttribute("names", customer.get().getFirstname() + " " +
                        customer.get().getLastname());
                List<Appointment> list = appService.getByCustomerId(customer
                        .get().getCust_id());
                session.setAttribute("list", list);
                return "client_home";
            }

        } else if(userType.equals("financial_officer")) {
            Optional<Financial_Officer> fanOff = Optional
                    .ofNullable(fanOffService.getFinancialOfficerByUsername(username));

            if(fanOff.isPresent()) {
                session.setAttribute("username", username);
                session.setAttribute("names", fanOff.get().getFirstname() + " " +
                        fanOff.get().getLastname());
                session.setAttribute("payments", pmtService.getAllPayments());
                return "financial_officer_home";
            }

        } else if(userType.equals("stylist")) {
            Optional<Stylist> stylist = Optional.ofNullable(stylistService
                    .getStylistByUsername(username));

            if(stylist.isPresent()) {
                session.setAttribute("username", username);
                session.setAttribute("names", stylist.get().getFirstname() + " " +
                        stylist.get().getLastname());
                List<Appointment> list = appService.getByStylistId(stylist
                        .get().getStylist_id());
                session.setAttribute("list", list);
                return "hairdresser_home";
            }
        } else {
            session.setAttribute("msg", "Failed to go to the user home page.");
        }

        return "success";
    }

    @GetMapping("GetCreateUserAccountPage")
    public String getCreateUserPage() {
        return "create_user";
    }

    @GetMapping("Logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @DeleteMapping("DeleteUserAccount")
    public String deleteUserAccount() {
        return "";
    }

    @PutMapping("UpdateUserAccount")
    public String updateUser() {
        return "";
    }

    @GetMapping("GetSearchUserPage")
    public String getSearchUserPage() {
        return "search_user";
    }

    @GetMapping("DeleteUser")
    public String deleteUser(HttpServletRequest request, HttpSession session) {
        String errorPage = "unauthorised_bad_request_result_page";

        String username = (String) session.getAttribute("useUsername");
        String userType = (String) session.getAttribute("useUsertype");

        if(userType.equals("admin")) {
            Optional<Admin> admin = Optional
                    .ofNullable(adminService.getAdminByUsername(username));

            if(admin.isPresent()) {
                adminService.removeAdmin(admin.get().getUsername());
                session.setAttribute("msg", "The admin has been " +
                        "removed from the database.");
                return "success";
            }

        } else if(userType.equals("customer")) {
            Optional<Customer> customer = Optional
                    .ofNullable(customerService.getCustomerByUsername(username));

            if(customer.isPresent()) {
                appService.makeIdNullById(customer.get().getCust_id());
                customerService.removeCustomer(customer.get().getUsername(),
                        customer.get().getPassword());
                session.setAttribute("msg", "The customer has been " +
                        "removed from the database.");
                return "success";
            }

        } else if(userType.equals("financial_officer")) {
            Optional<Financial_Officer> fanOff = Optional
                    .ofNullable(fanOffService.getFinancialOfficerByUsername(username));

            if(fanOff.isPresent()) {
                pmtService.makeIdNullByFanOffId(fanOff.get().getFan_off_id());
                fanOffService.removeFinancialOfficer(fanOff.get().getUsername(),
                        fanOff.get().getPassword());
                session.setAttribute("msg", "The financial officer has been " +
                        "removed from the database.");
                return "success";
            }

        } else if(userType.equals("stylist")) {
            Optional<Stylist> stylist = Optional.ofNullable(stylistService
                    .getStylistByUsername(username));

            if(stylist.isPresent()) {
                appService.makeIdNullByStylistId(stylist.get().getStylist_id());
                stylistService.removeStylist(stylist.get().getUsername(),
                        stylist.get().getPassword());
                session.setAttribute("msg", "The stylist has been " +
                        "removed from the database.");
                return "success";
            }
        } else {
            request.setAttribute("msg", "The user has not been " +
                    "removed from the database.");
        }

        return errorPage;
    }

    @PostMapping("SearchUser")
    public String searchUser(@RequestParam("useUsername") String useUsername,
                             @RequestParam("useUsertype") String useUsertype,
                             HttpSession session,
                             HttpServletRequest request) {

        if(useUsertype.equals("admin")) {
            Optional<Admin> admin = Optional
                    .ofNullable(adminService.getAdminByUsername(useUsername));

            if(admin.isPresent()) {
                session.setAttribute("useUsername", admin.get().getUsername());
                session.setAttribute("useNames", admin.get().getFirstname()
                        + " " + admin.get().getLastname());
                session.setAttribute("useUsertype", "admin");
                return "user_search_outcome";
            }

        } else if(useUsertype.equals("customer")) {
            Optional<Customer> customer = Optional
                    .ofNullable(customerService.getCustomerByUsername(useUsername));

            if(customer.isPresent()) {
                session.setAttribute("useUsername", customer.get().getUsername());
                session.setAttribute("useNames", customer.get().getFirstname()
                        + " " + customer.get().getLastname());
                session.setAttribute("useUsertype", "customer");
                return "user_search_outcome";
            }

        } else if(useUsertype.equals("financial_officer")) {
            Optional<Financial_Officer> fanOff = Optional
                    .ofNullable(fanOffService.getFinancialOfficerByUsername(useUsername));

            if(fanOff.isPresent()) {
                session.setAttribute("useUsername", fanOff.get().getUsername());
                session.setAttribute("useNames", fanOff.get().getFirstname()
                        + " " + fanOff.get().getLastname());
                session.setAttribute("useUsertype", "financial_officer");
                return "user_search_outcome";
            }

        } else if(useUsertype.equals("stylist")) {
            Optional<Stylist> stylist = Optional.ofNullable(stylistService
                    .getStylistByUsername(useUsername));

            if(stylist.isPresent()) {
                session.setAttribute("useUsername", stylist.get().getUsername());
                session.setAttribute("useNames", stylist.get().getFirstname()
                        + " " + stylist.get().getLastname());
                session.setAttribute("useUsertype", "stylist");
                return "user_search_outcome";
            }
        } else {
            request.setAttribute("msg", "There is no such user in the database.");
        }

        return "success";
    }

}
