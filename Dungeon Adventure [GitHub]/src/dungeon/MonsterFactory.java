package dungeon;

import java.util.Random;

public class MonsterFactory 
{
	public enum MstrTypes
	{
		Gremlin, Ogre, Skeleton, Dragon, Wolf;
	}
	
	public Monster createMonster(MstrTypes type, String name, String gender, String picPath)
	{
		switch(type)
		{
			case Gremlin: 
				return new Gremlin(name, gender, picPath);

			case Ogre: 
				return new Ogre(name, gender, picPath);

			case Skeleton: 
				return new Skeleton(name, gender, picPath);

			case Dragon: 
				return new Dragon(name, gender, picPath);

			case Wolf: 
				return new Wolf(name, gender, picPath);

			default: 
				return new Gremlin(name, gender, picPath);
		}
	}
	
	public Monster randomMonster()
	{
		Random numGenerator = new Random();
		int selection = numGenerator.nextInt(5);
		
		switch(selection)
		{
			case 0:
				return new Gremlin(gremNameGen(), getRandomGender(), "/dungeon/Images/Monsters/Gremlin.png");
				
			case 1:
				return new Ogre(ogreNameGen(), getRandomGender(), "/dungeon/Images/Monsters/Ogre.png");
				
			case 2:
				return new Skeleton(skelNameGen(), getRandomGender(), "/dungeon/Images/Monsters/Skeleton.png");
				
			case 3:
				return new Dragon(dragonNameGen(), getRandomGender(), "/dungeon/Images/Monsters/Dragon.png");
				
			case 4:
				return new Wolf(wolfNameGen(), getRandomGender(), "/dungeon/Images/Monsters/Wolf.png");
				
			default:
				return new Ogre(ogreNameGen(), getRandomGender(), "/dungeon/Images/Monsters/Ogre.png");
		}
	}
	
	private String getRandomGender()
	{
		Random numGen = new Random();
		int diceRoll = numGen.nextInt(2);
		
		if(diceRoll == 0)
			return "Male";
		
		else
			return "Female";
	}
	
	private String gremNameGen()
	{
		// Got names from: https://www.fantasynamegenerators.com/ogre-names.php
		String[] names = new String[10];
		Random numGen = new Random();
		int diceRoll = numGen.nextInt(9);		
		names[0] = "Zozar";
		names[1] = "Eezur";
		names[2] = "Zigrot";
		names[3] = "Nugut";
		names[4] = "Urek";
		names[5] = "Akor";
		names[6] = "Brokeg";
		names[7] = "Yazir";
		names[8] = "Glenk";
		names[9] = "Vurok";
				
		return names[diceRoll];
	}
	
	private String ogreNameGen()
	{
		// Got names from: https://www.fantasynamegenerators.com/ogre-names.php
		String[] names = new String[10];
		Random numGen = new Random();
		int diceRoll = numGen.nextInt(9);		
		names[0] = "Shrek";
		names[1] = "Azar";
		names[2] = "Tukag";
		names[3] = "Breakurk";
		names[4] = "Ezog";
		names[5] = "Tagark";
		names[6] = "Kregrok";
		names[7] = "Gerog";
		names[8] = "Yirog";
		names[9] = "Ugrok";
				
		return names[diceRoll];
	}
	
	private String skelNameGen()
	{
		// Got some names from: https://www.bungie.net/en-US/Forums/Post/3486040
		String[] names = new String[10];
		Random numGen = new Random();
		int diceRoll = numGen.nextInt(9);		
		names[0] = "Sans";
		names[1] = "Papyrus";
		names[2] = "Gaster";
		names[3] = "McRibs";
		names[4] = "Cal C. Iam";
		names[5] = "Skeletor";
		names[6] = "Jack Marrow";
		names[7] = "Jack Skellington";
		names[8] = "Brook";
		names[9] = "Grim";
				
		return names[diceRoll];
	}
	
	private String dragonNameGen()
	{
		// Got some names from: Skyrim & http://www.fantasynamegenerators.com/dragon_names.php
		String[] names = new String[10];
		Random r = new Random();
		int diceRoll = r.nextInt(9);		
		names[0] = "Vulthuryol";
		names[1] = "Spike";
		names[2] = "Alduin";
		names[3] = "Paarthurnax";
		names[4] = "Sahrotaar";
		names[5] = "Nahagliiv";
		names[6] = "Durnehviir";
		names[7] = "Zarvait";
		names[8] = "Mylreoth";
		names[9] = "Giandal";

		return names[diceRoll];
	}
	
	private String wolfNameGen()
	{
		// Got names from: http://www.fantasynamegenerators.com/gargoyle-names.php
		String[] names = new String[10];
		Random r = new Random();
		int diceRoll = r.nextInt(9);		
		names[0] = "Zonnor";
		names[1] = "Inas";
		names[2] = "Koze";
		names[3] = "Senol";
		names[4] = "Sabenas";
		names[5] = "Kevu";
		names[6] = "Vongel";
		names[7] = "Strovus";
		names[8] = "Najoba";
		names[9] = "Lauden";

		return names[diceRoll];
	}
}
