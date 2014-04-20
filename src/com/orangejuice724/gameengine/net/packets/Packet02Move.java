package com.orangejuice724.gameengine.net.packets;

import com.orangejuice724.gameengine.net.GameClient;
import com.orangejuice724.gameengine.net.server.GameServer;

public class Packet02Move extends Packet
{
	
	private String username;
	private int x, y;
	
	public Packet02Move(byte[] data)
	{
		super(02);
		this.username = readData(data);
	}
	
	public Packet02Move(String username)
	{
		super(02);
		this.username = username;
	}
	
	@Override
	public void writeData(GameClient client)
	{
		client.sendData(getData());
	}
	
	@Override
	public void writeData(GameServer server)
	{
		server.sendDataToAllClients(getData());
	}
	
	@Override
	public byte[] getData()
	{
		return ("02" + this.username).getBytes();
	}
	
	public String getUsername()
	{
		return username;
	}
}
