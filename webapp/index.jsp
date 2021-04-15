<%@page import="models.Vehicle"%>
<%@page import="java.util.ArrayList"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/site.css">
<title>All Vehicles</title>
<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="v" %>>
</head>
<body>
	<h1>All Vehicles</h1>
	<table>
		<tr>
			<th>ID</th>
			<th>Make</th>
			<th>Model</th>
			<th>Year</th>
			<th>Price</th>
			<th>License Number</th>
			<th>Colour</th>
			<th>Number of Doors</th>
			<th>Transmission Type</th>
			<th>Mileage</th>
			<th>Fuel Type</th>
			<th>Engine Size</th>
			<th>Body Style</th>
			<th>Condition</th>
			<th>Notes</th>
			<v:forEach items="${allVehicles}" var="v">
				<tr>
					<td>${v.getVehicleID()}</td>
					<td>${v.getMake()}</td>
					<td>${v.getModel()}</td>
					<td>${v.getYear()}</td>
					<td>${v.getPrice()}</td>
					<td>${v.getLicenseNumber()}</td>
					<td>${v.getColour()}</td>
					<td>${v.getNumberDoors()}</td>
					<td>${v.getTransmission()}</td>
					<td>${v.getMileage()}</td>
					<td>${v.getFuelType()}</td>
					<td>${v.getEngineSize()}</td>
					<td>${v.getBodyStyle()}</td>
					<td>${v.getCondition()}</td>
					<td>${v.getNotes()}</td>
				</tr>
			</v:forEach>
	</table>
	<br>
</body>
</html>