// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;
import java.io.*;

public class FdTest extends Frame {
    TextArea myTextArea;
    Label myLabel;
    Button loadButton;
    Button saveButton;
    FdTest () {
        super ("File Dialog Tester");
        Panel p = new Panel ();
        p.add (loadButton = new Button ("Load"));
        p.add (saveButton = new Button ("Save"));
        add ("North", myLabel = new Label ());
        add ("South", p);
        add ("Center", myTextArea = new TextArea (10, 40));
        Menu m = new Menu ("File");
        m.add (new MenuItem ("Quit"));
        MenuBar mb = new MenuBar();
        mb.add (m);
        setMenuBar (mb);
        pack();
    }
    public static void main (String args[]) {
        FdTest f = new FdTest();
	f.show();
    }
    public boolean handleEvent (Event e) {
        if (e.id == Event.WINDOW_DESTROY) {
            hide ();
            dispose ();
            System.exit(0);
            return true;  // never gets here
        }
        return super.handleEvent (e);
    }
    public boolean action (Event e, Object o) {
        if (e.target instanceof MenuItem) {
            hide ();
            dispose ();
            System.exit(0);
            return true;  // never gets here
        } else if (e.target instanceof Button) {
            int state;
            String msg;
            if (e.target == loadButton) {
                state = FileDialog.LOAD;
                msg = "Load File";
            } else { // if (e.target == saveButton)
                state = FileDialog.SAVE;
                msg = "Save File";
            }
            FileDialog file = new FileDialog (this, msg, state);
            file.setFile ("*.java");  // Set initial filename filter
            file.show(); // Blocks
            String curFile;
            if ((curFile = file.getFile()) != null) {
                String filename = file.getDirectory() + curFile;
                // curFile ends in .*.* if file does not exist
                byte[] data;
                setCursor (Frame.WAIT_CURSOR);
                if (state == FileDialog.LOAD) {
                    File f = new File (filename);
                    try {
                        FileInputStream fin = new FileInputStream (f);
                        int filesize = (int)f.length();
                        data = new byte[filesize];
                        fin.read (data, 0, filesize+100);
                    } catch (FileNotFoundException exc) {
                        String errorString = "File Not Found: " + filename;
                        data = new byte[errorString.length()];
                        errorString.getBytes (0, errorString.length(), data, 0);
                    } catch (IOException exc) {
                        String errorString = "IOException: " + filename;
                        data = new byte[errorString.length()];
                        errorString.getBytes (0, errorString.length(), data, 0);
                    }
                    myLabel.setText ("Load: " + filename);
                } else {
// Remove trailing ".*.*" if present - signifies file does not exist
                    if (filename.indexOf (".*.*") != -1) {
                        filename = filename.substring (0, filename.length()-4);
                    }
                    File f = new File (filename);
                    try {
                        FileOutputStream fon = new FileOutputStream (f);
                        String text = myTextArea.getText();
                        int textsize = text.length();
                        data = new byte[textsize];
                        text.getBytes (0, textsize, data, 0);
                        fon.write (data);
                        fon.close ();
                    } catch (IOException exc) {
                        String errorString = "IOException: " + filename;
                        data = new byte[errorString.length()];
                        errorString.getBytes (0, errorString.length(), data, 0);
                    }
                    myLabel.setText ("Save: " + filename);
                }
                // Note - on successful save, text is redisplayed
                myTextArea.setText (new String (data, 0));
                setCursor (Frame.DEFAULT_CURSOR);
            }
            return true;
        }
        return false;
    }
}
