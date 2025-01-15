package com.groupa.mma_moriri.repo;

import com.groupa.mma_moriri.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Long> {
    public List<Rating> findByStylistId(Long stylistId);
    public List<Rating> findByCustomerId(Long customerId);
}
