package src.com;

import java.util.Scanner;

public class Main {

  public static void main(String args[]) {
    Scanner s = new Scanner(System.in);
    System.out.println("Would you like to create a channel set up? (Yes/No)");
    String creatingChannel = s.nextLine();
    if (creatingChannel.equalsIgnoreCase("Yes")) {
      System.out.println("We will now be creating a new channel.");
      System.out.println("Are you not using the Twitch servers? (Yes/No)");
      String notTwitch = s.nextLine();
      System.out.println("Please enter your twitch username:");
      String user = s.nextLine();
      System.out.println("Please enter your twitch channel:");
      String channel = s.nextLine();
      System.out.println("To find your oauth, please follow the link: http://twitchapps.com/tmi/");
      System.out.println("Please enter your oauth:");
      int oauth = Integer.parseInt(s.nextLine());
        if (notTwitch.equalsIgnoreCase("Yes")) {
          //new Channel(
        } else {
          new Channel(user, channel, oauth);
        }
    }
  }

}
