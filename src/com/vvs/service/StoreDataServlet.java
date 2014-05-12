package com.vvs.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.vv.apns.service.JavaAPNS;

/**
 * Servlet implementation class StoreDataServlet
 */
public class StoreDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBUtil dbUtil=new  DBUtil();
	
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StoreDataServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// http://localhost:8080/VVSProject/storeData?category=Notices&action=getCategoryData
		
		String category = request.getParameter("category");
		System.out.println("category::" + category);
		String action = request.getParameter("action");
		System.out.println("action::" + action);
		Timestamp todayDate = new Timestamp((new Date()).getTime());
		
		// Timestamp todayDate2 ="2013-04-24 20:17:05.519".getTime() ;

		Calendar currentDate = Calendar.getInstance();
		System.out.println("currentDate:" + currentDate);
		Calendar oldDate = Calendar.getInstance();
		oldDate.add(Calendar.DATE, -10);
		try {
			Connection connection =dbUtil.getConnection();
		//	("jdbc:sqlserver://192.168.10.6;databaseName=VVSDB;user=sa;password=sa@SQL123");
			PreparedStatement ps = null;
			ResultSet rs=null;
			int count = 0;
			
				String query ="";
				if(action.equals("getWebAppData"))
				{
					
					 query = "Select * from VVSDetails " + "where category='"
							+ category + "' ORDER BY Id DESC";
					 
					 HttpSession session=request.getSession();
					 session.setAttribute("categorySelected", category);
					 ps = connection.prepareStatement(query);
					 rs=ps.executeQuery();
					 List<HashMap> resultList=new ArrayList<HashMap>();
					 if (rs != null) {
							while (rs.next()) {
								HashMap m = new HashMap();
								m.put("id", rs.getString("Id"));
								m.put("Heading", rs.getString("heading"));
								m.put("Body", rs.getString("body"));

								resultList.add(m);
							}
					
				}
					 
					 RequestDispatcher rd = ((HttpServletRequest)request).getRequestDispatcher("ViewData.jsp");
					 System.out.println("resultList ::"+resultList.size());
						request.setAttribute("resultList",resultList );
						rd.forward(request, response);
				}else if(action.equals("getCategoryData")){
				long today = currentDate.getTime().getTime();
				java.sql.Date dateToday = new java.sql.Date(today);

				long tenDaysBack = oldDate.getTime().getTime();
				java.sql.Date dateTenDaysBack = new java.sql.Date(tenDaysBack);

				System.out.println("tenDaysBack::" + dateTenDaysBack);
				System.out.println("today::" + dateToday);

				query = "Select * from VVSDetails " + "where category='"
						+ category + "' and createdDate between '"
						+ dateTenDaysBack + "' and '" + dateToday
						+ "' ORDER BY Id DESC";
				System.out.println("query:" + query);
				ps = connection.prepareStatement(query);
				// ps.setTimestamp(2, "2012-12-12");
				rs = ps.executeQuery();
				List<Map> finalOutResp = new ArrayList<Map>();
				JSONArray array = new JSONArray();

				if (rs != null) {
					while (rs.next()) {
						Map m = new HashMap();
						m.put("id", ++count);
						m.put("Heading", rs.getString("heading"));
						m.put("Body", rs.getString("body"));

						finalOutResp.add(m);

						JSONObject json1 = new JSONObject(m);
						// JSONObject json2 = new JSONObject(jsonMap2);

						array.put(json1);
						// array.put(json2);

					}
					System.out.println("finalOutResp ::" + array.toString());
					response.getWriter().write(array.toString());
					response.setStatus(200);

				}
				
		}else if(action.equals("delData"))
		{
			System.out.println("in delete");
			query = "Delete from VVSDetails " + "where Id='"
					+ request.getParameter("id")+"'" ;
			System.out.println("query:" + query);
			ps = connection.prepareStatement(query);
			// ps.setTimestamp(2, "2012-12-12");
			int countOfAffectedRows = ps.executeUpdate();
			RequestDispatcher rd=request.getRequestDispatcher("ViewData.jsp");
			if(countOfAffectedRows>=1)
			{
				
				request.setAttribute("DELETE_STATUS",
						"Delete successfully");
				response.setStatus(200);
				
				//response.set(200);
					
			}
			HttpSession session=request.getSession();
			String categorySel=(String)session.getAttribute("categorySelected");
			 query = "Select * from VVSDetails " + "where category='"
						+ categorySel + "' ORDER BY Id DESC";
				 ps = connection.prepareStatement(query);
				 rs=ps.executeQuery();
				 List<HashMap> resultList=new ArrayList<HashMap>();
				 if (rs != null) {
						while (rs.next()) {
							HashMap m = new HashMap();
							m.put("id", rs.getString("Id"));
							m.put("Heading", rs.getString("heading"));
							m.put("Body", rs.getString("body"));

							resultList.add(m);
						}
				 }
				 request.setAttribute("resultList",resultList );
				 rd.forward(request, response);
			
		}
				 }catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().write("Invalid Request");
			response.setStatus(400);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Boolean isAddUser = Boolean.parseBoolean(request
				.getParameter("isAdduserPage"));
		try {
			
			Connection connection = dbUtil.getConnection();
//	("jdbc:sqlserver://192.168.10.6;databaseName=VVSDB;user=sa;password=sa@SQL123");
			if (!isAddUser) {
				String heading = (String) request.getParameter("heading");
				String body = (String) request.getParameter("body");
				String type = (String) request.getParameter("menu");
				Timestamp createdTimeStamp = new Timestamp(
						(new Date()).getTime());
				HttpSession session = request.getSession();
				String createdBy = (String) session
						.getAttribute("LOGGEDIN_NAME");
				System.out.println("createdDate:::" + createdTimeStamp);

				PreparedStatement ps = connection
						.prepareStatement("Insert into  VVSDetails values(?,?,?,?,?) ");
				ps.setString(1, heading);
				ps.setString(2, body);
				ps.setString(3, type);
				ps.setTimestamp(4, createdTimeStamp);
				ps.setString(5, createdBy);
				boolean result = ps.execute();
				
				RequestDispatcher rd = ((HttpServletRequest) request)
						.getRequestDispatcher("EnterNews.jsp");
				if (result)
					request.getSession().setAttribute("SuccessMsg",
							"Data Submitted Successfully");
				else
					request.getSession().setAttribute("SuccessMsg",
							"Problem occured while saving data");
				rd.forward(request, response);

				// Send Notification to devices
				JavaAPNS.sendUpdateNotificationToDevices(type);
			} else {

				String userName = request.getParameter("loginName");
				String emailId = request.getParameter("emailId");
				String isAdminFlag = request.getParameter("isAdminCheck");

				System.out.println("userName::" + userName + "emailId:::"
						+ emailId + "isAdminCheck::" + isAdminFlag);
				String password = generateRadomPassword(userName);
				PreparedStatement ps = connection
						.prepareStatement("Insert into  LoginDetails values(?,?,?,?) ");
				ps.setString(1, userName);
				ps.setString(2, password);
				ps.setString(3, emailId);
				ps.setString(4, isAdminFlag);
				try {
					Integer result = ps.executeUpdate();

					RequestDispatcher rd = ((HttpServletRequest) request)
							.getRequestDispatcher("AddUser.jsp");
					if (result.SIZE > 0) {
						request.getSession().setAttribute(
								"UserMsg",
								"Password generated for the user " + userName
										+ " is \"" + password + "\"");
						/* Send mail to newly created user with his details */
						boolean sendMailStatus = sendMail(userName, password,
								emailId);

					} else
						request.getSession().setAttribute("UserMsg",
								"Problem occured while adding user");
					rd.forward(request, response);
				} catch (SQLException e) {
					System.out.println("sql exec:" + e);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public boolean sendMail(String userName, String password, String emailId) {

		boolean status = false;
		java.util.Properties props = new java.util.Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "false");
		props.put("mail.debug", "false");
		props.setProperty("mail.user", "ajm.opus@gmail.com");
		props.setProperty("mail.password", "203.200.20");
		Session session = Session.getDefaultInstance(props, null);
		session = Session.getInstance(props, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ajm.opus@gmail.com",
						"203.200.20");
			}
		});

		session.setDebug(true);
		// Construct the message
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("ajm.opus@gmail.com"));
			Map<String, String> newEmailList = new HashMap<String, String>();
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
					emailId));
			msg.setSubject("VVS :Welcome ");
			msg.setHeader("charset", "UTF-8");
			msg.setHeader("Content-Transfer-Encoding", "8bit");
			StringBuilder body = new StringBuilder();
			body.append("<p>Hi ");
			body.append(userName + ",</p><br/>");
			body.append("Welcome to VVS App.You can now download the app and login using the below provided credentials<br/> <b>Login Name :</b> ");
			body.append(userName);
			body.append("<br/><b>Password :</b>");
			body.append(password);
			body.append("<br/><br/><br/><br/><hr/> Regards ,<br/> VVS App");
			msg.setContent(body.toString(), "text/html");

			// Send the message
			Transport.send(msg);
			status = true;
		} catch (AddressException e) {
			status = false;
			e.printStackTrace();
		} catch (MessagingException e) {
			status = false;
			e.printStackTrace();
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	public static String generateRadomPassword(String username) {

		Random random = new Random();
		Integer number = random.nextInt(500) + 1;
		// if random value generated is space
		if (((int) number) == 32) {
			number = random.nextInt(500) + 1;
			;
		}
		String randomValue = number.toString();
		String password = username.substring(0, 4).toUpperCase() + randomValue;
		return password;

	}

}
