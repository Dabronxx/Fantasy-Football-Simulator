public class RunningBack extends Player
{
    public RunningBack(String name, String risk)
    {
        super(name, risk);
    }
    public RunningBack(RunningBack otherRB)
    {
        super(otherRB);
    }
    public RunningBack(Player player)
    {
        super(player);
    }
    public String getPosition()
    {
        super.getPosition();
        return "Running Back";
    }

}
