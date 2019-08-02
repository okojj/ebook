// This example is from the book "Java in a Nutshell, Second Edition".
// Written by David Flanagan.  Copyright (c) 1997 O'Reilly & Associates.
// You may distribute this source code for non-commercial purposes only.
// You may study, modify, and use this example for any purpose, as long as
// this notice is retained.  Note that this example is provided "as is",
// WITHOUT WARRANTY of any kind either expressed or implied.

import java.applet.*;  	// Don't forget this import statement!
import java.awt.*;          // Or this one for the graphics!

public class FirstApplet extends Applet {
  // This method displays the applet.
  // The Graphics class is how you do all drawing in Java.
  public void paint(Graphics g) {
    g.drawString("Hello World", 25, 50);
  }
}
