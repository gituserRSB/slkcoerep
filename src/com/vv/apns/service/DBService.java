/**
 * 
 */
package com.vv.apns.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javapns.devices.Device;

/**
 * @author chandrika.morla
 *
 */
public class DBService {

	private static Connection connection;
	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection("jdbc:sqlserver://192.168.10.6;databaseName=VVSDB;user=sa;password=sa@SQL123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns list of registered mobile devices
	 * 
	 * @return
	 */
	public static List<Device> getDevices()
	{
		List<Device> devices = new ArrayList<Device>();
		//Connection connection;
		try {
			/*connection = DriverManager.getConnection
					//("jdbc:sqlserver://192.168.144.81;databaseName=VVSDB;user=sa;password=root123");

			("jdbc:sqlserver://192.168.10.6;databaseName=VVSDB;user=sa;password=sa@SQL123");*/
			PreparedStatement ps = null;
			ResultSet rs=null;
			String query = "Select * from DEVICES ";
			 
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();

			/* [UDID] ,[DEVICENAME] ,[REGID] ,[DEVICETOKEN] ,[DEVICEUNLOCKTOKEN]
			 * ,[DEVICEOS] ,[ACCOUNT]
			 */
			if (rs != null) {
				while (rs.next()) {
					MobileDevice device = new MobileDevice();
					device.setDeviceId(rs.getString("UDID"));
					device.setDeviceName(rs.getString("DEVICENAME"));
					device.setToken(rs.getString("DEVICETOKEN"));
					device.setDeviceUnlockToken(rs
							.getString("DEVICEUNLOCKTOKEN"));
					device.setDeviceOS(rs.getString("DEVICEOS"));
					device.setUserAccount(rs.getString("ACCOUNT"));
					device.setBadge(rs.getString("BADGE"));

					devices.add(device);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return devices;
	}
    
	/**
	 * 
	 * @param device
	 * @return
	 */
    public static boolean isDeviceRegistered(MobileDevice device)
    {
		boolean isDeviceRegistered = false;
		//Connection connection = null;
		try
		{

		
		/*connection = DriverManager.getConnection
		//("jdbc:sqlserver://192.168.144.81;databaseName=VVSDB;user=sa;password=root123");
					("jdbc:sqlserver://192.168.10.6;databaseName=VVSDB;user=sa;password=sa@SQL123");*/
			PreparedStatement ps = connection.prepareStatement("Select * from DEVICES where UDID like ?");
			
			ps.setString(1, device.deviceId);

			ResultSet rs=ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					isDeviceRegistered = true;
					break;
				}
			}
		} catch(Exception e)
		{
			e.printStackTrace();
		} finally {
			/*if(connection != null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}*/
			
		}
		return isDeviceRegistered;
    }

    /**
     * 
     * @param device
     * @return
     */
	public static boolean addDevice(MobileDevice device) {
		boolean result = true;
		//Connection connection = null;
		try
		{

		/*Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		connection = DriverManager.getConnection
		//("jdbc:sqlserver://192.168.144.81;databaseName=VVSDB;user=sa;password=root123");
					("jdbc:sqlserver://192.168.10.6;databaseName=VVSDB;user=sa;password=sa@SQL123");*/
			PreparedStatement ps = connection
					.prepareStatement("Insert into  DEVICES (UDID ,DEVICENAME ,DEVICETOKEN ,DEVICEUNLOCKTOKEN ,DEVICEOS ,ACCOUNT,BADGE) values(?,?,?,?,?,?,?) ");
			ps.setString(1, device.deviceId);
			ps.setString(2, device.deviceName);
			//ps.setString(3, device.getDeviceRegistrationID());
			ps.setString(3, device.token);
			ps.setString(4, device.deviceUnlockToken);
			ps.setString(5, device.deviceOS);
			ps.setString(6, device.getUserAccount());
			ps.setString(7, "0"); // TODO Need to get from device request
			/*	.prepareStatement("Insert into  DEVICES (UDID ,DEVICENAME ,REGID ,DEVICETOKEN ,DEVICEUNLOCKTOKEN ,DEVICEOS ,ACCOUNT) values(?,?,?,?,?,?,?) ");ps.setString(1, device.deviceId);
			ps.setString(2, device.deviceName);
			ps.setString(3, device.getDeviceRegistrationID());
			ps.setString(4, device.token);
			ps.setString(5, device.deviceUnlockToken);
			ps.setString(6, device.deviceOS);
			ps.setString(7, device.getUserAccount());*/
			ps.execute();
		
		} catch(Exception e)
		{
			result = false;
			e.printStackTrace();
		} finally {
			/*if(connection != null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}*/
			
		}
		return result;
	}
	

	/**
	 * 
	 * @param device
	 * @return
	 */
	public static boolean updateDevice(MobileDevice device) {
		boolean result = true;
		//Connection connection = null;
		try
		{

		/*Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		connection = DriverManager.getConnection
		//("jdbc:sqlserver://192.168.144.81;databaseName=VVSDB;user=sa;password=root123");
					("jdbc:sqlserver://192.168.10.6;databaseName=VVSDB;user=sa;password=sa@SQL123");*/
			PreparedStatement ps = connection
					.prepareStatement("update  DEVICES set DEVICENAME = ? ,DEVICETOKEN = ? ,DEVICEUNLOCKTOKEN = ? ,DEVICEOS = ? ,ACCOUNT = ? ,BADGE = ? where UDID like ? ");
			ps.setString(1, device.deviceName);
			ps.setString(2, device.token);
			ps.setString(3, device.deviceUnlockToken);
			ps.setString(4, device.deviceOS);
			ps.setString(5, device.getUserAccount());
			ps.setString(6, device.getBadge());
			ps.setString(7, device.deviceId);
			ps.execute();		
		} catch(Exception e)
		{
			result = false;
			e.printStackTrace();
		} finally {
			/*if(connection != null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}*/
			
		}
		return result;
	}
}
