// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
public class TextBox3D extends Canvas {
    String text;
    public TextBox3D (String s, int width, int height) {
        super();
        text=s;
        setSize(width, height);
    }
    public synchronized void paint (Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        Dimension size=getSize();
        int x = (size.width - fm.stringWidth(text))/2;
        int y = (size.height - fm.getHeight())/2;
        g.setColor (SystemColor.control);
        g.fillRect (0, 0, size.width, size.height);
        g.setColor (SystemColor.controlShadow);
        g.drawLine (0, 0, 0, size.height-1);
        g.drawLine (0, 0, size.width-1, 0);
        g.setColor (SystemColor.controlDkShadow);
        g.drawLine (0, size.height-1, size.width-1, size.height-1);
        g.drawLine (size.width-1, 0, size.width-1, size.height-1);
        g.setColor (SystemColor.controlText);
        g.drawString (text, x, y);
    }
}
