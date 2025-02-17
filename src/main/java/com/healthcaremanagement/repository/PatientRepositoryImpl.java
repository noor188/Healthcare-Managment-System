package com.healthcaremanagement.repository;

import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

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
            String hql = "SELECT p FROM Patient p LEFT JOIN FETCH p.doctors WHERE p.patientId = :patientId ";
            return session.createQuery(hql, Patient.class).setParameter("patientId", patientId).uniqueResult();
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
                Set<Doctor> doctors = patient.getDoctors();
                for (Doctor doctor : doctors) {
                    doctor.getPatients().remove(patient);
                    patient.getDoctors().remove(doctor);
                }
                session.delete(patient);
            }
            transaction.commit();
        }
    }

    public List<Patient> getAllPatients() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT p FROM Patient p LEFT JOIN FETCH p.doctors LEFT JOIN FETCH p.patients";
            return session.createQuery("from Patient", Patient.class).list();
        }
    }
}
