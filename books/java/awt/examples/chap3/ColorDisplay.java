// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
public class ColorDisplay extends Frame {
    int width, height;
    static Color colors[] =
                {Color.black, Color.blue, Color.cyan, Color.darkGray,
                Color.gray, Color.green, Color.lightGray, Color.magenta,
                Color.orange, Color.pink, Color.red, Color.white,
                Color.yellow};
    ColorDisplay () {
        super ("ColorDisplay");
        setBackground (Color.white);
    }
    static public void main (String args[]) {
        ColorDisplay f = new ColorDisplay();
        f.resize (300,300);
        f.show();
    }
    public void paint (Graphics g) {
        g.translate(insets().left, insets().top);
        if (width == 0) {
            Insets inset = insets();
            width  = (size().width - inset.right - inset.left) / 3;
            height = (size().height - inset.top - inset.bottom) / 5;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if ((i == 2) && (j >= 3)) break;
                g.setColor (colors[i*5+j]);
                g.fillRect (i*width, j*height, width, height);
            }
        }
    }
    public boolean keyDown (Event e, int c) {
        for (int i=0;i<colors.length;i++)
            colors[i] = colors[i].darker();
        repaint();
        System.out.println ("Help");
        return true;
    }
    public boolean mouseDown (Event e, int x, int y) {
        for (int i=0;i<colors.length;i++)
            colors[i] = colors[i].brighter();
        repaint();
        System.out.println ("ClickCount: " + e.clickCount);
        System.out.println (e.modifiers);
        return true;
    }
}

