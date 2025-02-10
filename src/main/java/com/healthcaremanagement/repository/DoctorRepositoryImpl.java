package com.healthcaremanagement.repository;

import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class DoctorRepositoryImpl {

    private SessionFactory sessionFactory;

    public DoctorRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // MODIFY: This
    // EFFECT: Manage a session, starts a transaction, persist a doctor object
    public void createDoctor(Doctor doctor) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(doctor);
            tx.commit();
        }
    }

    // EFFECT: Manage a sesion, returns a doctor object with the passed doctorId
    public Doctor getDoctorById(int doctorId){
        try(Session session = sessionFactory.openSession()) {
            String hql = "SELECT d FROM Doctor d LEFT JOIN FETCH d.patients WHERE d.doctorId = :id";
            return session.createQuery(hql, Doctor.class).setParameter("id", doctorId).uniqueResult();
        }
    }

    // MODIFY: This
    // EFFECT: Manage a session, starts a transaction, updates a doctor object
    public void updateDoctor(Doctor doctor){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(doctor);
            tx.commit();
        }
    }

    public void deleteDoctor(int doctorId){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, doctorId);
            Set<Patient> patients = doctor.getPatients();

            if (doctor != null)
            {
                // Many to Many with Patient
                for (Patient patient : patients) {
                    patient.getDoctors().remove(doctor);
                    doctor.getPatients().remove(patient);
                }

                // One to One with Office
                if (doctor.getOffice() != null){
                    doctor.getOffice().setDoctor(null);
                    session.update(doctor.getOffice());
                    doctor.setOffice(null);
                    session.update(doctor);
                }
                session.delete(doctor);
            }
            tx.commit();
        }
    }

    // EFFECT: Manages a session, return a list of all doctor objects from MySQL
    public List<Doctor> getAllDoctors(){
        try(Session session = sessionFactory.openSession()){
            String hql = "SELECT d FROM Doctor d";
            return session.createQuery(hql, Doctor.class).list();
        }
    }

}
