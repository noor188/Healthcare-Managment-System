package com.healthcaremanagement.service;

import com.healthcaremanagement.model.Appointment;
import com.healthcaremanagement.model.Patient;
import com.healthcaremanagement.repository.AppointmentRepositoryImpl;
import org.hibernate.Session;

import java.util.List;

public class AppointmentService {

    private final AppointmentRepositoryImpl appointmentRepositoryImpl;
    // patient


    public AppointmentService(AppointmentRepositoryImpl appointmentRepositoryImpl) {
        this.appointmentRepositoryImpl = appointmentRepositoryImpl;

    }

    public void createAppointment(Appointment appointment){
        this.appointmentRepositoryImpl.createAppointment(appointment);
    }

    public Appointment getAppointmentById(int appointmentId){
        return this.appointmentRepositoryImpl.getAppointmentById(appointmentId);
    }

    public void updateAppointment(Appointment appointment){
        this.appointmentRepositoryImpl.updateAppointment(appointment);
    }

    public void deleteAppointment(int appointmentId){
        this.appointmentRepositoryImpl.deleteAppointment(appointmentId);
    }

    public List<Appointment> getAllAppointments(){
        return this.appointmentRepositoryImpl.getAllAppointments();
    }

    public boolean hasOtherAppointmentsBetween(int doctorId, int patientId) {
        return this.appointmentRepositoryImpl.hasOtherAppointmentsBetween(doctorId, patientId);
    }
}
