package com.lowbot.commands;

import com.lowbot.Main;
import com.lowbot.constructors.Command;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class LeaveVCCommand implements Command{

	@Override
	public boolean onCommand(MessageReceivedEvent event) {
		
		
		for(IVoiceChannel vc : Main.getBot().getConnectedVoiceChannels()){
			if(vc.getGuild() == event.getGuild()){
				vc.leave();
			}
		}
		return true;
	}

	
	
}
