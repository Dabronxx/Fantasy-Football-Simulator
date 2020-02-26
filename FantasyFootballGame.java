/* FinalProject: FantasyFootballGame.java
 * Author: Branko Andrews, Shawn Ibanez
 * Due December 12, 2016
 * Project Statement: You're playing fantasy football against the three computer generated teams. Pick one quarterback, one runningback, and one widereceiver
 * to earn points based on yards and touchdowns.The user will pick players based on "risk" which is their probability to score. The draft order determines
 * each team's number and order of the draft is based on a snake draft. (First pick of round 1 becomes last pick in the next round).
 * Fumbles and interceptions will negatively effect your overall score and are based on the player's "risk". Each team's score will consist of total yards earned
 * through rushing yards, passing yards, and touchdowns. The player with the highest overall score wins.
 * Algorithm:
 * 1. State your constants: Points based on Touchdowns and yards
 * 2. For each position group, have at least one high risk, 2 medium risk, and 1 low risk player
 * 3. Create an object array for each position group
 * 4. Also initialize an int array set to null for each position group that will indicate which players have already been picked
 * 5. Create a two dimensional Player object array with 4 rows(teams), 3 columns(players) that will hold every team and their players
 * 6. Create introductory dialogue to introduce how the game works
 * 7. Start the draft within a while loop that will continue as long as a string 'option' variable is not 'exit'
 * 8. Set each int array for each position group to a new int array of a length of four
 * 9. Generate the user draft position with a random number from 1 to 4
 * 10. First round will consist of picking the quarterback objects created above
 * 11. Print out information for every QB object with a number 1-4 next to it.
 * 12. To start the picking process, loop through the 2 dimensional array created above for every team(row), but only through the first column for the first round, second column for the second round, etc.
 * 13. If it is not the user's turn to pick a player, have the computer team pick a player randomly
 * 14. Everytime a player is picked, its correspnding spot in the int array for player's already picked will be incremented
 * 15. After a player is picked, check and make sure it has not already been picked by checking that the corresponding space in int array is equal to zero or not
 * 16. Set the spot in the 2 dimensional array equal to a copy of the player object selected
 * 17. Do the same for Running Backs ins the second round in reverse order, and wide receivers in the third round in the same orginal order
 * 18. Print out the players the user drafted
 * 19. Create a fantasy team object array for each individual team created
 * 20. Create a team statistics objects array
 * 21. Use the statistics from each fantasy team object into a team statistics object
 * 22. When getting the total score for each team, use the the seasonScore method below
 * 23. Put all of the season scores for each team into a double array
 * 24. Use selection sort and the reverse method to sort the array of scores
 * 25. Determine what place the user finished by using the method that returns the team number from the double array of scores
 * 26. Display the team number and total points from the season, print standings with printf from highest to lowest
 * 27. Prints individual player statistics with the team statistics method from the Team Statistics class
 * 28. Allow the user to either continue playing or quitting by setting the option string variable to exit
 * 29. Create a method that takes in a Fantasy Team object, takes in every statistic, multiplies it by the corresponding constants for points per statistic, and then adds them all up for a total score
 * 30. Create a Selection Sort Method (see comments)
 * 31. Method that takes in a double array and reverses it
 */

import java.util.Random;
import java.util.Scanner;
public class FantasyFootballGame
{
    //State your constants: Points based on Touchdowns and yards*
    //Pass TD = 4 points, Pass Yd = .04 points, INT = -2 points, RB or WR TD = 6 points, WR or RB yard = .1 points, fumbles = -2 points
    public static final int POINTS_PER_PASS_TD = 4;
    public static final double POINTS_PER_PASS_YARD = .04;
    public static final int POINTS_PER_INT = -2;
    public static final int POINTS_PER_RB_OR_WR_TD = 6;
    public static final double POINTS_PER_RB_OR_WR_YARD = .1;
    public static final int POINTS_PER_FUMBLE = -2;
    
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        
        Random random = new Random();
        
