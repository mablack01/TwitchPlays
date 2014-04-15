package com;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Miles Black
 * Coding assistance used from https://github.com/Sidesplitter/TwitchPlays/
 * Handles the IRC Chat and Interpretation using PircBot
 */

public class IRC extends PircBot {
	
	private String[][] keys = {{"up", "VK_UP"},
							{"down", "VK_DOWN"},
							{"left", "VK_LEFT"},
							{"right", "VK_RIGHT"},
							{"select", "VK_X"},
							{"start", "VK_Y"},
							{"a", "VK_A"},
							{"b", "VK_B"}};

    private Robot robot;
    private Channel channel = Settings.getChannel();
    
    /*
     * Default Constructor
     * Sets up the channel user
     */

    public IRC(){
        this.setName(channel.getUser());
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Initiates the connection with the channel
     */

    public void start() throws IrcException, IOException {
	    	Main.setStatus("Connecting to IRC...");
	        this.connect(channel.getServer(), channel.getPort(), channel.getOAuth());
	        System.out.println("CONNECTED");
	        Main.setStatus("Joining the channel...");
	        this.joinChannel("#" + channel.getChannel());
	        System.out.println("JOINED");
	        Main.setStatus("Connected!");
	}
    
    /*
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

    public void onMessage(String channel, final String sender, String login, String hostname, final String message) {
    	for (int i = 0; i < keys.length; i++) {
    		if (keys[i][0].contains(message))
    			this.pressKey(keys[i][1]);
    	}
    }

    public Robot getRobot(){
        return this.robot;
    }

    public void pressKey(String key){
        try {
            getRobot().keyPress((Integer) KeyEvent.class.getDeclaredField(key).get(null));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
