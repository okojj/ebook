// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
public class Text3DTest extends java.applet.Applet {
  public void init () {
    add (new TextBox3D ("Help Me", getSize().width-10, getSize().height-10));
  }
}