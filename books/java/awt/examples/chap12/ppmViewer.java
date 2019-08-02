// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.IOException;
import java.applet.Applet;
public class ppmViewer extends Applet {
    Image image = null;
    public void init () {
        try {
            String file = getParameter ("file");
            if (file != null) {
                URL imageurl = new URL (getDocumentBase(), file);
                InputStream is = imageurl.openStream();
                PPMImageDecoder ppm = new PPMImageDecoder ();
                ppm.readImage (is);
                image = createImage (ppm);
                repaint();
            }
        } catch (MalformedURLException me) {
            System.out.println ("Bad URL");
         } catch (IOException io) {
            System.out.println ("Bad File");
        }
    }
    public void paint (Graphics g) {
        g.drawImage (image, 0, 0, this);
    }
}
