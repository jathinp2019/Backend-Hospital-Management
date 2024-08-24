package com.hsbc.controller;

import com.hsbc.beans.*;
import com.hsbc.service.*;
import java.util.List;

public class ViewController {
    private UserService userService;
    private DoctorService doctorService;
    private PatientService patientService;
    private AppointmentService appointmentService;

    public ViewController() {
        userService = ServiceFactory.getUserService();
        doctorService = ServiceFactory.getDoctorService();
        patientService = ServiceFactory.getPatientService();
        appointmentService = ServiceFactory.getAppointmentService();
    }

    // Simulated login method
    public void login(String username, String password) {
        User user = userService.authenticate(username, password);
        if (user != null) {
            System.out.println("Login successful. Role: " + user.getRole());
            // Redirect to respective functionality based on role
        } else {
            System.out.println("Invalid credentials");
        }
    }

    // Admin Functionalities
    public void importUsers(List<Doctor> doctors) {
        doctors.forEach(doctorService::addDoctor);
    }

    public void showAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        doctors.forEach(System.out::println);
    }

    public void cancelAppointment(int appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
    }

    public void showAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        patients.forEach(System.out::println);
    }

    // User Functionalities
    public void addPatient(Patient patient) {
        patientService.addPatient(patient);
    }

    public void bookAppointment(Appointment appointment) {
        appointmentService.bookAppointment(appointment);
    }

    public void viewAppointments(int doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctorId);
        appointments.forEach(System.out::println);
    }

    // Doctor Functionalities
    public void addSchedule(Schedule schedule) {
        doctorService.addSchedule(schedule);
    }

    public void viewDoctorAppointments(int doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctorId);
        appointments.forEach(System.out::println);
    }

    public void suggestMedicalTests(int patientId, String tests) {
        System.out.println("Suggesting tests: " + tests + " for patient ID: " + patientId);
    }

    public void suggestMedicines(int patientId, String medicines) {
        System.out.println("Suggesting medicines: " + medicines + " for patient ID: " + patientId);
    }
}
