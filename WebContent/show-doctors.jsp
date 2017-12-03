<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
}

.tg .tg-s0wq {
	background-color: #ffccc9;
	color: #330001
}

.tg .tg-xu9l {
	background-color: #ffccc9;
	color: #000000
}

.tg .tg-gvwu {
	background-color: #ffccc9;
	color: #000000;
	vertical-align: top
}

.tg .tg-i6eq {
	background-color: #ffccc9;
	vertical-align: top
}

.tg .tg-e3eb {
	background-color: #ffccc9;
	color: #330001;
	vertical-align: top
}

.tg .tg-yw4l {
	vertical-align: top
}
</style>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show All Doctors</title>
</head>
<body>
	<table border="1" class="tg" align="center">
		<tr>
			<th class="tg-xu9l">ID</th>
			<th class="tg-s0wq">Name</th>
			<th class="tg-e3eb">Is_Available</th>
			<th class="tg-e3eb">Specialty</th>
			<th class="tg-gvwu">Address</th>
			<th class="tg-i6eq">Phone</th>
			<th class="tg-i6eq">Email</th>
			<th class="tg-i6eq">Update</th>
			<th class="tg-i6eq">Delete</th>
		</tr>
		<c:forEach var="doctor" items="${allDoctors}">
			<c:url var="updateLink" value="ShowDoctors">
				<c:param name="command" value="LOAD"></c:param>
				<c:param name="doctorId" value="${doctor.id}"></c:param>
			</c:url>
			<c:url var="deleteLink" value="ShowDoctors">
				<c:param name="command" value="DELETE"></c:param>
				<c:param name="doctorId" value="${doctor.id}"></c:param>
			</c:url>
			<tr>
				<td>${doctor.id}</td>
				<td>${doctor.name}</td>
				<td>${doctor.specialty}</td>
				<td>${doctor.isAvailable}</td>
				<td>${doctor.address}</td>
				<td>${doctor.phone}</td>
				<td>${doctor.email}</td>
				<td><a href= "${updateLink}">Update</a></td>
				<td><a href= "${deleteLink}" onclick= "if(!confirm('Are you sure you want to delete this doctor?'))return false;">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<h4><a href = "UserMenu">Go Back</a></h4>
</body>
</html>