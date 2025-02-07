## Healthcare Management System


###  101: Ticket Breakdown: Healthcare Management System - Hibernate Implementation
1. Ticket 1: Implement Doctor Class and DoctorRepository
2. Ticket 2: Implement Appointment Class and AppointmentRepository
3. Ticket 3: Refactor Main Class to Include Doctors and Appointments

------------------------------------------------------------------------

### 102: Ticket Breakdown: Healthcare Management System - Advanced Hibernate Relationships Implementation
1. Ticket 1: Implement Office Class and OfficeRepository for One-to-One Relationship
2. Ticket 2: Implement Relationships Between Entities (One-to-Many, Many-to-One, Many-to-Many)
3. Ticket 3: Implement Helper Methods in Repositories
4. Ticket 4: Refactor HealthRunner Class to Include Office Management
5. Ticket 5: Refactor HealthRunner Class to Use Relationship Methods

------------------------------------------------------------------------

### 103: Ticket Breakdown: Healthcare Hibernate JUnit 
1. Ticket 1: OfficeServiceTest (Isolated Tests Using @BeforeEach)
2. Ticket 2: OfficeServiceTestAll (Tests That Depend on Each Other Using @BeforeAll and @AfterAll)

------------------------------------------------------------------------

### Exceptions
1. Input validation
   
#### **Project Structure:**

```
/src/main/java/com/healthcaremanagement/
├── dao/
│   ├── DatabaseConnection.java (if needed for non-Hibernate setup)
│   ├── PatientRepositoryImpl.java (starting point for repository)
│   ├── DoctorRepositoryImpl.java 
│
├── model/
│   ├── Patient.java (already implemented)
│   ├── Doctor.java 
│
├── service/
│   ├── PatientService.java (already implemented)
│   ├── DoctorService.java 
│
├── exceptions
|
└── Main.java (starter code for managing patients)
```


