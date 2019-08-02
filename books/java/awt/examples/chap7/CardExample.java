// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
import java.applet.*;
public class CardExample extends Applet {
    CardLayout cl = new CardLayout(10, 0);
    public void init () {
        String fonts[] = Toolkit.getDefaultToolkit().getFontList();
        setLayout (cl);
        Panel p1 = new Panel();
        Panel p3 = new Panel ();
        p1.setLayout (new GridLayout (3, 2));
        List l = new List(4, false);
        Choice c = new Choice ();
        for (int i=0;i<fonts.length;i++) {
            p1.add (new Checkbox (fonts[i]));
            l.addItem (fonts[i]);
            c.addItem (fonts[i]);
        }
        p3.add (l);
        p3.add (c);
        add ("One", p1);
        add ("Two", new Button ("Click Here"));
        add ("Three", p3);
    }
    public boolean action (Event e, Object o) {
        cl.next(this);
        return true;
    }
}
