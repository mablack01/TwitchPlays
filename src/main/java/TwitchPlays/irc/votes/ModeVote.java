package TwitchPlays.irc.votes;

import TwitchPlays.Main;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;


public class ModeVote {

    private HashMap<String, String> hashVotes;
    private Integer[] arrrayVotes;

    public ModeVote(){

        hashVotes = new HashMap<String, String>();
        arrrayVotes = new Integer[]{0, 0};
    }

    public void setVote(String user, String vote){
        //One votes per user
        if(!Main.config.get("mode", "multiple", Boolean.class)){

            hashVotes.put(user, vote);
        //Multiple votes per user
        }else{

            //Add a vote for the team
            if(vote.equals("democracy")) arrrayVotes[0]++;
            else arrrayVotes[1]++;
        }
        //Get a winner
        String winner = getWinner();
        //Check different mode


        if(!winner.equals(Main.config.get("mode", "mode")) && winner != ""){

            Main.config.put("mode", "mode", winner);

            //Democracy won, set the labels
            if(winner.equals("democracy")){

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        Main.mainLayout.modeDemocracyLabel.setEnabled(true);
                        Main.mainLayout.modeAnarchyLabel.setEnabled(false);
                    }
                });
            //Anarchy won, set the labels
            }else{
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {

                        Main.mainLayout.modeDemocracyLabel.setEnabled(false);
                        Main.mainLayout.modeAnarchyLabel.setEnabled(true);
                    }
                });
            }

            try {
                Main.config.store();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getWinner(){

        //One vote per user
        if(!Main.config.get("mode", "multiple", Boolean.class)){
            final HashMap<String, Integer> values = new HashMap<String, Integer>();

            //Count the frequency
            for(String value: this.hashVotes.values()){

                values.put(value, (values.get(value) != null ? values.get(value) + 1 : 1));
            }

            final Double democracyPercent = values.get("democracy").doubleValue() / ((Integer)this.hashVotes.values().size()).doubleValue() * 100;
            final Double anarchyPercent = values.get("anarchy").doubleValue() / ((Integer)this.hashVotes.values().size()).doubleValue() * 100;

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {

                    System.out.println("WW");
                    Main.mainLayout.voteLabel.setText("Anarchy: " + values.get("anarchy") + ", Democracy: " + values.get("democracy"));
                    Main.mainLayout.modeProgressBar.setValue(democracyPercent.intValue());
                }
            });

            if(Main.config.get("mode", "mode").equals("democracy")){

                //Check if more then the given percentage in the config is for anarchy
                if(anarchyPercent >= Main.config.get("mode", "d2a", Integer.class)){

                    //Return anarchy
                    return "anarchy";
                }
            }else if(Main.config.get("mode", "mode").equals("anarchy")){
                //Check if more then the given percentage in the config is for democracy
                if(democracyPercent >= Main.config.get("mode", "a2d", Integer.class)){

                    //Return democracy
                    return "democracy";
                }
            }

        //You can have multiple votes
        }else{
            //Calculate the percentages
            final Double democracyPercent = Double.valueOf(arrrayVotes[0]) / (double) (arrrayVotes[0] + arrrayVotes[1]) * 100;
            final Double anarchyPercent = Double.valueOf(arrrayVotes[1]) / (double) (arrrayVotes[0] + arrrayVotes[1]) * 100;

            //Set the progressbar
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {

                    Main.mainLayout.voteLabel.setText("Anarchy: " + arrrayVotes[1] + ", Democracy: " + arrrayVotes[0]);
                    Main.mainLayout.modeProgressBar.setValue(democracyPercent.intValue());
                }
            });

            //The mode is democracy
            if(Main.config.get("mode", "mode").equals("democracy")){

                //Check if more then the given percentage in the config is for anarchy
                if(anarchyPercent >= Main.config.get("mode", "d2a", Integer.class)){

                    //Return anarchy
                    return "anarchy";
                }
            //The mode is anarchy
            }else if(Main.config.get("mode", "mode").equals("anarchy")){
                //Check if more then the given percentage in the config is for democracy
                if(democracyPercent >= Main.config.get("mode", "a2d", Integer.class)){

                    //Return democracy
                    return "democracy";
                }
            }
        }
        //No winner
        return "";
    }
}
