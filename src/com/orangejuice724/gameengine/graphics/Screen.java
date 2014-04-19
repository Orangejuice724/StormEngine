package com.orangejuice724.gameengine.graphics;

public class Screen 
{
	public static final int MAP_WIDTH = 64;
	public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;
	
	public int[] pixels;
	
	public int xOffset = 0;
	public int yOffset = 0;
	
	public int width;
	public int height;
	
	public SpriteSheet sheet;
	
	public Screen(int width, int height, SpriteSheet sheet)
	{
		this.width = width;
		this.height = height;
		this.sheet = sheet;
		
		pixels = new int[width*height];
	}
	
	public void render(int[] pixels, int offset, int row)
	{
		for(int yTile = yOffset>>3; yTile <= (yOffset+height)>>3; yTile++)
		{
			int yMin = yTile * 8 - yOffset;
			int yMax = yMin + 8;
			if(yMin < 0) yMin = 0;
			if(yMax > height) yMax = height;
			
			for(int xTile = xOffset>>3; xTile <= (xOffset+width)>>3; xTile++)
			{
				int xMin = xTile * 8 - xOffset;
				int xMax = xMin + 8;
				if(xMin < 0) xMin = 0;
				if(xMax > width) xMax = width;
				
				int tileIndex = (xTile & (MAP_WIDTH_MASK)) + (yTile & (MAP_WIDTH_MASK)) * MAP_WIDTH;
				
				for(int y = yMin; y < yMax; y++)
				{
					int sheetPixel = ((y+yOffset) & 7) * sheet.width + ((xMin + xOffset) & 7);
					int tilePixel = offset + xMin + y * row;
					for(int x = xMin; x < xMax; x++)
					{
						int colour = tileIndex * 4 + sheet.pixels[sheetPixel++];
						pixels[tilePixel++] = colours[colour];
					}
				}
			}
		}
	}
	
	public void render(int xPos, int yPos, int tile, int colour)
	{
		xPos -= xOffset;
		yPos -= yOffset;
		
		int xTile = tile % 32;
		int yTile = tile / 32; 
	}
}
