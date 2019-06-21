package dungeon;

import java.util.Random;

public class AttackBash implements Attack
{
	@Override
	public String attack(DungeonCharacter player, DungeonCharacter enemy) 
	{
		String result = null;
		
		Random numGen = new Random();
		int diceRoll = numGen.nextInt(101);
		
		int dmgMin = 40, dmgMax = 75, hitChance = 50;
		
		if(diceRoll <= hitChance)
		{
			int dmg = numGen.nextInt(dmgMax - dmgMin + 1) + dmgMin; 
			enemy.setHp(enemy.getHp() - dmg);
			String line = player.getRandomSpecialLine();
			result = line + " -" + dmg + " hp to " + enemy.getName();
		}
		
		else if(diceRoll > hitChance)
		{
			result = player.getName() + "'s Bashed " + enemy.getName() + " failed!";
		}
		
		return result;
	}
}
