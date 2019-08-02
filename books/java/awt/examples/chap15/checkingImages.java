// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
import java.awt.image.*;
import java.applet.*;
public class checkingImages extends Applet {
    Image i;
    public void init () {
        i = getImage (getDocumentBase(), "ora-icon.gif");
    }
    public void displayChecks (int i) {
        if ((i & ImageObserver.WIDTH) != 0)
            System.out.print ("Width ");
        if ((i & ImageObserver.HEIGHT) != 0)
            System.out.print ("Height ");
        if ((i & ImageObserver.PROPERTIES) != 0)
            System.out.print ("Properties ");
        if ((i & ImageObserver.SOMEBITS) != 0)
            System.out.print ("Some-bits ");
        if ((i & ImageObserver.FRAMEBITS) != 0)
            System.out.print ("Frame-bits ");
        if ((i & ImageObserver.ALLBITS) != 0)
            System.out.print ("All-bits ");
        if ((i & ImageObserver.ERROR) != 0)
            System.out.print ("Error-loading ");
        if ((i & ImageObserver.ABORT) != 0)
            System.out.print ("Loading-Aborted ");
        System.out.println ();
    }
    public void paint (Graphics g) {
        displayChecks (Toolkit.getDefaultToolkit().checkImage(i, -1, -1, this));
        g.drawImage (i, 0, 0, this);
    }
}
