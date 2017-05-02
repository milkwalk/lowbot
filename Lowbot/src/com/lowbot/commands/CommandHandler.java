package com.lowbot.commands;

import com.lowbot.Main;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

public class CommandHandler {
	
	private final String PREFIX = "!";
	
	@EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) { 
	
		IMessage msg = event.getMessage();
		IChannel chan = msg.getChannel();
		IUser user = event.getAuthor();
		
		String mpref = event.getMessage().getContent().split(" ")[0];
		
		String final_command = "";
		
		if(mpref.contains(PREFIX)){
			mpref = mpref.substring(1);
			Main.getDebug().info("String is now " + mpref);			
			
			if(mpref.equals("help")){
				HelpCommand hl = new HelpCommand("Help", "help", Permissions.SEND_MESSAGES);
				hl.execute(msg);
			}
			
			mpref = mpref.substring(1, mpref.toCharArray().length-1);
			Main.getDebug().info("String is now " + mpref);
			
		}
		
		
		
	}
}
