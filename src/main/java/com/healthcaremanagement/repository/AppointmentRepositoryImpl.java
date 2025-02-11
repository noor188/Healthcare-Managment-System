package com.healthcaremanagement.repository;

import com.healthcaremanagement.model.Appointment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AppointmentRepositoryImpl {

    private SessionFactory sessionFactory;

    public AppointmentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // MODIFIES: This
    // EFFECT: Manage a session, start a transaction, persist the appointment object
    public void createAppointment(Appointment appointment){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(appointment);
            tx.commit();
        }
    }

    // EFFECT: Manage a session, return a doctor object with the passed appointmenId
    public Appointment getAppointmentById(int appointmenId){
        try(Session session = sessionFactory.openSession()) {
            return session.get(Appointment.class, appointmenId);
        }
    }

    // MODIFIES: This
    // EFFECT: Manage a session, start a transaction, update a doctor object
    public void updateAppointment(Appointment appointment){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(appointment);
            tx.commit();
        }
    }

    // MODIFIE: This
    // EFFECT: Manages a session, starts a transaction, deletes a doctor object
    public void deleteAppointment(int appointmentId){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appointmentId);

            if (appointment != null){
                appointment.setPatient(null);
                appointment.setDoctor(null);
                session.delete(appointment);
            }

            tx.commit();
        }
    }

    public List<Appointment> getAllAppointments(){
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT a FROM Appointment a";
            return session.createQuery(hql, Appointment.class).list();
        }
    }

}
