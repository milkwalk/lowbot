package com.lowbot.commands;

import com.lowbot.Command;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.Permissions;

public class HelpCommand extends Command {

	public HelpCommand(String cmdName, String prefix, Permissions perm) {
		super(cmdName, prefix, perm);		
	}
	
	public void execute(IMessage img){
		
		
		
		String str = "This is a Lowbot ! :) \nCommands are the following: \n!clear";
		img.getChannel().sendMessage(str);
		
	}

}
