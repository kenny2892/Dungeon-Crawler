package dungeon;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import dungeon.Room.EventTypes;

public class View 
{
	private JFrame frame;
	private JPanel mainPanel, resultsPanel;
	private ViewBattle battlePanel;
	private ViewMenu menuPanel;
	private ViewCharacterSelect characterPanel;
	private JLabel lblBG, lblResultsScreen;
	private JButton btnBackToMenu;
	
	public View()
	{
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setTitle("Dungeon Adventure");
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		lblBG = new JLabel("");
		lblBG.setBounds(0, 0, 794, 571);
		lblBG.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/BattleScreen_1.jpg")));
		
		battlePanel = new ViewBattle(mainPanel);
		menuPanel = new ViewMenu(mainPanel);
		characterPanel = new ViewCharacterSelect(mainPanel);
		
		resultsPanel();
		
		
		mainPanel.add(lblBG);
	}
	
	private void resultsPanel()
	{
		resultsPanel = new JPanel();
		resultsPanel.setBounds(10, 11, 774, 549);
		mainPanel.add(resultsPanel);
		resultsPanel.setLayout(null);
		
		btnBackToMenu = new JButton("Back To Menu");
		btnBackToMenu.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBackToMenu.setFocusPainted(false);
		btnBackToMenu.setBounds(271, 406, 232, 72);
		resultsPanel.add(btnBackToMenu);
		
		lblResultsScreen = new JLabel("");
		lblResultsScreen.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/Victory Screen.jpg")));
		lblResultsScreen.setBounds(0, 0, 774, 549);
		resultsPanel.add(lblResultsScreen);
	}

	public void setVisible()
	{
		frame.setVisible(true);
	}

	public JPanel getPlayerHpPanel() 
	{
		return battlePanel.getPlayerHpPanel();
	}

	public JLabel getLblPlayerName() 
	{
		return battlePanel.getLblPlayerName();
	}

	public JLabel getLblPlayerGender() 
	{
		return battlePanel.getLblPlayerGender();
	}

	public JLabel getLblPlayerHpNum() 
	{
		return battlePanel.getLblPlayerHpNum();
	}

	public JLabel getLblEnemyName() 
	{
		return battlePanel.getLblEnemyName();
	}

	public JLabel getLblEnemyGender() 
	{
		return battlePanel.getLblEnemyGender();
	}

	public JLabel getLblEnemyHpNum() 
	{
		return battlePanel.getLblEnemyHpNum();
	}

	public JLabel getLblRoomEvent() 
	{
		return battlePanel.getLblRoomEvent();
	}

	public JLabel getLblBG() 
	{
		return lblBG;
	}

	public JButton getBtnAttackOne() 
	{
		return battlePanel.getBtnAttackOne();
	}

	public JButton getBtnAttackTwo() 
	{
		return battlePanel.getBtnAttackTwo();
	}

	public JButton getBtnItems() 
	{
		return battlePanel.getBtnItems();
	}

	public JButton getBtnRunAway() 
	{
		return battlePanel.getBtnRunAway();
	}

	public JButton getBtnUp() 
	{
		return battlePanel.getBtnUp();
	}

	public JButton getBtnDown() 
	{
		return battlePanel.getBtnDown();
	}

	public JButton getBtnLeft() 
	{
		return battlePanel.getBtnLeft();
	}

	public JButton getBtnRight() 
	{
		return battlePanel.getBtnRight();
	}

	public JProgressBar getPlayerHpBar() 
	{
		return battlePanel.getPlayerHpBar();
	}

	public JProgressBar getEnemyHpBar() 
	{
		return battlePanel.getEnemyHpBar();
	}

	public JTextArea getTxtrTextArea() 
	{
		return battlePanel.getTxtrTextArea();
	}
	
	public JButton getBtnChest()
	{
		return battlePanel.getBtnOpenChest();
	}

	public JButton getBtnItemOk() 
	{
		return battlePanel.getBtnItemOk();
	}
	
	public JButton getBtnBackToMenu()
	{
		return btnBackToMenu;
	}
	
	public JButton getBtnStart()
	{
		return menuPanel.getBtnStart();
	}
	
	public JButton getBtnCreate()
	{
		return characterPanel.getbtnCreate();
	}

	public JButton getBtnUseHp() 
	{
		return battlePanel.getBtnUseHp();
	}

	public JButton getBtnUseVis() 
	{
		return battlePanel.getBtnUseVis();
	}

