package com.healthcaremanagement.repository;

import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.print.Doc;
import java.util.List;

public class DoctorRepositoryImpl {

    private SessionFactory sessionFactory;

    public DoctorRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void createDoctor(Doctor doctor){
        try(Session session = sessionFactory.openSession()){
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

    public void deleteDoctor(int docotrId){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            Doctor doctor = getDoctorById(docotrId);

            if (doctor != null){
                session.delete(doctor);
            }
            tx.commit();
        }
    }

    public List<Doctor> getAllDoctors(){
        try(Session session = this.sessionFactory.openSession()){
            return session.createQuery("from Doctor", Doctor.class).list();
        }
    }

    public void addPatientToDoctor(int doctorID, Patient patient){
        try(Session session = this.sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, doctorID);
            if(doctor != null & !doctor.getPatients().contains(patient)){
                doctor.getPatients().add(patient);
                session.merge(doctor);
            }
            tx.commit();
        }
    }

    public void removePatientFromDoctor(int doctorId, Patient patient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, doctorId);
            if (doctor != null && doctor.getPatients().contains(patient)) {
                doctor.getPatients().remove(patient);
                session.merge(doctor);
            }
            transaction.commit();
        }
    }
}
