package com.orangejuice724.gameengine.level.tiles;

import com.orangejuice724.gameengine.graphics.Screen;
import com.orangejuice724.gameengine.level.Level;


public class BaseTile extends Tile
{
	protected int tileId;
	protected int tileColour;
	
	public BaseTile(int id, int x, int y, int tileColour, int levelColour)
	{
		super(id, false, false, levelColour);
		this.tileId = x+y;
		this.tileColour = tileColour;
	}
	
	public void render(Screen screen, Level level, int x, int y)
	{
		screen.render(x, y, tileId, tileColour, 0x00, 1);
	}
}
