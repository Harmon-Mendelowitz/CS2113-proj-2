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
The update method checks a few things. First, it calls the kill( ) method in slayer to check if there is a zombie next to a slayer. Next, it calls the infect( ) method in zombie to check if there is a person next to a zombie to infect. It then calls the checkWall( ) and move( ) methods in person to check if each person or person subclass can move to a given location or if they need to turn first, then it will move them if there is nothing in the way. After all these a called, it will then iterate through every person in people[ ][ ] and change their boolean setMoved value to false so that the program knows they can be moved once more.



## person.java
- Contains an integer for direction, signifying the direction the person is facing with 0 being up, 1 being right, 2 down, and 3 left.
- Contains boolean values for moved, infected, and weapon.
- Each value has a get( ) method and Person has a setMoved( ) method which is used to determine whether a person has been moved or not within each update call.

### update  
Has a 10% chance of changing the direction value of the person to a random integer between 0 and 3 inclusive.  

### sight  
Checks if there is a zombie within 10 spaces of the direction the person is facing. If there is, then the person will attempt to turn around and move 2 spaces if there is nothing in the way.

### checkWall  
If the person is facing a wall, then they will turn until they are no longer directly in front of a wall.

### move
If the person has not seen a zombie from the call to sight( ) and the person has not yet been moved, then the person will engage in normal movement behavior by moving forward if there is no wall or person in the way. This is done by setting the target location's people[ ][ ] coordinates to the person being moved and setting the person's old location to null. The new person's Moved variable is then set to true so that the function will not move this person again until everyone has been moved and the next cycle of checks has begun.



--------------------
- This is the extra feature I added since I thought it would be cool to pin two teams against each other rather than just letting the zombies kill everyone.  
-There is also an added feature where if all non-slayers are turned into a zombie, then the slayers will lose motivation to continue fighting and "lose", and if all zombies are eliminated before that, then the humans win.

## slayer.java
- weapon boolean set to true.
- same methods as person due to extension unless otherwise specified

### kill
Checks if there is an infected person (zombie) next to the slayer. If there is, then that zombie is killed and the people[ ][ ] array at that location is set to null.

### sight
New sight method compared to the person class. Instead of running away from a zombie, if the slayer sees a zombie in its line of sight it will attempt to move towards it.

---------------------

## zombie.java
- infected = true
- extends the person class
- same update method as person, but there is a 20% chance the zombie will turn compared to the human's 10%

### sight
Same sight method as the slayer, except the zombie will attempt to move towards any person not infected (includes slayers).

### infect
Same as the slayer's kill( ), but instead of killing the person, if the zombie moves within a space of the target then they are turned into a zombie. This is done by setting the given people[ ][ ] location to a new zombie with the same direction variable as the person which was previously there.
