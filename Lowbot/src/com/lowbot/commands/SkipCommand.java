package com.lowbot.commands;

import com.lowbot.Main;
import com.lowbot.constructors.Command;
import com.lowbot.constructors.ServerInformation;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class SkipCommand implements Command {

	@Override
	public boolean onCommand(MessageReceivedEvent event) {
		
		ServerInformation iserver = Main.getApi().getServerInformation(event.getGuild());
		iserver.getMusicPlayer().getPlayer().skip();
		
		event.getChannel().sendMessage("Skipped");
		return true;
	}

}
