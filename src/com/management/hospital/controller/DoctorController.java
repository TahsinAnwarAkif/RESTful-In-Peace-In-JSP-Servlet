package com.management.hospital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.management.hospital.model.Doctor;
import com.management.hospital.service.DoctorDBUtil;


/**
 * Servlet implementation class TestServlet
 */
@WebServlet(Mappings.SHOW_DOCTORS_REQUEST_URL)
public class DoctorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = Mappings.RESOURCE_NAME)
	private DataSource dataSource;
	
	private DoctorDBUtil doctorDBUtil;
	
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
		doctorDBUtil = new DoctorDBUtil(dataSource);
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
		RequestDispatcher dispatcher = request.getRequestDispatcher(Mappings.SHOW_DOCTORS_VIEW);
		
		if(command != null){
			if(command.equals("ADD"))
				doctorDBUtil.createADoctor(request);
			else if(command.equals("LOAD")) {
				Doctor doctor = doctorDBUtil.findOne(request);
				request.setAttribute("doctor", doctor);
				dispatcher = request.getRequestDispatcher(Mappings.SHOW_DOCTOR_VIEW);
			}
			else if(command.equals("UPDATE")) {
				doctorDBUtil.updateADoctor(request);
			}
			else if(command.equals("DELETE")) {
				doctorDBUtil.deleteADoctor(request);
			}
		}
		List<Doctor> doctors = doctorDBUtil.findAll();
		request.setAttribute("allDoctors", doctors);
		dispatcher.forward(request, response);
	}

}
