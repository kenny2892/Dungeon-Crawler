package dungeon;

import dungeon.AttackFactory.AttackType;

public class Warrior extends Hero
{
    public Warrior(String name, String gender, String picPath)
	{
		super("Warrior", name, gender, 125, 4, .8, 35, 60, .2, picPath);
		
    	AttackFactory makeAtks = new AttackFactory();
    	super.setStandardAtk(makeAtks.getAttack(AttackType.Standard));
    	super.setSpecialAtk(makeAtks.getAttack(AttackType.CrushingBlow));
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
		super.addSpecialLine(this.getName() + " delivered a crushing blow to the enemy's head!");
		super.addSpecialLine(this.getName() + " lunged at the beast and slashed at their with lightning speed!");
		super.addSpecialLine(this.getName() + " delivered a crushing blow to the enemy's head!");
		super.addSpecialLine(this.getName() + " kicked up some dirt to blind the beast, and then swung your sword at its chest!");
		super.addSpecialLine(this.getName() + " charged at the monster and released a flury of attacks!");
	}
}