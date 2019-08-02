// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.applet.*;
import java.awt.*;
import java.awt.image.*;
public class MemoryImage extends Applet {
    Image i, j;
    int width = 200;
    int height = 200;
    public void init () {
        int rgbPixels[] = new int [width*height];
        byte indPixels[] = new byte [width*height];
        int index = 0;
        Color colorArray[] = {Color.red, Color.orange, Color.yellow,
                Color.green, Color.blue, Color.magenta};
        int rangeSize = width / colorArray.length;
        int colorRGB;
        byte colorIndex;
        byte reds[]   = new byte[colorArray.length];
        byte greens[] = new byte[colorArray.length];
        byte blues[]  = new byte[colorArray.length];
        for (int i=0;i<colorArray.length;i++) {
            reds[i]   = (byte)colorArray[i].getRed();
            greens[i] = (byte)colorArray[i].getGreen();
            blues[i]  = (byte)colorArray[i].getBlue();
        }
        for (int y=0;y<height;y++) {
            for (int x=0;x<width;x++) {
                if (x < rangeSize) {
                    colorRGB = Color.red.getRGB();
                    colorIndex = 0;
                } else if (x < (rangeSize*2)) {
                    colorRGB = Color.orange.getRGB();
                    colorIndex = 1;
                } else if (x < (rangeSize*3)) {
                    colorRGB = Color.yellow.getRGB();
                    colorIndex = 2;
                } else if (x < (rangeSize*4)) {
                    colorRGB = Color.green.getRGB();
                    colorIndex = 3;
                } else if (x < (rangeSize*5)) {
                    colorRGB = Color.blue.getRGB();
                    colorIndex = 4;
                } else {
                    colorRGB = Color.magenta.getRGB();
                    colorIndex = 5;
                }
                rgbPixels[index] = colorRGB;
                indPixels[index] = colorIndex;
                index++;
            }
        }
        i = createImage (new MemoryImageSource (width, height, rgbPixels,
            0, width));
        j = createImage (new MemoryImageSource (width, height,
            new IndexColorModel (8, colorArray.length, reds, greens, blues),
            indPixels, 0, width));
    }
    public void paint (Graphics g) {
        g.drawImage (i, 0, 0, this);
        g.drawImage (j, width+5, 0, this);
    }
}
