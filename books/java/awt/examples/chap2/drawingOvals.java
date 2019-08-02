// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;

public class drawingOvals extends Frame {
    drawingOvals () {
        super ("Drawing Ovals");
        resize (175, 225);
    }
    public void paint (Graphics g) {
      g.translate (insets().left, insets().top);
	g.drawOval (25, 10, 50, 75);
	g.fillOval (25, 110, 50, 75);
	g.drawOval (100, 10, 50, 50);
	g.fillOval (100, 110, 50, 50);
    }
    public static void main (String [] args) {
        Frame f = new drawingOvals ();
        f.show();
    }
}
