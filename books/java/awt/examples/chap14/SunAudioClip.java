// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.net.URL;
import java.io.FileInputStream;
import sun.audio.*;
public class SunAudioClip implements java.applet.AudioClip {
    private AudioData audiodata;
    private AudioDataStream audiostream;
    private ContinuousAudioDataStream continuousaudiostream;
    static int length;
    public SunAudioClip (URL url) throws java.io.IOException {
        audiodata = new AudioStream (url.openStream()).getData();
	audiostream = null;
	continuousaudiostream = null;
    }
    public SunAudioClip (String filename) throws java.io.IOException {
        FileInputStream fis = new FileInputStream (filename);
        AudioStream audioStream = new AudioStream (fis);
        audiodata = audioStream.getData();
	audiostream = null;
	continuousaudiostream = null;
    }
    public void play () {
        audiostream = new AudioDataStream (audiodata);
	AudioPlayer.player.start (audiostream);
    }
    public void loop () {
        continuousaudiostream = new ContinuousAudioDataStream (audiodata);
	AudioPlayer.player.start (continuousaudiostream);
    }
    public void stop () {
        if (audiostream != null)
		AudioPlayer.player.stop (audiostream);
        if (continuousaudiostream != null)
		AudioPlayer.player.stop (continuousaudiostream);
    }
    public static void main (String args[]) throws Exception {
        URL url1 = new URL ("http://localhost:8080/audio/1.au");
        URL url2 = new URL ("http://localhost:8080/audio/2.au");
        SunAudioClip sac1 = new SunAudioClip (url1);
        SunAudioClip sac2 = new SunAudioClip (url2);
        SunAudioClip sac3 = new SunAudioClip ("1.au");
        sac1.play ();
        sac2.loop ();
        sac3.play ();
        try { // Delay for loop
            Thread.sleep (2000);
        } catch (InterruptedException ie) { }
        sac2.stop();
    }
}

