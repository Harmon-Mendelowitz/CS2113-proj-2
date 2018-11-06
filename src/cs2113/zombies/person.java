package cs2113.zombies;

import cs2113.util.Helper;

public class person
{
    public int direction=0;
    public boolean moved = false;
    public boolean infected = false;

    public person(int d)
    {
        direction = d;
    }
    public person()
    {
    }

    public int getDirection()
    {
        return direction;
    }
    public boolean getInfected()
    {
        return false;
    }
    public boolean getMoved()
    {
        return moved;
    }
    public void setMoved(boolean a)
    {
        moved = a;
    }
    public void update()
    {
        int ud = Helper.nextInt(10);
        if(ud==1)
        {
            direction = Helper.nextInt(4);
        }
    }
}
