package com.management.hospital.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.management.hospital.controller.Mappings;
import com.management.hospital.model.Patient;

public class PatientService {
	
	private DataSource dataSource;
	
	public PatientService(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public List<Patient> findAll(){
		List<Patient> patients = new ArrayList<Patient>();
		Connection connection = null;
		PreparedStatement  statement  = null;
		ResultSet  resultSet  = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select * from patient";
			statement  = connection.prepareStatement(sql);
			resultSet  = statement.executeQuery();
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
		Connection connection = null;
		PreparedStatement  statement  = null;
		ResultSet  resultSet  = null;
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
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Utils.close(connection, statement, resultSet);
		}
	result.put("patient", patient);
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
		PreparedStatement  statementforDoctorIds  = null;
		ResultSet  resultSetForDoctorIds  = null;
		try {
			connection = dataSource.getConnection();
			String sqlForDoctorIds = "select id from doctor";
			statementforDoctorIds = connection.prepareStatement(sqlForDoctorIds);
			resultSetForDoctorIds  = statementforDoctorIds.executeQuery();
			
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
	public List<Patient> findByDoctorId(HttpServletRequest request) {
		List<Patient> patients = new ArrayList<Patient>();
		Connection connection 		  = null;
		PreparedStatement  statement  = null;
		ResultSet			resultSet = null;
		try {
			//int doctorId = Integer.parseInt(request.getParameter("doctorId"));
			connection = dataSource.getConnection();
			String sql = "select * from patient"
						 +" where doctor_id = ? ";
			statement  = connection.prepareStatement(sql);
			statement.setInt(1, Integer.parseInt(request.getParameter("doctorId")));
			resultSet = statement.executeQuery();
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
	
	public RequestDispatcher getRedirectLinkToDoctorsPatients(RequestDispatcher dispatcher, HttpServletRequest request) {
		if(Boolean.parseBoolean(request.getParameter("alreadyDoctorSelected"))) {
			List<Patient> patientsOfDoctor = findByDoctorId(request);
			request.setAttribute("patientsOfDoctor", patientsOfDoctor);
			dispatcher = request.getRequestDispatcher(Mappings.SHOW_DOCTORS_PATIENTS_VIEW);
		}
		return dispatcher;
	}
}
