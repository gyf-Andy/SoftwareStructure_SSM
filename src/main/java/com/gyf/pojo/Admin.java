package com.gyf.pojo;

import java.io.Serializable;

public class Admin implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8344510186312073818L;
	private String account;
	private String password;
	private String name;
	

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String toString(){
		return "[" + account+"  "+password+"   "+name+"]";
	}
	
}
