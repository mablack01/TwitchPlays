package com;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.jibble.pircbot.IrcException;

public class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 5051468767236928741L;
	
	/**
	 * Fields
	 */
	private Channel channel = Settings.getChannel();
	public static IRC irc;
	private JPanel mainPane;
	private static JButton startButton;
	private static JLabel currentStatus;
	private static JLabel statusLabel;
	public static JTextArea inputFeed = new JTextArea();
	private static GroupLayout gl_mainPane;
	private static Main frame = new Main();
	
	private static String status;
	private JScrollPane scrollPane;
	
	/**
	 * Gets the current status of the bot
	 * @return status of the bot
	 */
	public static String getStatus() {
		return status;
	}
	
	/**
	 * Sets the current status of the bot
	 * @param s The status that is being set
	 */
	public static void setStatus(String s) {
		status = s;
		currentStatus.setText(status);
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
	public Main() {
		setTitle("Twitch Plays Client by Miles Black");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 626);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem channelSettingsMenu = new JMenuItem("Channel Settings");
		fileMenu.add(channelSettingsMenu);
		channelSettingsMenu.addActionListener(this);
		channelSettingsMenu.setActionCommand("channelSettings");
		
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		
		startButton = new JButton("Start");
		startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		startButton.addActionListener(this);
		startButton.setActionCommand("startBot");
		
		statusLabel = new JLabel("Current Status:");
		statusLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		currentStatus = new JLabel(status);
		currentStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel chatFeedLabel = new JLabel("Chat Feed");
		chatFeedLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		inputFeed = new JTextArea();
		
		scrollPane = new JScrollPane(inputFeed);
		scrollPane.setViewportView(inputFeed);
		
		gl_mainPane = new GroupLayout(mainPane);
		gl_mainPane.setHorizontalGroup(
			gl_mainPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPane.createSequentialGroup()
					.addGroup(gl_mainPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mainPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(inputFeed, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_mainPane.createParallelGroup(Alignment.LEADING)
								.addComponent(currentStatus)
								.addComponent(statusLabel)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_mainPane.createSequentialGroup()
							.addGap(38)
							.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_mainPane.createSequentialGroup()
							.addGap(50)
							.addComponent(chatFeedLabel)))
					.addContainerGap(258, Short.MAX_VALUE))
		);
		gl_mainPane.setVerticalGroup(
			gl_mainPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPane.createSequentialGroup()
					.addGroup(gl_mainPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mainPane.createSequentialGroup()
							.addGap(8)
							.addComponent(chatFeedLabel)
							.addGroup(gl_mainPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_mainPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(inputFeed, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_mainPane.createSequentialGroup()
									.addGap(77)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_mainPane.createSequentialGroup()
							.addGap(60)
							.addComponent(statusLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(currentStatus)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(183, Short.MAX_VALUE))
		);
		mainPane.setLayout(gl_mainPane);
		setStatus("Idle...");
	}
	
	/**
	 * Handles the actions performed in the main frame.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("channelSettings")) {
			frame.setVisible(false);
			Settings s = new Settings();
		    s.setVisible(true);
		} else if (e.getActionCommand().equals("startBot")) {
			if (channel == null || channel.getChannel() == null || channel.getImage() == null || channel.getOAuth() == null
					|| channel.getPort() <= 0 || channel.getServer() == null || channel.getUser() == null) {
				JOptionPane.showMessageDialog(frame, "Please fill in all of your channel information!");
				return;
			}
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        irc = new IRC();
                        irc.start();
                    } catch (IrcException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
		}
	}
}
