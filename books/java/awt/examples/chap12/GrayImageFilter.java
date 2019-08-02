// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.image.*;

public class GrayImageFilter extends RGBImageFilter {
    public GrayImageFilter () {
        canFilterIndexColorModel = true;
    }
    public int filterRGB (int x, int y, int rgb) {
        int gray  = (((rgb & 0xff0000) >> 16) +
                    ((rgb & 0x00ff00) >> 8) +
                    (rgb & 0x0000ff)) / 3;
        return (0xff000000 | (gray << 16) | (gray <<  8) |  gray);
    }
}  

