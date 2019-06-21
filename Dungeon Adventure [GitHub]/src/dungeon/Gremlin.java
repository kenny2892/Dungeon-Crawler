package dungeon;

import dungeon.AttackFactory.AttackType;

public class Gremlin extends Monster
{
    public Gremlin(String name, String gender, String picPath)
	{
		super("Gremlin", name, gender, 70, 5, .8, .4, 15, 30, 20, 40, picPath);
		
    	AttackFactory makeAtks = new AttackFactory();
    	super.setStandardAtk(makeAtks.getAttack(AttackType.MonsterStandard));
    	super.setSpecialAtk(makeAtks.getAttack(AttackType.MonsterSpecial));
    }

	@Override
	public void createNormalLines() 
	{
		super.addNormalLine(this.getName() + " jumped up and attacked!");
	}

	@Override
	public void createSpecialLines() 
	{
		super.addSpecialLine(this.getName() + " slashed at your face!");
	}
}