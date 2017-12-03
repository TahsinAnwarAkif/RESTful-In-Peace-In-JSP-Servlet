<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row margin-top-20">
		<div class="col-md-12">
			<h2 align="center">Hospital Management</h2>
			<c:url var="showPatientForm" value="ShowPatients">
				<c:param name="action" value="LOAD_DOCTORS"></c:param>
			</c:url>
			<div style="width:500px;">
   				 <input type="button" value="${showAllDoctors}" onclick="location.href='ShowDoctors';"/>
    			 <input type="button" value="${showAllPatients}" onclick="location.href='ShowPatients';"/>
			</div>
			<br><br>
			<div style="width:500px;">
   				 <input type="button" value="${createADoctor}" onclick="location.href='create-a-doctor.jsp';"/>
    			 <input type="button" value="${createAPatient}" onclick="location.href='${showPatientForm}';"/>
			</div>
			
		<hr />
	</div>
</div>
