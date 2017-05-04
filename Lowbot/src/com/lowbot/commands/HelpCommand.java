package com.lowbot.commands;

import com.lowbot.constructors.Command;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class HelpCommand implements Command {

	@Override
	public boolean onCommand(MessageReceivedEvent event) {

		
		StringBuilder sb = new StringBuilder();

		sb.append("Hi! I am Lowbot :) here are my commands: \n\n");
		sb.append("!lowbot - will show help menu\n");
		sb.append("!lowbot clear - will clear chat\n");
		sb.append("!lowbot join <name> - will make me join a VC\n");
		sb.append("!lowbot leave - will make me leave a VC\n");
		sb.append("!lowbot queue - will show a current queue for the music\n");
		sb.append("!lowbot play <url> - will put an url to the playlist\n");
		sb.append("!lowbot skip - will skip a song");

		event.getChannel().sendMessage(sb.toString());
		return true;
	}

}
