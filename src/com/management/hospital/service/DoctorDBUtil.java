package com.management.hospital.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.management.hospital.model.Doctor;

public class DoctorDBUtil {
	
	private DataSource dataSource;
	
	public DoctorDBUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public List<Doctor> findAll(){
		List<Doctor> doctors = new ArrayList<Doctor>();
		Connection connection = null;
		Statement  statement  = null;
		ResultSet  resultSet  = null;
		try {
			connection = dataSource.getConnection();
			statement  = connection.createStatement();
			String sql = "select * from doctor";
			resultSet  = statement.executeQuery(sql);
			while(resultSet.next()) {
				Doctor doctor = new Doctor(Integer.parseInt(resultSet.getString("id")), resultSet.getString("name"), 
						resultSet.getString("is_available"), resultSet.getString("specialty"), resultSet.getString("address"), 
						resultSet.getString("phone"), resultSet.getString("email"));
				doctors.add(doctor);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Utils.close(connection, statement, resultSet);
		}
	return doctors;
	}
	public void createADoctor(HttpServletRequest request) {
		
		Connection connection = null;
		PreparedStatement  statement  = null;
	
		try {
			connection = dataSource.getConnection();
			String sql = " insert into doctor "
						+ "(name, is_available, specialty, address, phone, email) "
						+"values (? ,? , ? , ? ,?, ?)";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, request.getParameter("name"));
			statement.setString(2, request.getParameter("isAvailable"));
			statement.setString(3, request.getParameter("specialty"));
			statement.setString(4, request.getParameter("address"));
			statement.setString(5, request.getParameter("phone"));
			statement.setString(6, request.getParameter("email"));
			
			statement.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Utils.close(connection, statement, null);
		}
	}
	public Doctor findOne(HttpServletRequest request) {
		Doctor doctor = null;
		Connection connection = null;
		PreparedStatement  statement  = null;
		ResultSet  resultSet  = null;
		try {
			connection = dataSource.getConnection();
			int doctorId = Integer.parseInt(request.getParameter("doctorId"));
			String sql = "select * from doctor where id = ?";
			statement  = connection.prepareStatement(sql);
			statement.setInt(1, doctorId);
			resultSet  = statement.executeQuery();
			if(resultSet.next()) {
			doctor = new Doctor( Integer.parseInt(resultSet.getString("id")), resultSet.getString("name"), 
						resultSet.getString("is_available"), resultSet.getString("specialty"), resultSet.getString("address"), 
						resultSet.getString("phone"), resultSet.getString("email"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Utils.close(connection, statement, resultSet);
		}
	return doctor;
	}
	public void updateADoctor(HttpServletRequest request) {
		Connection connection = null;
		PreparedStatement  statement  = null;
	
		try {
			connection = dataSource.getConnection();
			int doctorId = Integer.parseInt(request.getParameter("id"));
			String sql = " update doctor set name = ?, is_available = ?, specialty = ?, address = ?, phone = ?, email = ? "
					+ "where id = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, request.getParameter("name"));
			statement.setString(2, request.getParameter("isAvailable"));
			statement.setString(3, request.getParameter("specialty"));
			statement.setString(4, request.getParameter("address"));
			statement.setString(5, request.getParameter("phone"));
			statement.setString(6, request.getParameter("email"));
			statement.setInt(7, doctorId);
			
			statement.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Utils.close(connection, statement, null);
		}
		
	}
	public void deleteADoctor(HttpServletRequest request) {
		Connection connection = null;
		PreparedStatement  statement  = null;
	
		try {
			connection = dataSource.getConnection();
			int doctorId = Integer.parseInt(request.getParameter("doctorId"));
			String sql = " delete from doctor "
					+ "where id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, doctorId);
			
			statement.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Utils.close(connection, statement, null);
		}
		
	}
}
