package com.hsbc.dao;

import com.hsbc.beans.Schedule;
import com.hsbc.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDaoImpl implements ScheduleDao {
    private Connection conn;

    public ScheduleDaoImpl() {
        this.conn = DbUtil.getConn();
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

    @Override
    public List<Schedule> getSchedulesByDoctor(int doctorId) {
        List<Schedule> schedules = new ArrayList<>();
        try {
            String query = "SELECT * FROM Schedules WHERE doctor_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setScheduleId(rs.getInt("schedule_id"));
                schedule.setDoctorId(rs.getInt("doctor_id"));
                schedule.setAvailableDate(rs.getDate("available_date"));
                schedule.setStartTime(rs.getTime("start_time"));
                schedule.setEndTime(rs.getTime("end_time"));
                schedules.add(schedule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedules;
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        try {
            String query = "UPDATE Schedules SET available_date = ?, start_time = ?, end_time = ? WHERE schedule_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDate(1, new java.sql.Date(schedule.getAvailableDate().getTime()));
            ps.setTime(2, new java.sql.Time(schedule.getStartTime().getTime()));
            ps.setTime(3, new java.sql.Time(schedule.getEndTime().getTime()));
            ps.setInt(4, schedule.getScheduleId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
