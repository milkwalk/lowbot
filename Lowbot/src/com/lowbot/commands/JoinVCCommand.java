package com.lowbot.commands;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.lowbot.LowbotAPI;
import com.lowbot.Main;
import com.lowbot.constructors.Command;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class JoinVCCommand implements Command{

	private LowbotAPI api = new LowbotAPI();
	
	@Override
	public boolean onCommand(MessageReceivedEvent event) {
		
		IMessage msg = event.getMessage();
		IVoiceChannel vchan;
		
		if(msg.getContent().split(" ").length == 3){
			vchan = msg.getGuild().getVoiceChannelsByName(msg.getContent().split(" ")[2]).get(0);
		}else{
			vchan = msg.getGuild().getVoiceChannelsByName("top").get(0);
		}

		vchan.join();
		
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResource("/resources/background.wav"));
			
			api.getServerInformation(event.getGuild()).getMusicPlayer().getPlayer().queue(inputStream);
			msg.getGuild().getAudioManager().setAudioProvider(api.getServerInformation(event.getGuild()).getMusicPlayer());
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	
	
}
