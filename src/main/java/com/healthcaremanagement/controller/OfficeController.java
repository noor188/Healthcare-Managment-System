package com.healthcaremanagement.controller;

import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Office;
import com.healthcaremanagement.model.Patient;
import com.healthcaremanagement.service.DoctorService;
import com.healthcaremanagement.service.OfficeService;
import com.healthcaremanagement.service.PatientService;

import java.util.Scanner;

public class OfficeController {

    private DoctorService doctorService;
    private OfficeService officeService;
    private Scanner scanner;

    public OfficeController(OfficeService officeService, DoctorService doctorService, Scanner scanner) {
        this.officeService = officeService;
        this.doctorService = doctorService;
        this.scanner = scanner;
    }

    public void manageOffices(){

        int back = 1;

        while (back == 1){


        System.out.println("1. Create a new office.");
        System.out.println("2. Read an office’s details by ID");
        System.out.println("3. Update an office’s details");
        System.out.println("4. Delete an office by ID");
        System.out.println("5. List all offices");

        int choice = scanner.nextInt();
        scanner.nextLine();



        switch (choice) {
            case 1:
                // Application calls the service layer, not the repository directly
                Office newOffice = new Office();
                System.out.print("Enter location: ");
                newOffice.setLocation(scanner.nextLine());
                System.out.print("Enter phone: ");
                newOffice.setPhone(scanner.nextLine());

                // Doctor one to one relationship
                System.out.print("Enter Doctor ID: ");
                int doctorID = scanner.nextInt();
                scanner.nextLine();
                Doctor doctor = doctorService.getDoctorById(doctorID);

                doctor.setOffice(newOffice);
                newOffice.setDoctor(doctor);

                officeService.createOffice(newOffice);
                System.out.println("Office created successfully.");
                break;
            case 2:
                // Application calls the service layer, not the repository directly
                System.out.print("Enter Office ID: ");
                int officeId = scanner.nextInt();
                Office office = officeService.getOfficeById(officeId);
                if (office != null) {
                    System.out.println("Office ID: " + office.getOfficeId());
                    System.out.println("Location: " + office.getLocation());
                    System.out.println("phone: " + office.getPhone());
                    System.out.println("Owned by (Doctor ID): " + office.getDoctor().getDoctorId());
                    System.out.println("Doctor name: " + office.getDoctor().getFirstName());
                } else {
                    System.out.println("Office not found.");
                }
                break;
            case 3:
                // Application calls the service layer, not the repository directly
                System.out.print("Enter Office ID: ");
                officeId = scanner.nextInt();
                scanner.nextLine();  // consume newline
                Office officeToUpdate = officeService.getOfficeById(officeId);
                if (officeToUpdate != null) {
                    System.out.print("Enter location: ");
                    officeToUpdate.setLocation(scanner.nextLine());
                    System.out.print("Enter phone: ");
                    officeToUpdate.setPhone(scanner.nextLine());

                    // Doctor one to one relationship
                    System.out.print("Enter Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    scanner.nextLine();
                    Doctor updatedDoctor = doctorService.getDoctorById(doctorId);
                    updatedDoctor.setOffice(officeToUpdate);
                    officeToUpdate.setDoctor(updatedDoctor);

                    officeService.updateOffice(officeToUpdate);  // Use service here
                    System.out.println("Office updated successfully.");
                } else {
                    System.out.println("Office not found.");
                }
                break;
            case 4:
                // Application calls the service layer, not the repository directly
                System.out.print("Enter Office ID: ");
                int officeIDToDelete = scanner.nextInt();
                officeService.deleteOffice(officeIDToDelete);  // Use service here
                System.out.println("Office deleted successfully.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
        System.out.print("\nBack to Doctor menu or exit menu (1/0)?");
        back = scanner.nextInt();
        scanner.nextLine();
    }
}
}
