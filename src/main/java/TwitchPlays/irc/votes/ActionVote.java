package TwitchPlays.irc.votes;

import java.util.HashMap;

public class ActionVote {

    private HashMap<String, String> votes;

    public ActionVote(){

        votes = new HashMap<String, String>();
    }

    public void setVote(String user, String vote){

        votes.put(user, vote);
    }

    public String getVote(String user){

        return votes.get(user);
    }

    public String getWinner(){

        HashMap<String, Integer> values = new HashMap<String, Integer>();

        //Count the frequency
        for(String value: this.votes.values()){

            values.put(value, (values.get(value) != null ? values.get(value) + 1 : 1));
        }

        //Get the first frequency
        if(values.size() == 0) return "";
        String winner = (String) values.keySet().toArray()[0];

        //Loop through all the frequencies to find the largest
        for(String value : values.keySet()){

            //Found a larger one
            if(values.get(value) > values.get(winner))

                return value;
        }
        //Return the frequency
        return winner;
    }
}
