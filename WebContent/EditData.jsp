<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

function checkURL()
{
 	alert(<%=request.getParameter("heading")%>);
 	<%--alert(<%=request.getParameter("body")%>); --%>
	
  
 <%--  document.getElementById("heading").value=<%=request.getParameter("heading")%>; --%>
} 


function ValidateForm()
{
	if(document.forms[0].heading.value=="" && document.forms[0].body.value=="")
		{
	
			alert("Nothing to store");
			return false;
		}
	
	 return true;
}

function navigateTo()
{
	
	window.location.href = "Menu.jsp";
	
	}
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
<body onload="checkURL()" >

<table align="right">
<tr>
<td><p style="font-size: 14;font-style: italic;color:purple;">Welcome <%=session.getAttribute("LOGGEDIN_NAME") %></p></td>
<td><input style="color:purple;background-image: url('images/01.jpg');background-repeat:no-repeat;" type="button" value="Logout" onClick="logout();"></td>
</tr>
</table>
<br/>
<br/>
<div align="left" style="height: 50px;background-image: url('images/01.jpg');background-repeat:no-repeat;background-position: top;width: 100% ;">
<h2 align="center" style="text-align: center;"><%=request.getParameter("category") %></h2></div>

<br/>
<br/>
<form action="storeData" method="post" onsubmit="return ValidateForm();" > 

<input type="hidden" id="menu"  name="menu" value='<%=request.getParameter("category") %>'/>
<table>
<tr>
<td>
<label >Heading </label>
</td>
<td>
&nbsp;
&nbsp;
<input type="text" id="heading" name="heading" value='<%=request.getParameter("heading")%>'  />

</td>
</tr>
<tr>
<td>
<br/>
<br/>
<label >Body </label>
</td>
<td>
<br/>
<br/>
&nbsp;
&nbsp;
<textarea type="" id="body" style="height: 100px;width: 500px" name="body"><%=request.getParameter("body")%></textarea>

</td>
</tr>
<tr>

<td>
<br/>
<input type="submit" id="submit" value="Submit" width="10"/>
</td>

<td>
<br/>
&nbsp;&nbsp;
<input type="button" id="submit" value="Back" onclick="navigateTo()" width="10"/>
</td>

</tr>
</table>
</body>
</html>