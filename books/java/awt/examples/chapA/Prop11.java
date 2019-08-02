// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.Properties;
import java.applet.Applet;
public class Prop11 extends Applet {
    Image im;
    Font f;
    String msg;
    public void paint (Graphics g) {
        g.setFont (f);
        if (im != null)
            g.drawImage (im, 50, 100, this);
        if (msg != null)
            g.drawString (msg, 50, 50);
    }
    public void init () {
        InputStream is = getClass().getResourceAsStream("prop11.list");
        Properties p = new Properties();
        try {
            p.load (is);
            f = Font.decode(p.getProperty("MyProg.font"));
            msg = p.getProperty("MyProg.message");
            String name = p.getProperty("MyProg.image");           
            URL url = getClass().getResource(name);
            im = getImage (url);
        } catch (IOException e) {
            System.out.println ("error loading props...");
        }
    }
}