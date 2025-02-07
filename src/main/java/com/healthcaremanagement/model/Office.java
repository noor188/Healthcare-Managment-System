package com.healthcaremanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return officeId == office.officeId && Objects.equals(location, office.location) && Objects.equals(phone, office.phone) && Objects.equals(doctor, office.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId, location, phone, doctor);
    }
}
