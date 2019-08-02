// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.applet.*;
import java.awt.*;
import java.awt.image.ImageObserver;
public class imageUpdateOver extends Applet {
    Image image;
    boolean loaded = false;
    public void init () {
        image = getImage (getDocumentBase(), "rosey.jpg");
        prepareImage (image, -1, -1, this);
    }
    public void paint (Graphics g) {
        if (loaded)
            g.drawImage (image, 0, 0, this);
    }
    public void update (Graphics g) {
        paint (g);
    }
    public synchronized boolean imageUpdate (Image image, int infoFlags,
                        int x, int y, int width, int height) {
        if ((infoFlags & ImageObserver.ALLBITS) != 0) {
            loaded = true;
            repaint();
            return false;
        } else {
            return true;
        }
    }
}
