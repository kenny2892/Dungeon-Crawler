package dungeon;

import dungeon.AttackFactory.AttackType;

public class Dragon extends Monster
{
    public Dragon(String name, String gender, String picPath)
	{
		super("Dragon", name, gender, 400, 2, .8, .1, 50, 100, 30, 100, picPath);
		
    	AttackFactory makeAtks = new AttackFactory();
    	super.setStandardAtk(makeAtks.getAttack(AttackType.MonsterStandard));
    	super.setSpecialAtk(makeAtks.getAttack(AttackType.MonsterAdvanced));
    }

	@Override
	public void createNormalLines() 
	{
		super.addNormalLine(this.getName() + " blasted you with fire!");
		super.addNormalLine(this.getName() + " launched a frost breath at you!");
		super.addNormalLine(this.getName() + " shot lightning out of its mouth!");
	}

	@Override
	public void createSpecialLines() 
	{
		super.addSpecialLine(this.getName() + " launched a barrage of wind at you!");
		super.addSpecialLine(this.getName() + " rained meteors from the sky!");
	}
}