/* Class: This class sets up and organizes your team based on statatistics and player choice. Each player is associated with a "risk" which returns
 * a random yardage based on their predicted stats. Each player has a different method in which they gain points or lose points from fumbles and interceptions.
 * These randomly generated values will determine the team's overall score based on results of the chosen players. The scores by the players and overall score
 * determine how well your fantasy team does against the computer generated teams.
 */

import java.util.Random;
public class FantasyTeam
{
    Random random = new Random();
    
    //Three private object variables for each player (Quarterback, Running Back, Wide Receiver) all set to null
    private Quarterback quarterback = null;
    private RunningBack runningBack = null;
    private WideReceiver wideReceiver = null;
    
    //Private int variables for passing td's, passing yards, interceptions, rushing yards, rushing touchdowns, fumbles, receiving yards, receptions, and receiving td's
    private int passingYards, passingTouchdowns, interceptions, rushingYards, rushingTouchdowns, fumbles, receivingYards, receivingTouchdowns, receptions;
    
    //Private int array for the team statistics
    private int[] statistics = new int[9];
    
    //Private String array for all of the player names
    private String[] names = new String[3];
    
    //Constructor that takes in an array of Player objects and then sets the first object in the array to a QB, second to RB, third to WR
    //Each Constructor will also initialize every setter method in the class
    public FantasyTeam(Player[] players)
    {
        quarterback = new Quarterback(players[0]);
        runningBack = new RunningBack(players[1]);
        wideReceiver = new WideReceiver(players[2]);
        setPassingYards();
        setPassingTouchdowns();
        setInterceptions();
        setRushingYards();
        setRushingTouchdowns();
        setFumbles();
        setReceivingYards();
        setReceivingTouchdowns();
        setReceptions();
    }
    //Copy Constructor
    public FantasyTeam(FantasyTeam otherTeam)
    {
        quarterback = new Quarterback(otherTeam.quarterback);
        runningBack = new RunningBack(otherTeam.runningBack);
        wideReceiver = new WideReceiver(otherTeam.wideReceiver);
        setPassingYards();
        setPassingTouchdowns();
        setInterceptions();
        setRushingYards();
        setRushingTouchdowns();
        setFumbles();
        setReceivingYards();
        setReceivingTouchdowns();
        setReceptions();
    }
    
