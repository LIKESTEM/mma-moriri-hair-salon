package com.groupa.mma_moriri.repo;

import com.groupa.mma_moriri.model.Financial_Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialOfficerRepo extends JpaRepository<Financial_Officer, Long> {
    public Financial_Officer findByUsername(String username);
}
