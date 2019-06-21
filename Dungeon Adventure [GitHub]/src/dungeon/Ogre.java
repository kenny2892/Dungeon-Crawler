package dungeon;

import dungeon.AttackFactory.AttackType;

public class Ogre extends Monster
{
    public Ogre(String name, String gender, String picPath)
	{
		super("Ogre", name, gender, 200, 2, .6, .1, 30, 50, 30, 50, picPath);
		
    	AttackFactory makeAtks = new AttackFactory();
    	super.setStandardAtk(makeAtks.getAttack(AttackType.MonsterStandard));
    	super.setSpecialAtk(makeAtks.getAttack(AttackType.MonsterSpecial));
    }

	@Override
	public void createNormalLines() 
	{
		super.addNormalLine(this.getName() + " swung a punch at you!");
	}

	@Override
	public void createSpecialLines() 
	{
		super.addSpecialLine(this.getName() + " slammed a club into you!");
	}
}