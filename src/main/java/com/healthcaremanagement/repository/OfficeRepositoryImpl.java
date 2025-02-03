package com.healthcaremanagement.repository;

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

    public void createOffice(Office office){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.save(office);
            tx.commit();
        }
    }

    public Office getOfficeById(int officeId){
        try(Session session = sessionFactory.openSession()){
            return session.get(Office.class, officeId);
        }
    }

    public void updateOffice(Office office){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.update(office);
            tx.commit();
        }
    }

    public void deleteOffice(int officeId){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            Office office = session.get(Office.class, officeId);

            if (office != null){
                session.delete(office);
            }

            tx.commit();
        }
    }

    public List<Office> getAllOffices(){
        try(Session session = sessionFactory.openSession()){
            String hql = "FROM Office";
            return session.createQuery(hql, Office.class).list();
        }
    }
}
