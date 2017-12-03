<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Doctor</title>
</head>
<body>
	<form action="ShowPatients" method="GET">
		<input type="hidden" name="command" value="UPDATE" /> <input
			type="hidden" name="id" value="${patient.id }" /> Name: <input
			type="text" name="name" value="${patient.name }" /> <br /> <br />
		Address: <input type="text" name="address" value="${patient.address }" />
		<br /> <br /> Phone: <input type="text" name="phone"
			value="${patient.phone }" /> <br /> <br /> Email: <input
			type="text" name="email" value="${patient.email }" /> <br /> <br />
		Assigned Doctor ID: <select name="doctorId">
			<option value="${patient.doctorId}" selected>${patient.doctorId}</option>
			<c:forEach var="doctor" items="${doctorIds}">
				<c:if test="${doctor != patient.doctorId}">
					<option value="${doctor}">${doctor}</option>
				</c:if>
			</c:forEach>
		</select> <br /> <br /> <input type="submit" value="Submit" /> <input
			type="button" value="Cancel" onclick="location.href='ShowPatients';" />
</body>
</html>