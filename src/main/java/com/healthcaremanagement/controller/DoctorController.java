package com.healthcaremanagement.controller;

import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.service.DoctorService;

import java.util.Scanner;

public class DoctorController {

    public static void manageDoctors(DoctorService doctorService, Scanner scanner) {

        int back = 1;

        while (back == 1){

        System.out.println("\n*********** Welcome to Doctor Managment system ****************");
        System.out.println("1. Create a new doctor");
        System.out.println("2. Read a doctor’s details");
        System.out.println("3. Update a doctor’s details");
        System.out.println("4. Delete a doctor");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                DoctorController.createDoctorPrompt(doctorService, scanner);
                break;
            case 2:
                DoctorController.readDoctorPrompt(doctorService, scanner);
                break;
            case 3:
                DoctorController.updateDoctorPrompt(doctorService, scanner);
                break;
            case 4:
                DoctorController.deleteDoctorPrompt(doctorService, scanner);
                break;
            default:
                System.out.println("Invalid choice");
        }
        System.out.print("\nBack to Doctor menu or exit menu (1/0)?");
        back = scanner.nextInt();
        scanner.nextLine();
        }
    }

    public static void createDoctorPrompt(DoctorService doctorService, Scanner scanner) {

        Doctor doctor = new Doctor();
        DoctorController.doctorFieldsPrompt(doctor, scanner);
        doctorService.createDoctor(doctor);
        System.out.println("Doctor created successfully");
    }

    public static void readDoctorPrompt(DoctorService doctorService, Scanner scanner) {

        System.out.print("Enter doctor ID ");
        Doctor doctor = doctorService.getDoctorById(scanner.nextInt());
        if (doctor != null) {
            System.out.println("Doctor name:" + doctor.getFirstName() + " " + doctor.getLastName());
            System.out.println("Specialty: " + doctor.getSpecialty());
            System.out.println("Email: " + doctor.getEmail());
        }else {
            System.out.println("Doctor not found");
        }
    }

    public static void updateDoctorPrompt(DoctorService doctorService, Scanner scanner) {

        System.out.print("Enter doctor ID: ");
        Doctor doctor = doctorService.getDoctorById(scanner.nextInt());
        scanner.nextLine();
        doctorFieldsPrompt(doctor, scanner);
        doctorService.updateDoctor(doctor);
        System.out.println("Doctor updated successfully");
    }

    public static void deleteDoctorPrompt(DoctorService doctorService, Scanner scanner) {

        System.out.print("Enter doctor ID: ");
        doctorService.deleteDoctor(scanner.nextInt());
        System.out.println("Doctor deleted successfully");
    }

    public static void doctorFieldsPrompt(Doctor doctor, Scanner scanner){
        System.out.print("Enter first name: ");
        doctor.setFirstName(scanner.nextLine());
        System.out.print("Enter last name: ");
        doctor.setLastName(scanner.nextLine());
        System.out.print("Enter specialty: ");
        doctor.setSpecialty(scanner.nextLine());
        System.out.print("Enter email: ");
        doctor.setEmail(scanner.nextLine());
    }


}
