// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;

public class drawing3dRects extends Frame {
    drawing3dRects () {
        super ("Drawing 3dRects");
        resize (175, 225);
    }
    public void paint (Graphics g) {
      g.translate (insets().left, insets().top);
	g.setColor (Color.gray);
	g.draw3DRect (25, 10, 50, 75, true);
	g.draw3DRect (25, 110, 50, 75, false);
	g.fill3DRect (100, 10, 50, 75, true);
	g.fill3DRect (100, 110, 50, 75, false);
    }
    public static void main (String [] args) {
        Frame f = new drawing3dRects ();
        f.show();
    }
}
