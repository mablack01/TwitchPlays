package com;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.SwingUtilities;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

/**
 * @author Miles Black
 * Coding assistance used from https://github.com/Sidesplitter/TwitchPlays/
 * Handles the IRC Chat and Interpretation using PircBot
 */

public class IRC extends PircBot {
	
	/**
	 * The input keys and their corresponding key actions
	 */
	private String[][] keys = {{"up", "VK_UP"},
							{"down", "VK_DOWN"},
							{"left", "VK_LEFT"},
							{"right", "VK_RIGHT"},
							{"select", "VK_X"},
							{"start", "VK_Y"},
							{"a", "VK_A"},
							{"b", "VK_B"}};
	
	/**
	 * Fields
	 */
    private Robot robot;
    private Channel twitch = Settings.getChannel();
    
    /**
     * Default Constructor
     * Sets up the channel user
     */

    public IRC(){
        this.setName(twitch.getUser());
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Initiates the connection with the channel
     */
    public void start() throws IrcException, IOException {
	    	Main.setStatus("Connecting to IRC...");
	        this.connect(twitch.getServer(), twitch.getPort(), twitch.getOAuth());
	        Main.setStatus("Joining the channel...");
	        this.joinChannel("#" + twitch.getChannel());
	        if (twitch.isDemocracy())
	        	Main.setStatus("We are now playing on Democracy Mode!");
	        else
	        	Main.setStatus("We are now playing on Anarchy Mode!");
	}
    
    /**
     * Handles the disconnection of the client
     */
    public void onDisconnect(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main.setStatus("Disconnected");
            }
        });
    }

    /**
     * Reacts to the messages sent to the chat
     */
    @SuppressWarnings("unchecked")
	public void onMessage(String channel, final String sender, String login, String hostname, final String message) {
        String[] input = message.split(" ");
        int amount = Integer.parseInt(input[1]);
    	for (int i = 0; i < keys.length; i++) {
    		if (keys[i][0].equalsIgnoreCase(input[0]) || keys[i][0].equalsIgnoreCase(message)) {
    			if (twitch.isDemocracy()) {
    				Main.listModel.addElement(sender + " " + input[0] + "\n");
    				this.pressKey(keys[i][1]);
    			} else if (amount > 0 && amount < 10) {
    				Main.listModel.addElement(sender + " " + message + "\n");
    				for (int j = 0; j <= amount; j++)
    					this.pressKey(keys[i][1]);
    			} else
    				return;
    		}
    	}
    }

    /**
     * Gets the robot that sends the actions
     * @return robot being used
     */
    public Robot getRobot(){
        return this.robot;
    }

    /**
     * Uses the robot to press the keys corresponding to the chat
     * @param key The button key that indicates the button to press
     * @param democracy Indicates the if the game mode is democracy
     * @param number The amount of times to press the button
     */
    public void pressKey(String key){
        	try {
				getRobot().keyPress((Integer) KeyEvent.class.getDeclaredField(key).get(null));
			} catch (IllegalArgumentException | IllegalAccessException
					| NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
    }
}
