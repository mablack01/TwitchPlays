package com;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JEditorPane;
import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ButtonGroup;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.Action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5600496626447407945L;
	private JTextField port;
	private JTextField server;
	private JTextField user;
	private JTextField channel;
	private JTextField oauth;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final Action action = new oAuth();
	private final Action action_1 = new saveProfile();
	private final Action action_2 = new loadProfile();
	private final Action action_3 = new Exit();
	private static Settings frame = new Settings();
	private final Action action_4 = new democracyMode();
	private static boolean democracy;
	private static Channel twitch;
	private final Action action_5 = new anarchyMode();
	
	public static Channel getChannel() {
		return twitch;
	}

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public Settings() {
		setAutoRequestFocus(false);
		setTitle("Channel Settings");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 310);
		
		JLabel lblEnterPort = new JLabel("Enter Port:");
		
		port = new JTextField();
		port.setText("6667");
		port.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter Server:");
		
		server = new JTextField();
		server.setText("irc.twitch.tv");
		server.setColumns(10);
		
		JLabel lblEnterUser = new JLabel("Enter User:");
		
		user = new JTextField();
		user.setColumns(10);
		
		JLabel lblEnterChannel = new JLabel("Enter Channel");
		
		channel = new JTextField();
		channel.setColumns(10);
		
		JLabel lblEnterOauth = new JLabel("Enter oAuth");
		
		oauth = new JTextField();
		oauth.setColumns(10);
		
		JButton btnFindOauth = new JButton("Find oAuth");
		btnFindOauth.setAction(action);
		
		JRadioButton rdbtnDemocracy = new JRadioButton("Democracy");
		rdbtnDemocracy.setAction(action_4);
		buttonGroup.add(rdbtnDemocracy);
		
		JRadioButton rdbtnAnarchy = new JRadioButton("Anarchy");
		rdbtnAnarchy.setAction(action_5);
		buttonGroup.add(rdbtnAnarchy);
		
		JLabel lblChoseAGame = new JLabel("Chose a Game Mode:");
		
		JButton btnSaveSettings = new JButton("Load Settings");
		btnSaveSettings.setAction(action_2);
		
		JButton btnLoadSettings = new JButton("Exit");
		btnLoadSettings.setAction(action_3);
		
		JButton btnCreateSettings = new JButton("Save Settings");
		btnCreateSettings.setAction(action_1);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEnterUser)
						.addComponent(user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEnterChannel)
						.addComponent(channel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEnterPort)
								.addComponent(port, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(server, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)
								.addComponent(btnFindOauth))
							.addGap(23)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnCreateSettings)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSaveSettings)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnLoadSettings))
								.addComponent(lblEnterOauth)
								.addComponent(lblChoseAGame)
								.addComponent(oauth)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(rdbtnDemocracy)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnAnarchy)
									.addPreferredGap(ComponentPlacement.RELATED)))))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterPort)
						.addComponent(lblEnterOauth))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(port, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(oauth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblChoseAGame))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(server, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnDemocracy)
						.addComponent(rdbtnAnarchy))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblEnterUser)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblEnterChannel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(channel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFindOauth)
						.addComponent(btnCreateSettings)
						.addComponent(btnSaveSettings)
						.addComponent(btnLoadSettings))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private class oAuth extends AbstractAction {
		
		private static final long serialVersionUID = -6317472743755430650L;

		public oAuth() {
			putValue(NAME, "Grab oAuth");
			putValue(SHORT_DESCRIPTION, "Retrieves your oAuth used for your channel settings.");
		}
		
		public void actionPerformed(ActionEvent e) {
			 Desktop d = Desktop.getDesktop();
			 try {
				d.browse(new URI("http://twitchapps.com/tmi/"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	private class saveProfile extends AbstractAction {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 6302203154105704959L;

		public saveProfile() {
			putValue(NAME, "Save Profile");
			putValue(SHORT_DESCRIPTION, "Saves your channel profile to profile.ser");
		}
		
		public void actionPerformed(ActionEvent e) {
			try {
				Channel c = new Channel(server.getText(), Integer.parseInt(port.getText()), user.getText(), channel.getText(), oauth.getText(), democracy);
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("profile.ser"));
				out.writeObject(c);
				out.close();
			} catch(Throwable e1) {
				
			}
		}
		
	}
	
	private class loadProfile extends AbstractAction {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 4590669890416091725L;

		public loadProfile() {
			putValue(NAME, "Load Profile");
			putValue(SHORT_DESCRIPTION, "Loads the information from profile.ser");
		}
		
		public void actionPerformed(ActionEvent e) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("profile.ser"));
				Channel c = (Channel) in.readObject();
				in.close();
				port.setText(Integer.toString(c.getPort()));
				server.setText(c.getServer());
				user.setText(c.getUser());
				channel.setText(c.getChannel());
				oauth.setText(c.getOAuth());
			} catch(Throwable e1) {
				
			}
		}
		
	}
	
	private class Exit extends AbstractAction {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 2059988636808683931L;

		public Exit() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Save and exit your channel data.");
		}
		
		public void actionPerformed(ActionEvent e) {
			if (twitch == null) {
				twitch = new Channel(server.getText(), Integer.parseInt(port.getText()), user.getText(), channel.getText(), oauth.getText(), democracy);
			}
			frame.setVisible(false);
			Main gui = new Main();
		    gui.setVisible(true);
			twitch.setPort(Integer.parseInt(port.getText()));
			twitch.setServer(server.getText());
			twitch.setUser(user.getText());
			twitch.setChannel(channel.getText());
			twitch.setOAuth(oauth.getText());
			twitch.setDemocracy(democracy);
		}
		
	}
	
	private class democracyMode extends AbstractAction {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -1462165668825952617L;

		public democracyMode() {
			putValue(NAME, "Democracy");
			putValue(SHORT_DESCRIPTION, "Chose the game mode Democracy.");
		}
		
		public void actionPerformed(ActionEvent e) {
			democracy = true;
		}
		
	}
	
	private class anarchyMode extends AbstractAction {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 9199147910725275622L;

		public anarchyMode() {
			putValue(NAME, "Anarchy");
			putValue(SHORT_DESCRIPTION, "Chose the game mode Anarchy.");
		}
		
		public void actionPerformed(ActionEvent e) {
			democracy = false;
		}
		
	}
	
}
