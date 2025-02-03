package com.healthcaremanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "Offices")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "officeId")
    private int officeId;

    @Column(name = "Location")
    private String location;

    @Column(name = "Phone")
    private String phone;

    @OneToOne
    @JoinColumn(name="DoctorID")
    private Doctor doctor;

}
