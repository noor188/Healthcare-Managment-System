package com.healthcaremanagement.controller;

import org.hibernate.SessionFactory;

import java.util.Scanner;

public class DoctorController {

    private SessionFactory sessionFactory;
    private Scanner scanner;

    public DoctorController(SessionFactory sessionFactory, Scanner scanner) {
        this.sessionFactory = sessionFactory;
        this.scanner = scanner;
    }

    public void manageDoctor() {

        System.out.println("1. Create a doctor");
        System.out.println("2. Update a doctor");
        System.out.println("3. Delete a doctor");
        System.out.println("4. Get a doctor");
        System.out.println("5. List all doctors");
        System.out.println("6. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                System.out.println("Invalid option");
        }

    }
}
