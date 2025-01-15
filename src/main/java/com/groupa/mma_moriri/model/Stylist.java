package com.groupa.mma_moriri.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Table(name = "stylist")
public class Stylist {
    @Id
    @GeneratedValue
    private Long stylist_id;
    private String firstname;
    private String lastname;
    private long phone_number;
    private String username;
    private String password;
    private byte[] image;

    public Stylist(String firstname, String lastname,
                   long phone_number, String username, String password, byte[] image) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone_number = phone_number;
        this.username = username;
        this.password = password;
        this.image = image;
    }
}
