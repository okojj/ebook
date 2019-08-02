// This example is from the book _Java AWT Reference_ by John Zukowski.
// Written by John Zukowski.  Copyright (c) 1997 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or
import java.awt.*;

public class VerticalLabel extends Component {
   public static final int LEFT = 0;
   public static final int CENTER = 1;
   public static final int RIGHT = 2;
   private String       text;
   private int          vgap;
   private int          alignment;
   Dimension    mySize;
   int          textLength;
   char         chars[];
   // constructors
   public VerticalLabel () {
        this (null, 0, CENTER);
   }
   public VerticalLabel (String text) {
        this (text, 0, CENTER);
   }
   public VerticalLabel (String text, int vgap, int alignment) {
      this.text = text;
      this.vgap = vgap;
      this.alignment = alignment;
   }
   void init () {                      
      textLength = text.length();
      chars = new char[textLength];
      text.getChars (0, textLength, chars, 0);
      Font f = getFont();
      FontMetrics fm = getFontMetrics (f);
      mySize = new Dimension(0,0);
      mySize.height = (fm.getHeight() * textLength) + (vgap * 2);
      for (int i=0; i < textLength; i++) {
          mySize.width = Math.max (mySize.width, fm.charsWidth(chars, i, 1));
      }
   }
   public int getAlignment () {
      return alignment;
   }
   public void addNotify () {
       super.addNotify();
       init();  // Component must be visible for init to work
   }
   public void setText (String text)    { this.text = text; init();}
   public String getText ()             { return text; }
   public void setVgap (int vgap)       { this.vgap = vgap; init();}
   public int getVgap ()                { return vgap; }
   public Dimension preferredSize ()    { return mySize; }
   public Dimension minimumSize ()      { return mySize; }
   public void paint (Graphics g) {
      int x,y;
      int xPositions[];
      int yPositions[];
// Must redo this each time since font/screen area might change
// Use actual width for alignment
      Font f = getFont();
      FontMetrics fm = getFontMetrics (f);
      xPositions = new int[textLength];
      for (int i=0; i < textLength; i++) {
         if (alignment == RIGHT) {
            xPositions[i] = size().width - fm.charWidth (chars[i]);
         } else if (alignment == LEFT) {
            xPositions[i] = 0;
         } else { // CENTER
            xPositions[i] = (size().width - fm.charWidth (chars[i])) / 2; 
         }
      }
      yPositions = new int[textLength];
      for (int i=0; i < textLength; i++) {
         yPositions[i] = (fm.getHeight() * (i+1)) + vgap;
      }
      for (int i = 0; i < textLength; i++) {
         x = xPositions[i];
         y = yPositions[i];
         g.drawChars (chars, i, 1, x, y);
      }

   }
   protected String paramString () {
      String str=",align=";
      switch (alignment) {
         case LEFT:    str += "left"; break;
         case CENTER:  str += "center"; break;
         case RIGHT:   str += "right"; break;
      }
      if (vgap!=0) str+= ",vgap=" + vgap;
      return super.paramString() + str + ",label=" + text;
   }
}
