// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.util.Vector;
import java.awt.*;
public class MenuTest extends Frame {
    MenuTest () {
        super ("MenuTest");
        MenuItem mi;
        Menu file = new Menu ("File", true);
        file.add ("Open");
        file.add (mi = new MenuItem ("Close"));
        mi.disable();
        Menu extras = new Menu ("Extras", false);
        extras.add (new CheckboxMenuItem ("What"));
        mi = extras.add (new MenuItem ("Yo"));
        mi = extras.add (new MenuItem ("Yo"));
        file.add (extras);
        file.addSeparator();
        file.add ("Quit");
        Menu help = new Menu("Help");
        help.add ("About");
        MenuBar mb = new MenuBar();
        mb.add (help);
        mb.add (file);
        mb.setHelpMenu (help);
        setMenuBar (mb);
        resize (200, 200);
    }

     public boolean handleEvent (Event e) {
        if (e.id == Event.WINDOW_DESTROY) {
            System.exit(0);
        }
        return super.handleEvent (e);
    }
    public boolean action (Event e, Object o) {
        if (e.target instanceof MenuItem) {
            if ("Quit".equals (o)) {
                dispose();
                System.exit(1);
            } else {
                System.out.println ("User selected " + o);
                if (e.target instanceof CheckboxMenuItem) {
                    CheckboxMenuItem cb = (CheckboxMenuItem)e.target;
                    System.out.println ("The value is: " + cb.getState());
                }
            }
            return true;
        }
        return false;
    }
    public static void main (String []args) {
        MenuTest f = new MenuTest ();
        f.show();
    }

}

