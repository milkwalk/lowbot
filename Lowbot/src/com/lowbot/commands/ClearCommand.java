package com.lowbot.commands;

import com.lowbot.constructors.Command;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class ClearCommand implements Command{

	@Override
	public boolean onCommand(MessageReceivedEvent event) {
		event.getChannel().bulkDelete();		
		return true;
	}

}
