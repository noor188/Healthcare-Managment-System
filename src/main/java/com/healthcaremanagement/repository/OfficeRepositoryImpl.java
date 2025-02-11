package com.healthcaremanagement.repository;

import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Office;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class OfficeRepositoryImpl {

    private SessionFactory sessionFactory;

    public OfficeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // MODIFIES: This
    // EFFECTS: Manages a session, start a transaction, persist the object
    public void createOffice(Office office) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(office);
            tx.commit();
        }
    }

    // EFFECT: Manage a session, returns a doctor object with the passed doctorId
    public Office getOfficeById(int officeId){
        try (Session session = sessionFactory.openSession()) {
            return session.get(Office.class, officeId);
        }
    }

    // MODIFY: This
    // EFFECT: Manges a session, starts a transaction, update a doctor object
    public void updateOffice(Office office) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            if (office.getDoctor() != null){
                Doctor doctor = session.get(Doctor.class, office.getDoctor().getDoctorId());
                office.setDoctor(doctor);
            }
            session.merge(office);
            tx.commit();
        }
    }

    // MODIFIE: This
    // EFFECT:  Manages a session, starts a transaction, deletes a doctor object
    public void deleteOffice(int officeId){
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Office office = session.get(Office.class, officeId);

            if(office != null){
                if(office.getDoctor() != null){
                    office.getDoctor().setOffice(null);
                    session.merge(office.getDoctor());
                    office.setDoctor(null);
                    session.merge(office);
                }
            }
            session.delete(office);
            tx.commit();
        }
    }

    // EFFECT: Manages a session, return a list of all doctor objects from MySQL
    public List<Office> getAllOffices(){
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT o FROM Office o";
            return session.createQuery(hql, Office.class).list();
        }
    }
}
