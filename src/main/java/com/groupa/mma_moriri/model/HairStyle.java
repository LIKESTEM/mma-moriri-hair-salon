package com.groupa.mma_moriri.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table(name = "hair_style")
public class HairStyle {
    @Id
    @GeneratedValue
    private Long style_id;
    private String name;
    private String description;
    private byte[] image;
    private double price;

    public HairStyle(String name, String description, byte[] image, double price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }
}
