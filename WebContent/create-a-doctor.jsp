<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create A Doctor</title>
</head>
<body>
<form action="ShowDoctors" method="GET">
 <input type = "hidden" name="command" value="ADD" />
Name: <input type = "text" name="name" />
<br /><br />
is_available: <input type = "text" name= "isAvailable" />
<br /><br />
Specialty: <input type = "text" name= "specialty" />
<br /><br />
Address: <input type = "text" name="address" />
<br /><br />
Phone: <input type = "text" name="phone" />
<br /><br />
Email: <input type = "text" name="email" />
<br /><br />
<input type = "submit" value = "Submit" />
<input type = "button" value = "Cancel" onclick="location.href='UserMenu';" />
</form>
	
</body>
</html>