// This example is from the book "Java in a Nutshell, Second Edition".
// Written by David Flanagan.  Copyright (c) 1997 O'Reilly & Associates.
// You may distribute this source code for non-commercial purposes only.
// You may study, modify, and use this example for any purpose, as long as
// this notice is retained.  Note that this example is provided "as is",
// WITHOUT WARRANTY of any kind either expressed or implied.

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.MissingResourceException;

/** A convenience class to automatically create localized menu panes */
public class SimpleMenu {
  /** The convenience method that creates menu panes */
  public static Menu create(String bundlename,
                            String menuname, String[] itemnames,
                            ActionListener listener, boolean popup) {
    // Get the resource bundle used for this menu.
    ResourceBundle b = ResourceBundle.getBundle(bundlename);

    // Get the menu title from the bundle.  Use name as default label.
    String menulabel;
    try { menulabel = b.getString(menuname + ".label"); }
    catch(MissingResourceException e) { menulabel = menuname; }

    // Create the menu pane.
    Menu m;
    if (popup) m = new PopupMenu(menulabel);
    else m = new Menu(menulabel);

    // For each named item in the menu.
    for(int i = 0; i < itemnames.length; i++) {
      // Look up the label for the item, using name as default.
      String itemlabel;
      try { itemlabel=b.getString(menuname + "." + itemnames[i] + ".label"); }
      catch (MissingResourceException e) { itemlabel = itemnames[i]; }

      // Look up a shortcut for the item, and create the menu shortcut, if any.
      String shortcut;
      try{shortcut = b.getString(menuname + "." + itemnames[i]+".shortcut"); }
      catch (MissingResourceException e) { shortcut = null; }
      MenuShortcut ms = null;
      if (shortcut != null) ms = new MenuShortcut(shortcut.charAt(0));

      // Create the menu item.
      MenuItem mi;
      if (ms != null) mi = new MenuItem(itemlabel, ms);
      else mi = new MenuItem(itemlabel);

      // Register an action listener and command for the item.
      if (listener != null) {
        mi.addActionListener(listener);
        mi.setActionCommand(itemnames[i]);
      }

      // Add the item to the menu.
      m.add(mi);
    }

    // Return the automatically created localized menu.
    return m;
  }

  /** A simple test program for the above code */
  public static void main(String[] args) {
    // Set the default locale based on the command-line args.
    if (args.length == 2) Locale.setDefault(new Locale(args[0], args[1]));

    Frame f = new Frame("SimpleMenu Test");  // Create a window.
    MenuBar menubar = new MenuBar();         // Create a menubar.
    f.setMenuBar(menubar);                   // Add menubar to window.

    // Create a menu using our convenience routine (and the default locale).
    Menu colors = SimpleMenu.create("Menus", "colors",
                                    new String[] { "red", "green", "blue" },
                                    null, false);

    menubar.add(colors);                     // Add the menu to the menubar.
    f.setSize(300, 150);                     // Set the window size.
    f.show();                                // Pop the window up.
  }
}
