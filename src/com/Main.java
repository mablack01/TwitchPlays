package com;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
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
import javax.swing.border.EmptyBorder;

import org.jibble.pircbot.IrcException;

import javax.swing.JList;

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
	@SuppressWarnings("rawtypes")
	public static JList inputList;
	@SuppressWarnings("rawtypes")
	public static DefaultListModel listModel;
	public static JScrollPane messageScrollList;
	public static JTextArea inputFeed = new JTextArea();
	private static Main frame = new Main();
	
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Main() {
		setTitle("Twitch Plays Client by Miles Black");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 349);
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
		startButton.setBounds(187, 217, 162, 55);
		startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		startButton.addActionListener(this);
		startButton.setActionCommand("startBot");
		
		statusLabel = new JLabel("Current Status:");
		statusLabel.setBounds(182, 28, 126, 28);
		statusLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		currentStatus = new JLabel(status);
		currentStatus.setBounds(182, 70, 299, 31);
		currentStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel chatFeedLabel = new JLabel("Chat Feed");
		chatFeedLabel.setBounds(27, 23, 126, 31);
		chatFeedLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		listModel = new DefaultListModel();
		
		inputList = new JList(listModel);
		inputList.setBounds(10, 62, 162, 210);
		mainPane.add(inputList);
		
		JScrollPane feedScroller = new JScrollPane(inputList);
		feedScroller.setOpaque(false);
		feedScroller.setBounds(10, 62, 162, 210);
		feedScroller.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    }); 
		
	    mainPane.setLayout(null);
		mainPane.add(currentStatus);
		mainPane.add(statusLabel);
		mainPane.add(startButton);
		mainPane.add(chatFeedLabel);
		mainPane.add(inputList);
		mainPane.add(feedScroller);
		
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
