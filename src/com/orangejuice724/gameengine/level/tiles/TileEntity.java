package com.orangejuice724.gameengine.level.tiles;

public class TileEntity extends BaseTile
{

	public TileEntity(int id, int x, int y, int tileColour, int levelColour)
	{
		super(id, x, y, tileColour, levelColour);
		this.solid = true;
		this.entity = true;
	}
	
}
