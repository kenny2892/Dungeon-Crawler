package dungeon;

import java.util.Random;

public class AttackMnstrStandard implements Attack
{
	@Override
	public String attack(DungeonCharacter player, DungeonCharacter enemy) 
	{
		Hero target = null;
		Monster mnstr = null;
		
		if(enemy instanceof Hero && player instanceof Monster)
		{
			target = (Hero) enemy;
			mnstr = (Monster) player;
		}
		
		else
			return "The target is not a Hero or the User isn't a Monster";
		
		String result = null;
		
		Random numGen = new Random();
		int diceRoll = numGen.nextInt(101);
		
		if(diceRoll > target.getChanceToBlock())
		{
			int dmg = numGen.nextInt(target.getDmgMax() - target.getDmgMin() + 1) + target.getDmgMin();
			
			target.setHp(target.getHp() - dmg);
			result = mnstr.getName() + " jabs their kris at " + target.getName() + " -" + dmg + " Hp\n";
		}
		
		else
			result = target.getName() + " successfully blocked " + mnstr.getName() + "'s attack!";
				
		return result;
	}
}
