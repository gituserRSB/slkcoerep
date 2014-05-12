<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.util.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>News</title>

<script>
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
function deleteApps(id)

{

	document.getElementById("delete").action = document.getElementById("delete").action +"?action=delData&id="+id;
	
	window.location.href = document.getElementById("delete").action;
	

}

function editData(idVal,headingVal,bodyVal)

{

	var heading = document.createElement("input");
	heading.type='hidden';
	heading.value= headingVal;
	heading.name= "heading";
	
//Create an input type dynamically.
var id = document.createElement("input");

//Assign different attributes to the element.
id.type='hidden';
id.value= idVal;
id.name= "id";


alert(heading.value);

var body = document.createElement("input");
body.type='hidden';
body.value= bodyVal;
body.name= "body";


var category = document.createElement("input");
category.type='hidden';
category.value= '<%=request.getParameter("category") %>';
category.name= "category";
alert(category.value);

document.getElementById("edit").appendChild(id);
document.getElementById("edit").appendChild(heading);
document.getElementById("edit").appendChild(body);
document.getElementById("edit").appendChild(category);
document.getElementById("edit").submit();






/* 


//Assign different attributes to the element.
heading.setAttribute("type", hidden);
heading.setAttribute("value", headingVal);
heading.setAttribute("name", type);

var body = document.createElement("input");

//Assign different attributes to the element.
body.setAttribute("type", hidden);
body.setAttribute("value", bodyVal);
body.setAttribute("name", type); */



/* alert(heading);
alert(body);
 */
	/* document.getElementById("delete").action = document.getElementById("delete").action +"?action=delData&id="+id;
	
	window.location.href = document.getElementById("delete").action;
	 */

}


	
</script>
</head>
<body >

<table align="right">
<tr>
<td><p style="font-size: 14;font-style: italic;color:purple;">Welcome <%=session.getAttribute("LOGGEDIN_NAME") %></p></td>
<td><input style="color:purple;background-image: url('images/01.jpg');background-repeat:no-repeat;" type="button" value="Logout" onClick="logout();"></td>
</tr>
</table>
<br/>
<br/>
<h2 align="center" style="text-align: center;"><%=request.getParameter("category") %></h2></div>
<br/>

<form id="delete" name="delete" action="storeData" method="post">

<table class="tablestyle" width="100%" id="table1" cellspacing="1" cellpadding="1" border="1">
							<tr style="background-color: lightblue">
								<th width="5%">Sl No</th>
								<th width="30%">Heading</th>
								<th width="55%">Body</th>
								<th width="5%"></th>
							</tr>
							<tr>
<%
List<HashMap> resultList=new ArrayList<HashMap>(); 
 resultList=(List<HashMap>)request.getAttribute("resultList");
 
 for(int i=0;i<resultList.size();i++){
	 HashMap result =  resultList.get(i); %>
	
	 <td width="5%">&nbsp;<%=i+1%></td>
								<td width="30%">&nbsp;<%=result.get("Heading") %></td>
								<td width="55%">&nbsp;<%=result.get("Body") %></td>
								<td width="5%">&nbsp;<img src="images/delete.png" onclick="deleteApps('<%=result.get("id")%>');"/>&nbsp;&nbsp;&nbsp;&nbsp;
								 </form>
								<%--  <form id="edit" name="edit" action="editData">
								<img src="images/edit1.png" onclick="editData('<%=result.get("id")%>','<%=result.get("Heading")%>','<%=result.get("Body") %>')"/></form> --%></td>
</tr>	 
<% }
 %>
 </table>

 <br/>
 <br/>
<input type="button" id="submit" value="Back" onclick="navigateTo()" width="10" />
</body>
</html>