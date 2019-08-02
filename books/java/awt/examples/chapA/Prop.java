// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.util.Properties;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.MalformedURLException;

public class Prop extends java.applet.Applet {
    Properties p;
    String theMessage;
    public void init () {
        p = new Properties();
        try {
            URL propSource = new URL (getDocumentBase(), "prop.list");
            InputStream propIS = propSource.openStream();
            p.load(propIS);
            p.list(System.out);
            initFromProps(p);
            propIS.close();
        } catch (MalformedURLException e) {
            System.out.println ("Invalid URL");
        } catch (IOException e) {
            System.out.println ("Error loading properties");
        }
    }
    public void initFromProps (Properties p) {
        String fontsize = p.getProperty ("MyProg.font.size");
        String fontname = p.getProperty ("MyProg.font.name");
        String fonttype = p.getProperty ("MyProg.font.type");
        String message  = p.getProperty ("MyProg.message");
        int size;
        int type;
        if (fontsize == null) {
            size = 12;
        } else {
            size = Integer.parseInt (fontsize);
        }
        if (fontname == null) {
            fontname = "TimesRoman";
        }
        type = Font.PLAIN;
        if (fonttype != null) {
            fonttype.toLowerCase();
            boolean bold = (fonttype.indexOf ("bold") != -1);
            boolean italic = (fonttype.indexOf ("italic") != -1);
            if (bold) type |= Font.BOLD;
            if (italic) type |= Font.ITALIC;
        }
        if (message == null) {
            theMessage = "Welcome to Java";
        } else {
            theMessage = message;
        }
        setFont (new Font (fontname, type, size));
    }
    public void paint (Graphics g) {
        g.drawString (theMessage, 50, 50);
    }
}
