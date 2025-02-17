package com.healthcaremanagement.controller;

import org.hibernate.SessionFactory;

import java.util.Scanner;

public class Controllers {

    public static void run(SessionFactory sessionFactory, Scanner scanner) {

        PatientController patientController = new PatientController(sessionFactory, scanner);
        DoctorController doctorController = new DoctorController(sessionFactory, scanner);
        int back = 1;

        while(back == 1) {
            System.out.println("************* Healthcare Management System ************");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Offices");
            System.out.println("4. Manage Appointments");
            System.out.println("5. Exit");
            System.out.println("Select a number: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    patientController.managePatient();
                    break;
                case 2:
                    doctorController.manageDoctor();
                    break;
                case 5:
                    back = 0;
                    System.out.println("\n Thank you for using our servie, exiting the healthcare management system, thank you");
                    break;
                default:
                    System.out.println("Invalid choice");
            }


        }
    }
}
