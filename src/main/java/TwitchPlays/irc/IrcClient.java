package TwitchPlays.irc;

import TwitchPlays.Main;
import TwitchPlays.irc.votes.ActionVote;
import TwitchPlays.irc.votes.ModeVote;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class IrcClient extends PircBot{

    private Robot robot;
    private ActionVote actionVote;
    private ModeVote modeVote;

    public IrcClient(){

        this.setName(Main.config.get("connection", "user"));
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IrcException, IOException {

        Main.mainLayout.statusLabel.setText("Connecting");
        this.connect(
                Main.config.get("connection", "server"),
                Main.config.get("connection", "port", int.class),
                Main.config.get("connection", "oauth")
        );

        Main.mainLayout.statusLabel.setText("Joining channel");
        this.joinChannel("#" + Main.config.get("connection", "channel"));
        Main.mainLayout.statusLabel.setText("Connected");

        this.actionVote = new ActionVote();
        this.modeVote = new ModeVote();

        //Action vote
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if(Main.config.get("mode", "mode").equals("democracy")){

                    if(actionVote.getWinner() != "")
                        Main.ircClient.pressKey(actionVote.getWinner());

                    actionVote = new ActionVote();
                }
            }
        }, 0, Main.config.get("mode", "actiontime", Integer.class));
    }

    public void onDisconnect(){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Main.mainLayout.statusLabel.setText("Disconnected");
            }
        });
    }

    public void onMessage(String channel, final String sender, String login, String hostname, final String message){

        //A vote for democracy or anarchy
        if(message.toLowerCase().equals("anarchy") || message.toLowerCase().equals("democracy") && Main.config.get("mode", "vote", Boolean.class)){

            this.getModeVote().setVote(sender, message.toLowerCase());

            //Add it to the table
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {

                    Main.chatTableModule.insertRow(0, new Object[]{sender, message.toLowerCase()});
                    //If it doesn't fit: remove it
                    if(Main.chatTableModule.getRowCount() * Main.mainLayout.chatTable.getRowHeight() > Main.mainLayout.tablePanel.getHeight())

                        Main.chatTableModule.removeRow(Main.chatTableModule.getRowCount() - 1);

                }
            });
        }

        //It's a key
        else if(Main.config.get("keys").containsKey(message.toLowerCase())){

            //Add it to the table
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {

                    Main.chatTableModule.insertRow(0, new Object[]{sender, message.toLowerCase()});
                    //If it doesn't fit: remove it
                    if(Main.chatTableModule.getRowCount() * Main.mainLayout.chatTable.getRowHeight() > Main.mainLayout.tablePanel.getHeight())

                        Main.chatTableModule.removeRow(Main.chatTableModule.getRowCount() - 1);

                }
            });

            //Democracy mode
            if(Main.config.get("mode", "mode").equals("democracy"))

                this.getActionVote().setVote(sender, Main.config.get("keys", message.toLowerCase()));

            //Anarchy mode
            else if(Main.config.get("mode", "mode").equals("anarchy")){

                this.pressKey(Main.config.get("keys", message.toLowerCase()));
            }
        }
    }

    public Robot getRobot(){

        return this.robot;
    }

    public ModeVote getModeVote() {
        return modeVote;
    }

    public void setModeVote(ModeVote modeVote) {
        this.modeVote = modeVote;
    }

    public ActionVote getActionVote() {
        return actionVote;
    }

    public void setActionVote(ActionVote actionVote) {
        this.actionVote = actionVote;
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
