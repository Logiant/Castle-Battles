package main;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;


public class AudioHandler {

	private Audio mainMusic;
	
	public void loadMainMusic() throws IOException {
		mainMusic = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("resources/Audio/DarkWinds_0.ogg"));
	}
	
	public void playMain() {
//		mainMusic.playAsMusic(1, 1, true);
	}
	
	public void stopAll() {
		mainMusic.stop();
	}
	
	public boolean isPlaying() {
		return mainMusic.isPlaying();
	}
}
