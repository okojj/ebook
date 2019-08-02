// This example is from the book "Java in a Nutshell, Second Edition".
// Written by David Flanagan.  Copyright (c) 1997 O'Reilly & Associates.
// You may distribute this source code for non-commercial purposes only.
// You may study, modify, and use this example for any purpose, as long as
// this notice is retained.  Note that this example is provided "as is",
// WITHOUT WARRANTY of any kind either expressed or implied.

package oreilly.beans.yesno;

public class AnswerEvent extends java.util.EventObject {
  protected int id;
  public static final int YES = 0, NO = 1, CANCEL = 2;
  public AnswerEvent(Object source, int id) {
    super(source);
    this.id = id;
  }
  public int getID() { return id; }
}
