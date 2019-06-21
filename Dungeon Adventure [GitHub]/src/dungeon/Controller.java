package dungeon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

import dungeon.Room.EventTypes;

public class Controller implements Observer
{
	public enum Mode
	{
		Menu, CharcterSelect, Battle, Inventory, Defeat, Victory;
	}
	
	private DungeonAdventure model;
	private View view;
	private String txtMsg;
	private Mode mode;
	
	public Controller(DungeonAdventure model, View view)
	{
		this.model = model;
		this.view = view;
		this.model.addObserver(this);
		this.txtMsg = "";
		this.mode = Mode.Menu;
		
		connectBtns();
	}
	
	public void start()
	{
		view.setVisible();
		updateView();
	}
	
	public void updateView()
	{
		switch(mode)
		{
			case Menu:
				menuUpdateView();
				break;
			
			case CharcterSelect:
				charSelectUpdateView();
				break;
	
			case Battle:
				battleUpdateView();
				break;
	
			case Inventory:
				battleUpdateView();
				invUpdateView();
				break;
				
			case Defeat:
				view.showResults("defeat");
				break;
				
			case Victory:
				view.showResults("win");
				break;
				
			default:
				menuUpdateView();
				break;
		}
	}
	
	private void charSelectUpdateView()
	{
		view.showCharSelect();
	}
	
	private void menuUpdateView()
	{
		view.showMenu();
	}
	
	private void battleUpdateView()
	{
		view.showBattle();
		view.getTxtrTextArea().setVisible(true);
		
		view.getLblPlayerName().setText(model.getPlyrName());
		view.getLblPlayerHpNum().setText(model.getPlyrHP() + " / " + model.getPlyrTotalHP());
		view.getPlayerHpBar().setMaximum(model.getPlyrTotalHP());
		view.getPlayerHpBar().setValue(model.getPlyrHP());
		view.getTxtrTextArea().setText(model.getEventTxt());
		
		if(model.getPlyrGender().startsWith("M"))
			view.setPlyrMale();
		
		else
			view.setPlyrFemale();
		
		if(view.getCheatMode() && view.isMapVisible())
			updateFullMap();
		
		EventTypes firstEvent = model.getEvent().get(0);
		
		String picPath = model.getEventPic();
		if(picPath != null)
			view.getLblRoomEvent().setIcon(new ImageIcon(View.class.getResource(model.getEventPic())));

		if(!model.plyrAlive())
		{
			view.getTxtrTextArea().setText("DEFEAT!");
			this.mode = Mode.Defeat;
			model.setRoomEmpty();
			return;
		}
		
		if(model.getEvent().contains(EventTypes.pitfall))
		{
			view.getTxtrTextArea().setText("You've Fallen Down a Pit & You Can't Get Up!");
			
			if(model.getEvent().size() != 1)
			{
				view.getTxtrTextArea().setText("You've Fallen Down a Pit & You Can't Get Up!\nBut look on the bright side, there's a chest down here!");
				view.displayItem();
			}
			
			else
				showDoors();
			
			model.activate();
		}
		
		else if(firstEvent == EventTypes.exit)
		{			
			String oldTxt = view.getTxtrTextArea().getText();
			
			if(model.getPillarNum() != 4)
			{
				view.getTxtrTextArea().setText(oldTxt + "\n ... But you don't have all four Pillars of OO, so you can't open the door!");
				showDoors();
			}
			
			else
			{
				mode = Mode.Victory;
				updateView();
			}
		}
		
		else if(firstEvent == EventTypes.empty || firstEvent == EventTypes.entrance)
			showDoors();
		
		else if(firstEvent != EventTypes.monster)
			view.displayItem();
		
		else
		{
			view.displayMonster();
			model.activate();
			updateEnemy();
		}
	}
	
