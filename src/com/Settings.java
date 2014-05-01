package com;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Settings class, handles the channel settings frame
 * @author Miles Black
 * April 14, 2014
 */

public class Settings extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -5600496626447407945L;
	
	/**
	 * Fields
	 */
	private JTextField portText;
	private JTextField serverText;
	private JTextField userText;
	private JTextField channelText;
	private JTextField oAuthText;
	private JTextField imageText;
	
	private static Channel twitch;
	private static Settings frame = new Settings();
	
	private static boolean democracy;
	
	/**
	 * Accesses the twitch channel
	 * @return The twitch channel information used by the bot
	 */
	public static Channel getChannel() {
		return twitch;
	}

	/**
	 * Launches the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the main frame.
	 */
	public Settings() {
		setAutoRequestFocus(false);
		setTitle("Channel Settings");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 310);
		
		JLabel portLabel = new JLabel("Enter Port:");
		portLabel.setBounds(10, 11, 53, 14);
		portText = new JTextField();
		portText.setBounds(10, 31, 86, 20);
		portText.setText("6667");
		portText.setColumns(10);
		
		JLabel serverLabel = new JLabel("Enter Server:");
		serverLabel.setBounds(10, 62, 65, 14);
		serverText = new JTextField();
		serverText.setBounds(10, 82, 86, 20);
		serverText.setText("irc.twitch.tv");
		serverText.setColumns(10);
		
		JLabel userLabel = new JLabel("Enter User:");
		userLabel.setBounds(10, 113, 55, 14);
		userText = new JTextField();
		userText.setBounds(10, 133, 86, 20);
		userText.setColumns(10);
		
		JLabel channelLabel = new JLabel("Enter Channel");
		channelLabel.setBounds(10, 164, 68, 14);
		channelText = new JTextField();
		channelText.setBounds(10, 184, 86, 20);
		channelText.setColumns(10);
		
		JLabel oAuthLabel = new JLabel("Enter oAuth");
		oAuthLabel.setBounds(119, 11, 58, 14);
		oAuthText = new JTextField();
		oAuthText.setBounds(119, 31, 259, 20);
		oAuthText.setColumns(10);
		
		JButton findOAuth = new JButton("Find oAuth");
		findOAuth.setBounds(10, 222, 85, 23);
		findOAuth.addActionListener(this);
		findOAuth.setActionCommand("grabOAuth");
		
		JRadioButton democracyButton = new JRadioButton("Democracy");
		democracyButton.setBounds(119, 81, 79, 23);
		democracyButton.addActionListener(this);
		democracyButton.setActionCommand("democracyMode");
		
		JRadioButton anarchyButton = new JRadioButton("Anarchy");
		anarchyButton.setBounds(200, 81, 65, 23);
		anarchyButton.addActionListener(this);
		anarchyButton.setActionCommand("anarchyMode");
		
		JLabel gameModeLabel = new JLabel("Chose a Game Mode:");
		gameModeLabel.setBounds(119, 62, 102, 14);
		
		JButton loadSettings = new JButton("Load Settings");
		loadSettings.setBounds(224, 222, 97, 23);
		loadSettings.addActionListener(this);
		loadSettings.setActionCommand("loadProfile");
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(327, 222, 51, 23);
		exitButton.addActionListener(this);
		exitButton.setActionCommand("exit");
		
		JButton saveSettings = new JButton("Save Settings");
		saveSettings.setBounds(119, 222, 99, 23);
		saveSettings.addActionListener(this);
		saveSettings.setActionCommand("saveProfile");
		
		JLabel imageLabel = new JLabel("Broadcast Image (URL):");
		imageLabel.setBounds(119, 113, 115, 14);
		imageText = new JTextField();
		imageText.setBounds(119, 133, 259, 20);
		imageText.setColumns(10);
		
		getContentPane().setLayout(null);
		getContentPane().add(channelLabel);
		getContentPane().add(channelText);
		getContentPane().add(portLabel);
		getContentPane().add(portText);
		getContentPane().add(serverText);
		getContentPane().add(serverLabel);
		getContentPane().add(findOAuth);
		getContentPane().add(userLabel);
		getContentPane().add(userText);
		getContentPane().add(imageLabel);
		getContentPane().add(saveSettings);
		getContentPane().add(loadSettings);
		getContentPane().add(exitButton);
		getContentPane().add(oAuthLabel);
		getContentPane().add(gameModeLabel);
		getContentPane().add(oAuthText);
		getContentPane().add(democracyButton);
		getContentPane().add(anarchyButton);
		getContentPane().add(imageText);
	}
	
	/**
	 * Handles the actions performed in the main frame.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("grabOAuth")) {
			 Desktop d = Desktop.getDesktop();
			 try {
				d.browse(new URI("http://twitchapps.com/tmi/"));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals("saveProfile")) {
			try {
				Channel c = new Channel(serverText.getText(), Integer.parseInt(portText.getText()), userText.getText(), channelText.getText(), oAuthText.getText(), democracy, imageText.getText());
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("profile.ser"));
				out.writeObject(c);
				out.close();
			} catch(Throwable e1) {
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals("loadProfile")) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("profile.ser"));
				Channel c = (Channel) in.readObject();
				in.close();
				portText.setText(Integer.toString(c.getPort()));
				serverText.setText(c.getServer());
				userText.setText(c.getUser());
				channelText.setText(c.getChannel());
				oAuthText.setText(c.getOAuth());
				imageText.setText(c.getImage());
			} catch(Throwable e1) {
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals("exit")) {
			if (twitch == null) {
				twitch = new Channel(serverText.getText(), Integer.parseInt(portText.getText()), userText.getText(), channelText.getText(), oAuthText.getText(), democracy, imageText.getText());
			} else {
				twitch.setPort(Integer.parseInt(portText.getText()));
				twitch.setServer(serverText.getText());
				twitch.setUser(userText.getText());
				twitch.setChannel(channelText.getText());
				twitch.setOAuth(oAuthText.getText());
				twitch.setDemocracy(democracy);
			}
			frame.setVisible(false);
			Main gui = new Main();
		    gui.setVisible(true);
		} else if (e.getActionCommand().equals("democracyMode")) {
			democracy = true;
		} else if (e.getActionCommand().equals("anarchyMode")) {
			democracy = false;
		}
	}
}
