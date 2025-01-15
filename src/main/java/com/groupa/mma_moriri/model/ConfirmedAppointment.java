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
@Table(name = "confirmed_appointment")
public class ConfirmedAppointment {
    @Id
    @GeneratedValue
    @Column(name = "confirmation_code")
    private Long confirmedCode;
    @Column(name = "appoint_id")
    private String appointId;
    @Column(name = "admin_id")
    private Integer adminId;
    @Column(name = "confirmation_date")
    private Timestamp confirmationDate;
    private boolean status;

    public ConfirmedAppointment(String appointId, Integer adminId,
                                Timestamp confirmationDate, boolean status) {
        this.appointId = appointId;
        this.adminId = adminId;
        this.confirmationDate = confirmationDate;
        this.status = status;
    }
}
