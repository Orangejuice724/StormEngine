package com.orangejuice724.gameengine.graphics;

public class Colours
{
	public static int get(int colour1, int colour2, int colour3, int colour4)
	{
		return (get(colour4) << 24) + (get(colour3) << 16)
				+ (get(colour2) << 8) + (get(colour1));
	}
	
	public static int get(int colour1, int colour2, int colour3, int colour4, int colour5, int colour6)
	{
		return (get(colour6) << 64) + (get(colour5) << 32) + (get(colour4) << 24) + (get(colour3) << 16)
				+ (get(colour2) << 8) + (get(colour1));
	}

	private static int get(int colour)
	{
		if (colour < 0)
			return 255;
		int r = colour / 100 % 10;
		int g = colour / 10 % 10;
		int b = colour % 10;
		return r * 36 + g * 6 + b;
	}
}
