package com.orangejuice724.gameengine.net.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.orangejuice724.gameengine.GameEngine;
import com.orangejuice724.gameengine.entities.player.PlayerMP;
import com.orangejuice724.gameengine.net.packets.Packet;
import com.orangejuice724.gameengine.net.packets.Packet.PacketTypes;
import com.orangejuice724.gameengine.net.packets.Packet00Login;
import com.orangejuice724.gameengine.net.packets.Packet01Disconnect;
import com.orangejuice724.gameengine.net.packets.Packet02Move;

public class GameServer extends Thread
{
	
	private DatagramSocket socket;
	private GameEngine gameEngine;
	private List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();
	
	public GameServer(GameEngine gameEngine)
	{
		this.gameEngine = gameEngine;
		try
		{
			this.socket = new DatagramSocket(1331);
		}
		catch (SocketException e)
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
					+ " has connected...");
			PlayerMP player = new PlayerMP(gameEngine.level, 30, 30,
					((Packet00Login) packet).getUsername(), address, port);
			this.addConnection(player, (Packet00Login) packet);
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(data);
			System.out.println("[" + address.getHostAddress() + ":" + port
					+ "] " + ((Packet01Disconnect) packet).getUsername()
					+ " has left...");
			this.removeConnection((Packet01Disconnect) packet);
			break;
		case MOVE:
			packet = new Packet02Move(data);
			System.out.println(((Packet02Move) packet).getUsername()
					+ " has moved to X:" + ((Packet02Move) packet).getX()
					+ ", Y:" + ((Packet02Move) packet).getY());
			this.handleMove(((Packet02Move)packet));
			break;
		}
	}

	public void addConnection(PlayerMP player, Packet00Login packet)
	{
		boolean alreadyConnected = false;
		for (PlayerMP p : this.connectedPlayers)
		{
			if (player.getUsername().equalsIgnoreCase(p.getUsername()))
			{
				if (p.ipAddress == null)
				{
					p.ipAddress = player.ipAddress;
				}
				if (p.port == -1)
				{
					p.port = player.port;
				}
				alreadyConnected = true;
			}
			else
			{
				// relay to the current connected player that there is a new
				// player
				sendData(packet.getData(), p.ipAddress, p.port);
				
				// relay to the new player that the currently connect player
				// exists
				packet = new Packet00Login(p.getUsername());
				sendData(packet.getData(), player.ipAddress, player.port);
			}
		}
		if (!alreadyConnected)
		{
			this.connectedPlayers.add(player);
		}
	}
	
	public void removeConnection(Packet01Disconnect packet)
	{
		this.connectedPlayers.remove(getPlayerMPIndex(packet.getUsername()));
		packet.writeData(this);
	}
	
	public PlayerMP getPlayerMP(String username)
	{
		for (PlayerMP player : this.connectedPlayers)
		{
			if (player.getUsername().equals(username))
				return player;
		}
		return null;
	}
	
	public int getPlayerMPIndex(String username)
	{
		int index = 0;
		for (PlayerMP player : this.connectedPlayers)
		{
			if (player.getUsername().equals(username))
			{
				break;
			}
			index++;
		}
		return index;
	}
	
	public void sendData(byte[] data, InetAddress ipAddress, int port)
	{
		DatagramPacket packet = new DatagramPacket(data, data.length,
				ipAddress, port);
		try
		{
			this.socket.send(packet);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void sendDataToAllClients(byte[] data)
	{
		for (PlayerMP p : connectedPlayers)
		{
			sendData(data, p.ipAddress, p.port);
		}
	}
	
	private void handleMove(Packet02Move packet)
	{
		if(getPlayerMP(packet.getUsername()) != null)
		{
			int index = getPlayerMPIndex(packet.getUsername());
			this.connectedPlayers.get(index).x = packet.getX();
			this.connectedPlayers.get(index).y = packet.getY();
			packet.writeData(this);
		}
				
	}
}