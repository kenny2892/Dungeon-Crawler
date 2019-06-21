package dungeon;

import java.util.Random;

public class AttackStandard implements Attack
{
	@Override
	public String attack(DungeonCharacter player, DungeonCharacter enemy)
	{
		String result = null;
		
		Random numGen = new Random();
		int diceRoll = numGen.nextInt(101);
		
		if(diceRoll <= player.getChanceToHit())
		{
			int dmg = numGen.nextInt(player.getDmgMax() - player.getDmgMin() + 1) + player.getDmgMin();
			enemy.setHp(enemy.getHp() - dmg);
			String line = player.getRandomNormalLine();
			result = line + " -" + dmg + " hp to " + enemy.getName();
		}
		
		else
			result = player.getName() + "'s attack on " + enemy.getName() + " failed!";
		
		return result;
	}
}
