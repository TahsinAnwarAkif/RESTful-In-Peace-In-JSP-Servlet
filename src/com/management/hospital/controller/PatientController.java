package com.management.hospital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.management.hospital.model.Patient;
import com.management.hospital.service.PatientDBUtil;


/**
 * Servlet implementation class TestServlet
 */
@WebServlet(Mappings.SHOW_PATIENTS_REQUEST_URL)
public class PatientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = Mappings.RESOURCE_NAME)
	private DataSource dataSource;
	
	private PatientDBUtil patientDBUtil;
	
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			patientDBUtil = new PatientDBUtil(dataSource);
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = (String)request.getParameter("command");
		String action  = (String)request.getParameter("action");
		RequestDispatcher dispatcher = request.getRequestDispatcher(Mappings.SHOW_PATIENTS_VIEW);
		
		if(action != null) {
			List<Integer> doctorIds = null;
			if(action.equals("LOAD_DOCTORS")) {
				try {
					doctorIds = patientDBUtil.findAvailableDoctorIds();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("doctorIds", doctorIds);
				dispatcher = request.getRequestDispatcher(Mappings.CREATE_A_PATIENT_FORM_VIEW);
			}
		}
		if(command != null){
			if(command.equals("ADD")) {
				patientDBUtil.createAPatient(request);
			}
			else if(command.equals("LOAD")) {
				Patient patient = (Patient) patientDBUtil.findOne(request).get("patient");
				request.setAttribute("patient", patient);
				dispatcher = request.getRequestDispatcher(Mappings.SHOW_PATIENT_VIEW);
			}
			else if(command.equals("UPDATE")) {
				patientDBUtil.updateAPatient(request);
			}
			else if(command.equals("DELETE")) {
				patientDBUtil.deleteAPatient(request);
			}
		}
		List<Patient> patients = patientDBUtil.findAll();
		request.setAttribute("allPatients", patients);
		dispatcher.forward(request, response);
	}

}
