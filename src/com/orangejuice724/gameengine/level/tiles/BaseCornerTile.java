package com.orangejuice724.gameengine.level.tiles;

import com.orangejuice724.gameengine.graphics.Screen;
import com.orangejuice724.gameengine.level.Level;

public class BaseCornerTile extends BaseTile
{
	
	private int corner;
	
	public BaseCornerTile(int id, int x, int y, int tileColour, int levelColour, int corner)
	{
		super(id, x, y, tileColour, levelColour);
		this.solid = true;
		this.corner = corner;
	}
	
	public void render(Screen screen, Level level, int x, int y)
	{
		if(corner == 0)	screen.render(x, y, tileId, tileColour, 0x00, 1);
		if(corner == 1)	screen.render(x, y, tileId, tileColour, 0x01, 1);
		if(corner == 2)	screen.render(x, y, tileId, tileColour, 0x10, 1);
		if(corner == 3)	screen.render(x, y, tileId, tileColour, 0x11, 1);
	}
	
}
