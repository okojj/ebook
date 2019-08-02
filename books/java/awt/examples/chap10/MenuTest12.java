// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
import java.awt.event.*;
public class MenuTest12 extends Frame implements ActionListener {
    class MyMenuItem extends MenuItem {
        public MyMenuItem (String s, ActionListener al) {
            super (s);
            addActionListener (al);
        }
    }
    public MenuTest12 () {
        super ("MenuTest");
        MenuItem mi;
        Menu file = new Menu ("File", true);
        file.add (new MyMenuItem ("Open", this));
        mi = file.add (new MyMenuItem ("Close", this));
        mi.setEnabled(false);
        Menu extras = new Menu ("Extras", false);
        mi = extras.add (new CheckboxMenuItem ("What"));
        mi.addActionListener(this);
        mi = extras.add (new MyMenuItem ("Yo", this));
        mi.setActionCommand ("Yo1");
        mi = extras.add (new MyMenuItem ("Yo", this));
        mi.setActionCommand ("Yo2");
        file.add (extras);
        file.addSeparator();
        file.add (new MyMenuItem ("Quit", this));
        Menu help = new Menu("Help");
        help.add (new MyMenuItem ("About", this));
        MenuBar mb = new MenuBar();
        mb.add (help);
        mb.add (file);
        mb.setHelpMenu (help);
        setMenuBar (mb);
        setSize (200, 200);
        enableEvents (AWTEvent.WINDOW_EVENT_MASK);
    }
// Cannot override processActionEvent since method of MenuItem
// Would have to subclass both MenuItem and CheckboxMenuItem
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Quit")) {
            System.exit(0);
        }
        System.out.println ("User selected " + e.getActionCommand());
        if (e.getSource() instanceof ItemSelectable) {
            ItemSelectable is = (ItemSelectable)e.getSource();
            System.out.println ("The value is: " + (is.getSelectedObjects().length != 0));
        }
    }
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            super.processWindowEvent (e);
            System.exit(0);
        } else {
            super.processWindowEvent (e);
        }
    }
    public static void main (String []args) {
        MenuTest12 f = new MenuTest12 ();
        f.show();
    }

}
