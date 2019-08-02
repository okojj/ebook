// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
import java.applet.*;
public class arcZoom extends Applet {
    Image i;
    Graphics gc;
    public void init () {
        i = createImage (50, 50);
        gc = i.getGraphics();
        // Really only want one corner
        gc.drawRoundRect (1, 1, 200, 200, 20, 40);
    }
    public void paint (Graphics g) {
        g.drawImage (i, 1, 1, 200, 200, this);
    }
}
