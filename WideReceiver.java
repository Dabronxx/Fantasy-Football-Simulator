public class WideReceiver extends Player
{
    public WideReceiver(String name, String risk)
    {
        super(name, risk);
    }
    public WideReceiver(WideReceiver otherWR)
    {
        super(otherWR);
    }
    public WideReceiver(Player player)
    {
        super(player);
    }
    public String getPosition()
    {
        super.getPosition();
        return "Wide Receiver";
    }
}
