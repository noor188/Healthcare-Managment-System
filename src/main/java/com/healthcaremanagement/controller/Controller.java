package com.healthcaremanagement.controller;

import com.healthcaremanagement.repository.AppointmentRepositoryImpl;
import com.healthcaremanagement.repository.DoctorRepositoryImpl;
import com.healthcaremanagement.repository.PatientRepositoryImpl;
import com.healthcaremanagement.service.AppointmentService;
import com.healthcaremanagement.service.DoctorService;
import com.healthcaremanagement.service.PatientService;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class Controller {

    public static void run2(SessionFactory sessionFactory, Scanner scanner){

        int back = 1;

        // Repository layer
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sessionFactory);
        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl(sessionFactory);
        AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl(sessionFactory);

        // Service layer
        PatientService patientService = new PatientService(patientRepository);
        DoctorService doctorService = new DoctorService(doctorRepository);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);

        // controllers
        AppointmentController appointmentController = new AppointmentController(patientService, doctorService, appointmentService, scanner);

        while (back == 1) {
        System.out.println("\n*********** Welcome to Healthcare Managment system ****************");
        System.out.println("1. Manage Patients");
        System.out.println("2. Manage Doctors");
        System.out.println("3. Manage Appointments");
        System.out.println("4. Exit");
        System.out.print("Please pick a number: ");
        int choise = scanner.nextInt();
        scanner.nextLine();

        switch (choise) {
            case 1:
                PatientController.managePatients(patientService, scanner);
                break;
            case 2:
                DoctorController.manageDoctors(doctorService, scanner);
                break;
            case 3:
                appointmentController.manageAppointment();
                break;
            case 4:
                back = 0;
                break;
            default:
                System.out.println("Invalid choice!");
        }
        if (back == 1){
            System.out.print("\nBack to main menu or exit (1/0)? ");
            back = scanner.nextInt();
            scanner.nextLine();
        }
        }
    }
}
