// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
public class Animate extends Frame {
    static Image im[];
    static int numImages = 12;
    static int counter=0;
    Animate () {
        super ("Animate");
    }
    public static void main (String[] args) {
        Frame f = new Animate();
        f.resize (225, 225);
        f.show();
        im = new Image[numImages];
        for (int i=0;i<numImages;i++) {
            im[i] = Toolkit.getDefaultToolkit().getImage ("clock"+i+".jpg");
        }
    }
    public synchronized void paint (Graphics g) {
        g.translate (insets().left, insets().top);
        g.drawImage (im[counter], 0, 0, this);
        counter++;
        if (counter == numImages)
            counter = 0;
        repaint (200);
    }
}
