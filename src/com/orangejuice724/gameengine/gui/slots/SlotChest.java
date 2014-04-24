package com.orangejuice724.gameengine.gui.slots;

import com.orangejuice724.gameengine.gui.Slot;

public class SlotChest extends Slot
{

	public SlotChest(int id, int x, int y)
	{
		super(x, y, id);
	}
	
	public void tick()
	{
		this.x = 50;
		this.y = 10;
	}
}
