package dungeon;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ViewMenu extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private JButton btnStart;
	
	public ViewMenu(JPanel mainPanel)
	{
		this.setOpaque(false);
		this.setBounds(10, 11, 774, 549);
		mainPanel.add(this);
		this.setLayout(null);
		
		btnStart = new JButton("Start");
		btnStart.setFont(new Font("Arial", Font.PLAIN, 20));
		btnStart.setFocusPainted(false);
		btnStart.setBounds(329, 436, 135, 51);
		this.add(btnStart);
	}
	
	public JButton getBtnStart()
	{
		return btnStart;
	}
}
