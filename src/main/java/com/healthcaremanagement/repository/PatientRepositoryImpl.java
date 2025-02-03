package com.healthcaremanagement.repository;

import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PatientRepositoryImpl{

    private SessionFactory sessionFactory;

    public PatientRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createPatient(Patient patient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(patient);
            transaction.commit();
        }
    }

    public Patient getPatientById(int patientId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Patient.class, patientId);
        }
    }

    public void updatePatient(Patient patient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(patient);
            transaction.commit();
        }
    }

    public void deletePatient(int patientId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, patientId);
            if (patient != null) {
                session.delete(patient);
            }
            transaction.commit();
        }
    }

    public List<Patient> getAllPatients() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Patient", Patient.class).list();
        }
    }

    public void addDoctorToPatient(int patientID, Doctor doctor) {
        try(Session session = this.sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            Patient patient = session.get(Patient.class, patientID);
            if(patient != null & !patient.getDoctors().contains(doctor)){
                patient.getDoctors().add(doctor);
                session.merge(patient);
            }
            tx.commit();
        }
    }

    public void removeDoctorFromPatient(int patientId, Doctor doctor) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, patientId);
            if (patient != null && patient.getDoctors().contains(doctor)) {
                patient.getDoctors().remove(doctor);
                session.merge(patient);
            }
            transaction.commit();
        }
    }

}