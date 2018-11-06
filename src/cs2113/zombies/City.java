package cs2113.zombies;

import cs2113.util.Helper;

import java.awt.Color;


public class City {

	/** walls is a 2D array with an entry for each space in the city.
	 *  If walls[x][y] is true, that means there is a wall in the space.
	 *  else the space is free. Humans should never go into spaces that
	 *  have a wall.
	 */
	private boolean walls[][];
	private person people[][];
	private int width, height;

	/**
	 * Create a new City and fill it with buildings and people.
	 * @param w width of city
	 * @param h height of city
	 * @param numB number of buildings
	 * @param numP number of people
	 */
	public City(int w, int h, int numB, int numP) {
		width = w;
		height = h;
		walls = new boolean[w][h];
		people = new person[w][h];

		randomBuildings(numB);
		populate(numP);
	}


	/**
	 * Generates numPeople random people distributed throughout the city.
	 * People must not be placed inside walls!
	 *
	 * @param numPeople the number of people to generate
	 */
	private void populate(int numPeople)
	{
		int tx,ty;
		// Generate numPeople new humans randomly placed around the city.
		for(int x=0; x<numPeople; x++)
		{
			tx = Helper.nextInt(width);
			ty = Helper.nextInt(height);
			while(walls[tx][ty] == true || people[tx][ty]!=null)
			{
				tx = Helper.nextInt(width);
				ty = Helper.nextInt(height);
			}
			people[tx][ty] = new person(Helper.nextInt(4));
		}

		tx = Helper.nextInt(width);
		ty = Helper.nextInt(height);
		while (walls[tx][ty] == true || people[tx][ty]!=null)
		{
			tx = Helper.nextInt(width);
			ty = Helper.nextInt(height);
		}
		people[tx][ty] = new zombie(Helper.nextInt(4));
	}


	/**
	 * Generates a random set of numB buildings.
	 *
	 * @param numB the number of buildings to generate
	 */
	private void randomBuildings(int numB) {
		/* Create buildings of a reasonable size for this map */
			int bldgMaxSize = width/6;
			int bldgMinSize = width/50;

			/* Produce a bunch of random rectangles and fill in the walls array */
			for(int i=0; i < numB; i++) {
				int tx, ty, tw, th;
				tx = Helper.nextInt(width);
				ty = Helper.nextInt(height);
				tw = Helper.nextInt(bldgMaxSize) + bldgMinSize;
				th = Helper.nextInt(bldgMaxSize) + bldgMinSize;

				for(int r = ty; r < ty + th; r++) {
					if(r >= height)
						continue;
					for(int c = tx; c < tx + tw; c++) {
						if(c >= width)
							break;
						walls[c][r] = true;
					}
				}
		}
	}

	/**
	 * Updates the state of the city for a time step.
	 */
	public void update() {
		// Move humans, zombies, etc
		//person[][] people2 = new person[width][height];
		for(int y=0; y<height; y++)
		{
			for(int x=0; x<width; x++)
			{
				if(people[x][y]!=null)
				{
					people[x][y].update();

					if(y>0 && people[x][y]!=null)
					{
						if(people[x][y].getDirection() == 0 && !walls[x][y - 1] && people[x][y-1]==null && people[x][y].getMoved()!=true)
						{
							//people2[x][y-1] = new person(people[x][y].getDirection());
							if(people[x][y].getInfected())
							{
								people[x][y - 1] = new zombie(people[x][y].getDirection());
							}
							else
							{
								people[x][y - 1] = new person(people[x][y].getDirection());
							}
							people[x][y-1].setMoved(true);
							people[x][y] = null;
							//break;
						}
					}
					if(y<height-1 && people[x][y]!=null)
					{
						if(people[x][y].getDirection() == 2 && !walls[x][y + 1] && people[x][y+1]==null && people[x][y].getMoved()!=true)
						{
							//people2[x][y+1] = new person(people[x][y].getDirection());
							if(people[x][y].getInfected())
							{
								people[x][y + 1] = new zombie(people[x][y].getDirection());
							}
							else
							{
								people[x][y + 1] = new person(people[x][y].getDirection());
							}
                            people[x][y+1].setMoved(true);
							people[x][y] = null;
							//break;
						}
					}
					if(x<width-1 && people[x][y]!=null)
					{
						if(people[x][y].getDirection() == 1 && !walls[x+1][y] && people[x+1][y]==null && people[x][y].getMoved()!=true)
						{
							//people2[x+1][y] = new person(people[x][y].getDirection());
							if(people[x][y].getInfected())
							{
								people[x+1][y] = new zombie(people[x][y].getDirection());
							}
							else
							{
								people[x + 1][y] = new person(people[x][y].getDirection());
							}
                            people[x+1][y].setMoved(true);
							people[x][y] = null;
							//break;
						}
					}
					if(x>0 && people[x][y]!=null)
					{
						if(people[x][y].getDirection() == 3 && !walls[x-1][y] && people[x-1][y]==null && people[x][y].getMoved()!=true)
						{
							//people2[x-1][y] = new person(people[x][y].getDirection());
							if(people[x][y].getInfected())
							{
								people[x-1][y] = new zombie(people[x][y].getDirection());
							}
							else
							{
								people[x - 1][y] = new person(people[x][y].getDirection());
							}
                            people[x-1][y].setMoved(true);
							people[x][y] = null;
							//break;
						}
					}
				}
			}
		}
        for(int y=0; y<height; y++) {
            for (int x = 0; x < width; x++) {
                if(people[x][y]!=null)
                    people[x][y].setMoved(false);
            }
        }
		//people = people2;
	}

	/**
	 * Draw the buildings and all humans.
	 */
	public void draw(){
		/* Clear the screen */
		ZombieSim.dp.clear(Color.black);

		drawWalls();
		drawPeople();
	}
private void drawPeople()
{
	ZombieSim.dp.setPenColor(Color.WHITE);
	for(int r = 0; r < height; r++)
	{
		for(int c = 0; c < width; c++)
		{
			if(people[c][r]!=null)
			{
				ZombieSim.dp.setPenColor(Color.WHITE);
				ZombieSim.dp.drawDot(c, r);
				if(people[c][r].getInfected())
				{
					ZombieSim.dp.setPenColor(Color.GREEN);
					ZombieSim.dp.drawDot(c, r);
				}
			}
		}
	}
}
	/**
	 * Draw the buildings.
	 * First set the color for drawing, then draw a dot at each space
	 * where there is a wall.
	 */
	private void drawWalls() {
		ZombieSim.dp.setPenColor(Color.DARK_GRAY);
		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(walls[c][r])
				{
					ZombieSim.dp.drawDot(c, r);
				}
			}
		}
	}

}
