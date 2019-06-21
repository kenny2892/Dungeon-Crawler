package dungeon;

import dungeon.AttackFactory.AttackType;

public class Thief extends Hero
{
    public Thief(String name, String gender, String picPath)
	{
		super("Thief", name, gender, 75, 6, .8, 20, 40, .5, picPath);

    	AttackFactory makeAtks = new AttackFactory();
    	super.setStandardAtk(makeAtks.getAttack(AttackType.Standard));
    	super.setSpecialAtk(makeAtks.getAttack(AttackType.Suprise));
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
		super.addNormalLine("The monster got distracted by a sound out in the distance. " + this.getName() + " took this moment of confusion to deal a painful blow!");
		super.addNormalLine(this.getName() + " closed the distance between you and the monster and took a swing at its head!");
	}

	@Override
	public void createSpecialLines() 
	{
		super.addSpecialLine(this.getName() + " snuck up on the monster and went for the neck!");
		super.addSpecialLine(this.getName() + " crawled behind the beast and cut its ankles!");
		super.addSpecialLine(this.getName() + " crept in the shadow of the monster and stabbed it in the back!");
		super.addSpecialLine(this.getName() + " evaded the beast's line of sight and got a sneak attack in!");
		super.addSpecialLine(this.getName() + " hid in a nearby bush and ambushed the beast when it passed by!");
	}
}