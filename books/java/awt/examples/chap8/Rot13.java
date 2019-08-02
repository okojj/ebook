// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;

public class Rot13 extends Frame {
    TextArea ta;
    Component rotate, done;
    public Rot13 () {
        super ("Rot-13 Example");
        add ("North", new Label ("Enter Text to Rotate:"));
        ta = (TextArea)(add ("Center", new TextArea (5, 40)));
        Panel p = new Panel ();
        rotate = p.add (new Button ("Rotate Me"));
        done = p.add (new Button ("Done"));
        add ("South", p);
    }
    public static void main (String args[]) {
        Rot13 rot = new Rot13();
        rot.pack();
        rot.show();
    }
    public boolean handleEvent (Event e) {
        if (e.id == Event.WINDOW_DESTROY) {
            hide();
            dispose();
            System.exit (0);
            return true;
        }
        return super.handleEvent (e);
    }
    public boolean action (Event e, Object o) {
        if (e.target == rotate) {
            ta.setText (rot13Text (ta.getText()));
            return true;
        } else if (e.target == done) {
            hide();
            dispose();
            System.exit (0);
        }
        return false;
    }
    String rot13Text (String s) {
        int len = s.length();
        StringBuffer returnString = new StringBuffer (len);
        char c;
        for (int i=0;i<len;i++) {
            c = s.charAt (i);
            if (((c >= 'A') && (c <= 'M')) ||
                ((c >= 'a') && (c <= 'm')))
                c += 13;
            else if (((c >= 'N') && (c <= 'Z')) ||
                ((c >= 'n') && (c <= 'z')))
                c -= 13;
            returnString.append (c);
        }
        return returnString.toString();
    }
}
