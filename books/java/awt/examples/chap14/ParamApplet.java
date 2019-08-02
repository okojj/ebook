// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.util.StringTokenizer;
public class ParamApplet extends java.applet.Applet {
    public void init () {
	String param;
        float one;
	String two;
	if ((param = getParameter ("ONE")) == null) {
            one = -1.0f;  // Not present
	} else {
            one = Float.valueOf (param).longValue();
	}
        if ((param = getParameter ("two")) == null) {
	    two = "two";
	} else {
            two = param.toUpperCase();
	}
        System.out.println ("One: " + one);
        System.out.println ("Two: " + two);
    }
}
