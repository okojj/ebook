// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
import java.applet.*;
public class drawingImages11 extends Applet {
    Image i, j;
    public void init () {
        i = getImage (getDocumentBase(), "rosey.gif");
    }
    public void paint (Graphics g) {
        g.drawImage (i, 10, 10, this);
        g.drawImage (i, 10, 85, i.getWidth(this)+10, i.getHeight(this)+85,
		i.getWidth(this), i.getHeight(this), 0, 0, this);
        g.drawImage (i, 270, 10, i.getWidth(this)+270, i.getHeight(this)*2+10,
		0, 0, i.getWidth(this), i.getHeight(this), Color.gray, this);
        g.drawImage (i, 10, 170, i.getWidth(this)*2+10, i.getHeight(this)+170,
		0, i.getHeight(this)/2, i.getWidth(this)/2, 0, this);
    }
}
