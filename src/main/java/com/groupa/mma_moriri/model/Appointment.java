package com.groupa.mma_moriri.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointment")
public class Appointment {
    @Id
    @Column(name = "ref_no")
    private String refNo;
    @Column(name = "date_time")
    private Timestamp dateTime;
    @Column(name = "cust_id", nullable = true)
    private Long custId;
    @Column(name = "stylist_id", nullable = true)
    private Long stylistId;
    @Column(name = "style_id")
    private Integer styleId;
    @Column(nullable = true)
    private boolean active;


}
