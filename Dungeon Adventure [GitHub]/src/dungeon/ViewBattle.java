package dungeon;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import dungeon.Room.EventTypes;

public class ViewBattle extends JLayeredPane
{
	private static final long serialVersionUID = 1L;
	
	private JPanel playerHpPanel, enemyHpPanel, mstrEventPanel, itemPanel, invPanel;
	private JLabel lblPlayerName, lblPlayerGender, lblPlayerHpNum, lblEnemyName, lblEnemyGender, lblEnemyHpNum, lblEnemyHpBg, lblRoomEvent;
	private JButton btnAttackOne, btnAttackTwo, btnItems, btnRunAway, btnUp, btnDown, btnLeft, btnRight, btnOpenChest;
	private JProgressBar playerHpBar, enemyHpBar;

	private JTextArea txtrTextArea;
	
	private JTextField txt0, txt1 ,txt2 ,txt3 ,txt4;
	private JTextField txt5 ,txt6 ,txt7 ,txt8 ,txt9;
	private JTextField txt10 ,txt11 ,txt12 ,txt13 ,txt14;
	private JTextField txt15 ,txt16, txt17, txt18, txt19;
	private JTextField txt20, txt21, txt22, txt23, txt24;
	private String[] eventChars = new String[25];
	private JTextField currRoom;
	
	private JLabel lblHpPot, lblVisPot, lblPillar;
	private JButton btnItemOk;
	
	private JPanel cheatDoorsPanel, fullMapPanel;
	
	private final String konamiCode = "38384040373937398384658284";
	private String cheatCode = "";
	private boolean cheatMode = false;
	
	private JPanel doorPanel;
	private JButton btnNorth, btnSouth, btnWest, btnEast;
	
	private JLabel lblHpPotNum, lblVisPotNum, lblPillarNum;
	private JButton btnUseHp, btnUseVis, btnCloseInv;
	
	public ViewBattle(JPanel mainPanel)
	{
		KeyListener keyListn = new KeyListener() 
		{	
			@Override
			public void keyTyped(KeyEvent e) 
			{}
			
			@Override
			public void keyReleased(KeyEvent e)
			{}
			
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(cheatCode.length() >= konamiCode.length())
					cheatCode = "";
				
				cheatCode += e.getKeyCode();
				
				checkCheat();
			}
		};		
		
		this.setOpaque(false);
		this.setBounds(10, 11, 774, 549);
		mainPanel.add(this);
		this.setLayout(null);
		
		invPanel();
		itemPanel();
		createFullMap();
		doorPanel();
		
		JPanel botPanel = new JPanel();
		botPanel.setOpaque(false);
		botPanel.setBorder(null);
		botPanel.setBackground(Color.WHITE);
		botPanel.setBounds(0, 421, 794, 150);
		this.add(botPanel);
		botPanel.setLayout(null);
		
		JPanel choicePanel = new JPanel();
		choicePanel.setOpaque(false);
		choicePanel.setBounds(109, 80, 575, 55);
		botPanel.add(choicePanel);
		choicePanel.setLayout(new GridLayout(1, 2, 4, 4));
		
		btnAttackOne = new JButton("Standard Attack");
		btnAttackOne.setFocusPainted(false);
		choicePanel.add(btnAttackOne);
		
		btnAttackTwo = new JButton("Special Attack");
		btnAttackTwo.setFocusPainted(false);
		choicePanel.add(btnAttackTwo);
		
		btnItems = new JButton("Items");
		btnItems.setFocusPainted(false);
		choicePanel.add(btnItems);
		
		btnRunAway = new JButton("Run Away");
		btnRunAway.setFocusPainted(false);
		choicePanel.add(btnRunAway);
		
		playerHpPanel = new JPanel();
		playerHpPanel.setBounds(231, 15, 332, 61);
		botPanel.add(playerHpPanel);
		playerHpPanel.setLayout(null);
		playerHpPanel.setOpaque(false);
		
		lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerName.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPlayerName.setBounds(62, 5, 208, 19);
		playerHpPanel.add(lblPlayerName);
		
		lblPlayerGender = new JLabel("");
		lblPlayerGender.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/male.png")));
		lblPlayerGender.setBounds(299, 31, 24, 24);
		playerHpPanel.add(lblPlayerGender);
		
		lblPlayerHpNum = new JLabel("100 / 100");
		lblPlayerHpNum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayerHpNum.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPlayerHpNum.setBounds(0, 34, 107, 22);
		playerHpPanel.add(lblPlayerHpNum);
		
