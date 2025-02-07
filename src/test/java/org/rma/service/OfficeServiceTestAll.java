package org.rma.service;


import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Office;
import com.healthcaremanagement.repository.DoctorRepositoryImpl;
import com.healthcaremanagement.repository.OfficeRepositoryImpl;
import com.healthcaremanagement.service.DoctorService;
import com.healthcaremanagement.service.OfficeService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OfficeServiceTestAll {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction tx;
    private DoctorRepositoryImpl doctorRepository;
    private OfficeRepositoryImpl officeRepository;
    private DoctorService doctorService;
    private OfficeService officeService;
    private Doctor doctor;
    private Office expectedOffice;
    private int expectedOfficeID;

    @BeforeAll
    public void setUp(){
        this.sessionFactory = new Configuration().configure("hibernate-test.cfg.xml").buildSessionFactory();
        this.session = this.sessionFactory.openSession();
        this.tx = this.session.beginTransaction();
        this.doctor  = new Doctor();
        this.expectedOffice = new Office();
        this.doctorRepository = new DoctorRepositoryImpl(this.sessionFactory);
        this.officeRepository = new OfficeRepositoryImpl(this.sessionFactory);
        this.doctorService = new DoctorService(this.doctorRepository);
        this.officeService = new OfficeService(this.officeRepository);
    }

    @AfterAll
    public void tearDown(){
        tx.rollback();
        session.close();
        sessionFactory.close();
    }

    @Test
    @Order(1)
    public void testCreateOffice(){
        // SetUp

        // Doctor
        this.doctor.setFirstName("Alex");
        this.doctor.setLastName("Smith");
        this.doctor.setSpecialty("Lungs");
        this.doctor.setEmail("alex.smith@gmail.com");
        this.doctorService.createDoctor(doctor);

        // Office
        this.expectedOffice.setLocation("123 Main St");
        this.expectedOffice.setPhone("123-456-7890");
        this.expectedOffice.setDoctor(this.doctor);

        // call method to test
        this.officeService.createOffice(this.expectedOffice);

        // Check that the executed method produced the expected result
        this.expectedOfficeID = this.expectedOffice.getOfficeId();

        Office fetchedOffice = this.officeService.getOfficeById(expectedOfficeID);

        // Asserts
        assertNotNull(fetchedOffice.getOfficeId(), "Office should be created with a valid ID.");
        assertTrue(fetchedOffice.getOfficeId() > 0, "Office ID should be a positive number.");

    }

    @Test
    @Order(2)
    public void testGetOfficeById(){
        // 1. Setup
        // 2. Call method to test
        Office actualOffice = this.officeService.getOfficeById(expectedOfficeID);

        // 3. Check that the executed method produced the expected result
        assertNotNull(this.expectedOffice, "Office should exist in the database." );
        assertTrue(Objects.equals(this.expectedOffice, actualOffice), "The retrived object does not match expected office");
    }

    @Test
    @Order(3)
    public void testUpdateOffice(){
        // 1. setup
        Office expectedOffice = officeService.getOfficeById(this.expectedOfficeID);

        // update
        String updatedLocation = "456 Maple St";
        String updatedPhone    = "555-123-4567";
        expectedOffice.setLocation(updatedLocation);
        expectedOffice.setPhone(updatedPhone);

        // 2. Call method to test
        this.officeService.updateOffice(expectedOffice);
        Office actualOffice = officeService.getOfficeById(this.expectedOfficeID);

        // 3. Check that the executed method produced the expected result
        assertNotNull(actualOffice, "Office should exist in the database." );
        assertEquals(actualOffice.getLocation(), updatedLocation);
        assertEquals(actualOffice.getPhone(), updatedPhone);
    }

    @Test
    @Order(4)
    public void testDeleteOffice(){
        // 1. Setup

        // 2. Call method to test
        this.officeService.deleteOffice(this.expectedOfficeID);
        Office actualOffice = officeService.getOfficeById(this.expectedOfficeID);

        // 3. Check that the executed method produced the expected result
        assertNull(actualOffice, "Office should be deleted and no longer retrievable." );

    }

    @ParameterizedTest
    @ValueSource(strings = {"Houston", "Boston", "Seattle", "San Diego" })
    @Order(5)
    public void testCreateOfficeWithDifferentLocations(String location){
        // 1. SetUp

        // Doctor
        this.doctor.setFirstName("Alex");
        this.doctor.setLastName("Smith");
        this.doctor.setSpecialty("Lungs");
        this.doctor.setEmail("alex.smith@gmail.com");
        this.doctorService.createDoctor(doctor);

        // Office
        this.expectedOffice.setLocation(location);
        this.expectedOffice.setPhone("123-456-7890");
        this.expectedOffice.setDoctor(this.doctor);

        // 2. call method to test
        this.officeService.createOffice(this.expectedOffice);

        // 3. Check that the executed method produced the expected result
        this.expectedOfficeID = this.expectedOffice.getOfficeId();

        Office fetchedOffice = this.officeService.getOfficeById(expectedOfficeID);

        // Asserts
        assertNotNull(fetchedOffice.getOfficeId(), "Office should be created with a valid ID.");
        assertTrue(fetchedOffice.getOfficeId() > 0, "Office ID should be a positive number.");

    }

    @Test
    public void testGetAllOffices(){

        // 1. SetUp
        // Doctor 2
        Doctor doctor1 = new Doctor();
        doctor1.setFirstName("Jane");
        doctor1.setLastName("Doe");
        doctor1.setSpecialty("Heart");
        doctor1.setEmail("jane.doe@gmail.com");
        this.doctorService.createDoctor(doctor1);

        // Doctor 3
        Doctor doctor2 = new Doctor();
        doctor2.setFirstName("John");
        doctor2.setLastName("Doe");
        doctor2.setSpecialty("Legs");
        doctor2.setEmail("john.doe@gmail.com");
        this.doctorService.createDoctor(doctor2);

        // Office 1
        Office office1 = new Office();
        office1.setLocation("Texas");
        office1.setPhone("123-456-7890");
        office1.setDoctor(doctor1);

        // Office 2
        Office office2 = new Office();
        office2.setLocation("New York");
        office2.setPhone("456-324-0000");
        office2.setDoctor(doctor2);

        // 2. call method to test
        List<Office> offices = officeService.getAllOffices();

        // 3. Check that the executed method produced the expected result
        assertNotNull(offices, "Office list should not be null.");
        assertFalse(offices.isEmpty(), "Office list should not be empty.");
        assertTrue(offices.size() >= 3, "At least three offices should exist.");

    }


}
