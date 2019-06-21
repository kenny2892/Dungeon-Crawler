package dungeon;

import java.util.Random;

public class AttackSuprise implements Attack
{
	private Attack standardAttack;
	
	public AttackSuprise(Attack standardAttack) 
	{
		this.standardAttack = standardAttack;
	}
	
	@Override
	public String attack(DungeonCharacter player, DungeonCharacter enemy) 
	{
		String result = null;
		
		int dmgMin = 30, dmgMax = 60;
		
		Random numGen = new Random();
		int diceRoll = numGen.nextInt(101);
		
		if(diceRoll <= 80 && diceRoll > player.getChanceToHit())
		{
			int dmg = numGen.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			enemy.setHp(enemy.getHp() - dmg);
			result = "Sneak Attack Successful!";
			String line = player.getRandomSpecialLine();
			result += "\n" + line + " -" + dmg + " hp to " + enemy.getName();	

			result += "\n" + standardAttack.attack(player, enemy);
		}
		
		else if(diceRoll <= player.getChanceToHit())
		{
			result = "You were unable to peform a sneak attack, but may be able to get in a normal attack.\n";
			result += standardAttack.attack(player, enemy);
		}
		
		else
			result = "You were caught before you could attack.";
		
		return result;
	}
	
}
