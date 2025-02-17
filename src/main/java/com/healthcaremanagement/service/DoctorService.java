package com.healthcaremanagement.service;

import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.repository.DoctorRepositoryImpl;

import java.util.List;

public class DoctorService {

    private DoctorRepositoryImpl doctorRepository;

    public DoctorService(DoctorRepositoryImpl doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void createDoctor(Doctor doctor) {
        this.doctorRepository.createDoctor(doctor);
    }

    public Doctor getDoctorById(int doctorId){
        return this.doctorRepository.getDoctorById(doctorId);
    }

    public void updateDoctor(Doctor doctor){
        this.doctorRepository.updateDoctor(doctor);
    }

    public void deleteDoctor(int doctorId){
        this.doctorRepository.deleteDoctor(doctorId);
    }

    public List<Doctor> getAllDoctors(){
        return this.doctorRepository.getAllDoctors();
    }
}