        //For each position group, have at least one high risk, 2 medium risk, and 1 low risk player
        //Create an object array for each position group
        //Also initialize an int array set to null for each position group that will indicate which players have already been picked
        //Four Quarterback objects
        Quarterback camNewton = new Quarterback("Cam Newton", "high");
        Quarterback drewBrees = new Quarterback("Drew Brees", "medium");
        Quarterback philipRivers = new Quarterback("Philip Rivers", "medium");
        Quarterback dakPrescott = new Quarterback("Dak Prescott", "low");
        Quarterback[] quarterbacks = {camNewton, drewBrees, philipRivers, dakPrescott};
        int[] quarterbacksPicked = null;
        
        //Four Quarterback objects
        RunningBack adrianPeterson = new RunningBack("Adrian Peterson", "high");
        RunningBack leveonBell = new RunningBack("Leveon Bell", "medium");
        RunningBack davidJohnson = new RunningBack("David Johnson", "medium");
        RunningBack ezekielElliott = new RunningBack("Ezekiel Elliot", "low");
        RunningBack[] runningBacks = {adrianPeterson, leveonBell, davidJohnson, ezekielElliott};
        int[] runningBacksPicked = null;
        
        //Four WideReceiver objects
        WideReceiver oBJ = new WideReceiver("Odell Beckam Jr.", "high");
        WideReceiver antonioBrown = new WideReceiver("Antonio Brown", "medium");
        WideReceiver julioJones = new WideReceiver("Julio Jones", "medium");
        WideReceiver larryFitz = new WideReceiver("Larry Fitzgerald", "low");
        WideReceiver[] wideReceivers = {oBJ, antonioBrown, julioJones, larryFitz};
        int[] receiversPicked = null;
        
        //Two Dimensional Player object array with 4 rows(teams), 3 columns(players) that will hold every team and their players
        Player[][] teams = new Player[4][3];
        
        //Dialogue to introduce game and how to play.
        System.out.println("Welcome to Java Fantasy Football!");
        System.out.println();
        System.out.println("You will pick a 3 player fantasy team and compete against three other computer teams.");
        System.out.println();
        System.out.println("Each team will have 1 Quarterback, 1 Running Back, and 1 Wide Receiver.(Press enter key to continue dialouge)");
        keyboard.nextLine();
        System.out.println();
        System.out.println("When choosing a particular player, it is important to consider the player's 'risk' rating.");
        System.out.println();
        System.out.println("Players with a 'high' risk rating have the highest and lowest potential output");
        System.out.println("Players with a 'medium' and 'low' risk rating are respectively safer options, though they do not have as high a potential output.");
        System.out.println();
        keyboard.nextLine();
        System.out.println("Quarterbacks will accumulate passing yards, touchdowns, and interceptions.");
        System.out.println("You will get .04 points per passing yard, 4 points per passing touchdown, and -2 points for every interception thrown.");
        keyboard.nextLine();
        System.out.println();
        System.out.println("Running Backs will accumulate rushing yards, touchdowns, and fumbles.");
        System.out.println("You will get .1 points per rushing yard, 6 points per rushing touchdown, and -2 points for every time the back fumbles.");
        keyboard.nextLine();
        System.out.println();
        System.out.println("Wide Receivers will accumulate receiving yards, touchdowns, and receptions.");
        System.out.println("You will get .1 points per passing yard, 6 points per receiving touchdown, and 1 point per reception.");
        keyboard.nextLine();
        System.out.println();
        System.out.println("You will pick your team in a 3 round snake draft where the draft order will reverse after every round.");
        System.out.println();
        System.out.println("Quarterbacks will go in the first round, running backs in the second, and then wide receivers in the third.");
        System.out.println();
        System.out.println("Are you ready?!?!");
        keyboard.nextLine();
        System.out.println();
        
        //String variable for what the user wants to do set to an empty array
        String option = "";
        
