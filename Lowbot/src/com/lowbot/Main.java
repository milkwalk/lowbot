package com.lowbot;

import java.util.logging.Logger;

import com.lowbot.commands.CommandHandler;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.DiscordException;

public class Main {
	
	private static IDiscordClient bot;
	private static Logger log;
	
	private static LowbotAPI api;
	
	public static void main(String[] args){
		//initialise logger
		log = Logger.getLogger("Lowbot");
		
		//login bot
		bot = createClient("MzA4ODc2MTc5ODY2MjU1MzYw.C-nPrA.cqzxGtd9jcz7yMogGTa9Pq9Iv7E", true);	
		log.info("Logged in.");
			
		//load api kek
		api = new LowbotAPI();
		
		//registering events
		log.info("Registering events..");
		EventDispatcher dispatcher = bot.getDispatcher(); 
		dispatcher.registerListener(new MessageEvent());
		dispatcher.registerListener(new CommandHandler());
		log.info("Done");
		
		//sheduled tasks
		new java.util.Timer().schedule( new java.util.TimerTask() {
	            @Override
	            public void run() {
	        		log.info("Bot servers: ");		        		
	        		for(IGuild g : bot.getGuilds()){
	        			log.info("      Server: " + g.getName() + " Members: " + g.getTotalMemberCount());
	        		}
	        		bot.getOurUser().getClient().changePlayingText("Type !help");
	            }
	        }, 5000); //run task in 5 seconds after startup ^^
		
	}
	
	
    public static IDiscordClient createClient(String token, boolean login) { // Returns a new instance of the Discord client
        ClientBuilder clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
        clientBuilder.withToken(token); // Adds the login info to the builder
        try {
            if (login) {
                return clientBuilder.login(); // Creates the client instance and logs the client in
            } else {
                return clientBuilder.build(); // Creates the client instance but it doesn't log the client in yet, you would have to call client.login() yourself
            }
        } catch (DiscordException e) { // This is thrown if there was a problem building the client
            e.printStackTrace();
            return null;
        }
    }
    
    public static Logger getDebug(){
    	return log;
    }
    
    public static IDiscordClient getBot(){
    	return bot;
    }
    
    public static LowbotAPI getApi(){
    	return api;
    }

}
