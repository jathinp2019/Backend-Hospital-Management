package com.hsbc.dao;

import com.hsbc.beans.Doctor;
import com.hsbc.beans.Schedule;
import com.hsbc.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {
    private Connection conn;

    public DoctorDaoImpl() {
        this.conn = DbUtil.getConn();
    }

    @Override
    public void addDoctor(Doctor doctor) {
        try {
            String query = "INSERT INTO Doctors(name, specialization, schedule) VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSpecialization());
            ps.setString(3, doctor.getSchedule());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            String query = "SELECT * FROM Doctors";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(rs.getInt("doctor_id"));
                doctor.setName(rs.getString("name"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctor.setSchedule(rs.getString("schedule"));
                doctors.add(doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public void addSchedule(Schedule schedule) {
        try {
            String query = "INSERT INTO Schedules(doctor_id, available_date, start_time, end_time) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, schedule.getDoctorId());
            ps.setDate(2, new java.sql.Date(schedule.getAvailableDate().getTime()));
            ps.setTime(3, new java.sql.Time(schedule.getStartTime().getTime()));
            ps.setTime(4, new java.sql.Time(schedule.getEndTime().getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
