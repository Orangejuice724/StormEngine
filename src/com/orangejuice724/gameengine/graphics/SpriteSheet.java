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
			image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
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
		
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		
		for(int i = 0; i < pixels.length; i++)
		{
			/*
			 * remove the alpha channel
			 * 0xff000000 = 0x000000
			 */
			pixels[i] = (pixels[i] & 0xff)/64;
		}
		
		for(int i = 0; i < 8; i++)
		{
			System.out.println(pixels[i]);
		}
	}
}
