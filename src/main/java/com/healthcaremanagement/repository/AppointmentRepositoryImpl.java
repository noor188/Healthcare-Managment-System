package com.healthcaremanagement.repository;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppointmentRepositoryImpl {

    private SessionFactory sessionFactory;

    public AppointmentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

    public boolean hasOtherAppointmentsBetween(int doctorId, int patientId) {
        try (Session session = sessionFactory.openSession()) {
            String query = "SELECT COUNT(a) FROM Appointment a " +
                    "WHERE a.doctor.doctorId = :doctorId " +
                    "AND a.patient.patientId = :patientId";
            Long count = session.createQuery(query, Long.class)
                    .setParameter("doctorId", doctorId)
                    .setParameter("patientId", patientId)
                    .uniqueResult();
            return count != null && count > 1;
        }
    }
}
