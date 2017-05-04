package com.lowbot.events;

import java.io.IOException;
import java.util.logging.Level;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.lowbot.LowbotAPI;
import com.lowbot.Main;
import com.lowbot.constructors.MusicPlayer;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.audio.AudioPlayer;

public class MessageEvent {

	LowbotAPI api = new LowbotAPI();

	@EventSubscriber
	public void onJoinServer(UserJoinEvent e) {
		IUser user = e.getUser();
		user.getOrCreatePMChannel().sendMessage("Welcome to the " + e.getGuild().getName() + "!");
	}

	@EventSubscriber
	public void onMessageReceivedEvent(MessageReceivedEvent event) {
		IMessage msg = event.getMessage();
		IChannel chan = msg.getChannel();
		IUser user = event.getAuthor();

		if (msg.getContent().equals("hi")) {
			chan.sendMessage("hi " + event.getAuthor());
			chan.sendMessage("Kenny is the best");
		} else if (msg.getMentions().contains(Main.getBot().getOurUser())) {
			chan.sendMessage("Lowbot");
		}

		if (msg.getContent().equalsIgnoreCase("!clear")) {
			try {
				chan.bulkDelete();
				chan.sendMessage("Cleared!");
			} catch (Exception e) {
				Main.getDebug().log(Level.SEVERE, "Count not clear chat. :(, Server: " + msg.getGuild().getName());
			}
		}

		if (msg.getContent().equals("!kappa")) {
			// msg.getGuild().getVoiceChannels().get(0).join();

			msg.getGuild().getVoiceChannelsByName("top").get(0).join();

			MusicPlayer player = new MusicPlayer(new AudioPlayer(chan.getGuild()));
			try {
				AudioInputStream inputStream = AudioSystem
						.getAudioInputStream(Main.class.getResource("/resources/background.wav"));
				player.getPlayer().queue(inputStream);
				// IVoiceChannel vchan =
				// msg.getGuild().getVoiceChannels().get(0);
				msg.getGuild().getAudioManager().setAudioProvider(player);

			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (msg.getContent().equals("!leave")) {
			msg.getGuild().getVoiceChannels().get(0).leave();
		}

		if (msg.getContent().contains("!test")) {
			for (int i = 0; i < 50; i++) {
				IMessage tmsg = chan.sendMessage("kek");

				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				tmsg.delete();
			}
		}

		Main.getDebug().info("Someone has written something: " + event.getMessage());

	}

}
