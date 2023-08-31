<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="dto.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
List<Customer> customers = (List<Customer>) request.getAttribute("list");
%>
<table border="1">

		<tr>
			
			<th>CustomerName</th>
			<th>Email</th>
			
			<th>Mobile</th>
			<th>Picture</th>
			<th>Gender</th>
			<th>DOB</th>
			
			<th>Delete</th>
		</tr>
		<%
		for (Customer customer :customers) {
		%>
		<tr>
			<th><%=customer.getFullname() %></th>
			<th><%=customer.getEmail() %></th>
			<th><%=customer.getMobile() %>
			<th>
			<%
				String base64 = Base64.encodeBase64String(customer.getPicture());
				%> <img height="150px" width="150px" alt="unknown"
				src="data:image/jpeg;base64,<%=base64%>">
			</th>
			<th><%=customer.getGender() %>
			<th><%=customer.getDob() %>
			<th><a href="deleteCustomer?id=<%=customer.getId()%>"><button>Delete</button></a></th>
			
		</tr>
		<%
		}
		%>
		</table>
	<br>
	<a href="AdminHome.html"><button>Back</button></a>
		
</body>
</html>