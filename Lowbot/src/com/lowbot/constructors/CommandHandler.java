package com.lowbot.constructors;

import java.util.HashMap;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class CommandHandler {

	private final String PREFIX = "!lowbot";
	private HashMap<String, Command> cmds = new HashMap<String, Command>();

	// method for registering commands
	public void registerCommand(String prefix, Command cmd) {
		cmds.put(prefix, cmd);
	}

	// method to get an executor
	public Command getExecutor(String name) {
		return cmds.get(name);
	}

	public boolean contains(String arg) {
		return cmds.containsKey(arg);
	}

	@EventSubscriber
	public void onMessageReceivedEvent(MessageReceivedEvent event) {

		// declaring some information
		IMessage msg = event.getMessage();
		// IChannel chan = msg.getChannel();
		// IUser user = event.getAuthor();

		// detecting if that is a bot command at all
		if (msg.getContent().startsWith(PREFIX) || msg.getContent().startsWith("!lb")) {
			if (msg.getContent().split(" ").length == 1) {
				// this is a command without any arguments ie !lowbot ^^
				// help command will be shown here
				getExecutor("help").onCommand(event);
			} else if (msg.getContent().split(" ").length > 1) {

				String[] split = msg.getContent().split(" ");

				if (contains(split[1])) {
					getExecutor(split[1]).onCommand(event);
				} else {
					msg.getChannel()
							.sendMessage("This command does not exist!\n" + "Type !lowbot for more information.");
				}
			}
		}
	}
}
