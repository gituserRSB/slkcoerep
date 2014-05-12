/**
 * 
 */
package com.vv.apns.service;

import java.sql.Timestamp;

import javapns.devices.Device;

/**
 * @author chandrika.morla
 *
 */
public class MobileDevice  implements Device{

    
	protected String deviceId;
    
	protected String type;
    
	protected String userAccount;
    
	protected String deviceName;
    
	protected String token;
    
	protected String deviceUnlockToken;
    
	protected String deviceOSVersion;
	
	private String deviceRegistrationID;
    
	protected String deviceOS;
	
	protected Timestamp lastRegister;
	
	protected String badge;


    public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String account) {
		this.userAccount = account;
	}

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param deviceUnlockToken the deviceUnlockToken to set
	 */
	public void setDeviceUnlockToken(String deviceUnlockToken) {
		this.deviceUnlockToken = deviceUnlockToken;
	}

	/**
	 * @return the deviceOSVersion
	 */
	public String getDeviceOSVersion() {
		return deviceOSVersion;
	}

	/**
	 * @param deviceOSVersion the deviceOSVersion to set
	 */
	public void setDeviceOSVersion(String deviceOSVersion) {
		this.deviceOSVersion = deviceOSVersion;
	}

	/**
	 * @return the deviceRegistrationID
	 */
	public String getDeviceRegistrationID() {
		return deviceRegistrationID;
	}

	/**
	 * @param deviceRegistrationID the deviceRegistrationID to set
	 */
	public void setDeviceRegistrationID(String deviceRegistrationID) {
		this.deviceRegistrationID = deviceRegistrationID;
	}

	/**
	 * @return the deviceOS
	 */
	public String getDeviceOS() {
		return deviceOS;
	}

	/**
	 * @param deviceOS the deviceOS to set
	 */
	public void setBadge(String badge) {
		this.badge = badge;
	}


	/**
	 * @return the deviceOS
	 */
	public String getBadge() {
		return badge;
	}

	/**
	 * @param deviceOS the deviceOS to set
	 */
	public void setDeviceOS(String deviceOS) {
		this.deviceOS = deviceOS;
	}
	@Override
	public String getToken() {
		return this.token;
	}

	@Override
	public Timestamp getLastRegister() {
		return this.lastRegister;
	}

	@Override
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public void setLastRegister(Timestamp lastRegister) {
		this.lastRegister = lastRegister;
	}

}
