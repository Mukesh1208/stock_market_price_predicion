package com.model;

import java.io.Serializable;



/**
 * @author Bushra
 *
 */
public class Message implements Serializable {	
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String msg;

public void setMsg(String msg) {
	System.out.println("Msg="+msg);
	this.msg = msg;
}

public String getMsg() {
	return msg;
}
	

}
