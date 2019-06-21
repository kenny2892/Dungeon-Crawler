package dungeon;

import dungeon.AttackFactory.AttackType;

public class Knight extends Hero
{
    public Knight(String name, String gender, String picPath)
	{
		super("Knight", name, gender, 200, 1, .7, 20, 30, .7, picPath);
		
    	AttackFactory makeAtks = new AttackFactory();
    	super.setStandardAtk(makeAtks.getAttack(AttackType.Standard));
    	super.setSpecialAtk(makeAtks.getAttack(AttackType.Bash));
    }

	@Override
	public void createNormalLines() 
	{
		super.addNormalLine(this.getName() + " jabbed at the monster!");
		super.addNormalLine(this.getName() + " lunged towards the monster!");
		super.addNormalLine(this.getName() + " fought the monster!");
		super.addNormalLine(this.getName() + " jabbed at the monster!");
		super.addNormalLine(this.getName() + " sliced at the monster!");
		super.addNormalLine(this.getName() + " waited for your moment to strike, and then attacked the monster!");
		super.addNormalLine(this.getName() + " dashed towards the beast, pushed through their defences and landed a slash with your sword!");
		super.addNormalLine(this.getName() + " saw an oppening in the beast's defences and struck!");
		super.addNormalLine("The monster got distracted by a sound out in the distance. " + this.getName() + "took this moment of confusion to deal a painful blow!");
		super.addNormalLine(this.getName() + " closed the distance between you and the monster and took a swing at its head!");
	}

	@Override
	public void createSpecialLines() 
	{
		super.addSpecialLine(this.getName() + " delivered a crushing bash to the enemy's head!");
		super.addSpecialLine(this.getName() + " lunged at the beast and slashed at their with lightning speed!");
		super.addSpecialLine(this.getName() + " charged at the monster shield first, knocking it over and allowing you to deal a devastating blow!");
		super.addSpecialLine(this.getName() + " kicked up some dirt to blind the beast, and then swung your shield at its chest!");
		super.addSpecialLine(this.getName() + " ran forward, smacked the beasts arms away, and swung at their face!");
	}
}