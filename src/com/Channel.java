package com;

import java.io.Serializable;

/**
 * Channel class, handles the twitch channels
 * @author Miles Black
 * April 14, 2014
 */

public class Channel implements Serializable {
	
	private static final long serialVersionUID = -8374238202673046429L;

	/**
	 * Channel Constructor
	 * @param server The server address for the twitch chat
	 * @param port The port for the twitch chat
	 * @param user The username of the twitch account being used
	 * @param channel The name of the twitch channel that is hosting
	 * @param oauth The OAuth key for the twitch stream
	 * @param democracy Indicates wether this is democracy or anarchy mode
	 * @param image The link of the image used for the stream
	 */
	public Channel(String server, int port, String user, String channel, String oauth, boolean democracy, String image) {
		this.server = server;
		this.port = port;
		this.user = user;
		this.channel = channel;
		this.oauth = oauth;
		this.democracy = democracy;
		this.image = image;
	}

	/**
	 * Fields
	 */
	private String server;
	private int port;
	private String user;
	private String channel;
	private String oauth;
	private boolean democracy;
	private String image;
	  
	/**
	 * Returns the chat server's address
	 * @return server address
	 */
	public String getServer() {
		return this.server;
	}
	  
	/**
	 * Returns the connecting port
	 * @return server port
	 */
	public int getPort() {
		return this.port;
	}
	  
	/**
	 * Returns the user name that interacts with the bot
	 * @return account user name
	 */
	public String getUser() {
	    return this.user;
	}
	  
	/**
	 * Returns the channel hosting the bot
	 * @return channel name
	 */
	public String getChannel() {
	    return this.channel;
	}
	  
	/**
	 * Returns the OAuth key used in the stream
	 * @return oauth key
	 */
	public String getOAuth() {
	    return this.oauth;
	}
	
	/**
	 * Returns if the game mode is Democracy or Anarchy
	 * @return democracy or anarchy
	 */
	public boolean isDemocracy() {
	    return this.democracy;
	}
	
	/**
	 * Returns the URL to the image used in the stream
	 * @return image url
	 */
	public String getImage() {
	    return this.image;
	}
	  
	/**
	 * Sets the address for the chat server
	 * @param server The address for the chat server
	 */
	public void setServer(String server) {
		this.server = server;
	}
	  
	/**
	 * Sets the port used to connect to the chat server
	 * @param port The port for the chat server
	 */
	public void setPort(int port) {
		this.port = port;
	}
	  
	/**
	 * Sets the user name that is running the chat bot
	 * @param user The user name that will run the chat bot
	 */
	public void setUser(String user) {
		this.user = user;
	}
	  
	/**
	 * Sets the channel that will be hosting the bot
	 * @param channel The name of the channel that is hosting the bot
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	  
	/**
	 * Sets the OAuth key that will validate the connection
	 * @param oauth The OAuth key that is used in the validation process
	 */
	public void setOAuth(String oauth) {
		this.oauth = oauth;
	}
	
	/**
	 * Sets the game mode as Democracy or Anarchy
	 * @param democracy Indicates whether the game mode is on Democracy or Anarchy
	 */
	public void setDemocracy(boolean democracy) {
		this.democracy = democracy;
	}
	
	/**
	 * Sets the image URL for the broadcast panel
	 * @param url The image URL used in the broadcast panel
	 */
	public void setImage(String url) {
		this.image = url;
	}

}
