package com.lowbot.constructors;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.audio.AudioPlayer;

public class ServerInformation {
		
		//class for some bullshit for differents servers ^^
		private IGuild server;
		private MusicPlayer player;
		
		public ServerInformation(IGuild server){
			this.server = server;
			this.player = new MusicPlayer(new AudioPlayer(server));
		}
		
		public MusicPlayer getMusicPlayer(){
			System.out.println(server.getName());
			return player;
		}
		
		public IGuild getServer(){
			return server;
		}
}
