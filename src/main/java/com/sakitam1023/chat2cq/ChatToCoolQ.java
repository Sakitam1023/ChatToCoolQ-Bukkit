package com.sakitam1023.chat2cq;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.java_websocket.drafts.Draft_6455;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class ChatToCoolQ extends JavaPlugin{
	public static Optional<Logger> LOGGER = Optional.empty();
	private static PluginListener webSocketClient = null;
	
	@Override
	public void onEnable() {
		LOGGER = Optional.of(getLogger());
		this.getLogger().info("插件开启成功");
		
		String URI = "ws://localhost:25303";
		
		if(webSocketClient == null) {
			try {
				webSocketClient = new PluginListener(new URI(URI), new Draft_6455());
			} catch (URISyntaxException e) {
				this.getLogger().log(Level.SEVERE, "this should never happen!", e);
			}
		}
		webSocketClient.connect();

		Bukkit.getPluginManager().registerEvents(webSocketClient, this);
	}
	
	@Override
	public void onDisable() {
		
	}

	public static void info(String msg) {
		LOGGER.ifPresent((l) -> l.info(msg));
	}
}
