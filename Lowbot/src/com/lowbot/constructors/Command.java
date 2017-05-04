package com.lowbot.constructors;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public interface Command {
	public boolean onCommand(MessageReceivedEvent event);
}
