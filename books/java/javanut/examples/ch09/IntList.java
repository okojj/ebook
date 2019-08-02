// This example is from the book "Java in a Nutshell, Second Edition".
// Written by David Flanagan.  Copyright (c) 1997 O'Reilly & Associates.
// You may distribute this source code for non-commercial purposes only.
// You may study, modify, and use this example for any purpose, as long as
// this notice is retained.  Note that this example is provided "as is",
// WITHOUT WARRANTY of any kind either expressed or implied.

import java.io.*;

/** A simple class that implements a growable array or ints, and knows
 *  how to serialize itself as efficiently as a non-growable array. */
public class IntList implements Serializable
{
  private int[] nums = new int[8]; // An array to store the numbers.
  private transient int size = 0;  // Index of next unused element of nums[].

  /** Return an element of the array */
  public int elementAt(int index) throws ArrayIndexOutOfBoundsException {
    if (index >= size) throw new ArrayIndexOutOfBoundsException(index);
    else return nums[index];
  }

  /** Add an int to the array, growing the array if necessary */
  public void add(int x) {
    if (nums.length == size) resize(nums.length*2); // Grow array, if needed.
    nums[size++] = x;                               // Store the int in it.
  }

  /** An internal method to change the allocated size of the array */
  protected void resize(int newsize) {
    int[] oldnums = nums;
    nums = new int[newsize];                     // Create a new array.
    System.arraycopy(oldnums, 0, nums, 0, size); // Copy array elements.
  }

  /** Get rid of unused array elements before serializing the array */
  private void writeObject(ObjectOutputStream out) throws IOException {
    if (nums.length > size) resize(size);  // Compact the array.
    out.defaultWriteObject();              // Then write it out normally.
  }

  /** Compute the transient size field after deserializing the array */
  private void readObject(ObjectInputStream in)
          throws IOException, ClassNotFoundException {
    in.defaultReadObject();                // Read the array normally.
    size = nums.length;                    // Restore the transient field.
  }
}
