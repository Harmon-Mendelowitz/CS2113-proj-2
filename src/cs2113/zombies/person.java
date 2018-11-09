package cs2113.zombies;

import cs2113.util.Helper;

public class person
{
    protected int direction=0;
    protected boolean moved = false;
    protected boolean infected = false;

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

    public static void checkWall(person[][] people, boolean[][] walls, int width, int height)
    {
        for(int y=0; y<height; y++){
            for (int x = 0; x < width; x++){
                if(people[x][y]!=null) {
                    people[x][y].update();
                    if (y != 0) {
                        if (people[x][y].getDirection() == 0 && (walls[x][y - 1] || people[x][y-1]!=null)) {
                            people[x][y].update();
                            while (people[x][y].getDirection() == 0) {
                                people[x][y].update();
                            }
                        }
                    }
                    if (y != height-1) {
                        if (people[x][y].getDirection() == 2 && (walls[x][y + 1] || people[x][y+1]!=null)) {
                            people[x][y].update();
                            while (people[x][y].getDirection() == 2) {
                                people[x][y].update();
                            }
                        }
                    }
                    if (x != 0) {
                        if (people[x][y].getDirection() == 3 && (walls[x - 1][y] || people[x-1][y]!=null)) {
                            people[x][y].update();
                            while (people[x][y].getDirection() == 3) {
                                people[x][y].update();
                            }
                        }
                    }
                    if (x != width-1) {
                        if (people[x][y].getDirection() == 1 && (walls[x + 1][y] || people[x+1][y]!=null)) {
                            people[x][y].update();
                            while (people[x][y].getDirection() == 1) {
                                people[x][y].update();
                            }
                        }
                    }
                    if(y==0 && people[x][y].getDirection()==0)
                    {
                        people[x][y].update();
                        while (people[x][y].getDirection() == 0) {
                            people[x][y].update();
                        }
                    }
                    if(y==height-1 && people[x][y].getDirection()==2)
                    {
                        people[x][y].update();
                        while (people[x][y].getDirection() == 2) {
                            people[x][y].update();
                        }
                    }
                    if(x==0 && people[x][y].getDirection()==3)
                    {
                        people[x][y].update();
                        while (people[x][y].getDirection() == 3) {
                            people[x][y].update();
                        }
                    }
                    if(x==width-1 && people[x][y].getDirection()==1)
                    {
                        people[x][y].update();
                        while (people[x][y].getDirection() == 1) {
                            people[x][y].update();
                        }
                    }
                }
            }
        }
    }

    public static void move(person[][] people, boolean[][] walls, int width, int height)
    {
        for(int y=0; y<height; y++)
        {
            for(int x=0; x<width; x++)
            {
                if(people[x][y]!=null)
                {
                    //people[x][y].update();

                    if(y>0 && people[x][y]!=null)
                    {
                        if(people[x][y].getDirection() == 0 && !walls[x][y - 1] && people[x][y-1]==null && people[x][y].getMoved()!=true)
                        {
                            //people2[x][y-1] = new person(people[x][y].getDirection());
                            //if(people[x][y].getInfected())
                            //	people[x][y - 1] = new zombie(people[x][y].getDirection());
                            //else
                            //	people[x][y - 1] = new person(people[x][y].getDirection());
                            people[x][y-1] = people[x][y];
                            people[x][y-1].setMoved(true);
                            people[x][y] = null;
                            //break;
                        }
                    }
                    if(y<height-1 && people[x][y]!=null)
                    {
                        if(people[x][y].getDirection() == 2 && !walls[x][y + 1] && people[x][y+1]==null && people[x][y].getMoved()!=true)
                        {
                            people[x][y+1] = people[x][y];
                            people[x][y+1].setMoved(true);
                            people[x][y] = null;
                            //break;
                        }
                    }
                    if(x<width-1 && people[x][y]!=null)
                    {
                        if(people[x][y].getDirection() == 1 && !walls[x+1][y] && people[x+1][y]==null && people[x][y].getMoved()!=true)
                        {
                            people[x+1][y] = people[x][y];
                            people[x+1][y].setMoved(true);
                            people[x][y] = null;
                            //break;
                        }
                    }
                    if(x>0 && people[x][y]!=null)
                    {
                        if(people[x][y].getDirection() == 3 && !walls[x-1][y] && people[x-1][y]==null && people[x][y].getMoved()!=true)
                        {
                            people[x-1][y] = people[x][y];
                            people[x-1][y].setMoved(true);
                            people[x][y] = null;
                            //break;
                        }
                    }
                }
            }
        }
    }
}
