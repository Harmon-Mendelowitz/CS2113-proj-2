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

	public person[][] getP()
    {
        return people;
    }
    public boolean[][] getW() { return walls; }
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

		//creates buildings randomly and populates the city with 'numB' number of people
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

		// Generates a new zombie at a random location in the city.
		tx = Helper.nextInt(width);
		ty = Helper.nextInt(height);
		while (walls[tx][ty] == true || people[tx][ty]!=null)
		{
			tx = Helper.nextInt(width);
			ty = Helper.nextInt(height);
		}
		people[tx][ty] = new zombie(Helper.nextInt(4));

		// Generates new slayers at random locations, with the number of slayers depending on the number of people
		for(int x=0; x<numPeople/15; x++) {
			tx = Helper.nextInt(width);
			ty = Helper.nextInt(height);
			while (walls[tx][ty] == true || people[tx][ty] != null) {
				tx = Helper.nextInt(width);
				ty = Helper.nextInt(height);
			}
			people[tx][ty] = new slayer();
		}
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
		//removes any zombies adjacent to a slayer
		slayer.kill(people, width, height);
		//turns a human next to a zombie into a zombie
		zombie.infect(people, width, height);
		//updates the direction of each person to not face a wall
		person.checkWall(people,walls,width,height);
		//moves each person or person subclass by one space
		person.move(people,walls,width,height);
		//after everyone has been moved, sets their moved variable to false
        for(int y=0; y<height; y++) {
            for (int x = 0; x < width; x++) {
                if(people[x][y]!=null)
                    people[x][y].setMoved(false);
            }
        }
	}

	/**
	 * Draw the buildings and all humans.
	 */
	public void draw(){
		/* Clear the screen */
		ZombieSim.dp.clear(Color.black);

		//redraw people and walls
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
			//if there is a person at people[c][r], then color that location white
			if(people[c][r]!=null)
			{
				ZombieSim.dp.setPenColor(Color.WHITE);
				ZombieSim.dp.drawDot(c, r);
				//if they are infected, color them green
				if(people[c][r].getInfected())
				{
					ZombieSim.dp.setPenColor(Color.GREEN);
					ZombieSim.dp.drawDot(c, r);
				}
				//if they have a weapon, color them red
				if(people[c][r].hasWeapon())
				{
					ZombieSim.dp.setPenColor(Color.RED);
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
				//if walls[c][r] is true, then there is a wall at that location, so the panel colors it gray
				if(walls[c][r])
				{
					ZombieSim.dp.drawDot(c, r);
				}
			}
		}
	}

}
