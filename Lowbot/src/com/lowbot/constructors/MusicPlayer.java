package com.lowbot.constructors;

import sx.blah.discord.handle.audio.IAudioProvider;
import sx.blah.discord.util.audio.AudioPlayer;

//this is a music player class
public class MusicPlayer implements IAudioProvider {

	// tracks all users inputs (like music etc)
	private final AudioPlayer audioPlayer;
	// buffer storage of the audiofile ^^
	private byte[] lastFrame;

	public MusicPlayer(AudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
	}

	public AudioPlayer getPlayer() {
		return this.audioPlayer;
	}

	@Override
	public boolean isReady() {
		lastFrame = audioPlayer.provide();
		return lastFrame != null;
	}

	@Override
	public byte[] provide() {
		return lastFrame;
	}
}
