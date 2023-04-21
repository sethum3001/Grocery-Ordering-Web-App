<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import ="java.util.ArrayList"%>
    <%@ page import = "model.item"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css " href="css/itemMgmt.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<title>Insert title here</title>
</head>
<body>
	<center><h1>Item Management</h1><br></center>

	<form method="POST" action="AddItem">
		<input type="hidden" name="code" id="code" value="">
		<center><div class="inpute-field">Item Name : <input type="text" name="name" id="name" value="" required></div>
		<div class="inpute-field">Unit Price : <input type="text" name="price" id="price" value="" ></div>
		<div class="inpute-field">Quantity On Hand : <input type="text" name="qtyOnHand" id="qtyOnHand" value="" ></center></div>
		<div style="clear:both"></div>
		
		<center><input type="submit" name="submit" value="Add">
		<button type="submit" name="search" formaction="Search">Search</button>
		<button type="submit" name="update" formaction="Update">Update</button>
		<button type="submit" name="delete" formaction="DeleteItem">Delete Item</button>
		 <button type="submit" name="cusMng" formaction="cusManagement">Customer Management</button>
		</center>
	</form>
	
	<table class="item-table">
	
	<th>Name</th>
	<th>Price</th>
	<th>Quantity</th>
	<th>Select</th>
	<%String x = request.getParameter("search");
	if(x!=null){
	%>
	
	<% ArrayList<item> list = (ArrayList)request.getAttribute("searchList"); 
		for(item Item : list){
	%>
		<tr>
			<td><%=Item.getName() %></td>
			<td><%=Item.getUnitPrice() %></td>
			<td><%=Item.getQtyOnHand() %></td>
			<td><form method="post" action="selectItem">
				<input type="hidden" name="itemId" value="<%=Item.getCode() %>">
				<input type="submit" value="Select" name="select">
			</form></td>
			</tr>
		<%} %>
		
		
		
	</table>
	<% }%>
	<%String y = request.getParameter("select");
		if(y!=null){
		%>
	
	<% item item = (item)request.getAttribute("item");%>
	
	<form action="">
	<input type="hidden" name="iCode" id="iCode" value="<%=item.getCode() %>">
	<input type="hidden" name="iName" id="iName" value="<%=item.getName()%>">
	<input type="hidden" name="iPrice" id="iPrice" value="<%=item.getUnitPrice()%>">
	<input type="hidden" name="iQuantity" id="iQuantity" value="<%=item.getQtyOnHand()%>">
	</form>
			
		<script type="text/javascript">
			document.getElementById("code").value =document.getElementById("iCode").value;
			document.getElementById("name").value =document.getElementById("iName").value;
			document.getElementById("price").value =document.getElementById("iPrice").value;
			document.getElementById("qtyOnHand").value =document.getElementById("iQuantity").value;
		</script>
	<%} %>
	
</body>
</html>