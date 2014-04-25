package com.orangejuice724.gameengine.level.tiles;

public class BaseCornerTile extends BaseTile
{
	public BaseCornerTile(int id, int x, int y, int tileColour, int levelColour)
	{
		super(id, x, y, tileColour, levelColour);
		this.solid = true;
	}
}
