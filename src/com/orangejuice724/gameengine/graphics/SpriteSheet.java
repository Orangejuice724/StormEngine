package com.orangejuice724.gameengine.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet 
{
	public String path;
	public int width;
	public int height;
	
	public int[] pixels;
	
	public SpriteSheet(String path)
	{
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(SpriteSheet.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(image == null)
		{
			return;
		}
		
		this.path = path;
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
		
		for(int i = 0; i < pixels.length; i++)
		{
			/*
			 * remove the alpha channel
			 * 0xff000000 = 0x000000
			 */
			pixels[i] = (pixels[i] & 0xff)/64;
		}
		
//		pixels = new int[width * height];
//		
//		for (int i = 0; i < width * height; i++) {
//			int r = (pixels[i] & 0xff0000) >> 16;
//			int g = (pixels[i] & 0xff00) >> 8;
//			int b = (pixels[i] & 0xff);
//			pixels[i] = r << 16 | g << 8 | b;
//		}
	}
}
