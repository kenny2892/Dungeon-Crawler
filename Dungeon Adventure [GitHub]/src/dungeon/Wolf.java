package dungeon;

import dungeon.AttackFactory.AttackType;

public class Wolf extends Monster
{
    public Wolf(String name, String gender, String picPath)
	{
		super("Wolf", name, gender, 80, 4, .6, .5, 30, 50, 30, 50, picPath);
		
    	AttackFactory makeAtks = new AttackFactory();
    	super.setStandardAtk(makeAtks.getAttack(AttackType.MonsterStandard));
    	super.setSpecialAtk(makeAtks.getAttack(AttackType.MonsterSpecial));
    }

	@Override
	public void createNormalLines() 
	{
		super.addNormalLine(this.getName() + " pounced at you!");
	}

	@Override
	public void createSpecialLines() 
	{
		super.addSpecialLine(this.getName() + " bit into you!");
	}
}