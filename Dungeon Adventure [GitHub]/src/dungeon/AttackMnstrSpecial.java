package dungeon;

import java.util.Random;

public class AttackMnstrSpecial implements Attack
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
			int minDmg = 20, maxDmg = 40;
			int dmg = numGen.nextInt(maxDmg - minDmg + 1) + minDmg;
			
			target.setHp(target.getHp() - dmg);
			result = mnstr.getName() + " successfuly landed a special attack!";
			result = result + "\n" + mnstr.getName() + " slashed at your face! -" + dmg + " hp\n";
		}
		
		else
			result = target.getName() + " successfully blocked " + mnstr.getName() + "'s attack!";
				
		return result;
	}
}
