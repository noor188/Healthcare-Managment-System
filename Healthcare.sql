CREATE DATABASE IF NOT EXISTS HealthcareManagementTwoDB;
USE HealthcareManagementTwoDB;

CREATE TABLE Patients (
    PatientID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    DateOfBirth DATE,
    Email VARCHAR(100),
    PhoneNumber VARCHAR(20)
);

CREATE TABLE Doctors (
    DoctorID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Specialty VARCHAR(100),
    Email VARCHAR(100)
);

CREATE TABLE Appointments (
    AppointmentID INT AUTO_INCREMENT PRIMARY KEY,
    PatientID INT,
    DoctorID INT,
    AppointmentDate DATE,
    Notes VARCHAR(255),
    FOREIGN KEY (PatientID) REFERENCES Patients(PatientID)
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    FOREIGN KEY (DoctorID) REFERENCES Doctors(DoctorID)
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);
INSERT INTO Patients (FirstName, LastName, DateOfBirth, Email, PhoneNumber) VALUES
('John', 'Doe', '1980-01-15', 'johndoe@example.com', '555-1234'),
('Jane', 'Smith', '1990-02-20', 'janesmith@example.com', '555-5678'),
('Jim', 'Brown', '1975-03-10', 'jimbrown@example.com', '555-8765');

INSERT INTO Doctors (FirstName, LastName, Specialty, Email) VALUES
('Alice', 'Johnson', 'Cardiology', 'alicejohnson@example.com'),
('Bob', 'Williams', 'Neurology', 'bobwilliams@example.com'),
('Charlie', 'Davis', 'Pediatrics', 'charliedavis@example.com');

INSERT INTO Appointments (PatientID, DoctorID, AppointmentDate, Notes) VALUES
(1, 1, '2024-01-15', 'Routine check-up'),
(2, 2, '2024-01-16', 'Neurology consultation'),
(3, 3, '2024-01-17', 'Pediatrics consultation');
