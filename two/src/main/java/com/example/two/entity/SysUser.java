package com.example.two.entity;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable/*implements UserDetails*/ {
	
	/**
	 *  @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -8971529096677356964L;
	
	/*user id*/
    private String id;
 
    private String userName;
 
    private String password;
    
    private Date lastPasswordResetDate;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	
    

}
