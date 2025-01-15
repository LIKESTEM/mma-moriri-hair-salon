package com.groupa.mma_moriri.repo;

import com.groupa.mma_moriri.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    public Customer findByUsername(String username);
}
