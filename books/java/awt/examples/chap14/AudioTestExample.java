// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.net.*;
import java.awt.*;
import java.applet.*;
public class AudioTestExample extends Applet{
    AudioClip audio1, audio2, audio3;
    public void init () {
        audio1 = getAudioClip (getCodeBase(), "audio/bong.au");
        audio2 = getAudioClip (getCodeBase(), "audio/joy.au");
        try {
            audio3 = getAudioClip  (new URL (getCodeBase(), "audio/return.au"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public boolean mouseDown (Event e, int x, int y) {
        if (audio1 != null)
            audio1.play();
        return true;
    }
    public void start () {
        if (audio2 != null)
            audio2.loop();
    }
    public void paint (Graphics g) {
        if (audio3 != null)
            audio3.play();
    }
    public void stop () {
        if (audio1 != null)
            audio1.stop();
        if (audio2 != null)
            audio2.stop();
        if (audio3 != null)
            audio3.stop();
    }
}
