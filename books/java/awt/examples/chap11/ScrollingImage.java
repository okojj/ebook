// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
import java.io.FilenameFilter;
import java.io.File;

// True for files ending in jpeg/jpg/gif/xbm
class ImageFileFilter implements FilenameFilter {
    public boolean accept (File dir, String name) {
        String tempname = name.toLowerCase();
        return (tempname.endsWith ("jpg") || tempname.endsWith ("jpeg") ||
                tempname.endsWith ("gif") || tempname.endsWith ("xbm"));
    }
}

class ImageListDialog extends Dialog {
    private String name = null;
    private String entries[];
    private List list;
    ImageListDialog (Frame f) {
        super (f, "Image List", true);
        File dir = new File (System.getProperty("user.dir"));
        entries = dir.list (new ImageFileFilter());
        list = new List (10, false);
        for (int i=0;i<entries.length;i++) {
            list.addItem (entries[i]);
        }
        add ("Center", list);
        pack();
    }
    public String getName () {
        return name;
    }
    public boolean action (Event e, Object o) {
        name = (String)e.arg;
        ((ScrollingImage)getParent()).processImage();
        dispose();
        return true;
    }
}

class ImageCanvas extends Canvas {
    Image image;
    int xPos, yPos;
    public void redraw (int xPos, int yPos, Image image) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.image = image;
        repaint();
    }
    public void paint (Graphics g) {
        if (image != null)
            g.drawImage (image, -xPos, -yPos, this);
    }
}

public class ScrollingImage extends Frame {
    static Scrollbar horizontal, vertical;
    ImageCanvas center;
    int xPos, yPos;
    Image image;
    ImageListDialog ild;
    ScrollingImage () {
        super ("Image Viewer");
        add ("Center", center = new ImageCanvas ());
        add ("South",  horizontal = new Scrollbar (Scrollbar.HORIZONTAL));
        add ("East", vertical = new Scrollbar (Scrollbar.VERTICAL));
        Menu m = new Menu ("File", true);
        m.add ("Open");
        m.add ("Close");
        m.add ("-");
        m.add ("Quit");
        MenuBar mb = new MenuBar();
        mb.add (m);
        setMenuBar (mb);
        resize (400, 300);
    }
    public static void main (String args[]) {
        ScrollingImage si = new ScrollingImage ();
        si.show();
    }
    public boolean handleEvent (Event e) {
         if (e.id == Event.WINDOW_DESTROY) {
            System.exit(0);
        } else if (e.target instanceof Scrollbar) {
            if (e.target == horizontal) {
                xPos = ((Integer)e.arg).intValue();
            } else if (e.target == vertical) {
                yPos = ((Integer)e.arg).intValue();
            }
            center.redraw (xPos, yPos, image);
        }
        return super.handleEvent (e);
    }
    public void processImage () {
        image = getToolkit().getImage (ild.getName());
        MediaTracker tracker = new MediaTracker (this);
        tracker.addImage (image, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException ie) {
        }
        xPos = 0;
        yPos = 0;
        int imageHeight = image.getHeight (this);
        int imageWidth = image.getWidth (this);
        vertical.setValues (0, 5, 0, imageHeight);
        horizontal.setValues (0, 5, 0, imageWidth);
        center.redraw (xPos, yPos, image);
    }
    public boolean action (Event e, Object o) {
        if (e.target instanceof MenuItem) {
            if ("Open".equals (o)) {
                // If showing already, do not show again
                if ((ild == null) || (!ild.isShowing())) {
                    ild = new ImageListDialog (this);
                    ild.show();
                }
            } else if ("Close".equals(o)) {
                image = null;
                center.redraw (xPos, yPos, image);
            } else if ("Quit".equals(o)) {
                System.exit(0);
            }
            return true;
        }
        return false;
    }
    public boolean keyDown (Event e, int key) {
        if (e.id == Event.KEY_ACTION) {
            Scrollbar target = null;
            switch (key) {
                case Event.HOME:
                    target = vertical;
                    vertical.setValue(vertical.getMinimum());
                    break;
                case Event.END:
                    target = vertical;
                    vertical.setValue(vertical.getMaximum());
                    break;
                case Event.PGUP:
                    target = vertical;
                    vertical.setValue(vertical.getValue() - vertical.getPageIncrement());
                    break;
                case Event.PGDN:
                    target = vertical;
                    vertical.setValue(vertical.getValue() + vertical.getPageIncrement());
                    break;
                case Event.UP:
                    target = vertical;
                    vertical.setValue(vertical.getValue() - vertical.getLineIncrement());
                    break;
                case Event.DOWN:
                    target = vertical;
                    vertical.setValue(vertical.getValue() + vertical.getLineIncrement());
                    break;
                case Event.LEFT:
                    target = horizontal;
                    if (e.controlDown())
                        horizontal.setValue(horizontal.getValue() - horizontal.getPageIncrement());
                    else 
                        horizontal.setValue(horizontal.getValue() - horizontal.getLineIncrement());
                    break;
                case Event.RIGHT:
                    target = horizontal;
                    if (e.controlDown())
                        horizontal.setValue(horizontal.getValue() + horizontal.getPageIncrement());
                    else 
                        horizontal.setValue(horizontal.getValue() + horizontal.getLineIncrement());
                    break;
                default:
                    return false;
            }
            Integer value = new Integer (target.getValue());
            postEvent (new Event ((Object)target, Event.SCROLL_ABSOLUTE, (Object)value));
            return true;
        }
        return false;
    }
}
