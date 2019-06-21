package dungeon;

import java.util.Random;

public class AttackOP implements Attack
{
	@Override
	public String attack(DungeonCharacter player, DungeonCharacter enemy) 
	{
		String result = null;
		
		Random numGen = new Random();
		
		int dmgMin = 999, dmgMax = 1000;
		
		int dmg = numGen.nextInt(dmgMax - dmgMin + 1) + dmgMin; 
		enemy.setHp(enemy.getHp() - dmg);
		String line = player.getRandomSpecialLine();
		result = line + " -" + dmg + " hp to " + enemy.getName();
		
		return result;
	}
}
