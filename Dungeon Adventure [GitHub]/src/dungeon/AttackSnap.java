package dungeon;

import java.util.Random;

public class AttackSnap implements Attack
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
			String line = player.getRandomSpecialLine();
			result = line + " -" + dmg + " hp to " + enemy.getName();
		}
		
		else if(diceRoll > player.getChanceToHit())
		{
			result = player.getName() + "'s Snap " + enemy.getName() + " failed!";
		}
		
		return result;
	}
}
