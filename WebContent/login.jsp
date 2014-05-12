<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <%@page import="com.slk.mm.server.Constant"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>VVS Admin Console Login</title>

<script type="text/javascript">
function ValidateForm()
{
	if(document.forms[0].username.value=="")
		{
	
			alert("Login Name cannot be Empty");
			return false;
		}
	 if(document.forms[0].password.value=="")
		{
		alert("Password cannot be Empty");
		return false;
		}
	 return true;
}
</script>
</head>
<body  > <!-- style="background-image: url('images/Header BG.jpg');background-repeat:no-repeat;background-position: top;width: 100% ;" class="topCls" -->
<table align="center">
<tr><td height="100px;"></td></tr>
<tr><td>
<form action="login" method="post" onsubmit="return ValidateForm();"> 
<div align="left" class="logintable" style="height: 500px;background-image: url('images/01.jpg');background-repeat:no-repeat;background-position: top;width: 800px ;">

<p align="center" style="color: black;"><b><h2 align="center">VVS Admin Console</h2></b></p>
<p><span class="errormsg" style="color: red;"> <%=session.getAttribute("LOGIN_ERROR")==null?"": session.getAttribute("LOGIN_ERROR")%></span></p>	
<br><br>
<table align="center">
<tr>
<td align ="right">
<br/>
<br/>
<input type="hidden" value="webAppAuthentication" name="action"/>
<span class="msg">Login Name</span>
</td>
<td>
<br/>
<br/>
&nbsp;&nbsp;
<input type="text" id="userName" name="username"/>
</td>
</tr>
<tr>

<td align ="right">
<br/>
<br/>

<span class="msg">Password</span>
</td>
<td>
<br/>
<br/>
&nbsp;&nbsp;
<input type="password"  id="password" name="password"/>
</td>
</tr>
<tr>
<td colspan="2">
</td>
</tr>
<tr>
<td>
</td>
<td>
<div>
<br/>

<input class="loginbtn" type="submit" id="login" value="Sign In"/>
&nbsp;&nbsp;<a href="#" id="passwordMsg">Forget password?</a>
</div>
</td>
</tr>
</table>
<br>
</div>
</form>
</td></tr>
</table>

<div style="height: 150px;"></div>
<div class="copyrgt" align="center">
<hr/>
			Copyright ©2013. VVS. All Rights Reserved.
</div>
</body>
</html>