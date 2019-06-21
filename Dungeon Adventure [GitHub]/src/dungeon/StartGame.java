// Jesse Ross
// Started June 7, 2019
// Finished June 11, 2019
// CSCD 349 - Final Project

// The cheat code (Dev Tool) for the game is as follows:
// Once the game has started (you've created your character and are in the maze), click the upper left hand corner of the background.
// Then type in the Konami Code (Arrow Up, Arrow Up, Arrow Down, Arrow Down, Arrow Left, Arrow Right, Arrow Left, Arrow Right, S, T, A, R, T)
// If this doesn't work, try clicking the left corner a few more times (There is an invisible button there and it has to be in-focus to type in the code).
// If at any point you mess up or want to turn off cheats, click the upper right hand corner of the background.

// Extra Credits Attempted:
//			- Attack Interface
// 			- Add 2 heroes and 2 monsters
//			- Implement Vision Potion
package dungeon;

public class StartGame 
{
	public static void main(String[] args)
	{
		DungeonAdventure model = new DungeonAdventure();
		View view = new View();
		Controller contrl = new Controller(model, view);
		
		contrl.start();
	}
}
