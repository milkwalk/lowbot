package com.lowbot.commands;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.UnsupportedAudioFileException;

import com.github.axet.vget.VGet;
import com.lowbot.Main;
import com.lowbot.constructors.Command;
import com.lowbot.constructors.ServerInformation;
import com.lowbot.constructors.YouTubeAPI;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class PlayCommand implements Command{

	YouTubeAPI yapi = new YouTubeAPI();
	
	@Override
	public boolean onCommand(MessageReceivedEvent event) {
		IMessage msg = event.getMessage();
		
		if(msg.getContent().split(" ").length == 3){
		ServerInformation iserver = Main.getApi().getServerInformation(event.getGuild());
		
		String input = msg.getContent().split(" ")[2];
		boolean isURL = false;
		URL wurl;
		try {
			wurl = new URL(input);
			isURL = true;
		} catch (MalformedURLException e) {
			msg.getChannel().sendMessage(":octagonal_sign:  URL '" + input +"' is not valid! Will search something in YT...");
		}
		
        System.out.println("1");
		String url = "";
		
		if(isURL){
			url = input;
		}else{
			try {
				 url = yapi.search(input);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("2");
	File file;
	 VGet v;
	 
       try {
           // ex: "/Users/axet/Downloads"
            v = new VGet(new URL(url), new File("C:/Users/Vids"));
           v.download();
           System.out.println("downloading...");
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
       
       file = v.getTarget();
       
		try {
			iserver.getMusicPlayer().getPlayer().queue(file);
			iserver.getMusicPlayer().getPlayer().skip();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else{
			event.getChannel().sendMessage("To queue a song, type !lowbot play url");
		}
		return true;
	}

}
