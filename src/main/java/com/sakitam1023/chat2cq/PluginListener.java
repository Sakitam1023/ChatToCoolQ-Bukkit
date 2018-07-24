package com.sakitam1023.chat2cq;

import com.google.gson.Gson;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.logging.Level;

public class PluginListener extends WebSocketClient implements Listener{

	PluginListener(URI serverUri, Draft draft) {
		super(serverUri, draft);
	}

	@EventHandler()
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		sendMessage(event.getPlayer().getName() + ":" + event.getMessage());
		//Bukkit.getServer().broadcastMessage(event.getPlayer().getName() + ":" + event.getMessage());
	}

	private void sendMessage(String str) {
		AskQQMessage askMessage = new AskQQMessage();
		askMessage.setAct("101");
		askMessage.setGroupid("673221698"); // are you really want to hardcode?
		askMessage.setMsg(str);
		String ask = new Gson().toJson(askMessage);
		send(ask);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		ChatToCoolQ.info("Connected to CoolQ! " + handshakedata.getHttpStatus() + " " + handshakedata.getHttpStatusMessage());
	}

	@Override
	public void onMessage(String message) {
		// ChatToCoolQ.info("Message received! " + message);
	} // incoming message ignored

	@Override
	public void onClose(int code, String reason, boolean remote) {
		ChatToCoolQ.info("Disconnected from CoolQ! code:" + code + " reason: " + reason + " remote:" + remote);
	}

	@Override
	public void onError(Exception ex) {
		ChatToCoolQ.LOGGER.ifPresent((l) -> l.log(Level.WARNING, "Exception occurred in WebSocket!", ex));
	}
}

//Bukkit.getServer().broadcastMessage(event.getPlayer().getName() + ":" + event.getMessage());