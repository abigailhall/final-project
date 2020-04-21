
Group Members: Kate Frisch, Van Griffith, & Abby Hall

For our Final Project, we are interested in desiging/developing two already existing games; Fruit Ninja and Minesweeper. 

Starting with Fruit Ninja, we would use the mouse as the sword so that when the player presses and drags the sword it will slice the fruit and earn the player points, the player is only allowed 3 strikes until it is game over or until they hit a bomb. A strike is defined as a full fruit that hits the bottom of the screen. The fruit and bombs will be randomly generated to be thrown up in the air and fall down using gravity. Based on the fruit there will be points awarded to the player and the high score will be saved. We will implement a way for the player to pick between different game modes (easy, medium, & hard) and customize different parts of the game experience (including the use of different kinds of swords and backgrounds). Players will also receive bonus points if three of the same fruit are sliced at once. This game will satisfy all of the requirements of the projecct. Starting off with a meaningful object-oriented design which can include an abstract class representing a fruit that is extended by all fruits. For the event-driven programming including Java graphics and Java Swing GUI components requirement, we will be using Mouse Events, more specifically mousePressed, mouseDragged, and mouseReleased to simulate the sword. Graphics wise, we will be using a variety of fruit pixel art that will be animated. In regards to the threads for animation or computational speedup requirement, depending on which level is chosen, the harder and faster the fruit will fall from the screen using threads and possibly use threads to animate different kinds of swords. For each level, the speed and the amount of fruit falling at once increaes. For the appropriate use of data structures requirement, we will be using at least ArrayLists and tree structures. Finally, the last requirement is to include something we have not learned yet, and we are planning on implementing sound effects throughout the program.

The second game we plan on implementing would be a clone of the classic Microsoft game Minesweeper. At the start of a game, the program would generate a random array of tiles, each designated as either a bomb or a free space. The size of the array and the amount of bombs could be specified by the player, but we will include presets for easy, medium, and expert. The game would be controlled entirely through mouse clicks. Each tile would contain hidden information about itself and the area around it, containing a number indicating the amount of bombs in the 8 surrounding tiles, a bomb, or nothing if the space is not adjacent to any bombs. Revealing a blank tile will reveal any adjacent blank or number tiles. The goal for the player is to click on/reveal all non-bomb tiles on the board, while not triggering any bombs themselves. We would include different fuctions for the right and left mouse clicks. Should the player click on a tile designated as a bomb, each of the remaining bombs on the board would reveal themselves for a brief moment before disappearing with an explosion animation (possibly one at a time, maybe all at once), as well as play an explosion sound effect. Tiles marked with a flag will display a flag graphic over them, as an indication. We will likely implement an abstract class Tile, with subclasses for bomb tiles, number tiles, blank tiles, and possibly revealed tiles. Like the windows xp version of Minesweeper, the top of the screen will display bombs remaining, a smiley face, and a timer. The face will change expression smiling while no action is occuring, a surprised :o face while clicking a tile, a dead face if the game is lost, and a face with sunglasses should the game be won. Clicking the smiley face will reset the game and timer. The timer will count upwards each second from 0, up to 999. The bombs remaining will begin a game displaying the number of bombs on the board, and will decrement with each tile you flag or increment with each you unflag. We will track high scores for times in separate categories for each difficulty (easy/medium/hard). 

Below is a rough schedule with some major milestones in order to complete this final project on time:

               Thursday April 16th: 4:00 - 6:00: Start Fuit Ninja. Get "fruit" (may just use balls as a place holder 
               for now) to fall properly on screen, with the three different game levels. Get sword functionality 
               working as well.
               
               Tuesday April 21th: 4:00-6:00:  Add bombs, a counter for score, and the 3 different strikes on screen.
               
               Wednesday April 22th: 3:30-5:30: Clean up anything. Hopefully by now the game should 
               have full functionality. Fix what isn't workly properly.
               
               Thursday April 23th: 4:00-6:00: Make the game look pretty. Add option for different backgrounds and 
               swords. 
               
               Tuesday April 28th: 4:00- 6:00: Add sound effects to the game. Add any enhacements we think of.
               
               Wednesday April 29th: 3:30-5:30: Clean up the game and completely finish it. Possibly start 
               Minesweeper.
               
               Thursday April 30th: 4:00-6:00: Begin Minesweeper. Create a display with placeholders for each 
               of the fourscreen elements: the bombs remaining, the smiley face, the timer, and the minefield. 
               Create the abstract tile class and begin creating its subclasses.
               
               Tuesday May 1st: 4:00-6:00: Implement code that tells non bomb tiles to count the amount of adjacent bombs,
               so that they can display the correct numbers. Add ability for a left click to reveal what is underneath a 
               tile, and right click to flag a tile. 
               
               Wednesday May 2nd: 3:30-5:30: Make left clicking a blank tile reveal each of its adjacent tiles (This 
               should be easy recursively, since all tiles adjacent to a blank tile must be safe numbers or another blank).
               Implement a game over/game won scenario, including revealing all bombs on the board. Add functionality to the 
               bomb counter and timers, and smiley face reset button.
               
               Thursday May 4th: 4:00-6:00: Add final touches, including graphics for the bombs, flags, and smiley face.
               
               **Sundays are also open to work on any unfinished tasks**
               
               
**300 Point Breakdown:**


 
 | Feature                                 | Value         |
 | -------------                           | ------------- |
 | Nice graphical layout for Fruit Ninja   | 20            |
 | Fruit Falling                           | 5             |
 | Sword Functionality                     | 15            |
 | Customization of backgrounds            | 8             |
 | Variety of Fruit images                 | 5             |
 | Randomness of different Fruits          | 4             |
 | Option for 3 different levels           | 5             |
 | Level Difficulty                        | 10            |
 | Customization of backgrounds            | 8             |
 | Customization of swords                 | 10            |
 | Bomb functionality ends game            | 4             |
 | Display and Functionality of 3 strikes  | 8             |
 | Start/Restart/Play Again button         | 4             |
 | High Scores List                        | 10            |
 | 3 Fruit slice bonus                     | 10            |
 | Sound Effects                           | 20            |
 | **Total for Fruit Ninja**               | **150**       |
 
 | Feature                                 | Value         |
 | -------------                           | ------------- |
 | Nice panel layout for Minesweeper       | 20            |
 | Creating a random game                  | 30            |
 | Fastest solving time highscore list     | 10            |
 | Implementing a right vs left mouse click| 10            |
 | High Scores List per difficulty         | 10            |
 | Sound Effects                           | 20            |
 | Game ending scenarios (loss/victory)    | 5             |
 | Start/Restart (Smiley) button           | 5             |
 | Smiley button graphic updating          | 10            |
 | Bomb explosion animations               | 10            |
 | Drawing Flag/Tile graphics              | 5             |
 | Constantly updating timer               | 5             |
 | Remaining bombs tracker                 | 5             |
 | Option for different game sizes         | 5             |
 | **Total for Minesweeper**               | **150**       |
           

                       
