package com.lowbot.commands;

import java.util.Iterator;
import java.util.Map;

import com.google.api.services.youtube.model.Playlist;
import com.lowbot.LowbotAPI;
import com.lowbot.constructors.Command;
import com.lowbot.constructors.MusicPlayer;
import com.lowbot.constructors.ServerInformation;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.audio.AudioPlayer.Track;

public class QueueCommand implements Command {

	private LowbotAPI api = new LowbotAPI();
		
	@Override
	public boolean onCommand(MessageReceivedEvent event) {
		ServerInformation iserver = api.getServerInformation(event.getGuild());
		MusicPlayer player = iserver.getMusicPlayer();
		Playlist list;
		
		String qlist = "Current queue of the music: \n";
		
		return true;
	}

}
