package com.healthcaremanagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Offices")
@Data
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"doctor"})
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "OfficeID")
    private int officeId;

    @Column(name = "Location")
    private String location;

    @Column(name = "Phone")
    private String phone;

    @OneToOne
    @JoinColumn(name = "DoctorID", nullable = true)
    private Doctor doctor;

}
