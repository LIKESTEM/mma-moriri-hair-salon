package com.groupa.mma_moriri.repo;

import com.groupa.mma_moriri.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    public Admin findByUsername(String username);
//    public List<Admin> findByNameContainingOrSurnameContaining(String name, String surname);
}
