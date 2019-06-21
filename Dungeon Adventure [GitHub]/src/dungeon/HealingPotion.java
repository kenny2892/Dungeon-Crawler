package dungeon;
import java.util.Random;

public class HealingPotion extends Item
{
	public HealingPotion() 
	{
		super("Healing Potion", "A potion that can heal 5 - 15 hit points instantly!", "/Resources/Images/HpPot.png");
	}

	public int heal()
	{
		Random r = new Random();
		return r.nextInt(11) + 5;
	}
}
