package com.healthcaremanagement.controller;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import com.healthcaremanagement.service.AppointmentService;
import com.healthcaremanagement.service.DoctorService;
import com.healthcaremanagement.service.PatientService;

import java.util.Scanner;

public class AppointmentController {

    private PatientService patientService;
    private DoctorService doctorService;
    private AppointmentService appointmentService;
    private Scanner scanner;

    public AppointmentController(PatientService patientService, DoctorService doctorService, AppointmentService appointmentService, Scanner scanner) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
        this.scanner = scanner;
    }

    public void manageAppointment() {

        int back = 1;

        while (back == 1){

            System.out.println("\n*********** Welcome to Appointment Managment system ****************");
            System.out.println("1. Create an appointment");
            System.out.println("2. Read a appointment’s details");
            System.out.println("3. Update an appointment’s details");
            System.out.println("4. Delete an appointment");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAppointmentPrompt();
                    break;
                case 2:
                    readAppointmentPrompt();
                    break;
                case 3:
                    updateAppointmentPrompt();
                    break;
                case 4:
                    deleteAppointmentPrompt();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.print("\nBack to Appointment menu or exit menu (1/0)?");
            back = scanner.nextInt();
            scanner.nextLine();
        }
    }

    public void createAppointmentPrompt() {

        Appointment appointment = new Appointment();
        appointmentFieldsPrompt(appointment);
        this.appointmentService.createAppointment(appointment);
        System.out.println("Appointment created successfully");
    }

    public void readAppointmentPrompt() {

        System.out.print("Enter appointment ID ");
        Appointment appointment = this.appointmentService.getAppointmentById(this.scanner.nextInt());
        if (appointment != null) {
            System.out.println("Doctor name:" + appointment.getDoctor());
            System.out.println("Patient name: " + appointment.getPatient());
            System.out.println("Appointment date: " + appointment.getAppointmentDate());
            System.out.println("Notes: " + appointment.getNotes());
        }else {
            System.out.println("Appointment not found");
        }
    }

    public void updateAppointmentPrompt() {

        System.out.print("Enter appointment ID: ");
        Appointment appointment = this.appointmentService.getAppointmentById(this.scanner.nextInt());
        this.scanner.nextLine();

        Doctor originalDoctor = appointment.getDoctor();
        Patient originalPatient = appointment.getPatient();

        if (!appointmentService.hasOtherAppointmentsBetween(
                originalDoctor.getDoctorId(), originalPatient.getPatientId())) {
            doctorService.removePatientFromDoctor(originalDoctor.getDoctorId(), originalPatient);
            patientService.removeDoctorFromPatient(originalPatient.getPatientId(), originalDoctor);
        }

        appointmentFieldsPrompt(appointment);
        this.appointmentService.updateAppointment(appointment);


        System.out.println("Appointment updated successfully");
    }

    public void deleteAppointmentPrompt() {

        System.out.print("Enter appointment ID: ");
        int appointmentId = scanner.nextInt();
        Appointment appointment = this.appointmentService.getAppointmentById(appointmentId);
        this.appointmentService.deleteAppointment(appointmentId);

        Doctor doctorToCheck = appointment.getDoctor();
        Patient patientToCheck = appointment.getPatient();

        if (!appointmentService.hasOtherAppointmentsBetween(
                doctorToCheck.getDoctorId(), patientToCheck.getPatientId())) {
            doctorService.removePatientFromDoctor(doctorToCheck.getDoctorId(), patientToCheck);
            patientService.removeDoctorFromPatient(patientToCheck.getPatientId(), doctorToCheck);
        }

        System.out.println("Appointment deleted successfully");
    }

    public void appointmentFieldsPrompt(Appointment appointment){

        // set patient
        Patient patient = getPatientByID();
        appointment.setPatient(patient);

        // set doctor
        Doctor doctor = getDoctorByID();
        appointment.setDoctor(doctor);

        System.out.print("Enter date: ");
        appointment.setAppointmentDate(this.scanner.nextLine());
        System.out.print("Enter notes: ");
        appointment.setNotes(this.scanner.nextLine());

        // add the patient to the doctor set of doctors (update join table)
        this.doctorService.addPatientToDoctor(doctor.getDoctorId(), patient);
        this.patientService.addDoctorToPatient(patient.getPatientId(), doctor);
    }

    public Patient getPatientByID(){
        System.out.print("Enter patient ID: ");
        int patientId = this.scanner.nextInt();
        this.scanner.nextLine();
        return this.patientService.getPatientById(patientId);
    }

    public Doctor getDoctorByID(){
        System.out.print("Enter Doctor ID: ");
        int doctorId = this.scanner.nextInt();
        this.scanner.nextLine();
        return this.doctorService.getDoctorById(doctorId);
    }

}
