package dungeon;

public class HeroFactory 
{
	public enum HeroType
	{
		Warrior, Sorceress, Thief, BattleMage, Knight, GameMaster;
	}
	
	public Hero createHero(HeroType type, String name, String gender)
	{
		switch(type)
		{
			case Warrior: 
				return new Warrior(name, gender, "/dungeon/Images/Characters/Warrior" + gender.charAt(0) + ".png");

			case Sorceress: 
				return new Sorceress(name, gender, "/dungeon/Images/Characters/Sorceress" + gender.charAt(0) + ".png");

			case Thief: 
				return new Thief(name, gender, "/dungeon/Images/Characters/Thief" + gender.charAt(0) + ".png");

			case BattleMage: 
				return new BattleMage(name, gender, "/dungeon/Images/Characters/BattleMage" + gender.charAt(0) + ".png");

			case Knight: 
				return new Knight(name, gender, "/dungeon/Images/Characters/Knight" + gender.charAt(0) + ".png");

			case GameMaster: 
				return new GameMaster(name, gender, "/dungeon/Images/Characters/GameMaster.png");

			default: 
				System.out.println("Invalid choice, returning Thief");
				return null;
		}
	}
}
