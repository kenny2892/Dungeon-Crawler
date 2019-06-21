package dungeon;

import java.util.Random;

public abstract class Monster extends DungeonCharacter
{
	private int chanceToHeal;
	private int minHeal, maxHeal;

	public Monster(String type, String name, String gender, int hitPoints, int attackSpeed, double chanceToHit, double chanceToHeal, int damageMin, int damageMax, int minHeal, int maxHeal, String picPath)
	{
		super(type, name, gender, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax, picPath);
		this.chanceToHeal = (int) (chanceToHeal * 100);
		this.maxHeal = maxHeal;
		this.minHeal = minHeal;
	}
	
	public String regenHealth() 
	{
		String result = null;

		Random numGen = new Random();
		int diceRoll = numGen.nextInt(101);

		if(diceRoll <= this.chanceToHeal)
		{
			int heal = numGen.nextInt(this.maxHeal - this.minHeal + 1) + this.minHeal;
			int newHp = this.getHp() + heal;

			if(newHp > this.getTotalHp())
			{
				this.setHp(this.getTotalHp());
				result = "\n" + this.getName() + " regained all of its hp back after the fight!\n";
			}

			else
			{
				this.setHp(newHp);
				result = "\n" + this.getName() + " regained " + heal + " hp back after the fight!\n";
			}			
		}

		else if(diceRoll > this.chanceToHeal)
			result = "\n" + this.getName() + " didn't gain any health back.\n";

		return result;
	}

	public double getChanceToHeal() 
	{
		return chanceToHeal;
	}

	public int getMinHeal() 
	{
		return minHeal;
	}

	public int getMaxHeal() 
	{
		return maxHeal;
	}
}