// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class PopupTest extends Applet implements ActionListener {
    PopupMenu popup;
    public void init() {	    
        MenuItem mi;
        popup = new PopupMenu("Title Goes Here");
        popup.add(mi = new MenuItem ("Undo"));
        mi.addActionListener (this);
        popup.addSeparator();
        popup.add(mi = new MenuItem("Cut")).setEnabled(false);
        mi.addActionListener (this);
        popup.add(mi = new MenuItem("Copy")).setEnabled(false);
        mi.addActionListener (this);
        popup.add(mi = new MenuItem ("Paste"));
        mi.addActionListener (this);
        popup.add(mi = new MenuItem("Delete")).setEnabled(false);
        mi.addActionListener (this);
        popup.addSeparator();
        popup.add(mi = new MenuItem ("Select All"));
        mi.addActionListener (this);
	  add (popup);
	  resize(200, 200);
        enableEvents (AWTEvent.MOUSE_EVENT_MASK);
    }
    protected void processMouseEvent (MouseEvent e) {
        if (e.isPopupTrigger())
            popup.show(e.getComponent(), e.getX(), e.getY());
        super.processMouseEvent (e);
    }
    public void actionPerformed(ActionEvent e) {
        System.out.println (e);
    }
}