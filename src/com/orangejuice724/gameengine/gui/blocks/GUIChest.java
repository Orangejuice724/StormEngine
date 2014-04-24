package com.orangejuice724.gameengine.gui.blocks;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.orangejuice724.gameengine.graphics.Colours;
import com.orangejuice724.gameengine.graphics.Font;
import com.orangejuice724.gameengine.graphics.Screen;
import com.orangejuice724.gameengine.gui.Slot;
import com.orangejuice724.gameengine.gui.slots.SlotChest;
import com.orangejuice724.gameengine.items.Item;

public class GUIChest
{
	public boolean isOpened;
	private Slot slotChest;
	private BufferedImage image;
	private String itemName;
	
	public GUIChest()
	{
		try
		{
			image = ImageIO.read(this.getClass().getResource(
					"/gui/chest_big.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		slotChest = new SlotChest(0, 0);
	}
	
	public void tick()
	{
		slotChest.tick();
	}
	
	public void render(Screen screen, Graphics g)
	{
		if (isOpened)
		{
			// slotChest.renderItem(screen);
			g.drawImage(image, (screen.width / 2) + 50, 35, null);
		}
	}
	
	public void toggleChest()
	{
		System.out.println(isOpened);
		isOpened = !isOpened;
		System.out.println(isOpened);
	}
	
	public void openChest()
	{
		isOpened = true;
	}
	
	public void closeChest()
	{
		isOpened = false;
	}
	
	public SlotChest getChestSlot()
	{
		if (slotChest == null)
			return null;
		return (SlotChest) slotChest;
	}
	
	public void addItemToSlot(int itemId)
	{
		System.out.println("Hi");
		slotChest.addItem(itemId);
	}
}
