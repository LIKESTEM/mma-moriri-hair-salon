package com.groupa.mma_moriri.web;

import com.groupa.mma_moriri.model.Customer;
import com.groupa.mma_moriri.service.AppointmentService;
import com.groupa.mma_moriri.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService service;

    @Autowired
    private AppointmentService appService;

    @PostMapping("CreateCustomerAccount")
    public String createCustomerAccount(@RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       @RequestParam("phone_number") long phone_number,
                                       @RequestParam("firstname") String firstname,
                                       @RequestParam("lastname") String lastname,
                                        HttpServletRequest request) {

        String nextPage = "signup";
        Customer customer = new Customer(firstname, lastname, phone_number, username, password);
        if(isValidPassword(password)) {
            if (service.addCustomer(customer) != null) {
                nextPage = "login_page";
            }
        } else {
            request.setAttribute("msg", "Please enter strong password that has the following:\n <br/>" +
                    "1. At least one uppercase letter,<br/>" +
                    " 2. At least one lowercase letters,<br/>" +
                    " 3. At least one digit,<br/>" +
                    " 4. At least one special character,<br/>" +
                    " 5. And minimum of eight (8) characters");
        }

        return nextPage;
    }

    public static boolean isValidPassword(String password) {
        // Check if the password length is at least 8 characters
        if (password.length() < 8) {
            return false;
        }

        // Regular expression patterns
        Pattern upperCasePattern = Pattern.compile("[A-Z]");
        Pattern lowerCasePattern = Pattern.compile("[a-z]");
        Pattern digitPattern = Pattern.compile("\\d");
        Pattern specialCharacterPattern = Pattern.compile("[^a-zA-Z0-9]");

        // Check if the password contains at least one uppercase letter
        if (!upperCasePattern.matcher(password).find()) {
            return false;
        }

        // Check if the password contains at least one lowercase letter
        if (!lowerCasePattern.matcher(password).find()) {
            return false;
        }

        // Check if the password contains at least one digit
        if (!digitPattern.matcher(password).find()) {
            return false;
        }

        // Check if the password contains at least one special character
        if (!specialCharacterPattern.matcher(password).find()) {
            return false;
        }

        // If all checks passed, the password is valid
        return true;
    }

    @GetMapping("GetCustomerByUsername/{username}")
    public Customer getCustomerByUsername(@PathVariable("username") String username) {
        return service.getCustomerByUsername(username);
    }

    @GetMapping("GetAllCustomers")
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }



    @PutMapping("UpdateCustomer")
    public String changePassword(@PathVariable("username") String username,
                                 @PathVariable("password") String password,
                                 @PathVariable("newPassword") String newPassword) {
        return service.setNewPassword(username, password, newPassword);
    }

    @GetMapping("RemoveCustomer")
    public String removeCustomer(HttpSession session, HttpServletRequest request) {
        String useUsername = (String) session.getAttribute("username");
        Customer customer = service.getCustomerByUsername(useUsername);
        String msg = "The customer has not been removed.";

        if(customer != null) {
            appService.makeIdNullById(customer.getCust_id());
            service.removeCustomer(customer.getUsername(), customer.getPassword());
            msg = "The customer has been removed successfully.";
        }

        session.invalidate();
        request.setAttribute("msg", msg);

        return "success";
    }

    @GetMapping("GetDeleteConfirmPage")
    public String getDeleteConfirmPage(HttpSession session) {
        String useUsername = (String) session.getAttribute("username");

        Customer customer = service.getCustomerByUsername(useUsername);
        session.setAttribute("customer", customer);

        return "delete_confirm_page";
    }

    /* I added this code 2024/05/06 */
    //Code I Added to update users password and the details
/*Customer will have to enter the 3 details when updating otherwise some information will be missing*/
    @PostMapping("/UpdateCustomer")
    public String updateCustomer(HttpSession session,
                                 @RequestParam("firstname") String firstname,
                                 @RequestParam("lastname") String lastname,
                                 @RequestParam( "phone_number") Long phone_number) {

        String username = (String) session.getAttribute("username");

        if (username != null) {

                service.updateCustomerDetails(username, firstname, lastname, phone_number);
        }
        return "updateSuccess";
    }
    @GetMapping("/GetNewPassWordSet")
    public String updatePassword() {
        return "updatePassword";
    }
    @PostMapping("/setNewPassword")
    public String setNewPassword(
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            HttpSession session)
    {
        String username = (String)session.getAttribute("username");
        Optional<Customer> customer = Optional
                .ofNullable(service.getCustomerByUsername(username));

        if (newPassword.equals(confirmPassword) && customer.get().getPassword().equals(currentPassword) ) {

            service.setNewPassword(username, currentPassword, newPassword);
            return "updateSuccess";
        } else {
            return "passwordMismatch";
        }
    }

}
