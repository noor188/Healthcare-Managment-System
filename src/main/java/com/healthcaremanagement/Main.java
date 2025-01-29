package com.healthcaremanagement;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("patient.cfg.xml").buildSessionFactory();
        Scanner scanner = new Scanner(System.in);
        int back = 1;

        while (back == 1){
            System.out.println("1. Manage Patient");
            System.out.println("2. Manage Doctor");
            System.out.println("3. Manage Appointment");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    Controller.managePatients(sessionFactory, scanner);
                    break;
                case 2:
                    Controller.manageDoctors(sessionFactory, scanner);
                    break;
                case 3:
                    Controller.manageAppointments(sessionFactory, scanner);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.print("Back to main menu? (0/1) ");
            back = scanner.nextInt();
            scanner.nextLine();
        }
        sessionFactory.close();
        scanner.close();
        System.exit(0);

    }
}