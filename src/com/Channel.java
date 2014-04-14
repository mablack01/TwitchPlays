package com;

public class Channel {

  public Channel(String server, int port, String user, String channel, int oauth) {
    this.SERVER = server;
    this.PORT = port;
    this.USER = user;
    this.CHANNEL = channel;
    this.OAUTH = oauth;
  }
  
  public Channel(String user, String channel, int oauth) {
    this.SERVER = "irc.twitch.tv";
    this.PORT = 6667;
    this.USER = user;
    this.CHANNEL = channel;
    this.OAUTH = oauth;
  }

  private String SERVER;
  private int PORT;
  private String USER;
  private String CHANNEL;
  private int OAUTH;
  
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
  
  public int getOAuth() {
    return this.OAUTH;
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
  
  public void setOAuth(int o) {
    this.OAUTH = o;
  }

}
