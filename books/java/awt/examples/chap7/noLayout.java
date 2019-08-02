// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.Button;
import java.applet.Applet;
public class noLayout extends Applet {
    public void init () {
        setLayout (null);
        Button x = new Button ("Hello");
        add (x);
        x.reshape (50, 60, 50, 70);
        Button y = new Button ("World");
        add (y);
        y.reshape (100, 120, 50, 70);
    }
}
