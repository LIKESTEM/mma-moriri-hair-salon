package com.groupa.mma_moriri.service;

import com.groupa.mma_moriri.model.Customer;
import com.groupa.mma_moriri.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo repo;

    public Customer getCustomerByUsername(String username) {
        return repo.findByUsername(username);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return repo.findById(id);
    }

    public Customer addCustomer(Customer Customer) {
        return repo.save(Customer);
    }

    public Customer setNewUsername(String username, String newUsername) {
        Customer Customer = repo.findByUsername(username);

        Customer.setUsername(newUsername);

        return repo.save(Customer);
    }

    public String setNewPassword(String username, String currPassword, String newPassword) {
        Customer customer = repo.findByUsername(username);
        String msg = "Your details are insufficient or incorrect to " +
                "permit you to update/edit this account.";

        if(customer != null) {
            customer.setPassword(currPassword);

            List<Customer> customers = repo.findAll();

            if(customers.contains(customer)) {
                customer.setPassword(newPassword);
                repo.save(customer);
                msg = "Your account has been updated.";
            }
        }

        return msg;
    }

    public String removeCustomer(String username, String password) {
        Customer customer = repo.findByUsername(username);
        String msg = "Your details are insufficient or incorrect to " +
                "permit you to delete this account.";

        if(customer != null) {
            customer.setPassword(password);

            List<Customer> customers = repo.findAll();

            if(customers.contains(customer)) {
                repo.delete(customer);
                msg = "Your account has been deleted from mma_moriri system.";
            }
        }

        return msg;
    }

    public List<Customer> getAllCustomers() {

        return repo.findAll();
    }

    /*added*/
    public void updateCustomerDetails(String username, String firstname, String lastname, Long phone_number) {
        Customer customer = repo.findByUsername(username);
        if (customer != null) {
            customer.setFirstname(firstname);
            customer.setLastname(lastname);
            customer.setPhone_number(phone_number);
            repo.save(customer);
        }
    }

}


