package cs2113.zombies;
import cs2113.util.Helper;

public class slayer extends person
{
    boolean weapon = true;

    public slayer(){}

    public boolean hasWeapon(){return weapon;}

    public static void kill(person[][] p, int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (p[x][y] != null && p[x][y].hasWeapon()) {
                    if (x > 0) {
                        if (p[x - 1][y] != null && p[x - 1][y].getInfected())
                            p[x - 1][y] = null;
                    }
                    if (y > 0) {
                        if (p[x][y - 1] != null && p[x][y - 1].getInfected())
                            p[x][y - 1] = null;
                    }
                    if (x < width - 1) {
                        if (p[x + 1][y] != null && p[x + 1][y].getInfected())
                            p[x + 1][y] = null;
                    }
                    if (y < height - 1) {
                        if (p[x][y + 1] != null && p[x][y + 1].getInfected())
                            p[x][y + 1] = null;
                    }
                }
            }
        }
    }

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
                if (p[dx][dy]!=null && p[dx][dy].getInfected()) {
                    System.out.println("Found Zombie");
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
                if (p[dx][dy]!=null && p[dx][dy].getInfected()) {
                    System.out.println("Found Zombie");
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
                if (p[dx][dy]!=null && p[dx][dy].getInfected()) {
                    System.out.println("Found Zombie");
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
                if (p[dx][dy]!=null && p[dx][dy].getInfected()) {
                    System.out.println("Found Zombie");
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
}
