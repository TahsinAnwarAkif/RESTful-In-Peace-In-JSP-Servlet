<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Patient</title>
</head>
<body>
	<c:url var="cancelLink" value="ShowPatients">
		<c:param name="command" value="LOAD_DOCTORS_PATIENTS"></c:param>
		<c:param name="doctorId" value="${patient.doctorId}"></c:param>
	</c:url>
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
			<c:if test="${!param.alreadyDoctorSelected }">
				<c:forEach var="doctor" items="${doctorIds}">
					<c:if test="${doctor != patient.doctorId}">
						<option value="${doctor}">${doctor}</option>
					</c:if>
				</c:forEach>
			</c:if>
		</select> <br /> <br />
		<c:if test="${param.alreadyDoctorSelected }">
			<input type="hidden" name="alreadyDoctorSelected"
				value="${param.alreadyDoctorSelected }" />
		</c:if>

		<input type="submit" value="Submit" />
		<c:if test="${param.alreadyDoctorSelected }">
			<input type="button" value="Cancel"
				onclick="location.href= '${cancelLink}';" />
		</c:if>
		<c:if test="${!param.alreadyDoctorSelected }">
			<input type="button" value="Cancel"
				onclick="location.href='ShowPatients';" />
		</c:if>
	</form>
</body>
</html>