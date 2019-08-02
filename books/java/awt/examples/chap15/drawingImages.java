// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.net.*;
import java.awt.*;
import java.awt.image.*;
import java.applet.*;
public class drawingImages extends Applet {
    Image i, j;
    public void init () {
        try {
            URL u = new URL (getDocumentBase(), "ora-icon.gif");
            i = Toolkit.getDefaultToolkit().getImage (u);
            TransparentImageFilter tf = new TransparentImageFilter (.5f);
            j = Toolkit.getDefaultToolkit().createImage (
                        new FilteredImageSource (i.getSource(), tf));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void paint (Graphics g) {
        g.drawImage (i, 10, 10, this);
        g.drawImage (j, 170, 10, Color.lightGray, this);
    }

}
