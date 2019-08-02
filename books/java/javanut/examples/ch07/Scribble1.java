// This example is from the book "Java in a Nutshell, Second Edition".
// Written by David Flanagan.  Copyright (c) 1997 O'Reilly & Associates.
// You may distribute this source code for non-commercial purposes only.
// You may study, modify, and use this example for any purpose, as long as
// this notice is retained.  Note that this example is provided "as is",
// WITHOUT WARRANTY of any kind either expressed or implied.

import java.applet.*;
import java.awt.*;

/** A simple applet using the Java 1.0 event handling model */
public class Scribble1 extends Applet {
  private int lastx, lasty;    // Remember last mouse coordinates.
  Button clear_button;         // The Clear button.
  Graphics g;                  // A Graphics object for drawing.

  /** Initialize the button and the Graphics object */
  public void init() {
    clear_button = new Button("Clear");
    this.add(clear_button);
    g = this.getGraphics();
  }
  /** Respond to mouse clicks */
  public boolean mouseDown(Event e, int x, int y) {
    lastx = x; lasty = y;
    return true;
  }
  /** Respond to mouse drags */
  public boolean mouseDrag(Event e, int x, int y) {
    g.setColor(Color.black);
    g.drawLine(lastx, lasty, x, y);
    lastx = x; lasty = y;
    return true;
  }
  /** Respond to key presses */
  public boolean keyDown(Event e, int key) {
    if ((e.id == Event.KEY_PRESS) && (key == 'c')) {
      clear();
      return true;
    }
    else return false;
  }
  /** Respond to Button clicks */
  public boolean action(Event e, Object arg) {
    if (e.target == clear_button) {
      clear();
      return true;
    }
    else return false;
  }
  /** convenience method to erase the scribble */
  public void clear() {
    g.setColor(this.getBackground());
    g.fillRect(0, 0, bounds().width, bounds().height);
  }
}
