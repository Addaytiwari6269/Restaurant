<%@page import="dto.CustomerFoodItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="dto.AddFoodItem"%>
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
	List<AddFoodItem> items = (List<AddFoodItem>) request.getAttribute("list");
List<CustomerFoodItem> cartitems = (List<CustomerFoodItem>) request.getAttribute("cartitems");
	%>

	<table border="1">

		<tr>
			<th>Picture</th>
			<th>Name</th>
			
			<th>Type</th>
			<th>Price</th>
			<th>Reduce</th>
			<th>Quantity</th>
			<th>Add</th>
			
		</tr>
		<%
		for (AddFoodItem item : items) {
		%>
		<tr>
			
			
			<th>
				<%
				String base64 = Base64.encodeBase64String(item.getPicture());
				%> <img height="150px" width="150px" alt="unknown"
				src="data:image/jpeg;base64,<%=base64%>">
			</th>
			<th><%=item.getName()%></th>
			<th><%=item.getType()%></th>
			<th><%=item.getPrice()%></th>
			<th><a href="removefromcart?id=<%=item.getId()%>"><button>-</button></a></th>
			<th>
			<% if(cartitems==null)
		    {%>0<%}else{
		    	boolean flag=true;
		    	for(CustomerFoodItem foodItem:cartitems)
		    	{
		    		if(foodItem.getName().equals(item.getName())){%> 
		    		<%=foodItem.getQuantity() %>
		    		<%flag=false;
		    		} 
				  }
		    	if(flag){
		   %>
		   <%=0 %>
		   <%
		   }}
			%>
			</th>
			
			
			<th><a href="addtocart?id=<%=item.getId()%>"><button>+</button></a></th>
		</tr>
	<%} %>
	</table>
	<br>
	<a href="viewcart"><button>View Cart</button></a>
	<a href="CustomerHome.html"><button>Back</button></a>
</body>
</html>