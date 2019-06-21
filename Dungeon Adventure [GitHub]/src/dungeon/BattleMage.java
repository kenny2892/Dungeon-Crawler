package dungeon;

import dungeon.AttackFactory.AttackType;

public class BattleMage extends Hero
{
    public BattleMage(String name, String gender, String picPath)
	{
		super("Battle Mage", name, gender, 95, 3, .7, 50, 70, .2, picPath);
		
    	AttackFactory makeAtks = new AttackFactory();
    	super.setStandardAtk(makeAtks.getAttack(AttackType.Standard));
    	super.setSpecialAtk(makeAtks.getAttack(AttackType.Spell));
    }

	@Override
	public void createNormalLines() 
	{
		super.addNormalLine(this.getName() + " shot lightning at the beast!");
		super.addNormalLine(this.getName() + " launched fireballs at the monster!");
		super.addNormalLine(this.getName() + " used a frost breath attack!");
		super.addNormalLine(this.getName() + " summoned a cloud of poison to engulf your enemy!");
		super.addNormalLine(this.getName() + " blasted the monster away with water!");
	}

	@Override
	public void createSpecialLines() 
	{
		super.addSpecialLine(this.getName() + " summoned a familiar to fight the beast!");
		super.addSpecialLine(this.getName() + " made it rain hell fire from the sky!");
		super.addSpecialLine(this.getName() + " launched a massive beam of magic directly into the monster!");
		super.addSpecialLine(this.getName() + " created a mini blizzard and sent it towards the beast!");
		super.addSpecialLine(this.getName() + " generated a storm cloud right over the monsters head!");
	}
}