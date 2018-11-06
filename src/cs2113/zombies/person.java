package cs2113.zombies;

import cs2113.util.Helper;

public class person
{
    private int direction=0;
    private boolean moved = false;

    public person(int d)
    {
        direction = d;
    }

    public int getDirection()
    {
        return direction;
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
