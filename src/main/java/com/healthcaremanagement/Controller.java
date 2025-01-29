package com.healthcaremanagement;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import com.healthcaremanagement.repository.AppointmentRepositoryImpl;
import com.healthcaremanagement.repository.DoctorRepositoryImpl;
import com.healthcaremanagement.repository.PatientRepositoryImpl;
import com.healthcaremanagement.service.AppointmentService;
import com.healthcaremanagement.service.DoctorService;
import com.healthcaremanagement.service.PatientService;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class Controller {

    public static void managePatients(SessionFactory sessionFactory, Scanner scanner){
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sessionFactory);
        PatientService patientService = new PatientService(patientRepository);

        System.out.println("1. Create Patient");
        System.out.println("2. Read Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    // Application calls the service layer, not the repository directly
                    Patient newPatient = new Patient();
                    System.out.print("Enter first name: ");
                    newPatient.setFirstName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newPatient.setLastName(scanner.nextLine());
                    System.out.print("Enter date of birth (YYYY-MM-DD): ");
                    newPatient.setDateOfBirth(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newPatient.setEmail(scanner.nextLine());
                    System.out.print("Enter phone number: ");
                    newPatient.setPhoneNumber(scanner.nextLine());
                    patientService.createPatient(newPatient);  // Use service here
                    System.out.println("Patient created successfully.");
                    break;
                case 2:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    Patient patient = patientService.getPatientById(patientId);  // Use service here
                    if (patient != null) {
                        System.out.println("Patient ID: " + patient.getPatientId());
                        System.out.println("Name: " + patient.getFirstName() + " " + patient.getLastName());
                        System.out.println("Date of Birth: " + patient.getDateOfBirth());
                        System.out.println("Email: " + patient.getEmail());
                        System.out.println("Phone: " + patient.getPhoneNumber());
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 3:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    patientId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    patient = patientService.getPatientById(patientId);  // Use service here
                    if (patient != null) {
                        System.out.print("Enter new first name: ");
                        patient.setFirstName(scanner.nextLine());
                        System.out.print("Enter new last name: ");
                        patient.setLastName(scanner.nextLine());
                        System.out.print("Enter new date of birth (YYYY-MM-DD): ");
                        patient.setDateOfBirth(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        patient.setEmail(scanner.nextLine());
                        System.out.print("Enter new phone number: ");
                        patient.setPhoneNumber(scanner.nextLine());
                        patientService.updatePatient(patient);  // Use service here
                        System.out.println("Patient updated successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 4:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    patientId = scanner.nextInt();
                    patientService.deletePatient(patientId);  // Use service here
                    System.out.println("Patient deleted successfully.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } finally {

        }
    }

    public static void manageDoctors(SessionFactory sessionFactory, Scanner scanner){
        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl(sessionFactory);
        DoctorService doctorService = new DoctorService(doctorRepository);

        System.out.println("1. Create Doctor");
        System.out.println("2. Read Doctor");
        System.out.println("3. Update Doctor");
        System.out.println("4. Delete Doctor");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    // Application calls the service layer, not the repository directly
                    Doctor newDoctor = new Doctor();
                    System.out.print("Enter first name: ");
                    newDoctor.setFirstName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newDoctor.setLastName(scanner.nextLine());
                    System.out.print("Enter your speciality ");
                    newDoctor.setSpecialty(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newDoctor.setEmail(scanner.nextLine());
                    doctorService.createDoctor(newDoctor);  // Use service here
                    System.out.println("Doctor created successfully.");
                    break;
                case 2:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    Doctor doctor = doctorService.getDoctorById(doctorId);// Use service here
                    if (doctor != null) {
                        System.out.println("Patient ID: " + doctor.getDoctorId());
                        System.out.println("Name: " + doctor.getFirstName() + " " + doctor.getLastName());
                        System.out.println("Email: " + doctor.getEmail());
                        System.out.println("Speciality: " + doctor.getSpecialty());
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 3: // update
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    doctorId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    doctor = doctorService.getDoctorById(doctorId);  // Use service here
                    if (doctor != null) {
                        System.out.print("Enter new first name: ");
                        doctor.setFirstName(scanner.nextLine());
                        System.out.print("Enter new last name: ");
                        doctor.setLastName(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        doctor.setEmail(scanner.nextLine());
                        System.out.print("Enter speciality: ");
                        doctor.setSpecialty(scanner.nextLine());
                        doctorService.updateDoctor(doctor);  // Use service here
                        System.out.println("Doctor updated successfully.");
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 4:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    doctorId = scanner.nextInt();
                    doctorService.deleteDoctor(doctorId);  // Use service here
                    System.out.println("Doctor deleted successfully.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } finally {
            //scanner.close();
           // sessionFactory.close();
        }
    }

    public static void manageAppointments(SessionFactory sessionFactory, Scanner scanner){
        AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl(sessionFactory);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);

        System.out.println("1. Create Appointment");
        System.out.println("2. Read Appointment");
        System.out.println("3. Update Appointment");
        System.out.println("4. Delete Appointment");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    // Application calls the service layer, not the repository directly
                    Appointment newAppointment = new Appointment();
                    System.out.print("Enter patientId: ");
                    Patient patient = appointmentService.getPatienById(scanner.nextInt());
                    newAppointment.setPatient(patient);
                    scanner.nextLine();
                    System.out.print("Enter doctorId: ");
                    Doctor doctor = appointmentService.getDoctorById(scanner.nextInt());
                    newAppointment.setDoctorId(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Enter Appointment date ");
                    newAppointment.setAppointmentDate(scanner.nextLine());
                    System.out.print("Enter Notes: ");
                    newAppointment.setNotes(scanner.nextLine());
                    appointmentService.createAppointment(newAppointment);  // Use service here
                    System.out.println("Appointment created successfully.");
                    break;
                case 2:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Appointment ID: ");
                    int appointmentId = scanner.nextInt();
                    Appointment appointment = appointmentService.getAppointmentById(appointmentId);// Use service here
                    if (appointment != null) {
//                        System.out.println("Appointment ID: " + appointment.getAppointmentId());
//                        System.out.println("patientId: " + appointment.getPatientId());
//                        System.out.println("DoctorId: " + appointment.getDoctorId());
//                        System.out.println("Notes: " + appointment.getNotes());
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                case 3: // update
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Appointment ID: ");
                    appointmentId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    appointment = appointmentService.getAppointmentById(appointmentId);  // Use service here
                    if (appointment != null) {
//                        System.out.print("Enter patientId: ");
//                        appointment.setPatientId(scanner.nextInt());
//                        System.out.print("Enter doctorId: ");
//                        appointment.setDoctorId(scanner.nextInt());
                        System.out.print("Enter Appointment date ");
                        appointment.setAppointmentDate(scanner.nextLine());
                        System.out.print("Enter Notes: ");
                        appointment.setNotes(scanner.nextLine());
                        appointmentService.updateAppointment(appointment);  // Use service here
                        System.out.println("Appointment updated successfully.");
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                case 4:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    appointmentId = scanner.nextInt();
                    appointmentService.deleteAppointment(appointmentId);  // Use service here
                    System.out.println("Appointment deleted successfully.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } finally {
            //scanner.close();
           // sessionFactory.close();
        }
    }
}
