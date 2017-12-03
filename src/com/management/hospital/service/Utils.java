package com.management.hospital.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Utils {
	public static boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String userName = (String) session.getAttribute("userName");
		String userPassword = (String) session.getAttribute("userPassword");
		if (userName == null || userPassword == null) {
			userName = new String();
			userPassword = new String();
		}
		if (request.getParameter("userName") != null && request.getParameter("userPassword") != null) {
			session.setAttribute("userName", request.getParameter("userName"));
			session.setAttribute("userPassword", request.getParameter("userPassword"));
		}

		if (session.getAttribute("userName") != null && session.getAttribute("userPassword") != null
				&& session.getAttribute("userName").equals("admin")
				&& session.getAttribute("userPassword").equals("admin")) {
			return true;
		}
		return false;
	}

	public static void close(Connection connection, Statement statement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
