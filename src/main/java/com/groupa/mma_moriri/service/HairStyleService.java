package com.groupa.mma_moriri.service;

import com.groupa.mma_moriri.model.HairStyle;
import com.groupa.mma_moriri.repo.HairStyleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HairStyleService {
    @Autowired
    private HairStyleRepo repo;

    public boolean addHairStyle(HairStyle hairStyle) {
        Optional<HairStyle> style = Optional.ofNullable(repo.findByName(hairStyle.getName()));

        if(style.isPresent()) {
            HairStyle styleFound = style.get();
            styleFound.setImage(hairStyle.getImage());
            repo.save(styleFound);
        } else {
            repo.save(hairStyle);
        }

        return true;
    }

    public HairStyle getByName(String name) {
        return repo.findByName(name);
    }

    public Optional<HairStyle> getByHairstyleId(Long styleId) {
        return repo.findById(styleId);
    }

    public boolean deleteHairStyle(String name) {
        repo.deleteByName(name);
        return true;
    }

    public List<HairStyle> getAllHairStyles() {
        return repo.findAll();
    }
}
