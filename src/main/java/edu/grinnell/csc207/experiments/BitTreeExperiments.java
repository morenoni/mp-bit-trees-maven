package edu.grinnell.csc207.experiments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import edu.grinnell.csc207.util.BitTree;

/**
 * Some experiments with Bit Trees.
 *
 * @author Your Name Here
 * @author Samuel A. Rebelsky
 */
public class BitTreeExperiments {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * Some of the conversion from braille to ASCII.
   */
  static final String B2A =
      """
      100000,A
      110000,B
      100100,C
      100110,D
      100010,E
      """;

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Attempt to get the value corresponding to a bit string.
   *
   * @param pen
   *   Used for logging.
   * @param bt
   *   The tree.
   * @param bits
   *   The key.
   */
  static void get(PrintWriter pen, BitTree bt, String bits) {
    pen.printf("get(\"%s\")", bits);
    pen.flush();
    try {
      String value = bt.get(bits);
      pen.printf(" = \"%s\"\n", value);
    } catch (Exception e) {
      pen.println(" FAILED (" + e.getMessage() + ")");
    } // try/catch
  } // get(PrintWriter, BitTree, String)

  /**
   * Attempt to set a bits/value pair in a tree.
   *
   * @param pen
   *   Used for logging.
   * @param bt
   *   The tree.
   * @param bits
   *   The key.
   * @param value
   *   The value.
   */
  static void set(PrintWriter pen, BitTree bt, String bits, String value) {
    pen.printf("set(\"%s\", \"%s\") ... ", bits, value);
    pen.flush();
    try {
      bt.set(bits, value);
      pen.println("OK");
    } catch (Exception e) {
      pen.println("Failed because " + e.getMessage());
    } // try/catch
  } // set(PrintWriter, BitTree, String, String)

  /**
   * Print a separator.
   *
   * @param pen
   *   Where to print the separator.
   */
  static void separator(PrintWriter pen) {
    pen.println("-".repeat(72));
    pen.println();
  } // separator(PrintWriter)

  // +-------------+-------------------------------------------------
  // | Experiments |
  // +-------------+

  /**
   * Create a tree, look up a few things, and dump it.
   *
   * @param pen
   *   Where to print the info.
   */
  static void experiment01(PrintWriter pen) {
    pen.println("Experiment 01");
    pen.println("-------------");

    BitTree bt = new BitTree(5);
    set(pen, bt, "00000", "Zero");
    set(pen, bt, "10000", "Sixteen");
    set(pen, bt, "01111", "Fifteen");

    get(pen, bt, "01111");
    get(pen, bt, "00000");
    get(pen, bt, "10000");

    set(pen, bt, "0011a", "Invalid character");
    set(pen, bt, "0011", "Too short");
    set(pen, bt, "001100", "Too long");

    get(pen, bt, "00111");      // Invalid key
    get(pen, bt, "11111");      // Invalid key
    get(pen, bt, "11a11");      // Invalid character
    get(pen, bt, "1111");       // Too short
    get(pen, bt, "110100");     // Too long

    set(pen, bt, "01111", "Five-teen");
    get(pen, bt, "01111");

    pen.println("Final contents");
    bt.dump(pen);

    separator(pen);
  } // experiment01(PrintWriter)

  /**
   * Load a tree, look up a few things, and dump it.
   *
   * @param pen
   *   Where to print the info.
   */
  static void experiment02(PrintWriter pen) {
    pen.println("Experiment 02");
    pen.println("-------------");
    BitTree b2aTree = new BitTree(6);
    InputStream b2aStream = new ByteArrayInputStream(B2A.getBytes());
    b2aTree.load(b2aStream);
    try {
      b2aStream.close();
    } catch (IOException e) {
      // We don't care if we can't close the stream.
    } // try/catch
    get(pen, b2aTree, "100000");
    get(pen, b2aTree, "110000");
    get(pen, b2aTree, "100100");
    get(pen, b2aTree, "100110");
    get(pen, b2aTree, "100010");
    get(pen, b2aTree, "101000");
    pen.println("Contents:");
    b2aTree.dump(pen);
    separator(pen);
  } // experiment02(PrintWriter)

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Run our experiments.
   *
   * @param args
   *   Command-line arguments (ignored).
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    experiment01(pen);
    experiment02(pen);

    pen.close();
  } // main(String[])

} // class BitTreeExperiments
