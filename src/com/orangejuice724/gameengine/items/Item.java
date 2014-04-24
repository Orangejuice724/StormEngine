package com.orangejuice724.gameengine.items;

import com.orangejuice724.gameengine.graphics.Colours;
import com.orangejuice724.gameengine.graphics.Screen;

public abstract class Item
{
	public static final Item[] items =  new Item[256];
	
	public static final Item itemStick = new ItemBase(0, "Stick", 0, 8, Colours.get(-1, 321, 000, -1));
	
	protected byte id;
	protected boolean shouldRender;
	protected String itemName;
	protected int tileId;
	
	public Item(int id, String name, int tileId)
	{
		this.id = (byte)id;
		items[id] = this;
		items[id].tileId = tileId;
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
	
	public String getName()
	{
		return itemName;
	}
	
	public Item getItem()
	{
		return items[id];
	}
	
	public static Item getItem(int id)
	{
		return items[id];
	}
	
	public int getTileID()
	{
		return tileId;
	}
}
