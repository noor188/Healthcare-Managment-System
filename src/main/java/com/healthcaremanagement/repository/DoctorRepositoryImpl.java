package com.healthcaremanagement.repository;

import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Queue;

public class DoctorRepositoryImpl {

    private SessionFactory sessionFactory;
    private final PatientRepositoryImpl patientRepository;

    public DoctorRepositoryImpl(SessionFactory sessionFactory, PatientRepositoryImpl patientRepository) {
        this.sessionFactory = sessionFactory;
        this.patientRepository = patientRepository;
    }

    public void createDoctor(Doctor doctor){
        try(Session session = this.sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.save(doctor);
            tx.commit();
        }
    }

    public Doctor getDoctorById(int doctorId){
        try(Session session = sessionFactory.openSession()){
            return session.get(Doctor.class, doctorId);
        }
    }

    public void updateDoctor(Doctor doctor){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.update(doctor);
            tx.commit();
        }
    }

    public void deleteDoctor(int doctorId){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            Doctor doctor = this.getDoctorById(doctorId);
            if(doctor != null) {
                session.delete(doctorId);
            }
            tx.commit();
        }
    }

    public List<Doctor> getAllDoctors(){
        try(Session session = sessionFactory.openSession()){
            // try resourse
            Query doctorQuery = session.createQuery("from Doctors", Doctor.class);
            return doctorQuery.list();
        }
    }

    public List<Patient> getAllPatients(){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from Doctor", Patient.class).getResultList();
        }
    }
}
