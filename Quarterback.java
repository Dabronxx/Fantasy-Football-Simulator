public class Quarterback extends Player
{
    public Quarterback(String name, String risk)
    {
        super(name, risk);
    }
    public Quarterback(Quarterback qbCopy)
    {
        super(qbCopy);
    }
    public Quarterback(Player player)
    {
        super(player);
    }
    public String getPosition()
    {
        super.getPosition();
        return "Quarterback";
    }
}
