// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
import java.awt.event.*;
class ItemEventComponent extends Component implements ItemSelectable {
    boolean selected;
    int i = 0;
    ItemListener itemListener = null;
    ItemEventComponent () {
        enableEvents (AWTEvent.MOUSE_EVENT_MASK);
    }
    public Object[] getSelectedObjects() {
        Object o[] = new Object[1];
        o[0] = new Integer (i);
        return o;
    }
    public void addItemListener (ItemListener l) {
        itemListener = AWTEventMulticaster.add (itemListener, l);
    }
    public void removeItemListener (ItemListener l) {
        itemListener = AWTEventMulticaster.remove (itemListener, l);
    }
    public void processEvent (AWTEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            if (itemListener != null) {
                selected = !selected;
                i++;
                itemListener.itemStateChanged (
                    new ItemEvent (this, ItemEvent.ITEM_STATE_CHANGED,
                        getSelectedObjects(),
                        (selected?ItemEvent.SELECTED:ItemEvent.DESELECTED)));
            }
        }
    }
}

public class ItemFrame extends Frame implements ItemListener {
    ItemFrame () {
        super ("Listening In");
        ItemEventComponent c = new ItemEventComponent ();
        add (c, "Center");
        c.addItemListener (this);
        c.setBackground (SystemColor.control);
        setSize (200, 200);
    }
    public void itemStateChanged (ItemEvent e) {
        Object[] o = e.getItemSelectable().getSelectedObjects();
        Integer i = (Integer)o[0];
        System.out.println (i);
    }
    public static void main (String args[]) {
        ItemFrame f = new ItemFrame();
        f.show();
    }
}
