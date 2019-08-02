// This example is from the book "Java in a Nutshell, Second Edition".
// Written by David Flanagan.  Copyright (c) 1997 O'Reilly & Associates.
// You may distribute this source code for non-commercial purposes only.
// You may study, modify, and use this example for any purpose, as long as
// this notice is retained.  Note that this example is provided "as is",
// WITHOUT WARRANTY of any kind either expressed or implied.

import java.applet.*;
import java.awt.*;

public class Scribble extends Applet {
  private int last_x = 0, last_y = 0;  // Fields to store a point in.

  // Called when the user clicks.
  public boolean mouseDown(Event e, int x, int y) {
    last_x = x; last_y = y;            // Remember the location of the click.
    return true;
  }

  // Called when the mouse moves with the button down
  public boolean mouseDrag(Event e, int x, int y)  {
    Graphics g = getGraphics();        // Get a Graphics to draw with.
    g.drawLine(last_x, last_y, x, y);  // Draw a line from last point to this.
    last_x = x; last_y = y;            // And update the saved location.
    return true;
  }
}
