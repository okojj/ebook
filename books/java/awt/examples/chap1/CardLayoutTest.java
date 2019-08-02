// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or

import java.awt.*;

class CardPanel extends Panel {
    Panel create(LayoutManager layout) {
	Panel p = new Panel();
	p.setLayout(layout);
	p.add("North",  new Button("this"));
	p.add("West",   new Button("is"));
	p.add("South",  new Button("a"));
	p.add("East",   new Button("test"));
	p.add("Center", new Button("applet"));
	return p;
    }
    CardPanel() {
	setLayout(new CardLayout());
	add("flow", create(new FlowLayout()));
	add("border", create(new BorderLayout()));
	add("grid", create(new GridLayout(2, 2)));
    }
}
public class CardLayoutTest extends java.applet.Applet {
    CardPanel cards;
    public CardLayoutTest() {
	setLayout(new BorderLayout());
	add("Center", cards = new CardPanel());
	Choice c = new Choice();
	c.addItem("flow");
	c.addItem("border");
	c.addItem("grid");
        add("South", c);
    }
    public boolean action(Event evt, Object arg) {
	if (evt.target instanceof Choice) {
	    ((CardLayout)cards.getLayout()).show(cards,(String)arg);
	}
	return true;
    }
    public static void main(String args[]) {
	Frame f = new Frame("CardLayoutTest");
	CardLayoutTest card = new CardLayoutTest();
	card.init();
	card.start();
	f.add("Center", card);
	f.resize(300, 300);
	f.show();
    }
}
