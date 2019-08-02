// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.applet.*;
import java.awt.*;
import java.awt.image.*;
public class DynamicImages extends Applet {
    Image i, j;
    public void init () {
        i = getImage (getDocumentBase(), "rosey.jpg");
        j = createImage (new FilteredImageSource (i.getSource(),
                        new DynamicFilter(250, 5, Color.yellow)));
    }
    public void paint (Graphics g) {
        g.drawImage (j, 10, 10, this);
    }
}
