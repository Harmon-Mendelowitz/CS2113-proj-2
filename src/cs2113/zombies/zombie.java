package cs2113.zombies;

import cs2113.util.Helper;

public class zombie extends person
{
    public boolean infected = true;
    public zombie(int d)
    {
        direction = d;
    }
    public void update()
    {
        int ud = Helper.nextInt(5);
        if(ud==1)
        {
            direction = Helper.nextInt(4);
        }
    }
    public boolean getInfected()
    {
        return true;
    }
}
