package dungeon;

import dungeon.AttackFactory.AttackType;

public class GameMaster extends Hero
{
    public GameMaster(String name, String gender, String picPath)
	{
		super("Game Master", name, gender, 1000, 6, .99, 100, 200, .9, picPath);
		
    	AttackFactory makeAtks = new AttackFactory();
    	super.setStandardAtk(makeAtks.getAttack(AttackType.Snap));
    	super.setSpecialAtk(makeAtks.getAttack(AttackType.OP));
    }

	@Override
	public void createNormalLines() 
	{
		super.addNormalLine(this.getName() + " snapped your fingers and lightning struck the beast!");
		super.addNormalLine(this.getName() + " snapped and the monster doesn't feel so good.");
		super.addNormalLine(this.getName() + " shot lasers out of your eyes towards the beast!");
		super.addNormalLine(this.getName() + " glanced at the beast, only for an anvil to fall from the sky and land on the monster!");
		super.addNormalLine(this.getName() + " summoned an army of kittens to fight the beast for you!");
		super.addNormalLine(this.getName() + " sneezed and summoned a tornado!");
	}

	@Override
	public void createSpecialLines() 
	{
		super.addSpecialLine(this.getName() + " got tired of fighting and ended the fight!");
		super.addSpecialLine(this.getName() + " rolled thier eyes and the monster exploded!");
		super.addSpecialLine(this.getName() + " sped up time until the monster had died of old age!");
	}
}