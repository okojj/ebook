// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;

public class drawingLines extends Frame {
    drawingLines () {
        super ("Drawing Lines");
        resize (200, 200);
    }
    public void paint (Graphics g) {
      g.translate (insets().left, insets().top);
	g.drawLine (5, 5, 50, 75);   // line
	g.drawLine (5, 75, 5, 75);   // point
	g.drawLine (50, 5, 50, 5);   // point
    }
    public static void main (String [] args) {
        Frame f = new drawingLines ();
        f.show();
    }
}
