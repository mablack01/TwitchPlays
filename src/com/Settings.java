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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

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
		portText = new JTextField();
		portText.setText("6667");
		portText.setColumns(10);
		
		JLabel serverLabel = new JLabel("Enter Server:");
		serverText = new JTextField();
		serverText.setText("irc.twitch.tv");
		serverText.setColumns(10);
		
		JLabel userLabel = new JLabel("Enter User:");
		userText = new JTextField();
		userText.setColumns(10);
		
		JLabel channelLabel = new JLabel("Enter Channel");
		channelText = new JTextField();
		channelText.setColumns(10);
		
		JLabel oAuthLabel = new JLabel("Enter oAuth");
		oAuthText = new JTextField();
		oAuthText.setColumns(10);
		
		JButton findOAuth = new JButton("Find oAuth");
		findOAuth.addActionListener(this);
		findOAuth.setActionCommand("grabOAuth");
		
		JRadioButton democracyButton = new JRadioButton("Democracy");
		democracyButton.addActionListener(this);
		democracyButton.setActionCommand("democracyMode");
		
		JRadioButton anarchyButton = new JRadioButton("Anarchy");
		anarchyButton.addActionListener(this);
		anarchyButton.setActionCommand("anarchyMode");
		
		JLabel gameModeLabel = new JLabel("Chose a Game Mode:");
		
		JButton loadSettings = new JButton("Load Settings");
		loadSettings.addActionListener(this);
		loadSettings.setActionCommand("loadProfile");
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		exitButton.setActionCommand("exit");
		
		JButton saveSettings = new JButton("Save Settings");
		saveSettings.addActionListener(this);
		saveSettings.setActionCommand("saveProfile");
		
		JLabel imageLabel = new JLabel("Broadcast Image (URL):");
		imageText = new JTextField();
		imageText.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(channelLabel)
						.addComponent(channelText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(portLabel)
								.addComponent(portText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(serverText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(serverLabel)
								.addComponent(findOAuth)
								.addComponent(userLabel)
								.addComponent(userText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(23)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(imageLabel)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(saveSettings)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(loadSettings)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(exitButton))
								.addComponent(oAuthLabel)
								.addComponent(gameModeLabel)
								.addComponent(oAuthText)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(democracyButton)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(anarchyButton)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(imageText))))
					.addContainerGap(76, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(portLabel)
						.addComponent(oAuthLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(portText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(oAuthText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(serverLabel)
						.addComponent(gameModeLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(serverText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(democracyButton)
						.addComponent(anarchyButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(userLabel)
						.addComponent(imageLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(userText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(imageText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(channelLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(channelText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(findOAuth)
						.addComponent(saveSettings)
						.addComponent(loadSettings)
						.addComponent(exitButton))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
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
