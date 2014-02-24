package TwitchPlays;

import TwitchPlays.irc.IrcClient;
import TwitchPlays.irc.gui.MainLayout;
import org.ini4j.Wini;
import org.jibble.pircbot.IrcException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;

public class Main {

    public static Wini config;
    public static IrcClient ircClient;
    public static MainLayout mainLayout;
    public static DefaultTableModel chatTableModule;

    public static void main(String[] args) throws IOException {

        File f = new File("config.ini");
        //No config :(
        if(!f.exists())
            f.createNewFile();

        config = new Wini(f);

        //Generate default config
        if(config.size() == 0){

            config.put("connection", "server", "irc.twitch.tv");
            config.put("connection", "port", 6667);
            config.put("connection", "user", "");
            config.putComment("connection", "You can generate a oauth here: http://twitchapps.com/tmi/ Make sure that it starts with oauth(oauth:[OAUTH])");
            config.put("connection", "oauth", "");
            config.put("connection", "channel", "");

            config.put("keys", "up", "VK_UP");
            config.put("keys", "down", "VK_DOWN");
            config.put("keys", "left", "VK_LEFT");
            config.put("keys", "right", "VK_RIGHT");
            config.put("keys", "select", "VK_X");
            config.put("keys", "start", "VK_Y");
            config.put("keys", "a", "VK_A");
            config.put("keys", "b", "VK_B");

            config.putComment("mode", "Actiontime is the time it takes before the program decides what to do while in democracy mode, 2da is the percentage of votes it most get to switch to anarchy, a2d is the opposite. Multiple defines wether players votes count more than once.");
            config.put("mode", "mode", "democracy");
            config.put("mode", "multiple", true);
            config.put("mode", "vote", true);
            config.put("mode", "actiontime", 20000);
            config.put("mode", "d2a", 50);
            config.put("mode", "a2d", 80);

            config.store();
        }

        JFrame frame = new JFrame("MainLayout");
        mainLayout = new MainLayout();
        frame.setContentPane(mainLayout.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        chatTableModule = new DefaultTableModel();
        chatTableModule.addColumn("Name");
        chatTableModule.addColumn("Action");

        mainLayout.chatTable.setModel(chatTableModule);

        if(config.get("mode", "mode").equals("democracy"))
            mainLayout.modeAnarchyLabel.setEnabled(false);
        else
            mainLayout.modeDemocracyLabel.setEnabled(false);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {

                        ircClient = new IrcClient();
                        ircClient.start();
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