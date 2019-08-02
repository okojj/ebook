// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
public class PopupButtonFrame extends Frame {
    Image im;
    Window w = new PopupWindow (this);
    PopupButtonFrame () {
        super ("PopupButton Example");
        resize (250, 100);
        show();
        im = getToolkit().getImage ("rosey.jpg");
        MediaTracker mt = new MediaTracker (this);
        mt.addImage (im, 0);
        try {
            mt.waitForAll();
        } catch (Exception e) { e.printStackTrace(); }
    }
    public static void main (String args[]) {
        Frame f = new PopupButtonFrame ();
    }
    public void paint (Graphics g) {
        if (im != null)
            g.drawImage (im, 20, 20, this);
    }
    public boolean mouseDown (Event e, int x, int y) {
        if (e.modifiers == Event.META_MASK) {
            w.move (location().x+x, location().y+y);
            w.show();
            return true;
        }
        return false;
    }
}
class PopupWindow extends Window {
    PopupWindow (Frame f) {
        super (f);
        Panel p = new Panel ();
        p.add (new Button ("About"));
        p.add (new Button ("Save"));
        p.add (new Button ("Quit"));
        add ("North", p);
        setBackground (Color.gray);
        pack();
    }
    public boolean action (Event e, Object o) {
        if ("About".equals (o))
            System.out.println ("About");
        else if ("Save".equals (o))
            System.out.println ("Save Me");
        else if ("Quit".equals (o))
            System.exit (0);
        hide();
        return true;
    }
}
