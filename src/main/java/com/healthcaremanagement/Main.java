package com.healthcaremanagement;

import com.healthcaremanagement.controller.Controllers;
import com.healthcaremanagement.model.Patient;
import com.healthcaremanagement.service.PatientService;
import com.healthcaremanagement.repository.PatientRepositoryImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("patient.cfg.xml").buildSessionFactory();
        Scanner scanner = new Scanner(System.in);

        Controllers.run(sessionFactory, scanner);

        scanner.close();
        sessionFactory.close();
    }
}
