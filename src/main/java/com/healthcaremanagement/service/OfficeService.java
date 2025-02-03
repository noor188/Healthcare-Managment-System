package com.healthcaremanagement.service;

import com.healthcaremanagement.model.Office;
import com.healthcaremanagement.repository.OfficeRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OfficeService {

    private OfficeRepositoryImpl officeRepository;

    public OfficeService(OfficeRepositoryImpl officeRepository) {
        this.officeRepository = officeRepository;
    }

    public void createOffice(Office office){
        this.officeRepository.createOffice(office);
    }

    public Office getOfficeById(int officeId){
        return this.officeRepository.getOfficeById(officeId);
    }

    public void updateOffice(Office office){
        this.officeRepository.updateOffice(office);
    }

    public void deleteOffice(int officeId){
        this.officeRepository.deleteOffice(officeId);
    }

    public List<Office> getAllOffices(){
        return this.officeRepository.getAllOffices();
    }
}
