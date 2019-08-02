// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
//Java 1.1 only
import java.awt.*;
public class CornerLayout2 extends CornerLayout implements LayoutManager2 {

    public void addLayoutComponent(Component comp, Object constraints) {
        if ((constraints == null) || (constraints instanceof String)) {
            addLayoutComponent((String)constraints, comp);
        } else {
            throw new IllegalArgumentException(
                  "cannot add to layout: constraint must be a string (or null)");
        }
    }
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    public float getLayoutAlignmentX(Container parent) {
        return Component.CENTER_ALIGNMENT;
    }
    public float getLayoutAlignmentY(Container parent) {
        return Component.CENTER_ALIGNMENT;
    }
    public void invalidateLayout(Container target) {
    }
}
