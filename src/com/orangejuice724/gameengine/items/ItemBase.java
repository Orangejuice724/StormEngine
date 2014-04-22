package com.orangejuice724.gameengine.items;

import com.orangejuice724.gameengine.graphics.Screen;

public class ItemBase extends Item
{

	protected int tileId;
	protected int tileColour;
	
	public ItemBase(int id, String name, int tileX, int tileY)
	{
		super(id, name);
		this.tileId = tileX+tileY*32;
	}

	@Override
	public void render(Screen screen, int id)
	{
		if(shouldRender)
		{
			
		}
	}
	
}
