package dungeon;

import dungeon.AttackFactory.AttackType;

public class Sorceress extends Hero
{
    public Sorceress(String name, String gender, String picPath)
	{
		super("Sorceress", name, gender, 75, 5, .7, 25, 50, .3, picPath);

    	AttackFactory makeAtks = new AttackFactory();
    	super.setStandardAtk(makeAtks.getAttack(AttackType.Standard));
    	super.setSpecialAtk(makeAtks.getAttack(AttackType.HealSelf));
    }

	@Override
	public void createNormalLines() 
	{
		super.addNormalLine(this.getName() + " jabbed at the monster with a dagger!");
		super.addNormalLine(this.getName() + " lunged towards the monster!");
		super.addNormalLine(this.getName() + " launched lightning at the beast!");
		super.addNormalLine(this.getName() + " shot fireballs at the beast!");
		super.addNormalLine(this.getName() + " sliced at the monster's ankles!");
	}

	@Override
	public void createSpecialLines() 
	{
		super.addSpecialLine(this.getName() + " used a heal spell!");
	}
}