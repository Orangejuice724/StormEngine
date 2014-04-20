package com.orangejuice724.gameengine.level.tiles;

import com.orangejuice724.gameengine.graphics.Colours;
import com.orangejuice724.gameengine.graphics.Screen;
import com.orangejuice724.gameengine.level.Level;

public abstract class Tile
{
	public static final Tile[] tiles = new Tile[256];
	public static final Tile VOID = new BaseSolidTile(0, 0, 0, Colours.get(000,
			-1, -1, -1), 0xff000000);
	public static final Tile STONE = new BaseSolidTile(1, 1, 0, Colours.get(-1,
			333, -1, -1), 0xff555555);
	public static final Tile GRASS = new BaseTile(2, 2, 0, Colours.get(-1, 131,
			141, -1), 0xff00ff00);
	public static final Tile WATER = new AnimatedTile(3, new int[][]
	{
	{ 0, 5 },
	{ 1, 5 },
	{ 2, 5 },
	{ 1, 5 } }, Colours.get(-1, 004, 115, -1), 0xff0000ff, 1000);
	
	protected byte id;
	protected boolean solid;
	protected boolean emitter;
	private int levelColour;
	
	public Tile(int id, boolean isSolid, boolean isEmitter, int levelColour)
	{
		this.id = (byte) id;
		if (tiles[id] != null)
			throw new RuntimeException("Duplicate tile id on " + id);
		this.solid = isSolid;
		this.emitter = isEmitter;
		this.levelColour = levelColour;
		tiles[id] = this;
	}
	
	public byte getID()
	{
		return id;
	}
	
	public boolean isSolid()
	{
		return solid;
	}
	
	public boolean isEmitter()
	{
		return emitter;
	}
	
	public int getLevelColour()
	{
		return levelColour;
	}
	
	public abstract void tick();
	
	public abstract void render(Screen screen, Level level, int x, int y);
}
