package dungeon;
import java.util.ArrayList;
import java.util.Random;

import dungeon.Room.EventTypes;

public class Dungeon 
{
	private Room[][] layout;
	private Hero player;
	private int[] entranceCoords;
	private int[] extiCoords;
	
	public Dungeon(Hero player)
	{
		this.player = player;
		layout = new Room[5][5];
		
		fillRooms();
		placeWalls();
		placeEntrance();
		placeExit();
		placePillars();
		
		player.setCoordinates(entranceCoords[0], entranceCoords[1]);
	}
	
	public void enterRoom()
	{
		int[] plyrCoords = player.getCoordinates();
		Room currentRoom = layout[plyrCoords[0]][plyrCoords[1]];
		
		currentRoom.activateRoom();
		
		if(currentRoom.getEvents().get(0) == EventTypes.monster)
			System.out.println(currentRoom.getMstr().getName());
	}
	
	private void fillRooms()
	{
		for(int row = 0; row < layout.length; row++)
		{
			for(int col = 0; col < layout[row].length; col++)
			{
				layout[row][col] = new Room(player);
			}
		}
	}
	
	private void placeWalls()
	{
		for(int i = 0; i < layout.length; i++)
		{
			layout[0][i].setNorthDoorIsWall(true);
			layout[4][i].setSouthDoorIsWall(true);
			layout[i][0].setWestDoorIsWall(true);
			layout[i][4].setEastDoorIsWall(true);
		}
	}
	
	private void placeEntrance()
	{
		int[] coords = generateCoordinates();
		int row = coords[0], col = coords[1];
		
		layout[row][col].setEvent(EventTypes.entrance);
		
		entranceCoords = coords;
	}
	
	private void placeExit()
	{
		int[] coords = generateCoordinates();
		int row = coords[0], col = coords[1];
		
		layout[row][col].setEvent(EventTypes.exit);
		
		extiCoords = coords;
	}
	
	private void placePillars()
	{
		for(int i = 0; i < 4; i++)
			placePillar();
	}
	
	private void placePillar()
	{
		int[] coords = generateCoordinates();
		int row = coords[0], col = coords[1];
		
		layout[row][col].setEvent(EventTypes.pillar);
	}
	
	private int[] generateCoordinates()
	{
		Random numGenerator = new Random();
		
		int[] coord = new int[2];
		int row = numGenerator.nextInt(5);
		int col = numGenerator.nextInt(5);
		
		if(layout[row][col].isEntrance() || layout[row][col].hasPillar() || layout[row][col].isExit())
			coord = generateCoordinates();
		
		else
		{
			coord[0] = row;
			coord[1] = col;
		}
			
		return coord;
	}
	
	public Room[][] getLayout()
	{
		return layout;
	}
	
	public int[] getCurrCoords()
	{
		return player.getCoordinates();
	}
	
	public Room getCurrRoom()
	{
		int[] currCoords = player.getCoordinates();
		return layout[currCoords[0]][currCoords[1]];
	}
	
	public void moveUp()
	{
		Room curr = getCurrRoom();
		if(curr.isNorthDoorWall())
			System.out.println("Can't go up!");
		
		else
		{
			int[] currCoords = player.getCoordinates();
			player.setCoordinates(currCoords[0] - 1, currCoords[1]);
		}
	}
	
	public void moveDown()
	{
		Room curr = getCurrRoom();
		if(curr.isSouthDoorWall())
			System.out.println("Can't go down!");
		
		else
		{
			int[] currCoords = player.getCoordinates();
			player.setCoordinates(currCoords[0] + 1, currCoords[1]);
		}
	}
	
	public void moveRight()
	{
		Room curr = getCurrRoom();
		if(curr.isEastDoorWall())
			System.out.println("Can't go right!");
		
		else
		{
			int[] currCoords = player.getCoordinates();
			player.setCoordinates(currCoords[0], currCoords[1] + 1);
		}
	}
	
	public void moveLeft()
	{
		Room curr = getCurrRoom();
		if(curr.isWestDoorWall())
			System.out.println("Can't go left!");
		
		else
		{
			int[] currCoords = player.getCoordinates();
			player.setCoordinates(currCoords[0], currCoords[1] - 1);
		}
	}

	public int[] getEntranceCoords() 
	{
		return entranceCoords;
	}

	public int[] getExtitCoords() 
	{
		return extiCoords;
	}
	
	public String getEventPicPath()
	{
		return getCurrRoom().getEventPicPath();
	}

	public ArrayList<EventTypes> getEvent() 
	{
		return getCurrRoom().getEvents();
	}
	
	public Monster getMnstr()
	{
		return getCurrRoom().getMstr();
	}
	
	public void activate()
	{
		getCurrRoom().activateRoom();
	}

	public String mnstrStandardAtk() 
	{
		String result = "";
		
		if(getMnstr().isAlive())
			result = getMnstr().standardAttack(player);
			
		return result;
	}

	public String mnstrSpecialAtk() 
	{
		String result = "";
		
		if(getMnstr().isAlive())
			result = getMnstr().specialAttack(player);
			
		return result;
	}
	
	public String mnstrRegen()
	{
		return getMnstr().regenHealth();
	}

	public int getRoundNum() 
	{
		return getCurrRoom().getCurrRound();
	}
	
	public void decreaseRound()
	{
		getCurrRoom().decreaseCurrRound();
	}
	
	public void resetRoundNum()
	{
		getCurrRoom().resetCurrRound();
	}
}
