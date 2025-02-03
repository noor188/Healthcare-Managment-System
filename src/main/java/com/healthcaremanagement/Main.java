package com.healthcaremanagement;

import com.healthcaremanagement.controller.Controller;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("patient.cfg.xml").buildSessionFactory();
        Scanner scanner = new Scanner(System.in);

        Controller.run2(sessionFactory, scanner);

        sessionFactory.close();
        scanner.close();
        System.exit(0);
    }
}