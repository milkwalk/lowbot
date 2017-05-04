package com.lowbot.commands;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.lowbot.LowbotAPI;
import com.lowbot.Main;
import com.lowbot.constructors.Command;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.handle.obj.IVoiceState;

public class JoinVCCommand implements Command {

	private LowbotAPI api = new LowbotAPI();

	@Override
	public boolean onCommand(MessageReceivedEvent event) {

		IMessage iMsg = event.getMessage();
		IChannel iChan = event.getChannel();
		IUser iUser = event.getAuthor();
		IGuild iGuild = event.getGuild();

		IVoiceChannel vchan;

		// BOT CAN'T USE IT...
		if (iUser.isBot()) {
			iChan.sendMessage("User is BOT? " + iUser.isBot());
			iChan.sendMessage("BOT can't use it!");
			return false;
		}

		String voiceChannelToSend = getNameOfVoiceChannelInCommand(iMsg);

		// Don't have name of channel?
		if (voiceChannelToSend == "") {

			// Send BOT to channel where user is!
			IVoiceState iVoiceState = iUser.getVoiceStateForGuild(iGuild);
			voiceChannelToSend = iVoiceState.getChannel().getName();

		} else {

			// That name is exist in Guild?
			if (voiceChannelExistInGuild(voiceChannelToSend, iGuild)) {
				iChan.sendMessage("That Voice Channel doesn't exist. ");
				return false;
			}

		}
		
		iChan.sendMessage("Sending bot to channel " + voiceChannelToSend);
		vchan = iGuild.getVoiceChannelsByName(voiceChannelToSend).get(0);
		vchan.join();

		try {

			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(Main.class.getResource("/resources/background.wav"));

			api.getServerInformation(event.getGuild()).getMusicPlayer().getPlayer().queue(inputStream);
			iGuild.getAudioManager().setAudioProvider(api.getServerInformation(event.getGuild()).getMusicPlayer());
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * It will return name of channel in command, if return "", it mean doesn't
	 * exist name in command.
	 * 
	 * @author Kenny23
	 * @param iMsg
	 * @return name of channel
	 */
	private String getNameOfVoiceChannelInCommand(IMessage iMsg) {
		if (iMsg.getContent().split(" ").length == 3)
			return iMsg.getContent().split(" ")[2];

		return "";
	}

	/**
	 * Check if that channel exist in guild (Discord).
	 * 
	 * @author Kenny23
	 * @param voiceChannelNameByUser
	 *            - name of channel in command.
	 * @param guild
	 *            - guild
	 * @return true if channel exist in guild.
	 */
	private boolean voiceChannelExistInGuild(String voiceChannelNameByUser, IGuild guild) {
		if (guild.getVoiceChannelsByName(voiceChannelNameByUser).size() != 0)
			return false;
		return true;
	}
}
