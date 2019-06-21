package dungeon;

import java.util.ArrayList;
import java.util.Random;

public abstract class DungeonCharacter implements Comparable<DungeonCharacter> // Rubric said to call it DungeonCharacter, but Canvas said GameCharacter.
{																			   // So I left it as DungeonCharacter
	private String name;
	private String gender;
	private String type;
	private String picPath = "";
	private ArrayList<String> atkLines;
	private ArrayList<String> specialLines;
	private int hp;
	private int totalHp;
	private int attackSpeed;
	private int dmgMin, dmgMax;
	private int chanceToHit;
	private Attack standardAtk, specialAtk;

	public DungeonCharacter(String type, String name, String gender, int hp, int attackSpeed, double chanceToHit, int dmgMin, int dmgMax, String picPath)
	{
		this.name = name;
		this.gender = gender;
		this.type = type;
		this.atkLines = new ArrayList<String>();
		this.specialLines = new ArrayList<String>();
		this.hp = hp;
		this.totalHp = hp;
		this.attackSpeed = attackSpeed;
		this.chanceToHit = (int) (chanceToHit * 100);
		this.dmgMin = dmgMin;
		this.dmgMax = dmgMax;
		this.picPath = picPath;
		
		createNormalLines();
		createSpecialLines();
	}
	
    public boolean isAlive()
	{
	  return (hp > 0);
	}
    
    protected void setStandardAtk(Attack standard)
    {
    	this.standardAtk = standard;
    }
    
    protected void setSpecialAtk(Attack special)
    {
    	this.specialAtk = special;
    }
    
	public String standardAttack(DungeonCharacter enemy)
	{
		return standardAtk.attack(this, enemy);
	}
    
	public String specialAttack(DungeonCharacter enemy)
	{
		return specialAtk.attack(this, enemy);
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public String getType()
	{
		return type;
	}

	public int getHp()
	{
		return hp;
	}
	
	public void setHp(int newHp)
	{
		if(newHp < 0)
			this.hp = 0;
		
		else if(newHp > totalHp)
			this.hp = totalHp;
		
		this.hp = newHp;
	}

	public int getTotalHp()
	{
		return totalHp;
	}

	public int getAttackSpeed()
	{
		return attackSpeed;
	}
	
	public String getPicPath()
	{
		return picPath;
	}
	
	public int getChanceToHit()
	{
		return chanceToHit;
	}
	
	public int getDmgMin() 
	{
		return dmgMin;
	}

	public int getDmgMax() 
	{
		return dmgMax;
	}
	
	public abstract void createNormalLines();
	
	public abstract void createSpecialLines();
	
	public String getRandomNormalLine()
	{
		Random numGen = new Random();
		int index = numGen.nextInt(atkLines.size());
		
		return atkLines.get(index);
	}
	
	public void addNormalLine(String line)
	{
		if(line != null && line.length() != 0)
			atkLines.add(line);
	}
	
	public String getRandomSpecialLine()
	{
		Random numGen = new Random();
		int index = numGen.nextInt(specialLines.size());
		
		return specialLines.get(index);
	}
	
	public void addSpecialLine(String line)
	{
		if(line != null && line.length() != 0)
			specialLines.add(line);
	}


	public int compareTo(DungeonCharacter that)
	{
		return this.getHp() - that.getHp();
	}
}