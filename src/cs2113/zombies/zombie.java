package cs2113.zombies;

import cs2113.util.Helper;

public class zombie extends person
{
    private boolean infected = true;
    public zombie(int d)
    {
        direction = d;
    }
    //new update method with a 20% chance to turn compared to the person's 10%
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
        return infected;
    }

    //Same sight method as the slayer, but the zombie looks for non-infected people instead of infected
    public boolean sight(person[][] p, boolean[][] wall, int x, int y, int width, int height, int d)
    {
        boolean found = false;
        int i = 1;
        int dx=x;
        int dy=y;
        while(i<10 && dx<width-1 && dy<height-1 && dx>0 && dy>0 && !found) {
            if(d == 0) {
                dx=x;
                dy=y-i;
                if (p[dx][dy]!=null && !p[dx][dy].getInfected()) {
                    if(y-1>0) {
                        if (!wall[x][y - 1] && p[x][y - 1] == null) {
                            p[x][y - 1] = this;
                            p[x][y] = null;
                            p[x][y - 1].setMoved(true);
                        }
                    }
                    found=true;
                }
            }
            else if(d == 1 && !found) {
                dx=x+i;
                dy=y;
                if (p[dx][dy]!=null && !p[dx][dy].getInfected()) {
                    if(x+1<width-1) {
                        if (!wall[x + 1][y] && p[x + 1][y] == null) {
                            p[x + 1][y] = this;
                            p[x][y] = null;
                            p[x + 1][y].setMoved(true);
                        }
                    }
                    found=true;
                }
            }
            else if(d == 2 && !found) {
                dx=x;
                dy=y+i;
                if (p[dx][dy]!=null && !p[dx][dy].getInfected()) {
                    if(y+1<height-1) {
                        if (!wall[x][y + 1] && p[x][y + 1] == null) {
                            p[x][y + 1] = this;
                            p[x][y] = null;
                            p[x][y + 1].setMoved(true);
                        }
                    }
                    found=true;
                }
            }
            else if(d == 3 && !found) {
                dx=x-i;
                dy=y;
                if (p[dx][dy]!=null && !p[dx][dy].getInfected()) {
                    if(x-1>0) {
                        if (!wall[x - 1][y] && p[x - 1][y] == null) {
                            p[x - 1][y] = this;
                            p[x][y] = null;
                            p[x - 1][y].setMoved(true);
                        }
                    }
                    found=true;
                }
            }
            i++;
        }
        return found;
    }

    //if a zombie is next to a person, they are infected and turned into a zombie by creating a new zombie at the person's location
    // with the same direction value as the person there previously
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
