package dungeon;
import java.util.ArrayList;

public abstract class Hero extends DungeonCharacter
{	
	private int chanceToBlock;
	private int numTurns;
	private int healPotNum;
	private int visPotNum;
	private int pillarsNum;
	private int[] coords;
	private ArrayList<Item> inventory;

	public Hero(String type, String name, String gender, int hitPoints, int attackSpeed, double chanceToHit, int damageMin, int damageMax, double chanceToBlock, String picPath)
	{
		super(type, name, gender, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax, picPath);
		this.chanceToBlock = (int) (chanceToBlock * 100);
		this.healPotNum = 0;
		this.visPotNum = 0;
		this.pillarsNum = 0;
		this.coords = new int[2];
		this.inventory = new ArrayList<Item>();
	}
	
	public void hurtHero(int dmg)
	{
		if(dmg < 0)
			return;
		
		this.setHp(this.getHp() - dmg);
	}

	public int getHealPotNum() 
	{
		return healPotNum;
	}

	public void setHealPotNum(int healPotNum) 
	{
		if(healPotNum >= 0)
			this.healPotNum = healPotNum;
	}

	public int getVisPotNum() 
	{
		return visPotNum;
	}

	public void setVisPotNum(int visPotNum) 
	{
		if(visPotNum >= 0)
			this.visPotNum = visPotNum;
	}

	public int getPillarsNum() 
	{
		return pillarsNum;
	}

	public void setPillarsNum(int pillarsNum) 
	{
		if(pillarsNum >= 0)
			this.pillarsNum = pillarsNum;
	}
	
	public double getChanceToBlock()
	{
		return this.chanceToBlock;
	}
	
	public int getNumTurns()
	{
		return numTurns;
	}
	
	public int calculateTurns(DungeonCharacter enemy)
	{
		int speed = this.getAttackSpeed() / enemy.getAttackSpeed();
		
		if(speed <= 0)
			speed = 1;
		
		return speed;
	}

	public int[] getCoordinates() 
	{
		return coords;
	}

	public void setCoordinates(int x, int y) 
	{
		if(x < 0 || y < 0)
			return;
		
		this.coords[0] = x;
		this.coords[1] = y;
	}
	
	public void addToInv(Item item)
	{
		if(item != null)
			this.inventory.add(item);
	}
	
	public ArrayList<Item> getInv()
	{
		return inventory;
	}
	
	@Override
	public String toString()
	{
		return this.getName() + ":\nHp:  " + this.getHp() + "\nHealing Potions: " + this.getHealPotNum() + "\nVision Potions: " + this.getVisPotNum() + "\nPillars Found: " + this.getPillarsNum();
	}
}