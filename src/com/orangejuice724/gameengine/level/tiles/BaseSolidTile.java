package com.orangejuice724.gameengine.level.tiles;


public class BaseSolidTile extends BaseTile
{

	public BaseSolidTile(int id, int x, int y, int tileColour, int levelColour)
	{
		super(id, x, y, tileColour, levelColour);
		this.solid = true;
	}
}
