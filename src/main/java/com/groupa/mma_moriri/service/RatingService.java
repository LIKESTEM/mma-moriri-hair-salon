package com.groupa.mma_moriri.service;

import com.groupa.mma_moriri.model.Rating;
import com.groupa.mma_moriri.repo.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepo repo;

    public boolean addRating(Rating rating) {
        repo.save(rating);
        return true;
    }

    public List<Rating> getRatingsByStylistId(Long stylistId) {
        return repo.findByStylistId(stylistId);
    }

    public List<Rating> getAllRatings() {
        return repo.findAll();
    }

    public List<Rating> getRatingsByCustomerId(Long custId) {
        return (List<Rating>) repo.findByCustomerId(custId);
    }
}
