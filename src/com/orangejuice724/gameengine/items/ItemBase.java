package com.orangejuice724.gameengine.items;

import com.orangejuice724.gameengine.graphics.Screen;

public class ItemBase extends Item
{
	
	public int tileId;
	public int tileColour;
	
	public ItemBase(int id, String name, int tileX, int tileY, int itemColour)
	{
		super(id, name, (tileX + tileY)*32);
		this.tileId = tileX + tileY * 32;
		tileColour = itemColour;
	}
	
	@Override
	public void render(Screen screen, int id)
	{
		if (shouldRender)
		{
			
		}
	}
	
}
