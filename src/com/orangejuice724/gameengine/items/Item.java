package com.orangejuice724.gameengine.items;

import com.orangejuice724.gameengine.graphics.Screen;

public abstract class Item
{
	public static final Item[] items =  new Item[256];
	
	public Item itemStick = new ItemBase(0, "Stick", 0, 8);
	
	protected byte id;
	protected boolean shouldRender;
	protected String itemName;
	
	public Item(int id, String name)
	{
		this.id = (byte)id;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public abstract void render(Screen screen, int id);
	
	public static int getID(String name)
	{
		for(int i = 0; i < items.length; i ++)
		{
			if(items[i].itemName.equalsIgnoreCase(name))
				return items[i].id;
		}
		return -1;
	}
	
	public static String getName(int id)
	{
		return items[id].itemName;
	}
	
	public static Item getItem(int id)
	{
		return items[id];
	}
}
