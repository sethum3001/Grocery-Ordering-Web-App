<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "java.util.ArrayList" %>
    <%@page import ="model.item" %>
    <%@page import ="model.CartDetails" %>
        <%@page import = "java.text.SimpleDateFormat" %>
    <%@page import = "java.util.Date" %>
    
    <%
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
    HttpSession Session = request.getSession(false);
    Session.setAttribute("date",format.format(date));
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css " href="css/cart.css">
<link rel="stylesheet" type="text/css " href="css/header.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<title>Insert title here</title>
</head>
<body>
	<h1>Cart</h1>
	
	
		<div style="float:right"><div class="user-detials-img">
				
				<div class='drop-down'>
				<a href="#"><img src="images/user.png">
				<div class='drop-down-cont'>
				
						
						<a><form method="POST" action="Cart"><input type="submit" value="Cart"></form></a>				
						<a ><form method="POST" action="Cart"><input type="submit" value="My Profile"></form></a>
				</div>
				</div>
				</a></div><div class="user-detials"><input type="text" name="ID" disabled value="<%=String.valueOf(Session.getAttribute("Cus_name"))%>"></div></div>
				<input type="text" name="date" disabled value="<%=format.format(date)%>">
				
				
				<center><img src="images/logo.png" class="logo"></center>
	
	
	
	
	
	<table class="cart">
		<tr>
			<th>Item Name</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		<%ArrayList<ArrayList<Object>> list = (ArrayList<ArrayList<Object>>)request.getAttribute("list");
			for(ArrayList<Object> objList: list){
				
					item item = (item)objList.get(0);
					CartDetails cartDetails = (CartDetails)objList.get(1); %>

					<tr>
					<form method="post" action="CartDelete">
						<td><%=item.getName()%></td>						
						<td><input type="number" name="qty" id="qty<%=item.getCode()%>" min="1" onchange="calcTot<%=item.getCode()%>(this.value)" onkeyup="calcTot<%=item.getCode()%>(this.value)" value="<%=cartDetails.getQty()%>"></td>
						<td><input type="text" name="price" id="price<%=item.getCode()%>"  value="<%=cartDetails.getPrice()%>" disabled></td>
						<td><button type="submit" formaction="CartUpdate" class="btn">Update</button></td>
						<td><input type="submit" value="Delete"></td>
						<td><input type="hidden" name="cartId" value="<%=cartDetails.getCartId()%>">
						<td><input type="hidden" name="itemId" value="<%=item.getCode() %>"></td>
						<td><input type="hidden" name="unitPrice" value="<%=item.getUnitPrice()%>"></td>
					</form>
					</tr>
					<script type="text/javascript">
						function calcTot<%=item.getCode()%>(value){
							var qty =value;
							document.getElementById("price<%=item.getCode()%>").value = qty * <%=item.getUnitPrice()%>
						}
					</script>
		<%}%>	
	</table>
		<br><form method="POST" action="Order">
			<center><input type="submit" value="Order" style="margin-right:30px;"></center>
		</form>
</body>
</html>