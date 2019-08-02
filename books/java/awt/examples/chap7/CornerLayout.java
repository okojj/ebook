// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or

import java.awt.*;

/**
 * An 'educational' layout.  CornerLayout will layout a container
 * using members named "Northeast", "Northwest", "Southeast",
 * "Southwest", and "Center".
 *
 * The "Northeast", "Northwest", "Southeast" and "Soutwest" components
 * get sized relative to the adjacent corner's components and
 * the constraints of the container's size.  The "Center" component will
 * get any space left over. 
 */

public class CornerLayout implements LayoutManager {
    int hgap;
    int vgap;
    int mode;

    public final static int NORMAL = 0;
    public final static int FULL_WIDTH = 1;
    public final static int FULL_HEIGHT = 2;

    Component northwest;
    Component southwest;
    Component northeast;
    Component southeast;
    Component center;

    /**
     * Constructs a new CornerLayout.
     */
    public CornerLayout() {
        this (0, 0, CornerLayout.NORMAL);
    }

    public CornerLayout(int mode) {
        this (0, 0, mode);
    }

    public CornerLayout(int hgap, int vgap) {
        this (hgap, vgap, CornerLayout.NORMAL);
    }

    public CornerLayout(int hgap, int vgap, int mode) {
	this.hgap = hgap;
	this.vgap = vgap;
        this.mode = mode;
    }

    public void addLayoutComponent(String name, Component comp) {
	if ("Center".equals(name)) {
	    center = comp;
        } else if ("Northwest".equals(name)) {
            northwest = comp;
        } else if ("Southeast".equals(name)) {
            southeast = comp;
        } else if ("Northeast".equals(name)) {
            northeast = comp;
        } else if ("Southwest".equals(name)) {
            southwest = comp;
	}
    }

    public void removeLayoutComponent(Component comp) {
	if (comp == center) {
	    center = null;
        } else if (comp == northwest) {
            northwest = null;
        } else if (comp == southeast) {
            southeast = null;
        } else if (comp == northeast) {
            northeast = null;
        } else if (comp == southwest) {
            southwest = null;
	}
    }

    public Dimension minimumLayoutSize(Container target) {
	Dimension dim = new Dimension(0, 0);

        Dimension northeastDim = new Dimension (0,0);
        Dimension northwestDim = new Dimension (0,0);
        Dimension southeastDim = new Dimension (0,0);
        Dimension southwestDim = new Dimension (0,0);
        Dimension centerDim    = new Dimension (0,0);

        if ((northeast != null) && northeast.isVisible()) {
            northeastDim = northeast.minimumSize();
	}
        if ((southwest != null) && southwest.isVisible()) {
            southwestDim = southwest.minimumSize();
	}
        if ((center != null) && center.isVisible()) {
            centerDim = center.minimumSize();
	}
        if ((northwest != null) && northwest.isVisible()) {
            northwestDim = northwest.minimumSize();
	}
        if ((southeast != null) && southeast.isVisible()) {
            southeastDim = southeast.minimumSize();
	}

        dim.width = Math.max (northwestDim.width, southwestDim.width) +
                        hgap + centerDim.width + hgap +
                        Math.max (northeastDim.width, southeastDim.width);
        dim.height = Math.max (northwestDim.height, northeastDim.height) +
                        + vgap + centerDim.height + vgap +
                        Math.max (southeastDim.height, southwestDim.height);

	Insets insets = target.insets();
        dim.width += insets.left + insets.right;
	dim.height += insets.top + insets.bottom;

	return dim;
    }
    
    public Dimension preferredLayoutSize(Container target) {
	Dimension dim = new Dimension(0, 0);
        Dimension northeastDim = new Dimension (0,0);
        Dimension northwestDim = new Dimension (0,0);
        Dimension southeastDim = new Dimension (0,0);
        Dimension southwestDim = new Dimension (0,0);
        Dimension centerDim    = new Dimension (0,0);


        if ((northeast != null) && northeast.isVisible()) {
            northeastDim = northeast.preferredSize();
	}
        if ((southwest != null) && southwest.isVisible()) {
            southwestDim = southwest.preferredSize();
	}
        if ((center != null) && center.isVisible()) {
            centerDim = center.preferredSize();
	}
        if ((northwest != null) && northwest.isVisible()) {
            northwestDim = northwest.preferredSize();
	}
        if ((southeast != null) && southeast.isVisible()) {
            southeastDim = southeast.preferredSize();
	}

        dim.width = Math.max (northwestDim.width, southwestDim.width) +
                        hgap + centerDim.width + hgap +
                        Math.max (northeastDim.width, southeastDim.width);
        dim.height = Math.max (northwestDim.height, northeastDim.height) +
                        + vgap + centerDim.height + vgap +
                        Math.max (southeastDim.height, southwestDim.height);

	Insets insets = target.insets();
	dim.width += insets.left + insets.right;
	dim.height += insets.top + insets.bottom;

	return dim;
    }

