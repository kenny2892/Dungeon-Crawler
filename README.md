# Dungeon Crawler
![Menu](https://raw.githubusercontent.com/kenny2892/Dungeon-Crawler/master/Screenshots/Main%20Menu.png)

This was my final project in my Design Patterns class in which we learned about different patterns and principles to use when writing a program. I got 220 out of 200 on it (I did some of the extra credit for the project).
It is an alternate version of my previous game [Kamiroth](https://github.com/kenny2892/Kamiroth-Console-Edition), which was a project from a previous class.
The primary goal of the project was to implement many of the principles and patterns in a realistic way.

The game is pretty simple. Once you create your character you will be placed in a 5 x 5 grid of rooms. One room contains the Exit, but you cannot open the door until you have found the 4 Pillars of OO. These Pillars can each be found in one of the 25 rooms.
Each room can have a Healing Potion, Vision Potion, Pitfall, a Pillar, or a Monster.
If the room has a pillar or a monster, that will be the only thing in the room. If not, then it can have any combination of the previously mentioned items.
On Hardcore mode, the player will not heal after a fight (this means you have to solely rely on HP potions, which are rare).

Some of the patterns I implemented were:
  - Flyweight: I used an Attack Pool for all the different Attacks.
  - Simple Factory: I used different Factories for Heroes, Monsters, Attacks, and Items.
  - Model View Controller: I had a Controller class act as the middle man between the Model (DungeonAdventure class) and the View (View class).
  - Strategy: Used to implement all of the Attacks.
  - And various others.
 
I also added in Developer tools that can be accessed by following these steps:
  - Once the game has started (you've created your character and are in the maze), click the upper left hand corner of the background.
  - Then type in the Konami Code (Arrow Up, Arrow Up, Arrow Down, Arrow Down, Arrow Left, Arrow Right, Arrow Left, Arrow Right, S, T, A, R, T)
  - If this doesn't work, try clicking the left corner a few more times (There is an invisible button there and it has to be in-focus to type in the code).
  - If at any point you mess up or want to turn off cheats, click the upper right hand corner of the background.

Here are some Screenshots from the game:

Character Creation:
![Creation](https://raw.githubusercontent.com/kenny2892/Dungeon-Crawler/master/Screenshots/Character%20Creation.png)

Navigating through the Maze:
![Navgation](https://raw.githubusercontent.com/kenny2892/Dungeon-Crawler/master/Screenshots/Navigation.png)

Fighting a Monster:
![Fight](https://raw.githubusercontent.com/kenny2892/Dungeon-Crawler/master/Screenshots/Fighting.png)

Using your Inventory:
![Inv](https://raw.githubusercontent.com/kenny2892/Dungeon-Crawler/master/Screenshots/Inventory.png)

Finding a Pillar:
![Pillar](https://raw.githubusercontent.com/kenny2892/Dungeon-Crawler/master/Screenshots/Pillar.png)

Developer Cheats:
![Cheat](https://github.com/kenny2892/Dungeon-Crawler/blob/master/Screenshots/Dev%20Tools.png)
  
I do not own many of the assets in this (especially the background and any of the character portraits) and if one of them is yours, then I'd be happy to remove it.
