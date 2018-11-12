package cs2113.zombies;

import cs2113.util.DotPanel;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import cs2113.util.Helper;
import java.awt.event.*;


/*
 * You must add a way to represent humans.  When there is not a zombie apocalypse occurring, humans
 * should follow these simple rules:
 * 		if (1 in 10 chance):
 * 			turn to face a random direction (up/down/left/right)
 * 		Move in the current direction one space if not blocked by a wall
 *
 * We will add additional rules for dealing with sighting or running into zombies later.
 */
public class ZombieSim extends JFrame implements ActionListener, KeyListener, MouseListener{

	public City w;

	public void keyTyped(KeyEvent keyEvent) {
	}
	public void keyPressed(KeyEvent keyEvent)
	{
		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				System.out.println("Space");
				w = new City(MAX_X, MAX_Y, NUM_BUILDINGS, NUM_HUMANS);
		}
	}
	public void keyReleased(KeyEvent keyEvent) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1){
			System.out.println("clicked: " + e.getLocationOnScreen());
			person[][] p = w.getP();
			boolean[][] walls = w.getW();
			if (!walls[(e.getX() / 3) - 1][(e.getY() / 3) - 8])
				p[(e.getX() / 3) - 1][(e.getY() / 3) - 8] = new zombie(Helper.nextInt(4));
		}
		if(e.getButton()==MouseEvent.BUTTON3){
			System.out.println("clicked: " + e.getLocationOnScreen());
			person[][] p = w.getP();
			boolean[][] walls = w.getW();
			if (!walls[(e.getX() / 3) - 1][(e.getY() / 3) - 8])
				p[(e.getX() / 3) - 1][(e.getY() / 3) - 8] = new slayer();
		}
	}

	private static final long serialVersionUID = -5176170979783243427L;

	/** The Dot Panel object you will draw to */
	protected static DotPanel dp;

	/* Define constants using static final variables */
	public static final int MAX_X = 200;
	public static final int MAX_Y = 200;
	private static final int DOT_SIZE = 3;
	private static final int NUM_HUMANS = 200;
	private static final int NUM_BUILDINGS = 80;
	/*
	 * This fills the frame with a "DotPanel", a type of drawing canvas that
	 * allows you to easily draw squares to the screen.
	 */
	public ZombieSim() {
		this.setSize(MAX_X * DOT_SIZE, MAX_Y * DOT_SIZE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Braaiinnnnnsss");

		/* Create and set the size of the panel */
		dp = new DotPanel(MAX_X, MAX_Y, DOT_SIZE);

		/* Add the panel to the frame */
		Container cPane = this.getContentPane();
		cPane.add(dp);

		addKeyListener(this);
		addMouseListener(this);
//		setFocusable(true);
//		setFocusTraversalKeysEnabled(false);
		/* Initialize the DotPanel canvas:
		 * You CANNOT draw to the panel BEFORE this code is called.
		 * You CANNOT add new widgets to the frame AFTER this is called.
		 */
		this.pack();
		dp.init();
		dp.clear();
		dp.setPenColor(Color.red);
		this.setVisible(true);

		/* Create our city */
		City world = new City(MAX_X, MAX_Y, NUM_BUILDINGS, NUM_HUMANS);
		this.w = world;

		/* This is the Run Loop (aka "simulation loop" or "game loop")
		 * It will loop forever, first updating the state of the world
		 * (e.g., having humans take a single step) and then it will
		 * draw the newly updated simulation. Since we don't want
		 * the simulation to run too fast for us to see, it will sleep
		 * after repainting the screen. Currently it sleeps for
		 * 33 milliseconds, so the program will update at about 30 frames
		 * per second.
		 */
		while(true)
		{
			// Run update rules for world and everything in it
			w.update();
			// Draw to screen and then refresh
			w.draw();
			dp.repaintAndSleep(33);
			int numZ = 0;
			int numP = 0;
			for(int x=0;x<MAX_X; x++)
			{
				for(int y=0;y<MAX_Y; y++)
				{
					if(w.getP()[x][y]!=null) {
						if (w.getP()[x][y].getInfected())
							numZ++;
						if (!w.getP()[x][y].getInfected() && !w.getP()[x][y].hasWeapon())
							numP++;
					}
				}
			}
			if(numZ==0) {
				System.out.println("Humans Win");
				System.exit(0);
			}
			if(numP==0) {
				System.out.println("Zombies Win");
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		/* Create a new GUI window  */
		new ZombieSim();
	}


	public void actionPerformed(ActionEvent actionEvent) {
//		Color randColor = Color.getHSBColor((float)Helper.nextDouble(), (float)Helper.nextDouble(), (float)Helper.nextDouble());
//		int x,y;
//		x = Helper.nextInt(MAX_X);
//		y = Helper.nextInt(MAX_Y);
//		MovableDot dot = new MovableDot(x, y, randColor);
//		activeDot = dot;
//		System.out.println("New dot at dot coordinate: " + x + ", " + y);
	}
}
