package com.healthcaremanagement.repository;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AppointmentRepositoryImpl {

    private SessionFactory sessionFactory;
    private final PatientRepositoryImpl patientRepository;
    private final DoctorRepositoryImpl doctorRepository;

    public AppointmentRepositoryImpl(SessionFactory sessionFactory, PatientRepositoryImpl patientRepository, DoctorRepositoryImpl doctorRepository) {
        this.sessionFactory = sessionFactory;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public void createAppointment(Appointment appointment){
        try(Session session = this.sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.save(appointment);
            tx.commit();
        }
    }

    public Appointment getAppointmentById(int appointmentId){
        try(Session session = this.sessionFactory.openSession()){
            return session.get(Appointment.class, appointmentId);
        }
    }

    public void updateAppointment(Appointment appointment){
        try(Session session = this.sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.update(appointment);
            tx.commit();
        }
    }

    public void deleteAppointment(int appointmentId){
        try(Session session = this.sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            Appointment appointment = getAppointmentById(appointmentId);

            if (appointment != null){
                session.delete(appointment);
            }
            tx.commit();
        }
    }

    public List<Appointment> getAllAppointments(){
        try(Session session = this.sessionFactory.openSession()){
            return session.createQuery("from Appointment", Appointment.class).list();
        }
    }

    public Patient getPatienById(int patientId){
        return this.patientRepository.getPatientById(patientId);
    }

    public Doctor getDocotrById(int docotrId){
        return this.doctorRepository.getDoctorById(docotrId);
    }


}
