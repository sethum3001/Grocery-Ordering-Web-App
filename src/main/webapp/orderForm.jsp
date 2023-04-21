<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "java.text.SimpleDateFormat" %>
    <%@page import = "java.util.Date" %>
    <%@page import = "java.util.ArrayList" %>
    <%@page import = "model.item" %>
    <%@page import = "javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css " href="css/orderForm.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<% 	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		HttpSession Session = request.getSession(false);
		Session.setAttribute("date",format.format(date));
	%>
	
		<%if(request.getAttribute("value") != null){
	boolean value = (Boolean)request.getAttribute("value"); 
	if(value == true){%>
		<script type="text/javascript">
			alert("Item successfully added");
		</script>
	<%} }%>
	
	<%if(request.getAttribute("isUpdated") != null){
	boolean value = (Boolean)request.getAttribute("isUpdated"); 
	if(value == true){%>
		<script type="text/javascript">
			alert("Order placed successfully");
		</script>
	<%} }%>
	<div>
		<h2></h2>
		<div>
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
								
				<br><br><br><br><br><br>
											
				<form method="post" action="ItemSearch" class="search">
					<center><input type="text" name="itemSearch" placeholder="Search" >
					<input type="submit" value="Search"></center>
				</form>
				
				<table class="cart-table">
					<tr>
						<th>Item Name</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Total</th>
						<th></th>
					</tr>
					<%
						if(request.getAttribute("searchList")!=null){
							ArrayList<item> list = (ArrayList)request.getAttribute("searchList");
							for(item item : list){
							%>
							<tr>
							<form method="post" action="addToCart">	
							<td><%=item.getName() %></td>
							<td><input type="number" id="qty<%=item.getCode()%>" min="1" name="qty" onkeyup="totCalc<%=item.getCode()%>(this.value)" oninput="totCalc<%=item.getCode()%>(this.value)"></td>
							<td id="tdPrice<%=item.getCode()%>"><%=item.getUnitPrice()%></td>
							<td><input type="text" name="total" id="total<%=item.getCode()%>" disabled value=""></td>
							<td><input type="submit" value="Add"></td>
							<td><input style="display:none" type="hidden" name="itemcode" value="<%=item.getCode()%>" ></td>
							</form>		
							
							<script type="text/javascript">
		
								function totCalc<%=item.getCode()%>(value){
									var price = document.getElementById("tdPrice<%=item.getCode()%>").innerHTML;
									var tot = price * value;
									document.getElementById("total<%=item.getCode()%>").value = tot;
								}

							</script>
						</tr>
					<% 	}
					}
							
						else
						{
						ArrayList<item> list = (ArrayList<item>)session.getAttribute("list");
						for(item item : list){
					%>
						<tr>
							<form method="post" action="addToCart">	
							<td><%=item.getName() %></td>
							<td><input type="number" id="qty<%=item.getCode()%>" min="1" name="qty" onkeyup="totCalc<%=item.getCode()%>(this.value)" oninput="totCalc<%=item.getCode()%>(this.value)"></td>
							<td id="tdPrice<%=item.getCode()%>"><%=item.getUnitPrice()%></td>
							<td><input type="text" name="total" id="total<%=item.getCode()%>" disabled value=""></td>
							<td><input type="submit" value="Add"></td>
							<td><input style="display:none" type="hidden" name="itemcode" value="<%=item.getCode()%>" ></td>
							</form>		
							
							<script type="text/javascript">
		
								function totCalc<%=item.getCode()%>(value){
									var price = document.getElementById("tdPrice<%=item.getCode()%>").innerHTML;
									var tot = price * value;
									document.getElementById("total<%=item.getCode()%>").value = tot;
								}

							</script>
						</tr>
					<%
						}
						}
					%>	
				
				</table><br><br>
				
						
				
		</div>
	</div>
	
</body>
</html>