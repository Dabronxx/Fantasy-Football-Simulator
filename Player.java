public class Player
{
    private String name;
    private String risk;
    public Player(String name, String risk)
    {
        this.name = name;
        this.risk = risk;
    }
    public Player(Player otherPlayer)
    {
        this.name = otherPlayer.name;
        this.risk = otherPlayer.risk;
    }
    public String getName()
    {
        return name;
    }
    public String getRisk()
    {
        return risk;
    }
    public String getPosition()
    {
        return "";
    }
    public String toString()
    {
        return getPosition() + " " + name + " is a " + risk + " risk player." ;
    }
    public boolean equals(Object other)
    {
        if(other == null)
        {
            return false;
        }
        if(getClass() != other.getClass())
        {
            return false;
        }
        Player otherPlayer = (Player) other;
        return getPosition() == otherPlayer.getPosition() && otherPlayer.risk == risk;
    }
    
}
