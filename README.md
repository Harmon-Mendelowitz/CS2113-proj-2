# proj-2-HarmonMendelowitz

## City.java
- Contains two 2-D boolean arrays of walls and people, and the integer for width and height.
- Can access these arrays with the getP( ) and getW( ) functions to return the array of people and walls respectively.
- The city constructor takes an input for width, height, number of buildings, and number of people and immediately calls the randomBuildings( ) method and the populate( ) method.  

### randomBuildings  
The Building method creates randomly sized rectangles depending on the constraints of the city's width and height, and fills in the walls[ ][ ] array at the given locations.  

### populate  
The Populate method creates a person at a random location within the constraints of the city that is not already occupied by a wall, and it does this the same number of times as the number of people entered when the city was instantiated. No two people can share a location as well. These objects are stored in the locations of the poeple[ ][ ] array corresponding to their coordinates in the city. Next, an instance of the zombie class is created at a random location, not overlapping with a wall or person. Lastly, a number of slayers are instantiated at random locations, using the same parameters as above. The number of slayers corresponds to the number of people instantiated.  

- These are the methods which are called only once and set up the city for the simulation. The following methods are called in ZombieSim.java to update the graphic.  

### draw  
The draw method sets the dotPanel color to black and calls the drawWall( ) and drawPeople( ) methods.  

### drawWall  
The drawWall method sets the color to gray and whenever the walls[ ][ ] boolean array contains a true, that corresponding location on the panel is colored to signify part of a building.

### drawPeople  
Sets the color to white and iterates through the people[ ][ ] array of person objects. If one of the indexes is true, that location on the panel is colored. If the person at that index is infected, then they are colored green instead since they are a zombie. If the person has a weapon, then they are colored red to signify a slayer.

### update
The update method 
