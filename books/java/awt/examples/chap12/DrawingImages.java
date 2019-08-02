// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.applet.*;
import java.awt.*;
import java.awt.image.*;
public class DrawingImages extends Applet {
    Image i, j, k, l;
    public void init () {
        i = getImage (getDocumentBase(), "rosey.jpg");
        GrayImageFilter gif = new GrayImageFilter ();
        j = createImage (new FilteredImageSource (i.getSource(), gif));
        TransparentImageFilter tf = new TransparentImageFilter (.5f);
        k = createImage (new FilteredImageSource (j.getSource(), tf));
        l = createImage (new FilteredImageSource (i.getSource(), tf));
    }
    public void paint (Graphics g) {
        g.drawImage (i, 10, 10, this);                  // regular
        g.drawImage (j, 270, 10, this);                 // gray
        g.drawImage (k, 10, 110, Color.red, this);       // gray - transparent
        g.drawImage (l, 270, 110, Color.red, this);      // transparent
    }
}
