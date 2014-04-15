package com;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.AbstractAction;
import javax.swing.Action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.jibble.pircbot.IrcException;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JList;

public class Main extends JFrame {

	private JPanel contentPane;
	public static IRC irc;
	private static JLabel label;
	private static JButton btnNewButton;
	private static GroupLayout gl_contentPane;
	private static JLabel lblStatus;
	private static Main frame = new Main();
	private final Action channelSettings = new ChannelSettings();
	private final Action startBot = new StartBot();
	
	private static String status;
	
	public static String getStatus() {
		return status;
	}
	
	public static void setStatus(String s) {
		status = s;
		label.setText(status);
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
	public Main() {
		setTitle("Twitch Plays Client by Miles Black");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 626);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmChannelSettings = new JMenuItem("Channel Settings");
		mntmChannelSettings.setAction(channelSettings);
		mnFile.add(mntmChannelSettings);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnNewButton = new JButton("Start");
		btnNewButton.setAction(startBot);
		lblStatus = new JLabel("Status:");
		
		label = new JLabel(status);
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addComponent(lblStatus)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addGap(52)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(239, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(457, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStatus)
						.addComponent(label))
					.addGap(25))
		);
		contentPane.setLayout(gl_contentPane);
		setStatus("Idle...");
	}
	
	private class ChannelSettings extends AbstractAction {
		
		public ChannelSettings() {
			putValue(NAME, "Channel Settings");
			putValue(SHORT_DESCRIPTION, "Adjust your twitch channel settings.");
		}
		
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			Settings s = new Settings();
		    s.setVisible(true);
		}
		
	}
	
	private class StartBot extends AbstractAction {
		
		public StartBot() {
			putValue(NAME, "Start");
			putValue(SHORT_DESCRIPTION, "Start the IRC Bot!");
		}
		
		public void actionPerformed(ActionEvent e) {
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
