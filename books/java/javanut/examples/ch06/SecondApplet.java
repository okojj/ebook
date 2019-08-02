// This example is from the book "Java in a Nutshell, Second Edition".
// Written by David Flanagan.  Copyright (c) 1997 O'Reilly & Associates.
// You may distribute this source code for non-commercial purposes only.
// You may study, modify, and use this example for any purpose, as long as
// this notice is retained.  Note that this example is provided "as is",
// WITHOUT WARRANTY of any kind either expressed or implied.

import java.applet.*;
import java.awt.*;

public class SecondApplet extends Applet {
  static final String message = "Hello World";
  private Font font;

  // One-time initialization for the applet
  // Note: no constructor defined.
  public void init() {
    font = new Font("Helvetica", Font.BOLD, 48);
  }

  // Draw the applet whenever necessary.  Do some fancy graphics.
  public void paint(Graphics g) {
    // The pink oval
    g.setColor(Color.pink);
    g.fillOval(10, 10, 330, 100);

    // The red outline. Java doesn't support wide lines, so we
    // try to simulate a 4-pixel wide line by drawing four ovals.
    g.setColor(Color.red);
    g.drawOval(10,10, 330, 100);
    g.drawOval(9, 9, 332, 102);
    g.drawOval(8, 8, 334, 104);
    g.drawOval(7, 7, 336, 106);

    // The text
    g.setColor(Color.black);
    g.setFont(font);
    g.drawString(message, 40, 75);
  }
}
