<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

function ValidateForm()
{
		document.forms[0].isAdminCheck.value=document.forms[0].isAdmin.checked;
	
		if(document.forms[0].loginName.value=="" )
		{
	
			alert("Login name cannot be empty");
			return false;
		}
	
		if(document.forms[0].emailId.value=="" )
		{
	
			alert("Email cannot be empty");
			return false;
		}
		
		
	 return true;
}

function navigateTo()
{
	
	window.location.href = "Menu.jsp";
	
}

function checkStatus()
{
		<% if(session.getAttribute("UserMsg")==null)
	  	  {
	    	
	      } else
	    	{
	    	// session.setAttribute("UserMsg",null) ;
	    	  %>
	    	
	    	  alert("User Added successfully");
	    	 
	     <% }%>	
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
<body onload="checkStatus()">

<table align="right">
<tr>
<td><p style="font-size: 14;font-style: italic;color:purple;">Welcome <%=session.getAttribute("LOGGEDIN_NAME") %></p></td>
<td><input style="color:purple;background-image: url('images/01.jpg');background-repeat:no-repeat;" type="button" value="Logout" onClick="logout();"></td>
</tr>
</table>
<br/>
<br/>
<div align="left" style="height: 50px;background-image: url('images/01.jpg');background-repeat:no-repeat;background-position: top;width: 100% ;">
<h2 align="center" style="text-align: center;">Add User</h2></div>

<br/>
<br/>
<form action="addUser" method="post" onsubmit="ValidateForm();" >
<p style="color: red" align="center" > <%=session.getAttribute("UserMsg")==null?"":session.getAttribute("UserMsg")%> </p>
<table align="center">
<tr>
<td>
Login Name 
</td>
<td>
<input type="hidden" id="isAdduserPage" name="isAdduserPage" value="true"/>
<input type="text" id="loginName" name="loginName"/>
</td>
</tr>
<tr>
<td>
<br/>
Email Id
</td>
<td>
<br/>
<input type="text" id="emailId" name="emailId"/>
</td>
</tr>
<tr>
<td>
<br/>
Admin
</td>
<td>
<br/>
<input type="checkbox" id="isAdmin" name="isAdmin"/>
<input type="hidden" id="isAdminCheck" name="isAdminCheck"/>
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
</form> 
</body>
</html>