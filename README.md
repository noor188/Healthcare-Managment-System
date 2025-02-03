## Healthcare Management System - Hibernate Implementation


###  101: Ticket Breakdown: Healthcare Management System - Hibernate Implementation
1. Ticket 1: Implement Doctor Class and DoctorRepository
2. Ticket 3: Implement Appointment Class and AppointmentRepository
3. Ticket 4: Refactor Main Class to Include Doctors and Appointments

------------------------------------------------------------------------

### 102: Ticket Breakdown: Healthcare Management System - Advanced Hibernate Relationships Implementation
2. Ticket 2: Implement Relationships Between Entities (One-to-Many, Many-to-One, Many-to-Many)
3. Ticket 3: Implement Helper Methods in Repositories
5. Ticket 5: Refactor HealthRunner Class to Use Relationship Methods

------------------------------------------------------------------------

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
└── Main.java (starter code for managing patients)
```