		playerHpBar = new JProgressBar();
		playerHpBar.setBounds(110, 33, 188, 22);
		playerHpBar.setMaximum(110);
		playerHpBar.setValue(110);
		playerHpPanel.add(playerHpBar);
		
		mstrEventPanel = new JPanel();
		mstrEventPanel.setOpaque(false);
		mstrEventPanel.setBounds(251, 120, 292, 297);
		this.add(mstrEventPanel);
		mstrEventPanel.setLayout(null);
		mstrEventPanel.setVisible(true);
		
		enemyHpPanel = new JPanel();
		enemyHpPanel.setBounds(26, 0, 238, 83);
		mstrEventPanel.add(enemyHpPanel);
		enemyHpPanel.setOpaque(false);
		enemyHpPanel.setLayout(null);
		
		lblEnemyName = new JLabel("Enemy Name");
		lblEnemyName.setForeground(Color.WHITE);
		lblEnemyName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnemyName.setBounds(9, 5, 208, 19);
		enemyHpPanel.add(lblEnemyName);
		lblEnemyName.setFont(new Font("Arial", Font.PLAIN, 20));
		
		lblEnemyGender = new JLabel("");
		lblEnemyGender.setBounds(186, 55, 24, 24);
		enemyHpPanel.add(lblEnemyGender);
		lblEnemyGender.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/male.png")));
		
		lblEnemyHpNum = new JLabel("100 / 100");
		lblEnemyHpNum.setForeground(Color.WHITE);
		lblEnemyHpNum.setBounds(14, 52, 125, 27);
		enemyHpPanel.add(lblEnemyHpNum);
		lblEnemyHpNum.setFont(new Font("Arial", Font.PLAIN, 20));
		lblEnemyHpNum.setHorizontalAlignment(SwingConstants.LEFT);
		
		enemyHpBar = new JProgressBar();
		enemyHpBar.setBounds(45, 31, 165, 14);
		enemyHpPanel.add(enemyHpBar);
		enemyHpBar.setValue(95);
		
		lblEnemyHpBg = new JLabel("");
		lblEnemyHpBg.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/HpBar.png")));
		lblEnemyHpBg.setBounds(0, 0, 238, 83);
		enemyHpPanel.add(lblEnemyHpBg);
		
		lblRoomEvent = new JLabel("");
		lblRoomEvent.setBounds(251, 210, 292, 208);
		this.add(lblRoomEvent);
		lblRoomEvent.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/MissingnoMonster.png")));
		
		btnOpenChest = new JButton("Open Chest");
		btnOpenChest.setFocusPainted(false);
		btnOpenChest.setBounds(303, 130, 187, 60);
		this.add(btnOpenChest);
		
		cheatDoorsPanel = new JPanel();
		cheatDoorsPanel.setOpaque(false);
		cheatDoorsPanel.setBounds(8, 153, 189, 154);
		this.add(cheatDoorsPanel, JLayeredPane.PALETTE_LAYER);
		cheatDoorsPanel.setLayout(null);
		cheatDoorsPanel.setVisible(false);
		
		btnUp = new JButton("Up");
		btnUp.setFocusPainted(false);
		btnUp.setBounds(62, 0, 72, 42);
		cheatDoorsPanel.add(btnUp);
		
		btnDown = new JButton("Down");
		btnDown.setFocusPainted(false);
		btnDown.setBounds(62, 112, 72, 42);
		cheatDoorsPanel.add(btnDown);
		
		btnLeft = new JButton("Left");
		btnLeft.setFocusPainted(false);
		btnLeft.setBounds(0, 53, 72, 42);
		cheatDoorsPanel.add(btnLeft);
		
		btnRight = new JButton("Right");
		btnRight.setFocusPainted(false);
		btnRight.setBounds(117, 53, 72, 42);
		cheatDoorsPanel.add(btnRight);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBorder(new LineBorder(Color.WHITE, 4));
		scrollPane.setBounds(155, 11, 483, 101);
		this.add(scrollPane);
		
		txtrTextArea = new JTextArea();
		txtrTextArea.setForeground(Color.WHITE);
		txtrTextArea.setBackground(Color.BLACK);
		txtrTextArea.setBorder(null);
		txtrTextArea.setEditable(false);
		txtrTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
		txtrTextArea.setText(" Text Area");
		scrollPane.setViewportView(txtrTextArea);
		
