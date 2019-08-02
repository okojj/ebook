// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;

public class drawingArcs extends Frame {
    drawingArcs () {
        super ("Drawing Arcs");
        resize (175, 225);
    }
    public void paint (Graphics g) {
      g.translate (insets().left, insets().top);
	g.drawArc (25, 10, 50, 75, 0, 360);
	g.fillArc (25, 110, 50, 75, 0, 360);
	g.drawArc (100, 10, 50, 75, 45, 215);
	g.fillArc (100, 110, 50, 75, 45, 215);
    }
    public static void main (String [] args) {
        Frame f = new drawingArcs ();
        f.show();
    }
}
