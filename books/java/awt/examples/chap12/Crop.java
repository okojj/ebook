// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.applet.*;
import java.awt.*;
import java.awt.image.*;
public class Crop extends Applet {
    Image i, j;
    public void init () {
        MediaTracker mt = new MediaTracker (this);
        i = getImage (getDocumentBase(), "rosey.jpg");
        mt.addImage (i, 0);
        try {
            mt.waitForAll();
            int width        = i.getWidth(this);
            int height       = i. getHeight(this);
            j = createImage (new FilteredImageSource (i.getSource(),
                                new CropImageFilter (width/3, height/3,
                                                     width/3, height/3)));
        } catch (InterruptedException e) {
            e.printStackTrace();           
        }
    }
    public void paint (Graphics g) {
        g.drawImage (i, 10, 10, this);                  // regular
        if (j != null) {
            g.drawImage (j, 10, 90, this);             // cropped
        }
    }
}
