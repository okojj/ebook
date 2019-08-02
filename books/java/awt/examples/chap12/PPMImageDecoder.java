// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or implied.
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;

public class PPMImageDecoder implements ImageProducer {

/* Since done in-memory, only one consumer */
    private ImageConsumer consumer;
    boolean loadError = false;
    int width;
    int height;
    int store[][];
    Hashtable props = new Hashtable();
/* Format of Ppm file is single pass/frame, w/ complete scan lines in order */
    private static int PpmHints = (ImageConsumer.TOPDOWNLEFTRIGHT |
                                   ImageConsumer.COMPLETESCANLINES |
                                   ImageConsumer.SINGLEPASS |
                                   ImageConsumer.SINGLEFRAME);

/* There is only a single consumer. When it registers, produce image. */
/* On error, notify consumer */

    public synchronized void addConsumer (ImageConsumer ic) {
        consumer = ic;
        try {
            produce();
        } catch (Exception e) {
            if (consumer != null)
                consumer.imageComplete (ImageConsumer.IMAGEERROR);
        }
        consumer = null;
    }

/* If consumer passed to routine is single consumer, return true, else false */

    public synchronized boolean isConsumer (ImageConsumer ic) {
        return (ic == consumer);
    }

/* Disables consumer if currently consuming */

    public synchronized void removeConsumer (ImageConsumer ic) {
        if (consumer == ic)
            consumer = null;
    }

/* Production is done by adding conumer */

    public void startProduction (ImageConsumer ic) {
        addConsumer (ic);
    }

    public void requestTopDownLeftRightResend (ImageConsumer ic) {
        // Not needed.  The data is always in this format.
    }

/* Production Process:
        Prerequisite: Image already read into store array. (readImage)
                      props / width / height already set (readImage)
        Assumes RGB Color Model - would need to filter to change.
        Sends Ppm Image data to consumer.
        Pixels sent one row at a time.
*/

    private void produce () {
        ColorModel cm = ColorModel.getRGBdefault();
        if (consumer != null) {
            if (loadError) {
                consumer.imageComplete (ImageConsumer.IMAGEERROR);
            } else {
                consumer.setDimensions (width, height);
                consumer.setProperties (props);
                consumer.setColorModel (cm);
                consumer.setHints (PpmHints);
                for (int j=0;j<height;j++)
                    consumer.setPixels (0, j, width, 1, cm, store[j], 0, width);
                consumer.imageComplete (ImageConsumer.STATICIMAGEDONE);
            }
        }
    }

/* Allows reading to be from internal byte array, in addition to disk/socket */

    public void readImage (byte b[]) {
        readImage (new ByteArrayInputStream (b));
    }

/* readImage reads image data from Stream */
/* parses data for PPM format             */
/* closes inputstream when done           */

    public void readImage (InputStream is) {
        long tm = System.currentTimeMillis();
        boolean raw=false;
        DataInputStream dis = null;
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream (is);
            dis = new DataInputStream (bis);
            String word;
            word = readWord (dis);
            if ("P6".equals (word)) {
                raw = true;
            } else if ("P3".equals (word)) {
                raw = false;
            } else {
                throw (new AWTException ("Invalid Format " + word));
            }
            width = Integer.parseInt (readWord (dis));
            height = Integer.parseInt (readWord (dis));
            // Could put comments in props - makes readWord more complex
            int maxColors = Integer.parseInt (readWord (dis));
            if ((maxColors < 0) || (maxColors > 255)) {
                throw (new AWTException ("Invalid Colors " + maxColors));
            }
            store = new int[height][width];
            if (raw) {
                byte row[] = new byte [width*3];
                for (int i=0;i<height;i++){
                    dis.readFully (row);
                    for (int j=0,k=0;j<width;j++,k+=3) {
                        int red = row[k];
                        int green = row[k+1];
                        int blue = row[k+2];
                        if (red < 0)
                            red +=256;
                        if (green < 0)
                            green +=256;
                        if (blue < 0)
                            blue +=256;
                        store[i][j] = (0xff<< 24) | (red << 16) | (green << 8) | blue;
                    }
                }
            } else {
                for (int i=0;i<height;i++) {
                    for (int j=0;j<width;j++) {
                        int red = Integer.parseInt (readWord (dis));
                        int green = Integer.parseInt (readWord (dis));
                        int blue = Integer.parseInt (readWord (dis));
                        store[i][j] = (0xff<< 24) | (red << 16) | (green << 8) | blue;
                    }
                }
            }
        } catch (IOException io) {
            loadError = true;
            System.out.println ("IO Exception " + io.getMessage());
        } catch (AWTException awt) {
            loadError = true;
            System.out.println ("AWT Exception " + awt.getMessage());
        } catch (NoSuchElementException nse) {
            loadError = true;
            System.out.println ("No Such Element Exception " + nse.getMessage());
        } finally {
            try {
                if (dis != null)
                    dis.close();
                if (bis != null)
                    bis.close();
                if (is != null)
                    is.close();
            } catch (IOException io) {
                System.out.println ("IO Exception " + io.getMessage());
            }
        }
        System.out.println ("Done in " + (System.currentTimeMillis() - tm) + " ms");
    }

/* readWord returns a word of text from stream          */
/* Ignores PPM comment lines.                           */
/* word defined to be something wrapped by whitespace   */

    private String readWord (InputStream is) throws IOException {
        StringBuffer buf = new StringBuffer();
        int b;
        do { // get rid of leading whitespace
            if ((b=is.read()) == -1)
                throw new EOFException();
            if ((char)b == '#') {  // read to end of line - ppm comment
                DataInputStream dis = new DataInputStream (is);
                dis.readLine();
                b = ' ';  // ensure more reading
            }
        } while (Character.isSpace ((char)b));
        do {
            buf.append ((char)(b));
            if ((b=is.read()) == -1)
                throw new EOFException();
        } while (!Character.isSpace ((char)b));  // reads first space
        return buf.toString();
    }
}
