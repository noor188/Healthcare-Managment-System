package com.healthcaremanagement.service;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Patient;
import com.healthcaremanagement.repository.PatientRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PatientService {

    private final PatientRepositoryImpl patientRepository;

    public PatientService(PatientRepositoryImpl patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void createPatient(Patient patient) {
        this.patientRepository.createPatient(patient);
    }

    public Patient getPatientById(int id) {
        return patientRepository.getPatientById(id);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.getAllPatients();
    }

    public void updatePatient(Patient patient) {
        patientRepository.updatePatient(patient);
    }

    public void deletePatient(int id) {
        patientRepository.deletePatient(id);
    }

    public void removeDoctorForPatient(int patientId, Doctor doctor) {
        this.patientRepository.removeDoctorForPatient(patientId, doctor);
    }

    public void addDoctorToPatient(int patientID, Doctor doctor) {
        this.patientRepository.addDoctorToPatient(patientID, doctor);
    }

}