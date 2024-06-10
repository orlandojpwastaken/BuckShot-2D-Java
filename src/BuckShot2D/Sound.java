package BuckShot2D;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Sound {

    Clip clip;
    URL soundURL[] = new URL[3];

    public Sound() {
        soundURL[0] = getClass().getResource("/noBullets.wav");
        soundURL[1] = getClass().getResource("/shotgunShot.wav");
    }

    public void setFile(int i) {
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {

        }
    }

    public void play() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

}
