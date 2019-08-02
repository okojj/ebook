// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
public class simpleMenu extends Frame {
    simpleMenu () {
        super ("Menu Example");
        Menu m = new Menu ("File", true);
        m.add (new MenuItem ("New Web Browser", new MenuShortcut ('n')));
        m.add (new MenuItem ("New Mail Message", new MenuShortcut ('n', true)));
        m.add (new MenuItem ("New Folder"));
        m.addSeparator ();
        m.add (new MenuItem ("Close"));
        m.add (new MenuItem ("Quit"));
	MenuBar mb = new MenuBar ();
	mb.add (m);
        setMenuBar (mb);
        resize (200, 200);
    }
    public static void main (String args[]) {
        simpleMenu f = new simpleMenu();
	f.show();
    }
}
