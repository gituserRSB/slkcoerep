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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import javapns.Push;
import javapns.devices.Device;
import javapns.notification.PushedNotifications;

/**
 * @author chandrika.morla
 * 
 */
public class JavaAPNS {
	
	/**
	 * Notification Parameters
	 */
	private static int badge = 1;
	private static String sound = "default";
	private static String keystorepath = "D:\\VVS Cert\\CertificatesVVSApp.p12";
	private static String password = "Arjunamin1234";
	private static boolean production = false;
	
	public static PushedNotifications sendNotifications(String message, int badge, String sound, Object keystore, String password, boolean production,  List<javapns.devices.Device>  devices )
	{
		PushedNotifications SuccessfulNotifications = null;
		try {
			PushedNotifications sent = Push.combined(message, badge, sound, keystore, password, production, devices);
			System.out.println("Sent PushedNotifications " + sent);
			SuccessfulNotifications = sent
					.getSuccessfulNotifications();
			System.out.println("Sent SuccessfulNotifications "
					+ SuccessfulNotifications);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SuccessfulNotifications;
	}
	
	public static PushedNotifications sendNotificationToDevice(String message, int badge, String sound, Object keystore, String password, boolean production,  javapns.devices.Device  device )
	{
		PushedNotifications SuccessfulNotifications = null;
		try {
			PushedNotifications sent = Push.combined(message, badge, sound, keystore, password, production, device);
			System.out.println("Sent PushedNotifications " + sent);
			SuccessfulNotifications = sent
					.getSuccessfulNotifications();
			System.out.println("Sent SuccessfulNotifications "
					+ SuccessfulNotifications);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return SuccessfulNotifications;
	}
	
	public static void sendUpdateNotificationToDevices(String message)
	{
		List<Device> devices = DBService.getDevices();
		for(Device mobiDevice : devices)
		{

			int _badge = 0;
			String badge = ((MobileDevice)mobiDevice).getBadge();
			if(badge != null && badge.equals(""))
			{
				try{
					_badge = Integer.parseInt(badge);
				}catch(Exception e)
				{
					_badge = 0;
				}
			}
			++_badge;
			((MobileDevice)mobiDevice).setBadge(""+_badge);
			PushedNotifications SuccessfulNotifications = sendNotificationToDevice(message, _badge, sound,
					keystorepath, password, production, mobiDevice);
			System.out.println("After local method Sent SuccessfulNotifications "
					+ SuccessfulNotifications);
		}
		for(Device device : devices)
		{
			MobileDevice mobiDevice = (MobileDevice)device;
			DBService.updateDevice(mobiDevice);
		}
		/*PushedNotifications SuccessfulNotifications = sendNotifications(message, badge++, sound,
				keystorepath, password, production, devices);
		System.out.println("After local method Sent SuccessfulNotifications "
				+ SuccessfulNotifications);*/
	}
	
	
	public static void main(String[] args) throws Exception {
		try {
			// Push.alert("Hello World!", "keystore.p12", "keystore_password",
			// false, "Your token");

			String message = "Resending Notification!" + new Date();

			// KeyStore keystore = KeyStore.getInstance("keystore.p12");
			String keystorepath = "D:\\VVS Cert\\CertificatesVVSApp.p12";
			String password = "Arjunamin1234";
			boolean production = false;
			Device device = new MobileDevice();
			device.setToken("7702a0e8827db72a222139a2e462da9d206bfe356b79f936105d360e94a18799");
			// PushedNotifications sent = Push.alert(message, keystorepath,
			// password, production , devices);
			/*PushedNotifications sent = Push.combined(message, 45, "default",
					keystorepath, password, production, device);
			System.out.println("Sent PushedNotifications " + sent);
			PushedNotifications SuccessfulNotifications = sent
					.getSuccessfulNotifications();
			System.out.println("Sent SuccessfulNotifications "
					+ SuccessfulNotifications);*/
			// call Local method

			List<javapns.devices.Device>  devices = DBService.getDevices();
			PushedNotifications SuccessfulNotifications = sendNotifications(message, 45, "default",
					keystorepath, password, production, devices);
			System.out.println("After local method Sent SuccessfulNotifications "
					+ SuccessfulNotifications);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
