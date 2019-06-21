package dungeon;

import dungeon.AttackFactory.AttackType;

public class Skeleton extends Monster
{
    public Skeleton(String name, String gender, String picPath)
	{
		super("Skeleton", name, gender, 100, 3, .8, .3, 30, 50, 30, 50, picPath);
		
    	AttackFactory makeAtks = new AttackFactory();
    	super.setStandardAtk(makeAtks.getAttack(AttackType.MonsterStandard));
    	super.setSpecialAtk(makeAtks.getAttack(AttackType.MonsterSpecial));
    }

	@Override
	public void createNormalLines() 
	{
		super.addNormalLine(this.getName() + " swung a sword at your ribs!");
	}

	@Override
	public void createSpecialLines() 
	{
		super.addSpecialLine(this.getName() + " ripped off its arm and smacked you across the face with it!");
	}
}