	public JButton getBtnCloseInv() 
	{
		return battlePanel.getBtnCloseInv();
	}
	
	public ArrayList<String> getCharInfo()
	{
		return characterPanel.getHeroInfo();
	}
	
	public boolean isHardMode() 
	{
		return characterPanel.getHardMode();
	}
	
	private void hideAll()
	{
		characterPanel.setVisible(false);
		menuPanel.setVisible(false);
		battlePanel.setVisible(false);
		resultsPanel.setVisible(false);
		battlePanel.hideInv();
	}
	
	public JButton getBtnNorth()
	{
		return battlePanel.getBtnNorth();
	}
	
	public JButton getBtnSouth()
	{
		return battlePanel.getBtnSouth();
	}
	
	public JButton getBtnWest()
	{
		return battlePanel.getBtnWest();
	}
	
	public JButton getBtnEast()
	{
		return battlePanel.getBtnEast();
	}
	
	public void showDoors(boolean north, boolean south, boolean west, boolean east)
	{
		battlePanel.showDoors(north, south, west, east);
	}
	
	public void hideDoors()
	{
		battlePanel.hideDoors();
	}
	
	public void showBattle()
	{
		hideAll();
		battlePanel.setVisible(true);
		lblBG.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/BattleScreen_1.jpg")));
	}
	
	public void showMenu()
	{
		hideAll();
		menuPanel.setVisible(true);
		lblBG.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/Menu Screen.jpg")));
	}
	
	public void showResults(String result)
	{
		hideAll();
		resultsPanel.setVisible(true);
		
		if(result.equalsIgnoreCase("win") || result.equalsIgnoreCase("won") || result.equalsIgnoreCase("victory"))
			lblResultsScreen.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/Victory Screen.jpg")));
		
		else
			lblResultsScreen.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/Defeat Screen.jpg")));
	}
	
	public void showCharSelect()
	{
		hideAll();
		characterPanel.setVisible(true);
		lblBG.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/Character Select Screen.jpg")));
	}
	
	public void showError()
	{
		characterPanel.showError();
	}
	
	public void showInv()
	{
		showBattle();
		battlePanel.checkIfShouldShowBtns();
		battlePanel.showInv();
	}
	
	public boolean getCheatMode()
	{
		return battlePanel.getCheatMode();
	}
	
	public void showMap()
	{
		battlePanel.showMap();
	}
	
	public void hideMap()
	{
		if(!battlePanel.getCheatMode())
			battlePanel.hideMap();
	}
	
	public boolean isChestOpen()
	{
		return battlePanel.isChestOpen();
	}
	
	public void showChest(ArrayList<EventTypes> events)
	{
		battlePanel.showChest(events);
	}
	
	public void hideChest(boolean north, boolean south, boolean west, boolean east)
	{
		battlePanel.hideChest();
		showDoors(north, south, west, east);
	}
	
	public void displayMonster()
	{
		battlePanel.yesMonster();
	}
	
	public void displayItem()
	{
		battlePanel.yesItems();
	}
	
	public void displayNothing(boolean north, boolean south, boolean west, boolean east)
	{
		battlePanel.noMonster();
		showDoors(north, south, west, east);
	}
	
	public void displayExit()
	{
		battlePanel.noMonster();
	}
	
	public boolean isMapVisible()
	{
		return battlePanel.isMapVisible();
	}
	
	public void addFullMap(String[] newMap)
	{
		battlePanel.addFullMap(newMap);
	}
	
	public void updateCurrRoomOnMap(int[] coords)
	{
		battlePanel.updateCurrRoomOnMap(coords);
	}
	
	public void setPlyrFemale()
	{
		battlePanel.getLblPlayerGender().setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/female.png")));
	}
	
	public void setPlyrMale()
	{
		battlePanel.getLblPlayerGender().setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/male.png")));
	}
	
	public void setMnstrFemale()
	{
		battlePanel.getLblEnemyGender().setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/female.png")));
	}
	
	public void setMnstrMale()
	{
		battlePanel.getLblEnemyGender().setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/male.png")));
	}
	
	public void setHpPotNum(int num) 
	{
		battlePanel.setHpPotNum(num);
	}

	public void setVisPotNum(int num) 
	{
		battlePanel.setVisPotNum(num);
	}

	public void setPillarNum(int num) 
	{
		battlePanel.setPillarNum(num);
	}
}
