package dungeon;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import dungeon.HeroFactory.HeroType;
import dungeon.Room.EventTypes;

public class DungeonAdventure extends Observable 
{
	// Put main method in "StartGame" since this is the Model for my MVC
	private Hero player;
	private Dungeon dungeon;
	
	public void makeHero(String type, String name, String gender)
	{
		player = new HeroFactory().createHero(HeroType.valueOf(type.replace(" ", "")), name, gender);
		dungeon = new Dungeon(player);		
		dungeon.enterRoom();
	}
	
	public Dungeon getDungeon()
	{
		return dungeon;
	}
	
	public Room getRoom()
	{
		return dungeon.getCurrRoom();
	}
	
	public int[] getRoomCoords()
	{
		return dungeon.getCurrCoords();
	}
	
	public String getPlyrName()
	{
		return player.getName();
	}
	
	public String getPlyrType()
	{
		return player.getType();
	}
	
	public int getPlyrHP()
	{
		if(player.getHp() < 0)
			return 0;
		
		return player.getHp();
	}
	
	public int getPlyrTotalHP()
	{
		return player.getTotalHp();
	}
	
	public int getHpPotCount()
	{
		return player.getHealPotNum();
	}
	
	public int getVisPotCount()
	{
		return player.getVisPotNum();
	}
	
	public void useItem(String itemName)
	{
		ArrayList<Item> inventory = player.getInv();
		
		for(Item item : inventory)
		{
			if(item.getName().equalsIgnoreCase(itemName))
			{
				inventory.remove(item);
				activateItem(item);
				break;
			}
		}
	}
	
	private void activateItem(Item item)
	{
		if(item instanceof HealingPotion)
		{
			HealingPotion hpPot = (HealingPotion) item;
			player.setHp(player.getHp() + hpPot.heal());
			player.setHealPotNum(player.getHealPotNum() - 1);
			changed();
		}
		
		else if(item instanceof VisionPotion)
		{
			ArrayList<int[]> coordsToShow = new ArrayList<int[]>();
			
			int[] currCoords = dungeon.getCurrCoords();
			
			if(currCoords[0] - 1 >= 0 && currCoords[0] - 1 <= 4 && currCoords[1] >= 0 && currCoords[1] <= 4)
				coordsToShow.add(new int[]{currCoords[0] - 1, currCoords[1]});

			if(currCoords[0] - 1 >= 0 && currCoords[0] - 1 <= 4 && currCoords[1] + 1 >= 0 && currCoords[1] + 1 <= 4)
				coordsToShow.add(new int[]{currCoords[0] - 1, currCoords[1] + 1});
			
			if(currCoords[0] - 1 >= 0 && currCoords[0] - 1 <= 4 && currCoords[1] - 1 >= 0 && currCoords[1] - 1 <= 4)
				coordsToShow.add(new int[]{currCoords[0] - 1, currCoords[1] - 1});
			
			if(currCoords[0] >= 0 && currCoords[0] <= 4 && currCoords[1] >= 0 && currCoords[1] <= 4)
				coordsToShow.add(new int[]{currCoords[0], currCoords[1]});

			if(currCoords[0] >= 0 && currCoords[0] <= 4 && currCoords[1] + 1 >= 0 && currCoords[1] + 1 <= 4)
				coordsToShow.add(new int[]{currCoords[0], currCoords[1] + 1});
			
			if(currCoords[0] >= 0 && currCoords[0] <= 4 && currCoords[1] - 1 >= 0 && currCoords[1] - 1 <= 4)
				coordsToShow.add(new int[]{currCoords[0], currCoords[1] - 1});
			
			if(currCoords[0] + 1 >= 0 && currCoords[0] + 1 <= 4 && currCoords[1] >= 0 && currCoords[1] <= 4)
				coordsToShow.add(new int[]{currCoords[0] + 1, currCoords[1]});

			if(currCoords[0] + 1 >= 0 && currCoords[0] + 1 <= 4 && currCoords[1] + 1 >= 0 && currCoords[1] + 1 <= 4)
				coordsToShow.add(new int[]{currCoords[0] + 1, currCoords[1] + 1});
			
			if(currCoords[0] + 1 >= 0 && currCoords[0] + 1 <= 4 && currCoords[1] - 1 >= 0 && currCoords[1] - 1 <= 4)
				coordsToShow.add(new int[]{currCoords[0] + 1, currCoords[1] - 1});

			player.setVisPotNum(player.getVisPotNum() - 1);			
			setChanged();
			notifyObservers(coordsToShow);
		}
	}
	
	public Room[][] getLayout()
	{
		return dungeon.getLayout();
	}
	
	public ArrayList<EventTypes> getEvent()
	{
		return dungeon.getEvent();
	}
	
	public String getEventPic()
	{
		return dungeon.getEventPicPath();
	}
	
	public boolean[] getDoors()
	{
		boolean[] doors = new boolean[4];
		Room room = dungeon.getCurrRoom();
		
		doors[0] = !room.isNorthDoorWall();
		doors[1] = !room.isSouthDoorWall();
		doors[2] = !room.isWestDoorWall();
		doors[3] = !room.isEastDoorWall();
		
		return doors;
	}
	
	public String standardAtk()
	{
		String result = player.standardAttack(dungeon.getMnstr());

		if(!getMnstr().isAlive())
			getMnstr().setHp(0);
		
		changed();
		
		return result;
	}
	
	public String specialAtk()
	{
		String result = player.specialAttack(dungeon.getMnstr());

		if(!getMnstr().isAlive())
			getMnstr().setHp(0);
		
		changed();
		
		return result;
	}
	
	private void changed()
	{
		setChanged();
		notifyObservers();
	}
	
	public void moveUp()
	{
		dungeon.moveUp();
		changed();
	}
	
	public void moveDown()
	{
		dungeon.moveDown();
		changed();
	}
	
	public void moveRight()
	{
		dungeon.moveRight();
		changed();
	}
	
	public void moveLeft()
	{
		dungeon.moveLeft();
		changed();
	}

	public void setRoomEmpty() 
	{
		Room curr = dungeon.getCurrRoom();
		curr.setEvent(EventTypes.empty);
		changed();
	}

	public String getEventTxt() 
	{
		Room curr = dungeon.getCurrRoom();
		return curr.getEventTxt();
	}
	
	public Monster getMnstr()
	{
		return dungeon.getMnstr();
	}

	public void activate() 
	{
		dungeon.activate();
	}

	public String monsterAttack(boolean hardMode) 
	{
		String result = "";
		
		Random numGen = new Random();
		int diceRoll = numGen.nextInt(101);
		
		if(diceRoll <= 70)
			result = dungeon.mnstrStandardAtk();
		
		else
			result = dungeon.mnstrSpecialAtk();
		
		if(hardMode)
			result = result + dungeon.mnstrRegen();
		
		return result;
	}

	public int getRoundNum() 
	{
		return dungeon.getRoundNum();
	}

	public void resetRoundNum() 
	{
		dungeon.resetRoundNum();
	}

	public void decreaseRound() 
	{
		dungeon.decreaseRound();
		changed();
	}
	
	public boolean plyrAlive()
	{
		return player.isAlive();
	}
	
	public boolean mnstrAlive()
	{
		return getMnstr().isAlive();
	}

	public int getPillarNum() 
	{
		return player.getPillarsNum();
	}

	public String getPlyrGender() 
	{
		return player.getGender();
	}

	public void healPlyr() 
	{
		player.setHp(player.getTotalHp());
	}
}
