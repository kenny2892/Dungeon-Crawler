package dungeon;
import java.util.ArrayList;
import java.util.Random;

import dungeon.ItemFactory.ItemTypes;

public class Room 
{
	public enum EventTypes
	{
		healPot, visPot, pitfall, pillar, monster, entrance, exit, empty;
	}
	
	private Hero player;
	private Monster mstr;
	private ItemFactory createItems;
	private Random numGenerator = new Random();
	private ArrayList<EventTypes> events;
	private boolean nDoorIsWall, eDoorIsWall, sDoorIsWall, wDoorIsWall;
	private boolean activated;
	private int currRound, maxRound;
	
	public Room(Hero player)
	{
		this.player = player;
		this.createItems = new ItemFactory();
		this.events = new ArrayList<Room.EventTypes>();
		createDefaultEvent();
		
		nDoorIsWall = false;
		eDoorIsWall = false;
		sDoorIsWall = false;
		wDoorIsWall = false;
		
		activated = false;
	}
	
	public void activateRoom()
	{
		if(activated)
			return;
		
		for(EventTypes event : events)
		{
			switch(event)
			{
				case healPot:
					healPot();
					break;
					
				case visPot:
					visPot();
					break;
					
				case pitfall:
					pitfall();
					break;
					
				case pillar:
					pillar();
					break;
					
				case monster:
					monsterEncounter();
					break;
					
				case entrance:
					
					break;
					
				case exit:
					
					break;
					
				case empty:
					
					break;
					
			}
			
			activated = true;
		}
	}
	
	public void healPot()
	{
		player.setHealPotNum(player.getHealPotNum() + 1);
		player.addToInv(createItems.getItem(ItemTypes.Healing));
	}
	
	public void visPot()
	{
		player.setVisPotNum(player.getVisPotNum() + 1);
		player.addToInv(createItems.getItem(ItemTypes.Vision));
	}
	
	public void pitfall()
	{
		player.hurtHero(numGenerator.nextInt(20) + 1);
	}
	
	public void pillar()
	{
		player.setPillarsNum(player.getPillarsNum() + 1);
	}
	
	public void monsterEncounter()
	{		
		if(mstr == null)
			mstr = new MonsterFactory().randomMonster();
		
		maxRound = player.calculateTurns(mstr);
		currRound = maxRound;
	}
	
	public Monster getMstr()
	{
		return mstr;
	}
	
	public void addEvent(EventTypes type)
	{
		if(type != null)
			events.add(type);
	}
	
	public ArrayList<EventTypes> getEvents()
	{
		return events;
	}
	
	public void clearEvents()
	{
		events = new ArrayList<Room.EventTypes>();
		events.add(EventTypes.empty);
	}
	
	public boolean isEntrance()
	{
		if(events.get(0) == EventTypes.entrance)
			return true;
		
		return false;
	}
	
	public boolean isExit()
	{
		if(events.get(0) == EventTypes.exit)
			return true;
		
		return false;
	}
	
	public boolean hasPillar()
	{
		if(events.get(0) == EventTypes.pillar)
			return true;
		
		return false;
	}
	
	public boolean isNorthDoorWall() 
	{
		return nDoorIsWall;
	}

	public void setNorthDoorIsWall(boolean nDoorIsWall) 
	{
		this.nDoorIsWall = nDoorIsWall;
	}

	public boolean isEastDoorWall() 
	{
		return eDoorIsWall;
	}

	public void setEastDoorIsWall(boolean eDoorIsWall) 
	{
		this.eDoorIsWall = eDoorIsWall;
	}

	public boolean isSouthDoorWall() 
	{
		return sDoorIsWall;
	}

	public void setSouthDoorIsWall(boolean sDoorIsWall) 
	{
		this.sDoorIsWall = sDoorIsWall;
	}

	public boolean isWestDoorWall() 
	{
		return wDoorIsWall;
	}

	public void setWestDoorIsWall(boolean wDoorIsWall) 
	{
		this.wDoorIsWall = wDoorIsWall;
	}
	
	public String getEventChar()
	{
		EventTypes first = events.get(0);
		
		if(events.size() > 1)
			return "M";
		
		switch(first)
		{
			case pitfall:
				return "P";

			case entrance:
				return "I";

			case exit:
				return "O";

			case visPot:
				return "V";

			case healPot:
				return "H";

			case empty:
				return "E";

			case monster:
				return "X";

			case pillar: // Never Specified in Rubric
				return "OO";
		}
		
		return "?";
	}
	
	public String getEventPicPath()
	{
		if(events.size() > 1 || (events.get(0) != EventTypes.monster && events.get(0) != EventTypes.entrance && events.get(0) != EventTypes.exit && events.get(0) != EventTypes.pitfall))
			return "/dungeon/Images/Chest.png";
		
		else if(events.get(0) == EventTypes.monster)
			return null;
		
		return "/dungeon/Images/Nothing.png";
	}

	private void createDefaultEvent()
	{
		int haveHealPot = numGenerator.nextInt(100);
		if(haveHealPot >= 89)
			this.addEvent(EventTypes.healPot);
		
		int haveVisPot = numGenerator.nextInt(100);
		if(haveVisPot >= 89)
			this.addEvent(EventTypes.visPot);
		
		int havePit = numGenerator.nextInt(100);
		if(havePit >= 89)
			this.addEvent(EventTypes.pitfall);
		
		if(events.size() == 0)
		{
			int isEmpty = numGenerator.nextInt(100);
			
			if(isEmpty >= 9)
				this.addEvent(EventTypes.monster);
			
			else
				this.addEvent(EventTypes.empty);
		}
	}

	public void setEvent(EventTypes event) 
	{
		events = new ArrayList<Room.EventTypes>();
		events.add(event);
	}
	
	public String getEventTxt()
	{
		if(events.get(0) == EventTypes.monster)
			return " A Wild Monster Appeared!!";
		
		else if(events.get(0) == EventTypes.entrance)
			return " Let the Games BEGIN!";
		
		else if(events.get(0) == EventTypes.exit)
			return " Congratulations!\n You've found the exit!";
		
		else if(events.get(0) == EventTypes.empty)
			return " Welp, there's absolutely nothing here.";
		
		else
			return " Hey look, a Chest!";
	}

	public int getCurrRound() 
	{
		return currRound;
	}
	
	public void decreaseCurrRound()
	{
		currRound--;
	}
	
	public void resetCurrRound()
	{
		currRound = maxRound;
	}

	public int getMaxRound() 
	{
		return maxRound;
	}
	
	@Override
	public String toString()
	{
		String top ="*";
		if(nDoorIsWall)		
			top += "**";
		
		else
			top += "-*";
		
		String mid ="";
		
		if(wDoorIsWall)
			mid += "*";
		
		else
			mid += "|";
		
		mid += getEventChar();
		
		if(eDoorIsWall)
			mid += "*";
		
		else
			mid += "|";
		
		String bot = "*";
		if(sDoorIsWall)		
			bot += "**";
		
		else
			bot += "-*";
		
		return top + "\n" + mid + "\n" + bot;
	}
}
