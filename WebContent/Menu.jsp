<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<!--     pageEncoding="ISO-8859-1"%> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
<script type="text/javascript">
function logout()
{
	alert("Your are successfully logged out !!!");
	window.location.href = "login.jsp";
	<%
		/* session.removeAttribute("LOGGEDIN_NAME");
		session.removeAttribute("UserMsg");
		//session.invalidate(); */
	%>
}
</script>
</head>
<body>
 <%session.setAttribute("UserMsg",null);%> 
<%-- <p align="right" style="font-size: 11;">Welcome <%=session.getAttribute("LOGGEDIN_NAME") %></p> --%>

<table align="right">
<tr>
<td><p style="font-size: 14;font-style: italic;color:purple;">Welcome <%=session.getAttribute("LOGGEDIN_NAME") %></p></td>
<td><input style="color:purple;background-image: url('images/01.jpg');background-repeat:no-repeat;" type="button" value="Logout" onClick="logout();"></td>
</tr>
</table>
<br/>
<br/>
<div align="left" style="padding-top:10px; height: 50px;background-image: url('images/01.jpg');background-repeat:no-repeat;background-position: top;width: 100% ; ">

<h2 align="center" style="text-align: center;">Menu </h2></div>
<br/>
<br/>
<table>
<tr>
<td>
<a href="EnterNews.jsp?menu=News" > Enter News </a>
<br/>
<br/>
</td>
</tr>
<tr>
<td>
<a href="EnterNews.jsp?menu=Notice" > Enter Notices </a>
<br/>
<br/>
</td>
</tr>
<tr>
<td>
<a href="AddUser.jsp?menu=Add user" > Add User </a>
<br/>
<br/>
</td>
</tr>
<tr>
<td>
<a href="storeData?category=News&action=getWebAppData" > View News </a>
<br/>
<br/>
</td>
</tr>
<tr>
<td>
 <a href="storeData?category=Notice&action=getWebAppData" > View Notices </a>

<br/>
<br/>
</td>
</tr>
</table>
</body>
</html>