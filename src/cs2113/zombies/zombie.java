package cs2113.zombies;

import cs2113.util.Helper;

public class zombie extends person
{
    private boolean infected = true;
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

    public static void infect(person[][] p, int width, int height)
    {
        for(int y=0; y<height; y++) {
            for (int x = 0; x < width; x++) {
                if(p[x][y]!=null && p[x][y].getInfected()) {
                    if(y!=height-1)
                    {
                        if(p[x][y+1]!=null && !p[x][y+1].getInfected())
                        {
                            p[x][y+1] = new zombie(p[x][y+1].getDirection());
                        }
                    }
                    if(y!=0)
                    {
                        if(p[x][y-1]!=null && !p[x][y-1].getInfected()) {
                            p[x][y-1] = new zombie(p[x][y-1].getDirection());
                        }
                    }
                    if(x!=width-1) {
                        if (p[x+1][y]!=null && !p[x + 1][y].getInfected()) {
                            p[x+1][y] = new zombie(p[x+1][y].getDirection());
                        }
                    }
                    if(x!=0) {
                        if (p[x-1][y]!=null && !p[x - 1][y].getInfected()) {
                            p[x-1][y] = new zombie(p[x-1][y].getDirection());
                        }
                    }
                }
            }
        }
    }
}
