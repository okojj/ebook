// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;

public class drawingPoly extends Frame {
    int[] xPoints[] = {{50, 25, 25, 75, 75}, 
                       {50, 25, 25, 75, 75},
		       {100, 100, 150, 100, 150, 150, 125, 100, 150},
		       {100, 100, 150, 100, 150, 150, 125, 100, 150}};
    int[] yPoints[] = {{10, 35, 85, 85, 35, 10}, 
                       {110, 135, 185, 185, 135},
		       {85, 35, 35, 85, 85, 35, 10, 35, 85},
		       {185, 135, 135, 185, 185, 135, 110, 135, 185}};
    int   nPoints[] = {5, 5, 9, 9};
    drawingPoly () {
        super ("Drawing Poly");
        resize (175, 225);
    }
    public void paint (Graphics g) {
      g.translate (insets().left, insets().top);
	g.drawPolygon (xPoints[0], yPoints[0], nPoints[0]);
	g.fillPolygon (xPoints[1], yPoints[1], nPoints[1]);
	g.drawPolygon (new Polygon(xPoints[2], yPoints[2], nPoints[2]));
	g.fillPolygon (new Polygon(xPoints[3], yPoints[3], nPoints[3]));
    }
    public static void main (String [] args) {
        Frame f = new drawingPoly ();
        f.show();
    }
}
