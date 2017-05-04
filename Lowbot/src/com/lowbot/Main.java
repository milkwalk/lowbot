package com.lowbot;

import java.util.HashMap;
import java.util.logging.Logger;

import com.lowbot.commands.ClearCommand;
import com.lowbot.commands.HelpCommand;
import com.lowbot.commands.JoinVCCommand;
import com.lowbot.commands.LeaveVCCommand;
import com.lowbot.commands.PlayCommand;
import com.lowbot.commands.QueueCommand;
import com.lowbot.commands.SkipCommand;
import com.lowbot.constructors.CommandHandler;
import com.lowbot.constructors.ServerInformation;
import com.lowbot.events.MessageEvent;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.DiscordException;

public class Main {

	private static IDiscordClient bot;
	private static Logger log;

	private static LowbotAPI api;
	
	private static CommandHandler commands;
	private static HashMap<Long, ServerInformation> servers = new HashMap<Long, ServerInformation>();
	
	public static void registerCommands(){
		commands = new CommandHandler();	
		commands.registerCommand("help", new HelpCommand());		
		commands.registerCommand("clear", new ClearCommand());		
		commands.registerCommand("join", new JoinVCCommand());		
		commands.registerCommand("leave", new LeaveVCCommand());		
		commands.registerCommand("queue", new QueueCommand());		
		commands.registerCommand("play", new PlayCommand());		
		commands.registerCommand("skip", new SkipCommand());		
	}

	public static void main(String[] args) {
		// initialise logger
		log = Logger.getLogger("Lowbot");

		// login bot
		bot = createClient("MzA4ODc2MTc5ODY2MjU1MzYw.C-nPrA.cqzxGtd9jcz7yMogGTa9Pq9Iv7E", true);
		log.info("Logged in.");

		// load api kek
		api = new LowbotAPI();
		
		//registering commands
		registerCommands();
		
		//register events
		registerEvents();

		// sheduled tasks
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				log.info("Bot servers: ");
				for (IGuild g : bot.getGuilds()) {
					log.info("      Server: " + g.getName() + " Members: " + g.getTotalMemberCount());
					Main.getServers().put(g.getLongID(), new ServerInformation(g));
				}
				bot.getOurUser().getClient().changePlayingText("Type !lowbot");
			}
		}, 5000); // run task in 5 seconds after startup ^^

	}
	
	
	public static void registerEvents(){
		log.info("Registering events..");
		EventDispatcher dispatcher = bot.getDispatcher();
		dispatcher.registerListener(new MessageEvent());
		//dispatcher.registerListener(new EnableEvent());
		//registering event for commands
		dispatcher.registerListener(commands);
		log.info("Done");
	}

	public static IDiscordClient createClient(String token, boolean login) {
		ClientBuilder clientBuilder = new ClientBuilder(); 
		clientBuilder.withToken(token); // Adds the login info to the builder
		try {
			if (login) {
				return clientBuilder.login(); 
			} else {
				return clientBuilder.build(); 
			}
		} catch (DiscordException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Logger getDebug() {
		return log;
	}

	public static IDiscordClient getBot() {
		return bot;
	}

	public static LowbotAPI getApi() {
		return api;
	}
	
	public static HashMap<Long, ServerInformation> getServers(){
		return servers;
	}

}
