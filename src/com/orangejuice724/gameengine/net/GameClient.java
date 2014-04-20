package com.orangejuice724.gameengine.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.orangejuice724.gameengine.GameEngine;
import com.orangejuice724.gameengine.entities.player.PlayerMP;
import com.orangejuice724.gameengine.net.packets.Packet;
import com.orangejuice724.gameengine.net.packets.Packet.PacketTypes;
import com.orangejuice724.gameengine.net.packets.Packet00Login;
import com.orangejuice724.gameengine.net.packets.Packet01Disconnect;

public class GameClient extends Thread
{
	
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private GameEngine gameEngine;
	
	public GameClient(GameEngine gameEngine, String ipAddress)
	{
		this.gameEngine = gameEngine;
		try
		{
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		while (true)
		{
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try
			{
				socket.receive(packet);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			this.parsePacket(packet.getData(), packet.getAddress(),
					packet.getPort());
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port)
	{
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		Packet packet = null;
		switch (type)
		{
		default:
		case INVALID:
			break;
		case LOGIN:
			packet = new Packet00Login(data);
			System.out.println("[" + address.getHostAddress() + ":" + port
					+ "] " + ((Packet00Login) packet).getUsername()
					+ " has joined the game...");
			PlayerMP player = new PlayerMP(gameEngine.level, 30, 30,
					((Packet00Login) packet).getUsername(), address, port);
			gameEngine.level.addEntity(player);
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(data);
			System.out.println("[" + address.getHostAddress() + ":" + port
					+ "] " + ((Packet01Disconnect) packet).getUsername()
					+ " has left the world...");
			gameEngine.level.removePlayerMP(((Packet01Disconnect)packet).getUsername());
			break;
		}
	}
	
	public void sendData(byte[] data)
	{
		DatagramPacket packet = new DatagramPacket(data, data.length,
				ipAddress, 1331);
		try
		{
			socket.send(packet);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}