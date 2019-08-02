// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.net.*;
import java.applet.*;
public class audioTest extends Applet {
    public void init () {
        System.out.println ("Before");
        play (getDocumentBase(), "audio/beep.au");
        System.out.println ("After");
    }
}
