package com.lowbot.events;

import com.lowbot.Main;
import com.lowbot.constructors.ServerInformation;

import sx.blah.discord.Discord4J;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IGuild;

public class EnableEvent {

    @EventSubscriber
    public void onReady(ReadyEvent event) {//event fired when logged in
        Discord4J.LOGGER.info("Bot starting");
        
		for (IGuild g : Main.getBot().getGuilds()) {
			Main.getServers().put(g.getLongID(), new ServerInformation(g));
		}
        
        Discord4J.LOGGER.info("Bot ready/Servers information loaded");
    }
}
