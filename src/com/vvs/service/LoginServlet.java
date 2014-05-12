package com.vvs.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 677708785367010739L;
	DBUtil dbUtil=new  DBUtil();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
	try{
			
			 Connection connection = dbUtil.getConnection();
			 // ("jdbc:sqlserver://192.168.10.6;databaseName=VVSDB;user=sa;password=sa@SQL123");
			if(req.getParameter("action").equals("webAppAuthentication")){
			 PreparedStatement ps = connection.prepareStatement("Select * from LoginDetails " +
							"where userName =? and password=? and isAdmin=?");
				ps.setString(1, req.getParameter("username"));
				ps.setString(2, req.getParameter("password"));
				ps.setString(3, "true");
				ResultSet rs = ps.executeQuery();
				if(rs != null ){
					if(rs.next())
					{
						clearSession(req, resp);
						HttpSession httpSession=req.getSession();
						httpSession.setAttribute("LOGGEDIN_NAME",req.getParameter("username"));
						resp.sendRedirect("Menu.jsp");
					}else
					{
						RequestDispatcher rd = ((HttpServletRequest)req).getRequestDispatcher("login.jsp");
						req.getSession().setAttribute("LOGIN_ERROR", "Invalid User Credentials...");
						rd.forward(req, resp);
					}
					 	
				}else
				{
					RequestDispatcher rd = ((HttpServletRequest)req).getRequestDispatcher("login.jsp");
					req.getSession().setAttribute("LOGIN_ERROR", "Invalid User Credentials...");
					rd.forward(req, resp);
				}
		}
		else
		{
			 PreparedStatement ps = connection.prepareStatement("Select * from LoginDetails " +
						"where userName =? and password=?");
			ps.setString(1, req.getParameter("username"));
			ps.setString(2, req.getParameter("password"));
			ResultSet rs = ps.executeQuery();
			if(rs != null ){
				if(rs.next())
				{
					clearSession(req, resp);
					resp.getWriter().write("Success");
					resp.setStatus(200);
				}else
				{
					resp.getWriter().write("Failure");
					resp.setStatus(400);
				}
				 	
			}else
			{
				resp.getWriter().write("Failure");
				resp.setStatus(400);
			}
		}}
		catch(Exception e){
			
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	private void clearSession(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		 Enumeration<String> attrs = session.getAttributeNames();
		 while(attrs.hasMoreElements()) {
			 session.removeAttribute(attrs.nextElement());
		 }
	}
}
