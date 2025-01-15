package com.groupa.mma_moriri.repo;

import com.groupa.mma_moriri.model.Stylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StylistRepo extends JpaRepository<Stylist, Long> {
    public Stylist findByUsername(String username);
}
