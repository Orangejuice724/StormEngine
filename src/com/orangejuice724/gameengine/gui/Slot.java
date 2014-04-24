package com.orangejuice724.gameengine.gui;

import com.orangejuice724.gameengine.graphics.Colours;
import com.orangejuice724.gameengine.graphics.Screen;
import com.orangejuice724.gameengine.items.Item;
import com.orangejuice724.gameengine.items.ItemBase;

public abstract class Slot
{
	public static final Slot[] slots = new Slot[256];
	
	protected int x, y;
	public ItemBase item;
	
	public Slot(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	
	public void addItem(int id)
	{
		if(item == null)
		{
			Item.getItem(id);
		}
	}
	
	public void renderItem(Screen screen)
	{
		screen.render(x, y, (0+8)*32, Colours.get(-1, 321, 000, -1), 0, 1);
		
	}
}
