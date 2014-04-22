package com.orangejuice724.gameengine.gui.slots;

import com.orangejuice724.gameengine.gui.Slot;

public class SlotChest extends Slot
{

	public SlotChest(int x, int y)
	{
		super(x, y);
	}
	
	public void tick()
	{
		this.x = 50;
		this.y = 10;
	}
}
