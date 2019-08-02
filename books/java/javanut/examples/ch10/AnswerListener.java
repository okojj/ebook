// This example is from the book "Java in a Nutshell, Second Edition".
// Written by David Flanagan.  Copyright (c) 1997 O'Reilly & Associates.
// You may distribute this source code for non-commercial purposes only.
// You may study, modify, and use this example for any purpose, as long as
// this notice is retained.  Note that this example is provided "as is",
// WITHOUT WARRANTY of any kind either expressed or implied.

package oreilly.beans.yesno;

public interface AnswerListener extends java.util.EventListener {
  public void yes(AnswerEvent e);
  public void no(AnswerEvent e);
  public void cancel(AnswerEvent e);
}