	private void connectBtns()
	{
		view.getBtnAttackOne().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(model.plyrAlive() && model.mnstrAlive())
				{
					txtMsg = model.standardAtk();
					updateAfterAtk();
				}
			}
		});
		
		view.getBtnAttackTwo().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(model.plyrAlive() && model.mnstrAlive())
				{
					txtMsg = model.specialAtk();
					updateAfterAtk();
				}
			}
		});

		view.getBtnItems().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				mode = Mode.Inventory;
				updateView();
			}
		});

		view.getBtnRunAway().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(view.isChestOpen())
					view.getBtnItemOk().doClick();
				
				mode = Mode.Defeat;
				updateView();
			}
		});
		
		view.getBtnChest().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				view.showChest(model.getEvent());
			}
		});
		
		view.getBtnItemOk().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				boolean[] doors = model.getDoors();
				view.hideChest(doors[0], doors[1], doors[2], doors[3]);
				model.activate();
				model.setRoomEmpty();
			}
		});
		
		view.getBtnUp().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				goUp();
			}
		});
		
		view.getBtnDown().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				goDown();
			}
		});
		
		view.getBtnLeft().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				goLeft();
			}
		});
		
		view.getBtnRight().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				goRight();
			}
		});
		
		view.getBtnNorth().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				goUp();
			}
		});
		
		view.getBtnSouth().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				goDown();
			}
		});
		
		view.getBtnWest().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				goLeft();
			}
		});
		
		view.getBtnEast().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				goRight();
			}
		});
		
		view.getBtnStart().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				mode = Mode.CharcterSelect;
				updateView();
			}
		});
		
		view.getBtnBackToMenu().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				mode = Mode.Menu;
				updateView();
			}
		});
		
		view.getBtnCreate().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ArrayList<String> info = view.getCharInfo();
				
				if(info.size() == 3)
				{
					mode = Mode.Battle;
					model.makeHero(info.get(0), info.get(1), info.get(2));
					
					updateView();
				}
				
				else
					view.showError();
			}
		});
		
		view.getBtnCloseInv().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				mode = Mode.Battle;
				updateView();
			}
		});
		
		view.getBtnUseHp().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				model.useItem("Healing Potion");
				updateView();
			}
		});
		
		view.getBtnUseVis().addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				model.useItem("Vision Potion");
				updateView();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) 
	{
		updateView();
		
		if(arg instanceof ArrayList)
			updatePartialMap((ArrayList<int[]>) arg);
	}
	
	private void updateFullMap()
	{
		Room[][] layout = model.getLayout();
		String[] eventChars = new String[25];
		
		int index = 0;
		for(int row = 0; row < layout.length; row++)
		{
			for(int col = 0; col < layout[row].length; col++)
			{
				eventChars[index] = layout[row][col].getEventChar();
				index++;
			}
		}
		
		view.addFullMap(eventChars);
		
		int[] currRoom = model.getRoomCoords();
		view.updateCurrRoomOnMap(currRoom);
	}
	
	private void updatePartialMap(ArrayList<int[]> indexesToShow)
	{
		Room[][] layout = model.getLayout();
		String[] eventChars = new String[25];
		
		for(int i = 0; i < eventChars.length; i++)
			eventChars[i] = "?";
		
		for(int[] indexes : indexesToShow)
		{
			int index = indexes[0] * 5 + indexes[1];
			eventChars[index] = layout[indexes[0]][indexes[1]].getEventChar();
		}
		
		view.addFullMap(eventChars);
		
		int[] currRoom = model.getRoomCoords();
		view.updateCurrRoomOnMap(currRoom);
		view.showMap();
	}
	
	private void updateEnemy()
	{
		Monster mnstr = model.getMnstr();
		
		if(mnstr.getGender().equalsIgnoreCase("Male"))
			view.getLblEnemyGender().setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/male.png")));
		
		else
			view.getLblEnemyGender().setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/female.png")));
		
		view.getLblEnemyHpNum().setText(mnstr.getHp() + " / " + mnstr.getTotalHp());
		view.getEnemyHpBar().setMaximum(mnstr.getTotalHp());
		view.getEnemyHpBar().setValue(mnstr.getHp());
		view.getLblEnemyName().setText(mnstr.getName());
		view.getLblRoomEvent().setIcon(new ImageIcon(View.class.getResource(mnstr.getPicPath())));
		
		if(model.getRoundNum() <= 0 && mnstr.isAlive())
		{
			String result = model.monsterAttack(view.isHardMode());
			txtMsg += "\n" + result;
			view.getTxtrTextArea().setText(txtMsg);
			
			model.resetRoundNum();
			
			updateView();
		}
	}
	
	private void updateAfterAtk()
	{
		model.decreaseRound();
		view.hideMap();
		
		txtMsg += "\n Tunrs Left: " + model.getRoundNum();
		
		if(!model.mnstrAlive())
		{
			txtMsg += "\n CONGRATULATIONS!!!\n You've defeated " + model.getMnstr().getName() + "!";
			
			if(!view.isHardMode())
				model.healPlyr();
			
			model.setRoomEmpty();
		}
		
		view.getTxtrTextArea().setText(txtMsg);
	}
	
	private void invUpdateView()
	{
		view.setHpPotNum(model.getHpPotCount());
		view.setVisPotNum(model.getVisPotCount());
		view.setPillarNum(model.getPillarNum());
		
		view.showInv();
		view.hideDoors();
		view.getTxtrTextArea().setVisible(false);
	}
	
	private void showDoors()
	{
		boolean[] doors = model.getDoors();
		view.displayNothing(doors[0], doors[1], doors[2], doors[3]);
	}
	
	private void goUp()
	{
		model.moveUp();
		move();
	}
	
	private void goDown()
	{
		model.moveDown();
		move();
	}
	
	private void goLeft()
	{
		model.moveLeft();
		move();
	}
	
	private void goRight()
	{
		model.moveRight();
		move();
	}
	
	private void move()
	{
		view.hideMap();
		view.hideDoors();
		updateView();
	}
}
