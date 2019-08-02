// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.applet.*;
import java.awt.*;
import java.awt.image.*;
public class AvgFilt extends Applet {
    Image i, j;
    public void init () {
        i = getImage (getDocumentBase(), "rosey.jpg");
        MediaTracker mt = new MediaTracker (this);
        mt.addImage (i, 0);
        try {
            mt.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println ("Loaded");
        j = createImage (new FilteredImageSource (i.getSource(), new BlurFilter()));
        System.out.println ("Created");
    }
    public void paint (Graphics g) {
        g.drawImage (i, 10, 10, this);                  // regular
        if (j != null)
            g.drawImage (j, 250, 10, this);             // average
    }
}
