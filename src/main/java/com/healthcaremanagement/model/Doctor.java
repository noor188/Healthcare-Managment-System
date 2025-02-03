package com.healthcaremanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@ToString(exclude = {"patients", "appointments"})
@Entity
@Table(name= "Doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DoctorID")
    private int doctorId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Specialty")
    private String specialty;

    @Column(name = "Email")
    private String email;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(
            name = "patient_doctor",
            joinColumns = @JoinColumn(name  = "DoctorID"),
            inverseJoinColumns = @JoinColumn(name = "PatientID")
    )
    private Set<Patient> patients = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor that = (Doctor) o;
        return doctorId == that.doctorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId);
    }

}
