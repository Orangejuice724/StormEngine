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
	public static final Tile WOODPLANKS = new BaseTile(4, 3, 0, Colours.get(
			320, 431, 542, -1), 0xfff0f0f0);
	public static final Tile CHEST = new TileEntity(5, 0, 1, Colours.get(131,
			431, 542, 550), 0xff123456);
	public static final Tile FLOWER = new BaseTile(6, 4, 0, Colours.get(-1, 131,
			141, 550), 0xff000f00);
	public static final Tile BRICK = new BaseTile(7, 5, 0, Colours.get(-1, 232,
			454, -1), 0xffCC6699);
	public static final Tile FENCE = new BaseSolidTile(8, 6, 0, Colours.get(-1,
			131, 121, 555), 0xffCCFF99);
	public static final Tile SIDE_FENCE = new BaseSolidTile(9, 9, 0, Colours.get(-1,
			131, 121, 555), 0xFFCCFF88);
	public static final Tile CORNER_FENCE_0 = new BaseCornerTile(10, 8, 0, Colours.get(-1,
			131, 121, 555), 0xFFCCFF77, 0);
	public static final Tile CORNER_FENCE_1 = new BaseCornerTile(11, 8, 0, Colours.get(-1,
			131, 121, 555), 0xFFCCFF66, 1);
	public static final Tile CORNER_FENCE_2 = new BaseCornerTile(12, 8, 0, Colours.get(-1,
			131, 121, 555), 0xFFCCFF55, 2);
	public static final Tile CORNER_FENCE_3 = new BaseCornerTile(13, 8, 0, Colours.get(-1,
			131, 121, 555), 0xFFCCFF44, 3);
	
	protected byte id;
	protected boolean solid;
	protected boolean emitter;
	protected boolean entity;
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
