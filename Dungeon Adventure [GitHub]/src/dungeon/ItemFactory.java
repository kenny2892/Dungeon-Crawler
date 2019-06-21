package dungeon;

public class ItemFactory 
{
	public enum ItemTypes 
	{
		Healing, Vision;
	}
	
	public Item getItem(ItemTypes type)
	{
		switch(type)
		{
			case Healing: 
				return new HealingPotion();

			case Vision: 
				return new VisionPotion();
				
			default: 
				return new HealingPotion();
		}
	}
}
