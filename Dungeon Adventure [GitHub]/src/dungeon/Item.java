package dungeon;

public abstract class Item 
{
	private String name;
	private String description;
	private String picPath;
	
	public Item(String name, String description, String picPath)
	{
		this.name = name;
		this.description = description;
		this.picPath = picPath;
	}

	public String getName() 
	{
		return name;
	}

	public String getDescription() 
	{
		return description;
	}
	
	public String getPicPath()
	{
		return picPath;
	}
}
