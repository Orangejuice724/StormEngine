package com.orangejuice724.gameengine.window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.orangejuice724.gameengine.GameEngine;
import com.orangejuice724.gameengine.net.packets.Packet01Disconnect;

public class WindowHandler implements WindowListener
{
	
	private final GameEngine gameEngine;
	
	public WindowHandler(GameEngine gameEngine)
	{
		this.gameEngine = gameEngine;
		this.gameEngine.frame.addWindowListener(this);
	}
	
	@Override
    public void windowActivated(WindowEvent event) {
    }

    @Override
    public void windowClosed(WindowEvent event) {
    }

    @Override
    public void windowClosing(WindowEvent event) {
        Packet01Disconnect packet = new Packet01Disconnect(this.gameEngine.player.getUsername());
        packet.writeData(this.gameEngine.socketClient);
    }

    @Override
    public void windowDeactivated(WindowEvent event) {
    }

    @Override
    public void windowDeiconified(WindowEvent event) {
    }

    @Override
    public void windowIconified(WindowEvent event) {
    }

    @Override
    public void windowOpened(WindowEvent event) {
    }
	
}
