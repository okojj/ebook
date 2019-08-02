// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
public class flushMe extends Frame {
    Image im;
    flushMe () {
        super ("Flushing");
        im = Toolkit.getDefaultToolkit().getImage ("flush.gif");
        resize (175, 225);
    }
    public void paint (Graphics g) {
        g.drawImage (im, 0, 0, 175, 225, this);
    }
    public boolean mouseDown (Event e, int x, int y) {
        im.flush();
        repaint();
        return true;
    }
    public static void main (String [] args) {
        Frame f = new flushMe ();
        f.show();
    }
}
