package com;

import java.io.Serializable;

public class Channel implements Serializable {
	
	private static final long serialVersionUID = -8374238202673046429L;

	public Channel(String server, int port, String user, String channel, String oauth, boolean democracy) {
		this.server = server;
		this.port = port;
		this.user = user;
		this.channel = channel;
		this.oauth = oauth;
		this.democracy = democracy;
	}

	private String server;
	private int port;
	private String user;
	private String channel;
	private String oauth;
	private boolean democracy;
	  
	public String getServer() {
		return this.server;
	}
	  
	public int getPort() {
		return this.port;
	}
	  
	public String getUser() {
	    return this.user;
	}
	  
	public String getChannel() {
	    return this.channel;
	}
	  
	public String getOAuth() {
	    return this.oauth;
	}
	
	public boolean isDemocracy() {
	    return this.democracy;
	}
	  
	public void setServer(String server) {
		this.server = server;
	}
	  
	public void setPort(int port) {
		this.port = port;
	}
	  
	public void setUser(String user) {
		this.user = user;
	}
	  
	public void setChannel(String channel) {
		this.channel = channel;
	}
	  
	public void setOAuth(String oauth) {
		this.oauth = oauth;
	}
	
	public void setDemocracy(boolean democracy) {
		this.democracy = democracy;
	}

}
