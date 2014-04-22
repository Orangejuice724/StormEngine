package com.orangejuice724.gameengine.gui;

import com.orangejuice724.gameengine.graphics.Screen;
import com.orangejuice724.gameengine.items.Item;

public abstract class Slot
{
	public static final Slot[] slots = new Slot[256];
	
	protected int x, y;
	public Item item;
	
	public Slot(int x, int y)
	{
		
	}
	
	public void addItem(int id)
	{
		if(item == null)
		{
			Item.getItem(id);
		}
	}
	
	public void renderItem(Screen screen)
	{
		
	}
}
