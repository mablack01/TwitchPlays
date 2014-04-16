package com;

import java.awt.EventQueue;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.jibble.pircbot.IrcException;

public class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 5051468767236928741L;
	
	/**
	 * Fields
	 */
	public static IRC irc;
	private JPanel contentPane;
	private static JButton startButton;
	private static JLabel currentStatus;
	private static JLabel statusLabel;
	private static GroupLayout gl_contentPane;
	private static Main frame = new Main();
	private Channel channel = Settings.getChannel();
	
	private static String status;
	
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
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		startButton = new JButton("Start");
		startButton.addActionListener(this);
		startButton.setActionCommand("startBot");
		
		statusLabel = new JLabel("Status:");
		currentStatus = new JLabel(status);
		
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addComponent(statusLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(currentStatus)
					.addGap(52)
					.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(239, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(457, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addComponent(statusLabel)
						.addComponent(currentStatus))
					.addGap(25))
		);
		contentPane.setLayout(gl_contentPane);
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