		JButton btnCheat = new JButton("");
		btnCheat.addKeyListener(keyListn);
		btnCheat.setContentAreaFilled(false);
		btnCheat.setOpaque(false);
		btnCheat.setFocusPainted(false);
		btnCheat.setBorderPainted(false);
		btnCheat.setBounds(0, 0, 60, 60);
		this.add(btnCheat);
		
		JButton btnResetCheat = new JButton("");
		btnResetCheat.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				cheatCode = "";
				cheatDoorsPanel.setVisible(false);
				fullMapPanel.setVisible(false);
				cheatMode = false;
			}
		});
		btnResetCheat.setOpaque(false);
		btnResetCheat.setFocusPainted(false);
		btnResetCheat.setContentAreaFilled(false);
		btnResetCheat.setBorderPainted(false);
		btnResetCheat.setBounds(714, 0, 60, 60);
		this.add(btnResetCheat);
	}
	
	private void invPanel()
	{
		invPanel = new JPanel();
		invPanel.setBorder(new CompoundBorder(new LineBorder(new Color(255, 255, 255), 2), new LineBorder(new Color(0, 0, 0), 3)));
		invPanel.setBounds(8, 11, 766, 391);
		this.add(invPanel, JLayeredPane.MODAL_LAYER);
		invPanel.setLayout(null);
		
		JPanel hpPotPanel = new JPanel();
		hpPotPanel.setBounds(66, 44, 167, 237);
		invPanel.add(hpPotPanel);
		hpPotPanel.setLayout(null);
		
		JLabel lblHpPot = new JLabel("");
		lblHpPot.setBounds(0, 0, 167, 167);
		hpPotPanel.add(lblHpPot);
		lblHpPot.setIcon(new ImageIcon(ViewBattle.class.getResource("/dungeon/Images/HpPot.png")));
		
		lblHpPotNum = new JLabel("Count: ");
		lblHpPotNum.setBounds(11, 170, 146, 23);
		hpPotPanel.add(lblHpPotNum);
		lblHpPotNum.setFont(new Font("Arial", Font.PLAIN, 20));
		lblHpPotNum.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnUseHp = new JButton("Use");
		btnUseHp.setFont(new Font("Arial", Font.PLAIN, 15));
		btnUseHp.setFocusPainted(false);
		btnUseHp.setBounds(25, 200, 117, 36);
		hpPotPanel.add(btnUseHp);
		
		JPanel visPotPanel = new JPanel();
		visPotPanel.setLayout(null);
		visPotPanel.setBounds(299, 41, 167, 237);
		invPanel.add(visPotPanel);
		
		JLabel lblVisPot = new JLabel("");
		lblVisPot.setIcon(new ImageIcon(ViewBattle.class.getResource("/dungeon/Images/VisPot.png")));
		lblVisPot.setBounds(0, 0, 167, 167);
		visPotPanel.add(lblVisPot);
		
		lblVisPotNum = new JLabel("Count: ");
		lblVisPotNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisPotNum.setFont(new Font("Arial", Font.PLAIN, 20));
		lblVisPotNum.setBounds(11, 170, 146, 23);
		visPotPanel.add(lblVisPotNum);
		
		btnUseVis = new JButton("Use");
		btnUseVis.setFont(new Font("Arial", Font.PLAIN, 15));
		btnUseVis.setFocusPainted(false);
		btnUseVis.setBounds(25, 200, 117, 36);
		visPotPanel.add(btnUseVis);
		
		JPanel pillarPanel = new JPanel();
		pillarPanel.setLayout(null);
		pillarPanel.setBounds(532, 41, 167, 237);
		invPanel.add(pillarPanel);
		
		JLabel lblPillar = new JLabel("");
		lblPillar.setIcon(new ImageIcon(ViewBattle.class.getResource("/dungeon/Images/Pillar.png")));
		lblPillar.setBounds(0, 0, 167, 167);
		pillarPanel.add(lblPillar);
		
		lblPillarNum = new JLabel("Count: ");
		lblPillarNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblPillarNum.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPillarNum.setBounds(11, 170, 146, 23);
		pillarPanel.add(lblPillarNum);
		
		btnCloseInv = new JButton("Close");
		btnCloseInv.setFont(new Font("Arial", Font.PLAIN, 25));
		btnCloseInv.setFocusPainted(false);
		btnCloseInv.setBounds(299, 323, 167, 57);
		invPanel.add(btnCloseInv);
	}
	
	private void doorPanel()
	{
		doorPanel = new JPanel();
		doorPanel.setBorder(new LineBorder(Color.WHITE, 4));
		doorPanel.setBackground(Color.BLACK);
		doorPanel.setBounds(102, 76, 570, 312);
		this.add(doorPanel);
		doorPanel.setLayout(null);
		
		JLabel lblDoorPic = new JLabel("");
		lblDoorPic.setIcon(new ImageIcon(ViewBattle.class.getResource("/dungeon/Images/Door.png")));
		lblDoorPic.setBounds(208, 55, 154, 203);
		doorPanel.add(lblDoorPic);
		
		JLabel lblWhereTo = new JLabel("Where To?");
		lblWhereTo.setFont(new Font("Arial", Font.PLAIN, 40));
		lblWhereTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhereTo.setForeground(Color.WHITE);
		lblWhereTo.setBounds(95, 11, 380, 51);
		doorPanel.add(lblWhereTo);
		
		JPanel doorBtnPanel = new JPanel();
		doorBtnPanel.setOpaque(false);
		doorBtnPanel.setBounds(95, 240, 380, 63);
		doorPanel.add(doorBtnPanel);
		doorBtnPanel.setLayout(new GridLayout(0, 4, 6, 0));
		
		btnNorth = new JButton("North");
		btnNorth.setFocusPainted(false);
		doorBtnPanel.add(btnNorth);
		
		btnSouth = new JButton("South");
		btnSouth.setFocusPainted(false);
		doorBtnPanel.add(btnSouth);
		
		btnWest = new JButton("West");
		btnWest.setFocusPainted(false);
		doorBtnPanel.add(btnWest);
		
		btnEast = new JButton("East");
		btnEast.setFocusPainted(false);
		doorBtnPanel.add(btnEast);
	}
	
	private void createFullMap()
	{
		fullMapPanel = new JPanel();
		fullMapPanel.setFocusable(false);
		fullMapPanel.setBounds(564, 117, 195, 195);
		this.add(fullMapPanel, JLayeredPane.PALETTE_LAYER);
		fullMapPanel.setLayout(null);
		fullMapPanel.setVisible(false);
		
		JPanel rowZeroPanel = new JPanel();
		rowZeroPanel.setFocusable(false);
		rowZeroPanel.setOpaque(false);
		rowZeroPanel.setBounds(4, 4, 187, 29);
		fullMapPanel.add(rowZeroPanel);
		rowZeroPanel.setLayout(new GridLayout(0, 5, 15, 0));
		
		txt0 = new JTextField();
		txt0.setFocusable(false);
		txt0.setBorder(null);
		txt0.setHorizontalAlignment(SwingConstants.CENTER);
		txt0.setEditable(false);
		txt0.setText("0");
		rowZeroPanel.add(txt0);
		txt0.setColumns(10);
		
		txt1 = new JTextField();
		txt1.setFocusable(false);
		txt1.setBorder(null);
		txt1.setText("1");
		txt1.setHorizontalAlignment(SwingConstants.CENTER);
		txt1.setEditable(false);
		txt1.setColumns(10);
		rowZeroPanel.add(txt1);
		
		txt2 = new JTextField();
		txt2.setFocusable(false);
		txt2.setBorder(null);
		txt2.setText("2");
		txt2.setHorizontalAlignment(SwingConstants.CENTER);
		txt2.setEditable(false);
		txt2.setColumns(10);
		rowZeroPanel.add(txt2);
		
		txt3 = new JTextField();
		txt3.setFocusable(false);
		txt3.setBorder(null);
		txt3.setText("3");
		txt3.setHorizontalAlignment(SwingConstants.CENTER);
		txt3.setEditable(false);
		txt3.setColumns(10);
		rowZeroPanel.add(txt3);
		
		txt4 = new JTextField();
		txt4.setFocusable(false);
		txt4.setBorder(null);
		txt4.setText("4");
		txt4.setHorizontalAlignment(SwingConstants.CENTER);
		txt4.setEditable(false);
		txt4.setColumns(10);
		rowZeroPanel.add(txt4);
		
		JPanel rowOnePanel = new JPanel();
		rowOnePanel.setFocusable(false);
		rowOnePanel.setOpaque(false);
		rowOnePanel.setBounds(4, 41, 187, 33);
		fullMapPanel.add(rowOnePanel);
		rowOnePanel.setLayout(new GridLayout(0, 5, 15, 0));
		
		txt5 = new JTextField();
		txt5.setFocusable(false);
		txt5.setBorder(null);
		txt5.setText("5");
		txt5.setHorizontalAlignment(SwingConstants.CENTER);
		txt5.setEditable(false);
		txt5.setColumns(10);
		rowOnePanel.add(txt5);
		
		txt6 = new JTextField();
		txt6.setFocusable(false);
		txt6.setBorder(null);
		txt6.setText("6");
		txt6.setHorizontalAlignment(SwingConstants.CENTER);
		txt6.setEditable(false);
		txt6.setColumns(10);
		rowOnePanel.add(txt6);
		
		txt7 = new JTextField();
		txt7.setFocusable(false);
		txt7.setBorder(null);
		txt7.setText("7");
		txt7.setHorizontalAlignment(SwingConstants.CENTER);
		txt7.setEditable(false);
		txt7.setColumns(10);
		rowOnePanel.add(txt7);
		
		txt8 = new JTextField();
		txt8.setFocusable(false);
		txt8.setBorder(null);
		txt8.setText("8");
		txt8.setHorizontalAlignment(SwingConstants.CENTER);
		txt8.setEditable(false);
		txt8.setColumns(10);
		rowOnePanel.add(txt8);
		
		txt9 = new JTextField();
		txt9.setFocusable(false);
		txt9.setBorder(null);
		txt9.setText("9");
		txt9.setHorizontalAlignment(SwingConstants.CENTER);
		txt9.setEditable(false);
		txt9.setColumns(10);
		rowOnePanel.add(txt9);
		
		JPanel rowTwoPanel = new JPanel();
		rowTwoPanel.setFocusable(false);
		rowTwoPanel.setOpaque(false);
		rowTwoPanel.setBounds(4, 82, 187, 32);
		fullMapPanel.add(rowTwoPanel);
		rowTwoPanel.setLayout(new GridLayout(0, 5, 15, 0));
		
		txt10 = new JTextField();
		txt10.setFocusable(false);
		txt10.setBorder(null);
		txt10.setText("10");
		txt10.setHorizontalAlignment(SwingConstants.CENTER);
		txt10.setEditable(false);
		txt10.setColumns(10);
		rowTwoPanel.add(txt10);
		
		txt11 = new JTextField();
		txt11.setFocusable(false);
		txt11.setBorder(null);
		txt11.setText("11");
		txt11.setHorizontalAlignment(SwingConstants.CENTER);
		txt11.setEditable(false);
		txt11.setColumns(10);
		rowTwoPanel.add(txt11);
		
		txt12 = new JTextField();
		txt12.setFocusable(false);
		txt12.setBorder(null);
		txt12.setText("12");
		txt12.setHorizontalAlignment(SwingConstants.CENTER);
		txt12.setEditable(false);
		txt12.setColumns(10);
		rowTwoPanel.add(txt12);
		
		txt13 = new JTextField();
		txt13.setFocusable(false);
		txt13.setBorder(null);
		txt13.setText("13");
		txt13.setHorizontalAlignment(SwingConstants.CENTER);
		txt13.setEditable(false);
		txt13.setColumns(10);
		rowTwoPanel.add(txt13);
		
		txt14 = new JTextField();
		txt14.setFocusable(false);
		txt14.setBorder(null);
		txt14.setText("14");
		txt14.setHorizontalAlignment(SwingConstants.CENTER);
		txt14.setEditable(false);
		txt14.setColumns(10);
		rowTwoPanel.add(txt14);
		
		JPanel rowThreePanel = new JPanel();
		rowThreePanel.setFocusable(false);
		rowThreePanel.setOpaque(false);
		rowThreePanel.setBounds(4, 122, 187, 33);
		fullMapPanel.add(rowThreePanel);
		rowThreePanel.setLayout(new GridLayout(0, 5, 15, 0));
		
		txt15 = new JTextField();
		txt15.setFocusable(false);
		txt15.setBorder(null);
		txt15.setText("15");
		txt15.setHorizontalAlignment(SwingConstants.CENTER);
		txt15.setEditable(false);
		txt15.setColumns(10);
		rowThreePanel.add(txt15);
		
		txt16 = new JTextField();
		txt16.setFocusable(false);
		txt16.setBorder(null);
		txt16.setText("16");
		txt16.setHorizontalAlignment(SwingConstants.CENTER);
		txt16.setEditable(false);
		txt16.setColumns(10);
		rowThreePanel.add(txt16);
		
		txt17 = new JTextField();
		txt17.setFocusable(false);
		txt17.setBorder(null);
		txt17.setText("17");
		txt17.setHorizontalAlignment(SwingConstants.CENTER);
		txt17.setEditable(false);
		txt17.setColumns(10);
		rowThreePanel.add(txt17);
		
		txt18 = new JTextField();
		txt18.setFocusable(false);
		txt18.setBorder(null);
		txt18.setText("18");
		txt18.setHorizontalAlignment(SwingConstants.CENTER);
		txt18.setEditable(false);
		txt18.setColumns(10);
		rowThreePanel.add(txt18);
		
		txt19 = new JTextField();
		txt19.setFocusable(false);
		txt19.setBorder(null);
		txt19.setText("19");
		txt19.setHorizontalAlignment(SwingConstants.CENTER);
		txt19.setEditable(false);
		txt19.setColumns(10);
		rowThreePanel.add(txt19);
		
		JPanel rowFourPanel = new JPanel();
		rowFourPanel.setFocusable(false);
		rowFourPanel.setOpaque(false);
		rowFourPanel.setBounds(4, 163, 187, 28);
		fullMapPanel.add(rowFourPanel);
		rowFourPanel.setLayout(new GridLayout(0, 5, 15, 0));
		
		txt20 = new JTextField();
		txt20.setFocusable(false);
		txt20.setBorder(null);
		txt20.setText("20");
		txt20.setHorizontalAlignment(SwingConstants.CENTER);
		txt20.setEditable(false);
		txt20.setColumns(10);
		rowFourPanel.add(txt20);
		
		txt21 = new JTextField();
		txt21.setFocusable(false);
		txt21.setBorder(null);
		txt21.setText("21");
		txt21.setHorizontalAlignment(SwingConstants.CENTER);
		txt21.setEditable(false);
		txt21.setColumns(10);
		rowFourPanel.add(txt21);
		
		txt22 = new JTextField();
		txt22.setFocusable(false);
		txt22.setBorder(null);
		txt22.setText("22");
		txt22.setHorizontalAlignment(SwingConstants.CENTER);
		txt22.setEditable(false);
		txt22.setColumns(10);
		rowFourPanel.add(txt22);
		
		txt23 = new JTextField();
		txt23.setFocusable(false);
		txt23.setBorder(null);
		txt23.setText("23");
		txt23.setHorizontalAlignment(SwingConstants.CENTER);
		txt23.setEditable(false);
		txt23.setColumns(10);
		rowFourPanel.add(txt23);
		
		txt24 = new JTextField();
		txt24.setFocusable(false);
		txt24.setBorder(null);
		txt24.setText("24");
		txt24.setHorizontalAlignment(SwingConstants.CENTER);
		txt24.setEditable(false);
		txt24.setColumns(10);
		rowFourPanel.add(txt24);
		
		JLabel lblFullMap = new JLabel("");
		lblFullMap.setFocusable(false);
		lblFullMap.setBounds(4, 4, 187, 187);
		fullMapPanel.add(lblFullMap);
		lblFullMap.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/FullMap.png")));
	}
	
	private void itemPanel()
	{
		itemPanel = new JPanel();
		itemPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		itemPanel.setBounds(143, 11, 508, 399);
		this.add(itemPanel);
		itemPanel.setLayout(null);
		
		JPanel itemPicsPanel = new JPanel();
		itemPicsPanel.setBounds(36, 11, 435, 337);
		itemPanel.add(itemPicsPanel);
		itemPicsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblHpPot = new JLabel("");
		itemPicsPanel.add(lblHpPot);
		lblHpPot.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/HpPot.png")));
		lblHpPot.setVisible(false);
		
		lblVisPot = new JLabel("");
		itemPicsPanel.add(lblVisPot);
		lblVisPot.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/VisPot.png")));
		lblVisPot.setVisible(false);
		
		lblPillar = new JLabel("");
		itemPicsPanel.add(lblPillar);
		lblPillar.setIcon(new ImageIcon(View.class.getResource("/dungeon/Images/Pillar.png")));
		lblPillar.setVisible(false);
		
		btnItemOk = new JButton("Ok");
		btnItemOk.setFocusPainted(false);
		btnItemOk.setBounds(190, 352, 128, 38);
		itemPanel.add(btnItemOk);
		
		itemPanel.setVisible(false);
	}
	
	public void addFullMap(String[] newMap)
	{
		eventChars = newMap;
		updateFullMap();
	}
	
	private void updateFullMap()
	{
		txt0.setText(eventChars[0]);
		txt1.setText(eventChars[1]);
		txt2.setText(eventChars[2]);
		txt3.setText(eventChars[3]);
		txt4.setText(eventChars[4]);
		txt5.setText(eventChars[5]);
		txt6.setText(eventChars[6]);
		txt7.setText(eventChars[7]);
		txt8.setText(eventChars[8]);
		txt9.setText(eventChars[9]);
		txt10.setText(eventChars[10]);
		txt11.setText(eventChars[11]);
		txt12.setText(eventChars[12]);
		txt13.setText(eventChars[13]);
		txt14.setText(eventChars[14]);
		txt15.setText(eventChars[15]);
		txt16.setText(eventChars[16]);
		txt17.setText(eventChars[17]);
		txt18.setText(eventChars[18]);
		txt19.setText(eventChars[19]);
		txt20.setText(eventChars[20]);
		txt21.setText(eventChars[21]);
		txt22.setText(eventChars[22]);
		txt23.setText(eventChars[23]);
		txt24.setText(eventChars[24]);
	}
	
	public void updateCurrRoomOnMap(int[] coords)
	{
		if(currRoom != null)
		{
			currRoom.setForeground(Color.black);
			currRoom.setBackground(Color.white);
		}
		
		int index = coords[0] * 5 + coords[1];
		
		switch(index)
		{
			case 0:
				currRoom = txt0;
				break;

			case 1:
				currRoom = txt1;
				break;

			case 2:
				currRoom = txt2;
				break;

			case 3:
				currRoom = txt3;
				break;

			case 4:
				currRoom = txt4;
				break;

			case 5:
				currRoom = txt5;
				break;

			case 6:
				currRoom = txt6;
				break;

			case 7:
				currRoom = txt7;
				break;

			case 8:
				currRoom = txt8;
				break;

			case 9:
				currRoom = txt9;
				break;

			case 10:
				currRoom = txt10;
				break;

			case 11:
				currRoom = txt11;
				break;

			case 12:
				currRoom = txt12;
				break;

			case 13:
				currRoom = txt13;
				break;

			case 14:
				currRoom = txt14;
				break;

			case 15:
				currRoom = txt15;
				break;

			case 16:
				currRoom = txt16;
				break;

			case 17:
				currRoom = txt17;
				break;

			case 18:
				currRoom = txt18;
				break;

			case 19:
				currRoom = txt19;
				break;

			case 20:
				currRoom = txt20;
				break;

			case 21:
				currRoom = txt21;
				break;

			case 22:
				currRoom = txt22;
				break;

			case 23:
				currRoom = txt23;
				break;

			case 24:
				currRoom = txt24;
				break;
		}
		
		currRoom.setForeground(Color.white);
		currRoom.setBackground(Color.black);
	}
	
	private void checkCheat()
	{
		if(cheatCode.equals(konamiCode))
		{
			cheatDoorsPanel.setVisible(true);
			fullMapPanel.setVisible(true);
			cheatMode = true;
		}
	}
	
	public boolean isMapVisible()
	{
		return fullMapPanel.isVisible();
	}
	
	public void showMap()
	{
		fullMapPanel.setVisible(true);
	}
	
	public void hideMap()
	{
		fullMapPanel.setVisible(false);
	}
	
	public void showDoors(boolean north, boolean south, boolean west, boolean east)
	{
		hideDoors();
		doorPanel.setVisible(true);
		
		if(north)
			btnNorth.setVisible(true);
		
		if(south)
			btnSouth.setVisible(true);
		
		if(west)
			btnWest.setVisible(true);
		
		if(east)
			btnEast.setVisible(true);
	}
	
	public void hideDoors()
	{
		doorPanel.setVisible(false);
		btnNorth.setVisible(false);
		btnSouth.setVisible(false);
		btnWest.setVisible(false);
		btnEast.setVisible(false);
	}
	
	public boolean isChestOpen()
	{
		return itemPanel.isVisible();
	}
	
	public void showChest(ArrayList<EventTypes> events)
	{
		itemPanel.setVisible(true);
		btnOpenChest.setVisible(false);
		
		for(EventTypes event : events)
		{
			if(event == EventTypes.healPot)
				lblHpPot.setVisible(true);
			
			else if(event == EventTypes.visPot)
				lblVisPot.setVisible(true);
			
			else if(event == EventTypes.pillar)
				lblPillar.setVisible(true);
		}
	}
	
	public void hideChest()
	{
		itemPanel.setVisible(false);
		lblHpPot.setVisible(false);
		lblVisPot.setVisible(false);
		lblPillar.setVisible(false);
	}
	
	public void noMonster()
	{
		lblEnemyGender.setVisible(false);
		lblEnemyHpNum.setVisible(false);
		lblEnemyName.setVisible(false);
		lblEnemyHpBg.setVisible(false);
		lblRoomEvent.setVisible(false);
		enemyHpBar.setVisible(false);
		btnOpenChest.setVisible(false);
		mstrEventPanel.setVisible(false);
		btnAttackOne.setVisible(false);
		btnAttackTwo.setVisible(false);
	}
	
	public void yesMonster()
	{
		lblEnemyGender.setVisible(true);
		lblEnemyHpNum.setVisible(true);
		lblEnemyName.setVisible(true);
		lblEnemyHpBg.setVisible(true);
		lblRoomEvent.setVisible(true);
		enemyHpBar.setVisible(true);
		btnOpenChest.setVisible(false);
		mstrEventPanel.setVisible(true);
		btnAttackOne.setVisible(true);
		btnAttackTwo.setVisible(true);
	}
	
	public void yesItems()
	{
		noMonster();
		if(!isChestOpen())
			btnOpenChest.setVisible(true);
		
		lblRoomEvent.setVisible(true);
	}

	public JPanel getPlayerHpPanel() 
	{
		return playerHpPanel;
	}

	public JPanel getItemPanel() 
	{
		return itemPanel;
	}

	public JLabel getLblPlayerName() 
	{
		return lblPlayerName;
	}

	public JLabel getLblPlayerGender() 
	{
		return lblPlayerGender;
	}

	public JLabel getLblPlayerHpNum() 
	{
		return lblPlayerHpNum;
	}

	public JLabel getLblEnemyGender() 
	{
		return lblEnemyGender;
	}

	public JLabel getLblEnemyName() 
	{
		return lblEnemyName;
	}

	public JLabel getLblEnemyHpNum() 
	{
		return lblEnemyHpNum;
	}

	public JLabel getLblRoomEvent() 
	{
		return lblRoomEvent;
	}

	public JButton getBtnAttackOne() 
	{
		return btnAttackOne;
	}

	public JButton getBtnAttackTwo() 
	{
		return btnAttackTwo;
	}

	public JButton getBtnItems() 
	{
		return btnItems;
	}

	public JButton getBtnRunAway() 
	{
		return btnRunAway;
	}

	public JButton getBtnUp() 
	{
		return btnUp;
	}

	public JButton getBtnDown() 
	{
		return btnDown;
	}

	public JButton getBtnLeft() 
	{
		return btnLeft;
	}

	public JButton getBtnRight() 
	{
		return btnRight;
	}

	public JButton getBtnOpenChest() 
	{
		return btnOpenChest;
	}

	public JProgressBar getPlayerHpBar() 
	{
		return playerHpBar;
	}

	public JProgressBar getEnemyHpBar() 
	{
		return enemyHpBar;
	}

	public JTextArea getTxtrTextArea() 
	{
		return txtrTextArea;
	}

	public JLabel getLblPillar() 
	{
		return lblPillar;
	}

	public JButton getBtnItemOk() 
	{
		return btnItemOk;
	}
	
	public boolean getCheatMode()
	{
		return cheatMode;
	}
	
	public JButton getBtnNorth()
	{
		return btnNorth;
	}
	
	public JButton getBtnSouth()
	{
		return btnSouth;
	}
	
	public JButton getBtnWest()
	{
		return btnWest;
	}
	
	public JButton getBtnEast()
	{
		return btnEast;
	}
	
	public void checkIfShouldShowBtns()
	{
		try
		{
			int hpPotCount = Integer.parseInt(lblHpPotNum.getText().substring(7));
		
			if(hpPotCount > 0)
				btnUseHp.setVisible(true);
			
			else
				btnUseHp.setVisible(false);
			
			int visPotCount = Integer.parseInt(lblVisPotNum.getText().substring(7));
			
			if(visPotCount > 0)
				btnUseVis.setVisible(true);
			
			else
				btnUseVis.setVisible(false);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setHpPotNum(int num) 
	{
		this.lblHpPotNum.setText("Count: " + num);
	}

	public void setVisPotNum(int num) 
	{
		this.lblVisPotNum.setText("Count: " + num);
	}

	public void setPillarNum(int num) 
	{
		this.lblPillarNum.setText("Count: " + num);
	}

	public JButton getBtnUseHp() 
	{
		return btnUseHp;
	}

	public JButton getBtnUseVis() 
	{
		return btnUseVis;
	}

	public JButton getBtnCloseInv() 
	{
		return btnCloseInv;
	}
	
	public void showInv()
	{
		invPanel.setVisible(true);
	}
	
	public void hideInv()
	{
		invPanel.setVisible(false);
	}
}
