package com.groupa.mma_moriri.repo;

import com.groupa.mma_moriri.model.HairStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HairStyleRepo extends JpaRepository<HairStyle, Long> {
    public HairStyle findByName(String name);

    public void deleteByName(String name);
}
