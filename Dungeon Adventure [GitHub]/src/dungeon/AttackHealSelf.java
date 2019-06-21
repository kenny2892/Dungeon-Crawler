package dungeon;

import java.util.Random;

public class AttackHealSelf implements Attack
{
	@Override
	public String attack(DungeonCharacter player, DungeonCharacter enemy) 
	{
		String result = null;
		
		Random numGen = new Random();
		int diceRoll = numGen.nextInt(101);

		int dmgMin = 25, dmgMax = 50, hitChance = 40;

		if(diceRoll <= hitChance)
		{
			int hpToRestore = numGen.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			int newHp = player.getHp() + hpToRestore;
			
			if(newHp > player.getTotalHp())
			{
				player.setHp(player.getTotalHp());
				result = player.getName() + " healed all their Hp!";
			}
			
			else
			{
				player.setHp(newHp);
				result = player.getName() + " restored " + newHp + " Hp!";
			}
		}

		else
			result = player.getName() + "'s heal spell failed.";
		
		return result;
	}
}