    //Each method that sets statistics will do so based on the player's risk rating
    //'High' will generate a particular statistic within a wide statistical range, 'Low' will do so within a narrow range
    //Method that sets the passing yardage total based on the quarterback object's risk rating
    private void setPassingYards()
    {
        //For each setter method, initialize two int variables for high and low to zero
        int high = 0;
        int low = 0;
        
        //Create a 'risk' String variable with the getRisk() method for each player object
        String risk = quarterback.getRisk();
        
        //Use a switch statement in determining to the risk
        //If the risk is high, set the 'high' variable to the highest possible statistic accumulation for a season, and for the 'low' variable, the lowest possible
        //Medium and low risk should have a lower range
        switch(risk)
        {
            case "high":
                high = 5500;
                low = 2000;
                break;
            case "medium":
                high = 4750;
                low = 2750;
                break;
            case "low":
                high = 4250;
                low = 3750;
                break;
        }
        //Create a random number between the high and low numbers and set the corresponding int variable to it
        passingYards = random.nextInt(high - low) + low;
    }
    //Sets touchdowns based on on the quarterback's risk rating but also based on the passing yardage total
    //Every setter method for touchdowns and the one receptions will take into acccount how much yardage was generated for high risk players
    //If the yardage total was on the high end, so will the touchdowns.
    private void setPassingTouchdowns()
    {
        int high = 0;
        int low = 0;
        String risk = quarterback.getRisk();
        switch(risk)
        {
            case "high":
                if(passingYards >= 3750)
                {
                    high = 48;
                    low = 32;
                }
                else
                {
                    high = 31;
                    low = 15;
                }
                break;
            case "medium":
                if(passingYards >= 3750)
                {
                    high = 40;
                    low = 31;
                }
                else
                {
                    high = 30;
                    low = 20;
                }
                break;
            case "low":
                high = 32;
                low = 27;
                break;
        }
        passingTouchdowns = random.nextInt(high - low) + low;
    }
    //Sets interceptions based on quarterback's risk. Interceptions apply a negative value to your score.
    private void setInterceptions()
    {
        int high = 0;
        int low = 0;
        String risk = quarterback.getRisk();
        switch(risk)
        {
            case "high":
                high = 30;
                low = 5;
                break;
            case "medium":
                high = 23;
                low = 10;
                break;
            case "low":
                high = 17;
                low = 13;
                break;
        }
        interceptions = random.nextInt(high - low) + low;
    }
    //Sets rushing yards based on running back's risk
    private void setRushingYards()
    {
        int high = 0;
        int low = 0;
        String risk = runningBack.getRisk();
        switch(risk)
        {
            case "high":
                high = 2000;
                low = 500;
                break;
            case "medium":
                high = 1600;
                low = 800;
                break;
            case "low":
                high = 1200;
                low = 1000;
                break;
        }
        rushingYards = random.nextInt(high - low) + low;
    }
    //Sets rushing touchdowns based on rushing yards
    private void setRushingTouchdowns()
    {
        int high = 0;
        int low = 0;
        String risk = runningBack.getRisk();
        switch(risk)
        {
            case "high":
                if(rushingYards >= 1250)
                {
                    high = 18;
                    low = 11;
                }
                else
                {
                    high = 10;
                    low = 2;
                }
                break;
            case "medium":
                high = 13;
                low = 5;
                break;
            case "low":
                high = 9;
                low = 7;
                break;
        }
        rushingTouchdowns = random.nextInt(high - low) + low;
    }
    //Fumble Setter method
    //Fumbles are a negative value applied to running backs
    private void setFumbles()
    {
        int high = 0;
        int low = 0;
        String risk = runningBack.getRisk();
        switch(risk)
        {
            case "high":
                high = 12;
                low = 0;
                break;
            case "medium":
                high = 8;
                low = 3;
                break;
            case "low":
                high = 6;
                low = 4;
                break;
        }
        fumbles = random.nextInt(high - low) + low;
    }
    
    //Sets total yards for wide recievers
    private void setReceivingYards()
    {
        String risk = wideReceiver.getRisk();
        int high = 0;
        int low = 0;
        switch(risk)
        {
            case "high":
                high = 1900;
                low = 500;
                break;
            case "medium":
                high = 1500;
                low = 800;
                break;
            case "low":
                high = 1200;
                low = 1000;
                break;
        }
        receivingYards = random.nextInt(high - low) + low;
    }
    
    //Set Receiving Touchdowns
    private void setReceivingTouchdowns()
    {
        String risk = wideReceiver.getRisk();
        int high = 0;
        int low = 0;
        switch(risk)
        {
            case "high":
                if(receivingYards >= 1200)
                {
                    high = 18;
                    low = 11;
                }
                else
                {
                    high = 10;
                    low = 3;
                }
                break;
            case "medium":
                high = 13;
                low = 5;
                break;
            case "low":
                high = 9;
                low = 7;
                break;
        }
        receivingTouchdowns = random.nextInt(high - low) + low;
    }
    //Sets reception total
    private void setReceptions()
    {
        String risk = wideReceiver.getRisk();
        int high = 0;
        int low = 0;
        switch(risk)
        {
            case "high":
                if(receivingYards >= 1200)
                {
                    high = 140;
                    low = 90;
                }
                else
                {
                    high = 90;
                    low = 40;
                }
                break;
            case "medium":
                high = 100;
                low = 60;
                break;
            case "low":
                high = 85;
                low = 75;
                break;
        }
        receptions = random.nextInt(high - low) + low;
    }
    
    //Method that returns every statistics as an int array
    public int[] teamStatistics()
    {
        statistics[0] = passingYards;
        statistics[1] = passingTouchdowns;
        statistics[2] = interceptions;
        statistics[3] = rushingYards;
        statistics[4] = rushingTouchdowns;
        statistics[5] = fumbles;
        statistics[6] = receptions;
        statistics[7] = receivingYards;
        statistics[8] = receivingTouchdowns;
        return statistics;
    }
    //Method that returns player names as a String array
    public String[] playerNames()
    {
        names[0] = quarterback.getName();
        names[1] = runningBack.getName();
        names[2] = wideReceiver.getName();
        return names;
    }
    
}
