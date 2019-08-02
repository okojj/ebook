// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
import java.applet.*;
public class drawingImages extends Applet {
    Image i, j;
    public void init () {
        i = getImage (getDocumentBase(), "rosey.jpg");
        j = getImage (getDocumentBase(), "rosey.gif");
    }
    public void paint (Graphics g) {
        g.drawImage (i, 0, 0, this);
        g.drawImage (i, 10, 85, 150, 200, this);
        g.drawImage (j, 270, 10, Color.lightGray, this);
        g.drawImage (j, 270, 85, 150, 200, Color.lightGray, this);
    }
}
