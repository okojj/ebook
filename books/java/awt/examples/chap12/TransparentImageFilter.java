// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.image.*;

class TransparentImageFilter extends RGBImageFilter {
    float alphaPercent;
    public TransparentImageFilter () {
        this (0.75f);
    }
    public TransparentImageFilter (float aPercent)
            throws IllegalArgumentException {
        if ((aPercent < 0.0) || (aPercent > 1.0))
            throw new IllegalArgumentException();
        alphaPercent = aPercent;
        canFilterIndexColorModel = true;
    }
    public int filterRGB (int x, int y, int rgb) {
        int a = (rgb >> 24) & 0xff;
        a *= alphaPercent;
        return ((rgb & 0x00ffffff) | (a << 24));
    }
}
