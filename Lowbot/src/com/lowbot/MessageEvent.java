package com.lowbot;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

public class MessageEvent {
	
	LowbotAPI api = new LowbotAPI();
	
	@EventSubscriber
	public void onJoinServer(UserJoinEvent e){
		IUser user = e.getUser();
		
		user.getOrCreatePMChannel().sendMessage("Welcome to the " + e.getGuild().getName() + "!");
	}
	
	@EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) { 
		IMessage msg = event.getMessage();
		IChannel chan = msg.getChannel();
		IUser user = event.getAuthor();
		
		Main.getBot().getOurUser().getClient().changePlayingText("Type !help");	
		
		
		if(msg.getContent().equals("hi")){
			chan.sendMessage("hi " + event.getAuthor());
			chan.sendMessage("Kenny is the best");
		}else if(msg.getMentions().contains(Main.getBot().getOurUser())){
			
			chan.sendMessage("Lowbot");
		}
		
		if(msg.getContent().equalsIgnoreCase("!clear") && api.hasPermission(Permissions.ADMINISTRATOR, user)){
			chan.bulkDelete();
			chan.sendMessage("Cleared!");
		}
		
		if(msg.getContent().contains("!test")){
			for(int i = 0; i < 50; i++){
				IMessage tmsg = chan.sendMessage("kek");
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				tmsg.delete();
			}
		}
		
        Main.getDebug().info("Someone has written something: " + event.getMessage());      
        
    }
	
	
}
