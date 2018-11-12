package cs2113.zombies;

import cs2113.util.Helper;

public class person
{
    protected int direction=0;
    protected boolean moved = false;
    protected boolean infected = false;
    protected boolean weapon = false;

    public person(int d)
    {
        direction = d;
    }
    public person()
    {
    }

    public boolean hasWeapon(){return weapon;}
    public int getDirection()
    {
        return direction;
    }
    public boolean getInfected()
    {
        return infected;
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

    public boolean sight(person[][] p, boolean[][] wall, int x, int y, int width, int height, int d)
    {
        //int d = this.direction;
        boolean found = false;
        int i = 1;
        int dx=x;
        int dy=y;
        while(i<10 && dx<width-1 && dy<height-1 && dx>0 && dy>0 && !found) {
            if(d == 0) {
                dx=x;
                dy=y-i;
                if (p[dx][dy]!=null && p[dx][dy].getInfected()) {
                    System.out.println("Run");
                    this.direction = 2;
                    for(int a=1;a<3;a++) {
                        int ay=y+a;
                        if(y+a<height-1) {
                            if (!wall[x][y + a] && p[x][y + a] == null) {
                                p[x][ay] = this;
                                p[x][ay-1] = null;
                                p[x][ay].setMoved(true);
                            }
                        }
                    }
                    found=true;
                }
            }
            else if(d == 1 && !found) {
                dx=x+i;
                dy=y;
                if (p[dx][dy]!=null && p[dx][dy].getInfected()) {
                    System.out.println("Run");
                    this.direction = 3;
                    for(int a=1;a<3;a++) {
                        int ax=x-a;
                        if(x-a>0) {
                            if (!wall[x - a][y] && p[x - a][y] == null) {
                                p[ax][y] = this;
                                p[ax+1][y] = null;
                                p[ax][y].setMoved(true);
                            }
                        }
                    }
                    found=true;
                }
            }
            else if(d == 2 && !found) {
                dx=x;
                dy=y+i;
                if (p[dx][dy]!=null && p[dx][dy].getInfected()) {
                    System.out.println("Run");
                    this.direction = 0;
                    for(int a=1;a<3;a++) {
                        int ay=y-a;
                        if(y-a>0) {
                            if (!wall[x][y - a] && p[x][y - a] == null) {
                                p[x][ay] = this;
                                p[x][ay+1] = null;
                                p[x][ay].setMoved(true);
                            }
                        }
                    }
                    found=true;
                }
            }
            else if(d == 3 && !found) {
                dx=x-i;
                dy=y;
                if (p[dx][dy]!=null && p[dx][dy].getInfected()) {
                    System.out.println("Run");
                    this.direction = 1;
                    for(int a=1;a<3;a++) {
                        int ax=x+a;
                        if(x+a<width-1) {
                            if (!wall[x + a][y] && p[x + a][y] == null) {
                                p[ax][y] = this;
                                p[ax-1][y] = null;
                                p[ax][y].setMoved(true);
                            }
                        }
                    }
                    found=true;
                }
            }
            i++;
        }
        return found;
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
                    if(!people[x][y].getMoved()) {
                        if (!people[x][y].sight(people, walls, x, y, width, height, people[x][y].getDirection())) ;
                        {
                            if (y > 0 && people[x][y] != null) {
                                if (people[x][y].getDirection() == 0 && !walls[x][y - 1] && people[x][y - 1] == null && people[x][y].getMoved() != true) {
                                    //people2[x][y-1] = new person(people[x][y].getDirection());
                                    //if(people[x][y].getInfected())
                                    //	people[x][y - 1] = new zombie(people[x][y].getDirection());
                                    //else
                                    //	people[x][y - 1] = new person(people[x][y].getDirection());
                                    people[x][y - 1] = people[x][y];
                                    people[x][y - 1].setMoved(true);
                                    people[x][y] = null;
                                    //break;
                                }
                            }
                            if (y < height - 1 && people[x][y] != null) {
                                if (people[x][y].getDirection() == 2 && !walls[x][y + 1] && people[x][y + 1] == null && people[x][y].getMoved() != true) {
                                    people[x][y + 1] = people[x][y];
                                    people[x][y + 1].setMoved(true);
                                    people[x][y] = null;
                                    //break;
                                }
                            }
                            if (x < width - 1 && people[x][y] != null) {
                                if (people[x][y].getDirection() == 1 && !walls[x + 1][y] && people[x + 1][y] == null && people[x][y].getMoved() != true) {
                                    people[x + 1][y] = people[x][y];
                                    people[x + 1][y].setMoved(true);
                                    people[x][y] = null;
                                    //break;
                                }
                            }
                            if (x > 0 && people[x][y] != null) {
                                if (people[x][y].getDirection() == 3 && !walls[x - 1][y] && people[x - 1][y] == null && people[x][y].getMoved() != true) {
                                    people[x - 1][y] = people[x][y];
                                    people[x - 1][y].setMoved(true);
                                    people[x][y] = null;
                                    //break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
