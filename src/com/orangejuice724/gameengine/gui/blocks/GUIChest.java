package com.orangejuice724.gameengine.gui.blocks;

import com.orangejuice724.gameengine.graphics.Screen;
import com.orangejuice724.gameengine.gui.Slot;
import com.orangejuice724.gameengine.gui.slots.SlotChest;

public class GUIChest
{
	public boolean isOpened;
	private Slot slotChest = new SlotChest(0, 0);
	private BufferedImage image;
	
	public GUIChest()
	{
		
	}
	
	public void tick()
	{
		
	}
	
	public void render(Screen screen)
	{
		if(isOpened)
		{
			slotChest.renderItem(screen);
		}
	}
	
	public void toggleChest()
	{
		isOpened = !isOpened;
	}
	
	public Slot getChestSlot()
	{
		return slotChest;
	}
}
