package com.management.hospital.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.management.hospital.service.Utils;

/**
 * Servlet implementation class UserMenu
 */
@WebServlet("/UserMenu")
public class UserMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Utils.isAuthenticated(request, response, request.getSession())) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("ShowUserMenu.jsp");
			
			//Menu Creation
			request.setAttribute("showAllDoctors", "Show All Doctors");
			request.setAttribute("showAllPatients", "Show All Patients");
			request.setAttribute("createADoctor", "Create A Doctor");
			request.setAttribute("createAPatient", "Create A Patient");
			
			dispatcher.forward(request, response);
		}
		else {
			String redirectURL = "Login.jsp";
			response.sendRedirect(redirectURL);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
