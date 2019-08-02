// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
import java.util.*;
public class compList extends java.applet.Applet {
    Button done = new Button ("Done");
    Hashtable values = new Hashtable();
    public void init () {
        add (new Label ("Label"));
        add (new Button ("Button"));
        add (new Scrollbar (Scrollbar.HORIZONTAL, 50, 25, 0, 255));
        List l1 = new List (3, false);
        l1.addItem ("List 1");
        l1.addItem ("List 2");
        l1.addItem ("List 3");
        l1.addItem ("List 4");
        l1.addItem ("List 5");
        add (l1);
        List l2 = new List (3, true);
        l2.addItem ("Multi 1");
        l2.addItem ("Multi 2");
        l2.addItem ("Multi 3");
        l2.addItem ("Multi 4");
        l2.addItem ("Multi 5");
        add (l2);
        Choice c = new Choice ();
        c.addItem ("Choice 1");
        c.addItem ("Choice 2");
        c.addItem ("Choice 3");
        c.addItem ("Choice 4");
        c.addItem ("Choice 5");
        add (c);
        add (new Checkbox ("Checkbox"));
        add (new TextField ("TextField", 10));
        add (new TextArea ("TextArea", 3, 20));
        Canvas c1 = new Canvas ();
        c1.resize (50, 50);
        c1.setBackground (Color.blue);
        add (c1);
        add (done);
    }
    public boolean handleEvent (Event e) {
        if (e.target == done) {
            if (e.id == Event.ACTION_EVENT) {
                System.out.println (System.getProperty ("java.vendor"));
                System.out.println (System.getProperty ("java.version"));
                System.out.println (System.getProperty ("java.class.version"));
                System.out.println (System.getProperty ("os.name"));
                System.out.println (values);
//                stop();
            }
        } else {
            Vector v;
            Class c = e.target.getClass();
            v = (Vector)values.get(c);
            if (v == null)
                v = new Vector();
            Integer i = new Integer (e.id);
            if (!v.contains (i)) {
                v.addElement (i);
                values.put (c, v);
            }
        }

        return super.handleEvent (e);
    }
}
