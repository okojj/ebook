// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
interface GetSetString {
    public void setString (String s);
    public String getString ();
}
class UpDownCatcher extends MouseAdapter {
    GetSetString gss;
    public UpDownCatcher (GetSetString s) {
        gss = s;
    }
    public void mousePressed (MouseEvent e) {
        int mods = e.getModifiers();
        if ((mods & MouseEvent.BUTTON3_MASK) != 0) {
            gss.setString ("Right Button Pressed");
        } else if ((mods & MouseEvent.BUTTON2_MASK) != 0) {
            gss.setString ("Middle Button Pressed");
        } else {
            gss.setString ("Left Button Pressed");
        }
        e.getComponent().repaint();
    }
    public void mouseReleased (MouseEvent e) {
        gss.setString ("Press a Mouse Key");
        e.getComponent().repaint();
    }
}
public class mouseEvent11 extends Applet implements GetSetString {
    private String theString = "Press a Mouse Key";
    public synchronized void setString (String s) {
        theString = s;
    }
    public synchronized String getString () {
        return theString;
    }
    public synchronized void paint (Graphics g) {
        g.drawString (theString, 20, 20);
    }
    public void init () {
        addMouseListener (new UpDownCatcher(this));
    }
}
