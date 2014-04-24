package com.orangejuice724.gameengine.gui;

import com.orangejuice724.gameengine.graphics.Colours;
import com.orangejuice724.gameengine.graphics.Screen;
import com.orangejuice724.gameengine.gui.slots.SlotChest;
import com.orangejuice724.gameengine.items.Item;

public abstract class Slot
{
	public static final Slot[] slots = new Slot[256];
	
	public static final Slot slotChest = new SlotChest(0, 0, 0);
	
	protected int x, y;
	public Item item;
	
	public Slot(int id, int x, int y)
	{
		this.x = x;
		this.y = y;
		slots[id] = this;
	}
	
	public abstract void tick();
	
	public void addItem(int id, int slotId)
	{
		item = Item.items[id];
	}
	
	public void renderItem(Screen screen, int slotId)
	{
		screen.render(x, y, item.getTileID(), Colours.get(-1, 321, 000, -1), 0,
				1);
	}
}