        //Draftting players while loop that will continue as long as the option variable is not equal to 'exit'
        while(option.compareTo("exit") != 0)
        {
            //Set each int array for each position group to a new int array of a length of four*
            quarterbacksPicked = new int[4];
            runningBacksPicked = new int[4];
            receiversPicked = new int[4];
            
            //Generate the user draft position with a random number from 1 to 4*
            int draftPosition = random.nextInt(4) + 1;
            
            System.out.println("You will be picking number " + draftPosition + " in the first round as team number " + draftPosition + ".");
            keyboard.nextLine();
            System.out.println();
            
            //First round will consist of picking the quarterback objects created above*
            //Print out information for every QB object with a number 1-4 next to it.*
            System.out.println("Here are the list of Quarterbacks to pick in the first round.");
            System.out.println();
            for(int i = 0; i < quarterbacks.length; i++)
            {
                int index = i + 1;
                System.out.println(index + ". " + quarterbacks[i].toString());
            }
            System.out.println();
            System.out.println("When picking a player, enter the number it corresponds to on the list.");
            keyboard.nextLine();
            //To start the picking process, loop through the 2 dimensional array created above for every team(row), but only through the first column for the first round, second column for the second round, etc.*
            for(int row = 0; row < teams.length; row++)
            {
                for(int column = 0; column < 1; column++)
                {
                    //If it is not the user's turn to pick a player, have the computer team pick a player randomly*
                    //Everytime a player is picked, its correspnding spot in the int array for player's already picked will be incremented*
                    //After a player is picked, check and make sure it has not already been picked by checking that the corresponding space in int array is equal to zero or not*
                    if(draftPosition != (row + 1))
                    {
                        int pick = random.nextInt(4);
                        while(quarterbacksPicked[pick] != 0)
                        {
                            pick = random.nextInt(4);
                        }
                        int teamNumber = row + 1;
                        //Set the spot in the 2 dimensional array equal to a copy of the player object selected*
                        teams[row][column] = new Quarterback(quarterbacks[pick]);
                        quarterbacksPicked[pick]++;
                        //Be sure to print out the player selected
                        System.out.println("With pick #" + teamNumber + " in the first round, team number " + teamNumber + " picks " + quarterbacks[pick].getName());
                        keyboard.nextLine();
                    }
                    else
                    {
                        System.out.print("You are now on the clock. Please make a selection: ");
                        int pick = keyboard.nextInt();
                        while(quarterbacksPicked[pick - 1] != 0)
                        {
                            System.out.print("Player has already been picked. Choose again: ");
                            pick = keyboard.nextInt();
                        }
                        System.out.println("You have selected " + quarterbacks[pick - 1].getName());
                        teams[row][column] = new Quarterback(quarterbacks[pick - 1]);
                        quarterbacksPicked[pick - 1]++;
                        keyboard.nextLine();
                    }
                }
            }
            //Do the same for Running Backs ins the second round in reverse order, and wide receivers in the third round in the same orginal order*
            System.out.println("Round 1 is now over.");
            keyboard.nextLine();
            System.out.println("Here are the list of Running Backs to pick in the second round.");
            System.out.println();
            for(int i = 0; i < runningBacks.length; i++)
            {
                int index = i + 1;
                System.out.println(index + ". " + runningBacks[i].toString());
            }
            System.out.println();
            System.out.println("Draft order will now be reversed.");
            keyboard.nextLine();
            for(int row = teams.length - 1; row >= 0; row--)
            {
                for(int column = 1; column < 2; column++)
                {
                    if(draftPosition != (row + 1))
                    {
                        int pick = random.nextInt(4);
                        while(runningBacksPicked[pick] != 0)
                        {
                            pick = random.nextInt(4);
                        }
                        int teamNumber = row + 1;
                        int pickNumber = 4 - row;
                        teams[row][column] = new RunningBack(runningBacks[pick]);
                        runningBacksPicked[pick]++;
                        System.out.println("With pick #" + pickNumber + " in the second round, team number " + teamNumber + " picks " + runningBacks[pick].getName());
                        keyboard.nextLine();
                    }
                    else
                    {
                        System.out.print("You are now on the clock. Please make a selection: ");
                        int pick = keyboard.nextInt();
                        while(runningBacksPicked[pick - 1] != 0)
                        {
                            System.out.print("Player has already been picked. Choose again: ");
                            pick = keyboard.nextInt();
                        }
                        System.out.println("You have selected " + runningBacks[pick - 1].getName());
                        teams[row][column] = new RunningBack(runningBacks[pick - 1]);
                        runningBacksPicked[pick - 1]++;
                        keyboard.nextLine();
                    }
                }
            }
            System.out.println("Round 2 is now over.");
            keyboard.nextLine();
            System.out.println("Here are the list of Wide Receivers to pick in the third round.");
            System.out.println();
            for(int i = 0; i < wideReceivers.length; i++)
            {
                int index = i + 1;
                System.out.println(index + ". " + wideReceivers[i].toString());
            }
            System.out.println();
            System.out.println("Draft order will row return to the orginal order.");
            keyboard.nextLine();
            for(int row = 0; row < teams.length; row++)
            {
                for(int column = 2; column < 3; column++)
                {
                    if(draftPosition != (row + 1))
                    {
                        int pick = random.nextInt(4);
                        while(receiversPicked[pick] != 0)
                        {
                            pick = random.nextInt(4);
                        }
                        int teamNumber = row + 1;
                        teams[row][column] = new WideReceiver(wideReceivers[pick]);
                        receiversPicked[pick]++;
                        System.out.println("With pick #" + teamNumber + " in the third round, team number " + teamNumber + " picks " + wideReceivers[pick].getName());
                        keyboard.nextLine();
                    }
                    else
                    {
                        System.out.print("You are now on the clock. Please make a selection: ");
                        int pick = keyboard.nextInt();
                        while(receiversPicked[pick - 1] != 0)
                        {
                            System.out.print("Player has already been picked. Choose again: ");
                            pick = keyboard.nextInt();
                        }
                        System.out.println("You have selected " + wideReceivers[pick - 1].getName());
                        teams[row][column] = new WideReceiver(wideReceivers[pick - 1]);
                        receiversPicked[pick - 1]++;
                        keyboard.nextLine();
                    }
                }
            }
            //Print out the players the user drafted*
            System.out.println("The draft is now over. You selected: ");
            for(int i = 0; i < teams[draftPosition - 1].length; i++)
            {
                System.out.println(teams[draftPosition - 1][i].getPosition() + " " + teams[draftPosition - 1][i].getName());
            }
            System.out.println("Press enter to simulate the season.");
            keyboard.nextLine();
            
            //Create a fantasy team object array for each individual team created*
            FantasyTeam[] fantasyTeams = new FantasyTeam[4];
            
            for(int i = 0; i < teams.length; i++)
            {
                fantasyTeams[i] = new FantasyTeam(teams[i]);
            }
            //Create a team statistics objects array
            //Use the statistics from each fantasy team object into a team statistics object*
            //When getting the total score for each team, use the the seasonScore method below*
            TeamStatistics[] teamInformation = new TeamStatistics[4];
            
            for(int i = 0; i < fantasyTeams.length; i++)
            {
                int teamNumber = i + 1;
                teamInformation[i] = new TeamStatistics(seasonScore(fantasyTeams[i]), fantasyTeams[i].playerNames(), fantasyTeams[i].teamStatistics(), teamNumber);
            }
            
            //Put all of the season scores for each team into a double array*
            double[] teamScores = new double[4];
            
            for(int i = 0; i < teamScores.length; i++)
            {
                teamScores[i] = teamInformation[i].getScore();
            }
            //Use selection sort and the reverse method to sort the array of scores*
            sort(teamScores);
            reverse(teamScores);
            
            //Determine what place the user finished by using the method that returns the team number from the double array of scores*
            int standingsSpot = 0;
            for(int i = 0; i < teamInformation.length; i++)
            {
                if(getNumberTeam(teamInformation, teamScores[i]) == draftPosition)
                {
                    standingsSpot = i + 1;
                }
            }
            if(standingsSpot == 1)
            {
                System.out.println("Congratulations!! You finished 1st place!");
            }
            else
            {
                System.out.println("You finished in place number " + standingsSpot + " out of the four teams.");
            }
            keyboard.nextLine();
            
            //Print standings with printf from highest to lowest
            //Display the team number and total points from the season*
            System.out.println("Standings");
            System.out.printf("--------------------------------\n");
            System.out.printf("%-8s%10s%15s\n","Finish","Team", "Total Points");
            System.out.printf("--------------------------------\n");
            for(int i = 0; i < teamScores.length; i++)
            {
                int spot = i + 1;
                if(getNumberTeam(teamInformation, teamScores[i]) == draftPosition)
                {
                    System.out.printf("%-8d%10s%13.2f\n", spot, "Your Team", teamScores[i]);
                }
                else
                {
                    System.out.printf("%-8d%10d%13.2f\n", spot, getNumberTeam(teamInformation, teamScores[i]), teamScores[i]);
                }
            }
            keyboard.nextLine();
            
            //Prints individual player statistics with the team statistics method from the Team Statistics class*
            System.out.println("Here are the statistics for each player on each team.");
            for(int i = 0; i < teamInformation.length; i++)
            {
                if(teamInformation[i].getTeamNumber() == draftPosition)
                {
                    System.out.println("Your Team.");
                }
                else
                {
                    System.out.println("Team " + teamInformation[i].getTeamNumber());
                }
                teamInformation[i].teamStatistics();
                System.out.println();
            }
            //Allow the user to either continue playing or quitting by setting the option string variable to exit*.
            System.out.println("Press enter to play another season, or type 'exit' to stop playing.");
            option = keyboard.nextLine();
        }
        
    }
    
    //Method that takes in a Fantasy Team object, takes in every statistic, multiplies it by the corresponding constants for points per statistic, and then adds them all up for a total score and then returns it
    public static double seasonScore(FantasyTeam team)
    {
        int[] statistics = new int[team.teamStatistics().length];
        for(int i = 0; i < team.teamStatistics().length; i++)
        {
            statistics[i] = team.teamStatistics()[i];
        }
        double totalScore = 0;
        totalScore += statistics[0] * POINTS_PER_PASS_YARD;
        totalScore += statistics[1] * POINTS_PER_PASS_TD;
        totalScore += statistics[2] * POINTS_PER_INT;
        totalScore += (statistics[3] + statistics[7]) * POINTS_PER_RB_OR_WR_YARD;
        totalScore += (statistics[4] + statistics[8]) * POINTS_PER_RB_OR_WR_TD;
        totalScore += statistics[5] * POINTS_PER_FUMBLE;
        totalScore += statistics[6];
        return totalScore;
    }
    //Method that takes in an object array of the class Team Statistics, a double variable, and then determines and returns a team number that has that particular score
    public static int getNumberTeam(TeamStatistics[] teams, double score)
    {
        int teamNumber = 0;
        for(int i = 0; i < teams.length; i++)
        {
            if(score == teams[i].getScore())
            {
                teamNumber = teams[i].getTeamNumber();
            }
        }
        return teamNumber;
    }
    //Selection Sort Method
    public static void sort(double[] unsortedArray)
    {
        //Create an if statement where if the array argument is zero, it will return nothing
        if(unsortedArray.length == 0)
        {
            return;
        }
        //Create a temporary double array the length of the array argument
        double[] temp = new double[unsortedArray.length];
        
        //Initialize the first element in the temp array is equal to the first element in the array argument
        temp[0] = unsortedArray[0];
        
        //Create a for loop that cycles through every element in the array argument
        for(int index = 0; index < unsortedArray.length; index++)
        {
            //Within the for statement, create an int variable of 'insertHere' equal to zero
            int insertHere = 0;
            
            //Create a while statement that will increment the insert here variable as long as it less than the index variable in the for statement, and if the element at the 'insertHere' position is greater than the element at the current index in the array argument
            while((insertHere < index) && temp[insertHere] < unsortedArray[index])
            {
                insertHere++;
            }
            //After the while statement, create a for statement that starts at a position just below the index, ends at the insertHere variable, and decrements after every loop
            for(int i = index - 1; i >= insertHere; i--)
            {
                //Each loop of the statement should initialize the the next position in the temp array to be equal to the previous position
                temp[i + 1] = temp[i];
            }
            //After the previous for loop, set the temp array at position insertHere to be equal to array argument at the current index
            temp[insertHere] = unsortedArray[index];
        }
        //After the main for loop, create a for loop that will set each element in the array argument equal to the corresponding element in the temp array.
        for(int index = 0; index < unsortedArray.length; index++)
        {
            unsortedArray[index] = temp[index];
        }
        
    }
    //Method that takes in a double array and reverses it
    public static void reverse(double[] array)
    {
        double[] temp = new double[array.length];
        
        for(int i = 0; i < array.length; i++)
        {
            temp[i] = array[array.length - (i+1)];
        }
        for(int i = 0; i < array.length; i++)
        {
            array[i] = temp[i];
        }
    }
    
}

