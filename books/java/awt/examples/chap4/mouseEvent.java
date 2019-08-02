// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
import java.applet.*;
public class mouseEvent extends Applet {
    String theString = "Press a Mouse Key";
    public synchronized void setString (String s) {
        theString = s;
    }
    public synchronized String getString () {
        return theString;
    }
    public synchronized void paint (Graphics g) {
        g.drawString (theString, 20, 20);
    }
    public boolean mouseDown (Event e, int x, int y) {
        if (e.modifiers == Event.META_MASK) {
            setString ("Right Button Pressed");
        } else if (e.modifiers == Event.ALT_MASK) {
            setString ("Middle Button Pressed");
        } else {
            setString ("Left Button Pressed");
        }
        repaint ();
        return true;
    }
    public boolean mouseUp (Event e, int x, int y) {
        setString ("Press a Mouse Key");
        repaint ();
        return true;
    }
}
