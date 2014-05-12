package com.vv.apns.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






public class RegisterDeviceServlet extends HttpServlet {
	
	Logger Log = Logger.getLogger(RegisterDeviceServlet.class.getName());

    /**
     * @deprecated will be removed in next rel.
     */
    @Deprecated
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }
//?deviceid=7702a0e8827db72a222139a2e462da9d206bfe356b79f936105d360e94a18799&registrationid=7702a0e8827db72a222139a2e462da9d206bfe356b79f936105d360e94a18799&devicename=slkcoe&useraccount=Arjun&devicetoken=7702a0e8827db72a222139a2e462da9d206bfe356b79f936105d360e94a18799
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");        
        String deviceId = req.getParameter("deviceid");
        String deviceRegistrationID = req.getParameter("registrationid");
        String dname = req.getParameter("devicename");
        String account = req.getParameter("useraccount");
        String pwd = req.getParameter("password");
        String osmajor = req.getParameter("deviceos");
        String token = req.getParameter("devicetoken");
        String unlocktoken = req.getParameter("deviceunlocktoken");
        Log.info(deviceId + deviceRegistrationID + account);
        if (token == null || deviceId == null || account == null  ) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Registration Failed");
            return;
        } else {

    		MobileDevice device = new MobileDevice();
    		device.setDeviceId(deviceId);
    		device.setDeviceOS(osmajor);
    		device.setToken(token);
    		device.setDeviceUnlockToken(unlocktoken);
    		device.setDeviceName(dname);
    		device.setDeviceRegistrationID(deviceRegistrationID);
    		device.setUserAccount(account);
    		// Add device
    		boolean isDeviceAlreadyRegistered = DBService.isDeviceRegistered(device);
    		boolean success = true;
    		Log.info("isDeviceAlreadyRegistered "+isDeviceAlreadyRegistered);
    		Log.info("deviceId "+device.deviceId);
    		if(isDeviceAlreadyRegistered)
    		{
    			DBService.updateDevice(device);
    		}
    		else
    		{
    			DBService.addDevice(device);
    		}
        	if (success ) {
                resp.setStatus(200);
                resp.getWriter().println("Registration Successful");
        	} else {
                resp.setStatus(400);
                resp.getWriter().println("Registration Failed");
            }
        }
    }
}
