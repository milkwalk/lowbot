package com.lowbot.commands;

import com.lowbot.constructors.Command;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class HelpCommand implements Command {

	@Override
	public boolean onCommand(MessageReceivedEvent event) {

		String str = "Hi! I am Lowbot :) here are my commands: \n\n" 
				+ "!lowbot - will show help menu\n"
				+ "!lowbot clear - will clear chat\n" 
				+ "!lowbot join <name> - will make me join a VC\n"
				+ "!lowbot leave - will make me leave a VC\n"
				+ "!lowbot queue - will show a current queue for the music\n"
				+ "!lowbot play <url> - will put an url to the playlist\n"
				+ "!lowbot skip - will skip a song";

		event.getChannel().sendMessage(str);
		return true;
	}

}
