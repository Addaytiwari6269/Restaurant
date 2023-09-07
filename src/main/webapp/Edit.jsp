
<%@page import="org.apache.commons.codec.binary.Base64"%>

<%@page import="dto.AddFoodItem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit</title>
</head>
<body>
<% AddFoodItem item=(AddFoodItem)request.getAttribute("item"); %>

<form action="update" method="post" enctype="multipart/form-data">
     <input type="text" name="id" value="<%=item.getId()%>" hidden=""><br>
  Name:<input type="text" name="name" value="<%=item.getName()%>"><br>
  Price:<input type="text" name="price" value="<%=item.getPrice()%>"><br>
  Food Type:
  <%if(item.getType().equals("veg")){ %>
  <input type="radio" name="type" value="veg" checked="checked">Veg<br>
  <input type="radio" name="type" value="non-veg">Non-Veg
  <%}else{ %>
  <input type="radio" name="type" value="veg" >Veg
  <input type="radio" name="type" value="non-veg" checked="checked">Non-Veg<br>
  <%} %>
  <br>
  
  Quantity:<input type="text" name="quantity" value="<%=item.getStock()%>"><br>
  
  Picture:
  <%
				String base64 = Base64.encodeBase64String(item.getPicture());
				%> <img height="150px" width="150px" alt="unknown"
				src="data:image/jpeg;base64,<%=base64%>">
  <input type="file" name="pic"><br>
  <button type="submit">Update</button>
  <button type="reset">Cancel</button>
 </form>
 
</body>
</html>