    public void layoutContainer(Container target) {
	Insets insets = target.insets();
	int top = insets.top;
        int bottom = target.size().height - insets.bottom;
	int left = insets.left;
        int right = target.size().width - insets.right;

        Dimension northeastDim = new Dimension (0,0);
        Dimension northwestDim = new Dimension (0,0);
        Dimension southeastDim = new Dimension (0,0);
        Dimension southwestDim = new Dimension (0,0);
        Dimension centerDim    = new Dimension (0,0);

        Point topLeftCorner, topRightCorner, bottomLeftCorner,
                        bottomRightCorner;

        if ((northeast != null) && northeast.isVisible()) {
            northeastDim = northeast.preferredSize();
	}
        if ((southwest != null) && southwest.isVisible()) {
            southwestDim = southwest.preferredSize();
	}
        if ((center != null) && center.isVisible()) {
            centerDim = center.preferredSize();
	}
        if ((northwest != null) && northwest.isVisible()) {
            northwestDim = northwest.preferredSize();
	}
        if ((southeast != null) && southeast.isVisible()) {
            southeastDim = southeast.preferredSize();
	}

        topLeftCorner = new Point (left +
                          Math.max (northwestDim.width, southwestDim.width),
                                top + 
                          Math.max (northwestDim.height, northeastDim.height));
        topRightCorner = new Point (right -
                          Math.max (northeastDim.width, southeastDim.width),
                                top +
                          Math.max (northwestDim.height, northeastDim.height));
        bottomLeftCorner = new Point (left + 
                          Math.max (northwestDim.width, southwestDim.width),
                                bottom - 
                          Math.max (southwestDim.height, southeastDim.height));
        bottomRightCorner = new Point (right  -
                          Math.max (northeastDim.width, southeastDim.width),
                                bottom - 
                          Math.max (southwestDim.height, southeastDim.height));

        if ((northwest != null) && northwest.isVisible()) {
            northwest.reshape(left, top,
                                left + topLeftCorner.x,
                                top + topLeftCorner.y);
	}

        if ((southwest != null) && southwest.isVisible()) {
            southwest.reshape(left, bottomLeftCorner.y,
                                bottomLeftCorner.x - left,
                                bottom - bottomLeftCorner.y);
	}

        if ((southeast != null) && southeast.isVisible()) {
            southeast.reshape(bottomRightCorner.x,
                        bottomRightCorner.y,
                        right - bottomRightCorner.x,
                        bottom - bottomRightCorner.y);
	}

        if ((northeast != null) && northeast.isVisible()) {
            northeast.reshape(topRightCorner.x, top,
                                right - topRightCorner.x,
                                topRightCorner.y);
	}

        if ((center != null) && center.isVisible()) {
            int x = topLeftCorner.x + hgap;
            int y = topLeftCorner.y + vgap;
            int width = bottomRightCorner.x - topLeftCorner.x - hgap * 2;
            int height = bottomRightCorner.y - topLeftCorner.y - vgap * 2;

            if (mode == CornerLayout.FULL_WIDTH) {

                x = left;
                width = right - left;

            } else if (mode == CornerLayout.FULL_HEIGHT) {

                y = top;
                height = bottom - top;

            }

            center.reshape(x, y, width, height);

	}
    }
    
    public String toString() {
        String str;
        switch (mode) {
            case FULL_WIDTH:  str = "wide";
                         break;
            case FULL_HEIGHT: str = "tall";
                         break;
            default:     str = "normal";
                         break;
        }
	return getClass().getName() + "[hgap=" + hgap +
                        ",vgap=" + vgap + ",mode=" + str + "]";
    }
}
