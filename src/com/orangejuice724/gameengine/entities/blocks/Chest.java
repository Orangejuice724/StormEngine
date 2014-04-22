package com.orangejuice724.gameengine.entities.blocks;

import javax.swing.JOptionPane;

import com.orangejuice724.gameengine.entities.Entity;
import com.orangejuice724.gameengine.entities.player.Player;
import com.orangejuice724.gameengine.graphics.Colours;
import com.orangejuice724.gameengine.graphics.Font;
import com.orangejuice724.gameengine.graphics.Screen;
import com.orangejuice724.gameengine.gui.blocks.GUIChest;
import com.orangejuice724.gameengine.input.InputHandler;
import com.orangejuice724.gameengine.level.Level;

public class Chest extends Entity
{
	
	private int scale = 1;
	private int colour = Colours.get(-1, 431, 542, 550);
	private Player player;
	private InputHandler input;
	private boolean canOpenChest = true;
	private int id;
	private GUIChest guiChest;
	
	public Chest(Level level, int x, int y, Player player, InputHandler input,
			int id)
	{
		super(level);
		this.x = x;
		this.y = y;
		this.player = player;
		this.input = input;
		this.id = id;
		generateContents();
		guiChest = new GUIChest();
	}
	
	@Override
	public void tick()
	{
		if ((player.x - x) + (player.y - y) < 5)
		{
			if (input.keyE.isPressed() && canOpenChest)
				openChest();
		}
	}
	
	@Override
	public void render(Screen screen)
	{
		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;
		
		screen.render(xOffset, yOffset, (0 + 1) * 32, colour, 0, scale);
		if ((player.x - x) + (player.y - y) < 10 && canOpenChest)
		{
			String message = "Press E to Open";
			Font.render(message, screen, x - (32), yOffset - 10,
					Colours.get(-1, 555, 0, 0), scale);
		}
	}
	
	public void openChest()
	{
		JOptionPane.showConfirmDialog(null, "Lol this does nothing");
		canOpenChest = false;
	}
	
	public int getID()
	{
		return id;
	}
	
	public void generateContents()
	{
		guiChest.getChestSlot().addItem(0);
	}
}
