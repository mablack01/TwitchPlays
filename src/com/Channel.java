package com;

import java.io.Serializable;

public class Channel implements Serializable {
	
	private static final long serialVersionUID = -8374238202673046429L;

	public Channel(String server, int port, String user, String channel, String oauth, boolean democracy) {
		this.SERVER = server;
		this.PORT = port;
		this.USER = user;
		this.CHANNEL = channel;
		this.OAUTH = oauth;
		this.DEMOCRACY = democracy;
	}

	private String SERVER;
	private int PORT;
	private String USER;
	private String CHANNEL;
	private String OAUTH;
	private boolean DEMOCRACY;
	  
	public String getServer() {
		return this.SERVER;
	}
	  
	public int getPort() {
		return this.PORT;
	}
	  
	public String getUser() {
	    return this.USER;
	}
	  
	public String getChannel() {
	    return this.CHANNEL;
	}
	  
	public String getOAuth() {
	    return this.OAUTH;
	}
	
	public boolean isDemocracy() {
	    return this.DEMOCRACY;
	}
	  
	public void setServer(String s) {
		this.SERVER = s;
	}
	  
	public void setPort(int p) {
		this.PORT = p;
	}
	  
	public void setUser(String u) {
		this.USER = u;
	}
	  
	public void setChannel(String c) {
		this.CHANNEL = c;
	}
	  
	public void setOAuth(String o) {
		this.OAUTH = o;
	}
	
	public void setDemocracy(boolean b) {
		this.DEMOCRACY = b;
	}

}
