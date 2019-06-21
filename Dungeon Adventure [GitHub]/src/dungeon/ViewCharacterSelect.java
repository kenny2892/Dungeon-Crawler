package dungeon;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ViewCharacterSelect extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private String type = "", gender = "";
	private JLabel lblCharPic, lblError;
	private JTextField txtrTypeNameHere;
	private ButtonGroup typeSelectGroup, genderSelectGroup;
	private JButton btnCreate;
	private ArrayList<JRadioButton> typeBtns, genderBtns;

	private JCheckBox chckbxHardMode;
	
	public ViewCharacterSelect(JPanel mainPanel)
	{
		this.setOpaque(false);
		this.setBounds(10, 11, 774, 549);
		mainPanel.add(this);
		this.setLayout(null);
		
		btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCreate.setFocusPainted(false);
		btnCreate.setBounds(319, 491, 135, 51);
		this.add(btnCreate);
		
		txtrTypeNameHere = new JTextField();
		txtrTypeNameHere.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == 10)
					btnCreate.doClick();
			}
		});
		txtrTypeNameHere.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtrTypeNameHere.setFont(new Font("Arial", Font.PLAIN, 13));
		txtrTypeNameHere.setText("Type Name Here!");
		txtrTypeNameHere.setBounds(183, 22, 437, 42);
		this.add(txtrTypeNameHere);
		
		lblCharPic = new JLabel("");
		lblCharPic.setIcon(new ImageIcon(ViewCharacterSelect.class.getResource("/dungeon/Images/MissingnoMonster.png")));
		lblCharPic.setBounds(241, 194, 292, 208);
		this.add(lblCharPic);
		
		JPanel typeSelectionPanel = new JPanel();
		typeSelectionPanel.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		typeSelectionPanel.setBounds(10, 430, 754, 50);
		this.add(typeSelectionPanel);
		
		JRadioButton rdbtnWarrior = new JRadioButton("Warrior");
		rdbtnWarrior.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				updateCharImg();
			}
		});
		rdbtnWarrior.setFont(new Font("Arial", Font.PLAIN, 20));
		rdbtnWarrior.setFocusPainted(false);
		typeSelectionPanel.add(rdbtnWarrior);
		
		JRadioButton rdbtnSorceress = new JRadioButton("Sorceress");
		rdbtnSorceress.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				updateCharImg();
			}
		});
		rdbtnSorceress.setFont(new Font("Arial", Font.PLAIN, 20));
		rdbtnSorceress.setFocusPainted(false);
		typeSelectionPanel.add(rdbtnSorceress);
		
		JRadioButton rdbtnThief = new JRadioButton("Thief");
		rdbtnThief.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				updateCharImg();
			}
		});
		rdbtnThief.setFont(new Font("Arial", Font.PLAIN, 20));
		rdbtnThief.setFocusPainted(false);
		typeSelectionPanel.add(rdbtnThief);
		
		JRadioButton rdbtnKnight = new JRadioButton("Knight");
		rdbtnKnight.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				updateCharImg();
			}
		});
		rdbtnKnight.setFont(new Font("Arial", Font.PLAIN, 20));
		rdbtnKnight.setFocusPainted(false);
		typeSelectionPanel.add(rdbtnKnight);
		
		JRadioButton rdbtnBattleMage = new JRadioButton("Battle Mage");
		rdbtnBattleMage.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				updateCharImg();
			}
		});
		rdbtnBattleMage.setFont(new Font("Arial", Font.PLAIN, 20));
		rdbtnBattleMage.setFocusPainted(false);
		typeSelectionPanel.add(rdbtnBattleMage);
		
		JRadioButton rdbtnGameMaster = new JRadioButton("Game Master");
		rdbtnGameMaster.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				updateCharImg();
			}
		});
		rdbtnGameMaster.setFont(new Font("Arial", Font.PLAIN, 20));
		rdbtnGameMaster.setFocusPainted(false);
		typeSelectionPanel.add(rdbtnGameMaster);
		
		typeSelectGroup = new ButtonGroup();
		typeSelectGroup.add(rdbtnWarrior);
		typeSelectGroup.add(rdbtnSorceress);
		typeSelectGroup.add(rdbtnThief);
		typeSelectGroup.add(rdbtnKnight);
		typeSelectGroup.add(rdbtnBattleMage);
		typeSelectGroup.add(rdbtnGameMaster);
		
		typeBtns = new ArrayList<JRadioButton>();
		typeBtns.add(rdbtnWarrior);
		typeBtns.add(rdbtnThief);
		typeBtns.add(rdbtnSorceress);
		typeBtns.add(rdbtnKnight);
		typeBtns.add(rdbtnBattleMage);
		typeBtns.add(rdbtnGameMaster);		
		
		JPanel genderSelectionPanel = new JPanel();
		genderSelectionPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		genderSelectionPanel.setBounds(186, 108, 201, 33);
		this.add(genderSelectionPanel);
		genderSelectionPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				updateCharImg();
			}
		});
		rdbtnMale.setFocusPainted(false);
		genderSelectionPanel.add(rdbtnMale);
		rdbtnMale.setForeground(Color.BLACK);
		rdbtnMale.setOpaque(false);
		rdbtnMale.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				updateCharImg();
			}
		});
		rdbtnFemale.setFocusPainted(false);
		rdbtnFemale.setOpaque(false);
		rdbtnFemale.setForeground(Color.BLACK);
		rdbtnFemale.setFont(new Font("Arial", Font.PLAIN, 20));
		genderSelectionPanel.add(rdbtnFemale);
		
		genderSelectGroup = new ButtonGroup();
		genderSelectGroup.add(rdbtnMale);
		genderSelectGroup.add(rdbtnFemale);
		
		genderBtns = new ArrayList<JRadioButton>();
		genderBtns.add(rdbtnMale);
		genderBtns.add(rdbtnFemale);
		
		lblError = new JLabel("ERROR: Please make sure that the name is poperly filled in and you have selected both a Gender and Class");
		lblError.setOpaque(true);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Arial", Font.PLAIN, 15));
		lblError.setBounds(10, 146, 754, 33);
		this.add(lblError);
		lblError.setVisible(false);
		
		chckbxHardMode = new JCheckBox("Hard Mode");
		chckbxHardMode.setToolTipText("Enemies can regenerate health and your health will only regenerate through potions.");
		chckbxHardMode.setFocusPainted(false);
		chckbxHardMode.setFont(new Font("Arial", Font.PLAIN, 20));
		chckbxHardMode.setBounds(626, 25, 138, 33);
		this.add(chckbxHardMode);
	}
	
	private void updateCharImg()
	{
		for(JRadioButton check : typeBtns)
		{
			if(check.isSelected())
			{
				type = check.getText();
				break;
			}
		}
		
		for(JRadioButton check : genderBtns)
		{
			if(check.isSelected())
			{
				gender = check.getText();
				break;
			}
		}

		if(gender.equalsIgnoreCase("Female"))
			gender = "F";
		
		else
			gender = "M";
		
		if(type.length() == 0)
			return;
		
		String imgPath = "/dungeon/Images/Characters/" + type.replace(" ", "") + gender + ".png";
		
		if(type.equalsIgnoreCase("Game Master"))
			imgPath = "/dungeon/Images/Characters/" + type.replace(" ", "") + ".png";
		
		lblCharPic.setIcon(new ImageIcon(ViewCharacterSelect.class.getResource(imgPath)));
	}
	
	public void showError()
	{
		lblError.setVisible(true);
	}
	
	public JButton getbtnCreate()
	{
		return btnCreate;
	}
	
	public ArrayList<String> getHeroInfo()
	{
		ArrayList<String> info = new ArrayList<String>();
		
		if(type.length() != 0)
			info.add(type);
		
		if(!txtrTypeNameHere.getText().equalsIgnoreCase("Type Name Here!"))
			info.add(txtrTypeNameHere.getText());
		
		if(gender.length() != 0)
			info.add(gender);
		
		return info;		
	}
	
	public boolean getHardMode()
	{
		return chckbxHardMode.isSelected();
	}
}
