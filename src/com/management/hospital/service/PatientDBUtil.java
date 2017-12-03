package com.management.hospital.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.management.hospital.model.Patient;

public class PatientDBUtil {
	
	private DataSource dataSource;
	
	public PatientDBUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public List<Patient> findAll(){
		List<Patient> patients = new ArrayList<Patient>();
		Connection connection = null;
		Statement  statement  = null;
		ResultSet  resultSet  = null;
		try {
			connection = dataSource.getConnection();
			statement  = connection.createStatement();
			String sql = "select * from patient";
			resultSet  = statement.executeQuery(sql);
			while(resultSet.next()) {
				Patient patient = new Patient(Integer.parseInt(resultSet.getString("id")), resultSet.getString("name"),
						resultSet.getString("address"), resultSet.getString("phone"), resultSet.getString("email"), Integer.parseInt(resultSet.getString("doctor_id")) );
				patients.add(patient);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Utils.close(connection, statement, resultSet);
		}
	return patients;
	}
	public Map<String, Object> createAPatient(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Connection connection = null;
		PreparedStatement  statement  = null;
		Statement  statementforDoctorIds  = null;
		ResultSet  resultSetForDoctorIds  = null;
	
		try {
			connection = dataSource.getConnection();
			String sql = " insert into patient "
						+ "(name, address, phone, email, doctor_id) "
						+"values (? ,? , ? , ? ,?)";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, request.getParameter("name"));
			statement.setString(2, request.getParameter("address"));
			statement.setString(3, request.getParameter("phone"));
			statement.setString(4, request.getParameter("email"));
			statement.setInt(5, Integer.parseInt(request.getParameter("doctorId")));
			
			statement.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Utils.close(connection, statement, null);
		}
		return result;
	}
	public Map<String, Object> findOne(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Patient patient = null;
		List<Integer> doctorIds = new ArrayList<Integer>();
		Connection connection = null;
		PreparedStatement  statement  = null;
		Statement  statementforDoctorIds  = null;
		ResultSet  resultSet  = null;
		ResultSet  resultSetForDoctorIds  = null;
		try {
			connection = dataSource.getConnection();
			int patientId = Integer.parseInt(request.getParameter("patientId"));
			
			String sql = "select * from patient where id = ?";
			statement  = connection.prepareStatement(sql);
			statement.setInt(1, patientId);
			resultSet  = statement.executeQuery();
			if(resultSet.next()) {
				 	patient = new Patient(Integer.parseInt(resultSet.getString("id")), resultSet.getString("name"),
						resultSet.getString("address"), resultSet.getString("phone"), resultSet.getString("email"),
						Integer.parseInt(resultSet.getString("doctor_id")) );
			}
			//FIND THE AVAILABLE DOCTOR IDs
			statementforDoctorIds = connection.createStatement();
			String sqlForDoctorIds = "select id from doctor";
			resultSetForDoctorIds  = statementforDoctorIds.executeQuery(sqlForDoctorIds);
			
			while(resultSetForDoctorIds.next()) {
				doctorIds.add(resultSetForDoctorIds.getInt("id"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Utils.close(connection, statement, resultSet);
		}
	result.put("patient", patient);
	result.put("doctorIds", doctorIds);
	return result;
	}
	public void updateAPatient(HttpServletRequest request) {
		Connection connection = null;
		PreparedStatement  statement  = null;
	
		try {
			connection = dataSource.getConnection();
			int patientId = Integer.parseInt(request.getParameter("id"));
			String sql = " update patient set name = ?, address = ?, phone = ?, email = ?, doctor_id = ? "
					+ "where id = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, request.getParameter("name"));
			statement.setString(2, request.getParameter("address"));
			statement.setString(3, request.getParameter("phone"));
			statement.setString(4, request.getParameter("email"));
			statement.setInt(5, Integer.parseInt(request.getParameter("doctorId")));
			statement.setInt(6, patientId);
			
			statement.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Utils.close(connection, statement, null);
		}
		
	}
	public void deleteAPatient(HttpServletRequest request) {
		Connection connection = null;
		PreparedStatement  statement  = null;
	
		try {
			connection = dataSource.getConnection();
			int patientId = Integer.parseInt(request.getParameter("patientId"));
			String sql = " delete from patient "
					+ "where id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, patientId);
			
			statement.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Utils.close(connection, statement, null);
		}	
	}
	public List<Integer> findAvailableDoctorIds() throws SQLException{

		List<Integer> doctorIds = new ArrayList<Integer>();
		Connection connection = null;
		Statement  statementforDoctorIds  = null;
		ResultSet  resultSetForDoctorIds  = null;
		try {
			connection = dataSource.getConnection();
			statementforDoctorIds = connection.createStatement();
			String sqlForDoctorIds = "select id from doctor";
			resultSetForDoctorIds  = statementforDoctorIds.executeQuery(sqlForDoctorIds);
			
			while(resultSetForDoctorIds.next()) {
				doctorIds.add(resultSetForDoctorIds.getInt("id"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Utils.close(connection, statementforDoctorIds, resultSetForDoctorIds);
		}
	return doctorIds;
	}
}
