// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
import java.awt.image.*;
public class DynamicFilter extends ImageFilter {
    Color overlapColor;
    int   delay;
    int   imageWidth;
    int   imageHeight;
    int   iterations;
    DynamicFilter (int delay, int iterations, Color color) {
        this.delay      = delay;
        this.iterations = iterations;
        overlapColor    = color;
    }
    public void setDimensions (int width, int height) {
        imageWidth  = width;
        imageHeight = height;
        consumer.setDimensions (width, height);
    }
    public void setHints (int hints) {
        consumer.setHints (ImageConsumer.RANDOMPIXELORDER);
    }
    public void resendTopDownLeftRight (ImageProducer ip) {
    }
    public void imageComplete (int status) {
        if ((status == IMAGEERROR) || (status == IMAGEABORTED)) {
            consumer.imageComplete (status);
            return;
        } else {
            int xWidth = imageWidth / iterations;
            if (xWidth <= 0)
                xWidth = 1;
            int newPixels[] = new int [xWidth*imageHeight];
            int iColor = overlapColor.getRGB();
            for (int x=0;x<(xWidth*imageHeight);x++)
                newPixels[x] = iColor;
            int t=0;
            for (;t<(imageWidth-xWidth);t+=xWidth) {
                consumer.setPixels(t, 0, xWidth, imageHeight,
                        ColorModel.getRGBdefault(), newPixels, 0, xWidth);
                consumer.imageComplete (ImageConsumer.SINGLEFRAMEDONE);
                try {
                    Thread.sleep (delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int left = imageWidth-t;
            if (left > 0) {
                consumer.setPixels(imageWidth-left, 0, left, imageHeight,
                        ColorModel.getRGBdefault(), newPixels, 0, xWidth);
                consumer.imageComplete (ImageConsumer.SINGLEFRAMEDONE);
            }
            consumer.imageComplete (STATICIMAGEDONE);
        }
    }
}
