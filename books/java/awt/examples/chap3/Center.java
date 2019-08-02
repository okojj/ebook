// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
public class Center extends Frame {
    static String text[];
    private Dimension dim;
    static public void main (String args[]) {
        if (args.length == 0) {
            System.err.println ("Usage: java Center <some text>");
            return;
        }
        text = args;
        Center f = new Center();
        f.show();
    }
    public void addNotify() {
        super.addNotify();
        int maxWidth = 0;
        FontMetrics fm = getToolkit().getFontMetrics(getFont());
        for (int i=0;i<text.length;i++) {
            maxWidth = Math.max (maxWidth, fm.stringWidth(text[i]));
        }
        Insets inset = insets();
        dim = new Dimension (maxWidth + inset.left + inset.right,
            text.length*fm.getHeight() + inset.top + inset.bottom);
        resize (dim);
    }
    public void paint (Graphics g) {
        g.translate(insets().left, insets().top);
        FontMetrics fm = g.getFontMetrics();
        for (int i=0;i<text.length;i++) {
            int x,y;
            x = (size().width - fm.stringWidth(text[i]))/2;
            y = (i+1)*fm.getHeight()-1;
            g.drawString (text[i], x, y);
        }
  
    }
}
