public class TeamStatistics
{
    private double score = 0;
    private int teamNumber = 0;
    private int[] teamStatistics = null;
    private String[] playerNames = null;
    
    public TeamStatistics(double score, String[] names, int[] statistics, int teamNumber)
    {
        teamStatistics = new int[statistics.length];
        playerNames = new String[names.length];
        for(int i = 0; i < statistics.length; i++)
        {
            teamStatistics[i] = statistics[i];
        }
        for(int i = 0; i <names.length; i++)
        {
            playerNames[i] = names[i];
        }
        this.score = score;
        this.teamNumber = teamNumber;
    }
    public double getScore()
    {
        return score;
    }
    public int getTeamNumber()
    {
        return teamNumber;
    }
    public int[] statistics()
    {
        return teamStatistics;
    }
    public String[] getNames()
    {
        return playerNames;
    }
    public void teamStatistics()
    {
        System.out.println("Quarterback " + playerNames[0] + " threw for " + teamStatistics[0] + " yards, " + teamStatistics[1] + " touchdowns, and " + teamStatistics[2] + " interceptions.");
        System.out.println("Running Back " + playerNames[1] + " ran for " + teamStatistics[3] + " yards, " + teamStatistics[4] + " touchdowns, and fumbled " + teamStatistics[5] + " times.");
        System.out.println("Wide Receiver " + playerNames[2] + " caught " + teamStatistics[6] + " balls for " + teamStatistics[7] + " yards, and " + teamStatistics[8] + " touchdowns.");
    }
}
