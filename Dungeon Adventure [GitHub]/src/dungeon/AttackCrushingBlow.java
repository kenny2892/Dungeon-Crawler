package dungeon;

import java.util.Random;

public class AttackCrushingBlow implements Attack
{
	@Override
	public String attack(DungeonCharacter player, DungeonCharacter enemy) 
	{
		String result = null;
		
		Random numGen = new Random();
		int diceRoll = numGen.nextInt(101);
		
		int dmgMin = 75, dmgMax = 175, hitChance = 40;
		
		if(diceRoll <= hitChance)
		{
			int dmg = numGen.nextInt(dmgMax - dmgMin + 1) + dmgMin; 
			enemy.setHp(enemy.getHp() - dmg);
			String line = player.getRandomSpecialLine();
			result = line + " -" + dmg + " hp to " + enemy.getName();
		}
		
		else if(diceRoll > hitChance)
		{
			result = player.getName() + "'s Crushing Blow on " + enemy.getName() + " failed!";
		}
		
		return result;
	}
	
}
