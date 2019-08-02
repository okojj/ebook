// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.io.*;
import java.awt.*;
import java.awt.datatransfer.*;

public class ClipMe extends Frame {
    TextField tf;
    TextArea ta;
    Button copy, paste;
    Clipboard clipboard = null;
    ClipMe() {
        super ("Clipping Example");
        add (tf = new TextField("Welcome"), "North");
        add (ta = new TextArea(), "Center");
        ta.setEditable(false);
        Panel p = new Panel();
        p.add (copy = new Button ("Copy"));
        p.add (paste = new Button ("Paste"));
        add (p, "South");
        setSize (250, 250);
    }
    public static void main (String args[]) {
        new ClipMe().show();
    }
    public boolean handleEvent (Event e) {
        if (e.id == Event.WINDOW_DESTROY) {
            System.exit(0);
            return true;  // never gets here
        }
        return super.handleEvent (e);
    }
    public boolean action (Event e, Object o) {
        if (clipboard == null)
            clipboard = getToolkit().getSystemClipboard();
        if ((e.target == tf) || (e.target == copy)) {
            StringSelection data;
            data = new StringSelection (tf.getText());
            clipboard.setContents (data, data);
        } else if (e.target == paste) {
            Transferable clipData = clipboard.getContents(this);
            String s;
            try {
                s = (String)(clipData.getTransferData(DataFlavor.stringFlavor));
            } catch (Exception ee) {
                s = ee.toString();
            }
            ta.setText(s);
        }
        return true;
    }
}
