package org.rma.service;

import com.healthcaremanagement.model.Doctor;
import com.healthcaremanagement.model.Office;
import com.healthcaremanagement.repository.DoctorRepositoryImpl;
import com.healthcaremanagement.repository.OfficeRepositoryImpl;
import com.healthcaremanagement.service.DoctorService;
import com.healthcaremanagement.service.OfficeService;
import com.mysql.cj.protocol.x.Notice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class OfficeServiceTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction tx;
    private DoctorRepositoryImpl doctorRepository;
    private OfficeRepositoryImpl officeRepository;
    private DoctorService doctorService;
    private OfficeService officeService;

    @BeforeEach
    public void setUp(){
        this.sessionFactory = new Configuration().configure("hibernate-test.cfg.xml").buildSessionFactory();
        this.session = sessionFactory.openSession();
        this.tx = session.beginTransaction();
        this.officeRepository = new OfficeRepositoryImpl(sessionFactory);
        this.doctorRepository = new DoctorRepositoryImpl(sessionFactory);
        this.officeService = new OfficeService(officeRepository);
        this.doctorService = new DoctorService(doctorRepository);
    }

    @AfterEach
    public void tearDown(){
        this.tx.rollback();
        this.session.close();
        this.sessionFactory.close();
    }

    @Test
    public void testCreateOffice(){
        // SetUp

        // Doctor
        Doctor doctor = new Doctor();
        doctor.setFirstName("Alex");
        doctor.setLastName("Smith");
        doctor.setSpecialty("Lungs");
        doctor.setEmail("alex.smith@gmail.com");
        this.doctorService.createDoctor(doctor);

        // Office
        Office office = new Office();
        office.setLocation("123 Main St");
        office.setPhone("123-456-7890");
        office.setDoctor(doctor);

        // call method to test
        this.officeService.createOffice(office);

        // Check that the executed method produced the expected result
        String expectedLocation = "123 Main St";
        String expectedPhone    = "123-456-7890";

        Office fetchedOffice = this.officeService.getOfficeById(office.getOfficeId());
        String actualLocation = fetchedOffice.getLocation();
        String actualPhone    = fetchedOffice.getPhone();

        // Asserts
        assertNotNull(fetchedOffice, "Office should exist in the database.");
        assertEquals(expectedLocation, actualLocation, "Location should match the one assigned.");
        assertEquals(expectedPhone, actualPhone, "Phone number should match the one assigned.");

    }

    @Test
    public void testGetOfficeById(){
        // 1. Setup
        // SetUp

        // Doctor
        Doctor doctor = new Doctor();
        doctor.setFirstName("Alex");
        doctor.setLastName("Smith");
        doctor.setSpecialty("Lungs");
        doctor.setEmail("alex.smith@gmail.com");
        this.doctorService.createDoctor(doctor);

        // Office
        Office expectedOffice = new Office();
        expectedOffice.setLocation("123 Main St");
        expectedOffice.setPhone("123-456-7890");
        expectedOffice.setDoctor(doctor);
        this.officeService.createOffice(expectedOffice);

        // 2. Call method to test
        Office actualOffice = officeService.getOfficeById(expectedOffice.getOfficeId());

        // 3. Check that the executed method produced the expected result
        assertNotNull(expectedOffice, "Office should exist in the database." );
        assertTrue(Objects.equals(expectedOffice, actualOffice), "The retrived object does not match expected office");
    }

    @Test
    public void testUpdateOffice(){
        // 1. setup

        // Doctor
        Doctor doctor = new Doctor();
        doctor.setFirstName("Alex");
        doctor.setLastName("Smith");
        doctor.setSpecialty("Lungs");
        doctor.setEmail("alex.smith@gmail.com");
        this.doctorService.createDoctor(doctor);

        // Office
        Office expectedOffice = new Office();
        expectedOffice.setLocation("123 Main St");
        expectedOffice.setPhone("123-456-7890");
        expectedOffice.setDoctor(doctor);
        this.officeService.createOffice(expectedOffice);

        // update
        String updatedLocation = "456 Maple St";
        String updatedPhone    = "555-123-4567";
        expectedOffice.setLocation(updatedLocation);
        expectedOffice.setPhone(updatedPhone);

        // 2. Call method to test
        officeService.updateOffice(expectedOffice);
        int expectedOfficeID = expectedOffice.getOfficeId();
        Office actualOffice = officeService.getOfficeById(expectedOfficeID);

        // 3. Check that the executed method produced the expected result
        assertNotNull(actualOffice, "Office should exist in the database." );
        assertEquals(actualOffice.getLocation(), updatedLocation);
        assertEquals(actualOffice.getPhone(), updatedPhone);
    }

    @Test
    public void testDeleteOffice(){
        // 1. Setup
        // Doctor
        Doctor doctor = new Doctor();
        doctor.setFirstName("Alex");
        doctor.setLastName("Smith");
        doctor.setSpecialty("Lungs");
        doctor.setEmail("alex.smith@gmail.com");
        this.doctorService.createDoctor(doctor);

        // Office
        Office expectedOffice = new Office();
        expectedOffice.setLocation("123 Main St");
        expectedOffice.setPhone("123-456-7890");
        expectedOffice.setDoctor(doctor);
        this.officeService.createOffice(expectedOffice);

        // 2. Call method to test
        officeService.deleteOffice(expectedOffice.getOfficeId());
        Office actualOffice = officeService.getOfficeById(expectedOffice.getOfficeId());

        // 3. Check that the executed method produced the expected result
        assertNull(actualOffice, "Office should be deleted and no longer retrievable." );

    }

    @ParameterizedTest
    @ValueSource(strings = {"New York", "Los Angeles", "Chicago", "San Francisco"})
    public void testCreateOfficeWithDifferentLocations(String location){

        // SetUp

        // Doctor
        Doctor doctor = this.createTestDoctor("Alex", "Karev", "Surgery", "alex.karev@example.com");
        doctorService.createDoctor(doctor);

        // Office
        Office office = new Office();
        office.setLocation(location);
        office.setPhone("123-456-7890");
        office.setDoctor(doctor);

        // call method to test
        this.officeService.createOffice(office);

        // Check that the executed method produced the expected result
        String expectedLocation = location;
        String expectedPhone    = "123-456-7890";

        Office fetchedOffice = this.officeService.getOfficeById(office.getOfficeId());
        String actualLocation = fetchedOffice.getLocation();
        String actualPhone    = fetchedOffice.getPhone();

        // Asserts
        assertNotNull(fetchedOffice, "Office should exist in the database.");
        assertEquals(expectedLocation, actualLocation, "Location should match the one assigned.");
        assertEquals(expectedPhone, actualPhone, "Phone number should match the one assigned.");

    }

    public Doctor createTestDoctor(String firstname, String lastName, String specialty, String email){
        Doctor doctor = new Doctor();
        doctor.setFirstName(firstname);
        doctor.setLastName(lastName);
        doctor.setSpecialty(specialty);
        doctor.setEmail(email);
        return doctor;
    }
}
