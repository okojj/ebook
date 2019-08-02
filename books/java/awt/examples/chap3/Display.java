// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
public class Display extends Frame {
    static String[] fonts;
    private Dimension dim;
    Display () {
        super ("Font Display");
        fonts = Toolkit.getDefaultToolkit().getFontList();
    }
    public void addNotify() {
        Font f;
        super.addNotify();
        int height   = 0;
        int maxWidth = 0;
        final int vMargin  = 5, hMargin = 5;
        for (int i=0;i<fonts.length;i++) {
            f = new Font (fonts[i], Font.PLAIN, 12);
            height += getHeight (f);
            f = new Font (fonts[i], Font.BOLD, 12);
            height += getHeight (f);
            f = new Font (fonts[i], Font.ITALIC, 12);
            height += getHeight (f);
            f = new Font (fonts[i], Font.BOLD | Font.ITALIC, 12);
            height += getHeight (f);
            maxWidth = Math.max (maxWidth, getWidth (f, fonts[i] + " BOLDITALIC"));
        }
        Insets inset = insets();
        dim = new Dimension (maxWidth + inset.left + inset.right + hMargin,
                        height + inset.top + inset.bottom + vMargin);
        resize (dim);
    }
    static public void main (String args[]) {
        Display f = new Display();
        f.show();
    }
    private int getHeight (Font f) {
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(f);
        return fm.getHeight();
    }
    private int getWidth (Font f, String s) {
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(f);
        return fm.stringWidth(s);
    }
    public void paint (Graphics g) {
        g.translate(insets().left, insets().top);
        int x = 0;
        int y = 0;
        for (int i=0;i<fonts.length;i++) {
            Font plain = new Font (fonts[i], Font.PLAIN, 12);
            Font bold = new Font (fonts[i], Font.BOLD, 12);
            Font italic = new Font (fonts[i], Font.ITALIC, 12);
            Font bolditalic = new Font (fonts[i], Font.BOLD | Font.ITALIC, 12);
            g.setFont (plain);
            y += getHeight (plain);
            g.drawString (fonts[i] + " PLAIN", x, y);
            g.setFont (bold);
            y += getHeight (bold);
            g.drawString (fonts[i] + " BOLD", x, y);
            g.setFont (italic);
            y += getHeight (italic);
            g.drawString (fonts[i] + " ITALIC", x, y);
            g.setFont (bolditalic);
            y += getHeight (bolditalic);
            g.drawString (fonts[i] + " BOLDITALIC", x, y);
        }
        resize (dim);
    }
}
