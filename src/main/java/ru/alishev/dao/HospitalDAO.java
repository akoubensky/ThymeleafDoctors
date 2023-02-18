package ru.alishev.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.models.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HospitalDAO {
    private final JdbcTemplate jdbcTemplate;
    private final DoctorMapper doctorMapper;

    public List<Doctor> allDoctors() {
        return jdbcTemplate.query("Select * From doctor", doctorMapper);
    }

    public Doctor doctor(int id) {
        List<Doctor> doctors = jdbcTemplate.query("Select * From doctor Where id=?", doctorMapper, id);
        return doctors.get(0);
    }

    public void create(Doctor doctor) {
        jdbcTemplate.update("Insert Into doctor (name, job) values (?, ?)", doctor.getName(), doctor.getJob());
    }

    public void update(int id, Doctor doctor) {
        jdbcTemplate.update("Update doctor Set name = ?, job = ? Where id = ?", doctor.getName(), doctor.getJob(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("Delete From doctor Where id = ?", id);
    }
}
