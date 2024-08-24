package com.hsbc.dao;

import com.hsbc.beans.Patient;
import com.hsbc.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {
    private Connection conn;

    public PatientDaoImpl() {
        this.conn = DbUtil.getConn();
    }

    @Override
    public void addPatient(Patient patient) {
        try {
            String query = "INSERT INTO Patients(name, dob, contact_number) VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, patient.getName());
            ps.setDate(2, new java.sql.Date(patient.getDob().getTime()));
            ps.setString(3, patient.getContactNumber());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
            String query = "SELECT * FROM Patients";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("patient_id"));
                patient.setName(rs.getString("name"));
                patient.setDob(rs.getDate("dob"));
                patient.setContactNumber(rs.getString("contact_number"));
                patients.add(patient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }
}
