package edu.grinnell.csc207.experiments;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.BrailleAsciiTables;

/**
 * Some experiments with the Braille-Ascii Tables.
 *
 * @author Your Name Here
 * @author Samuel A. Rebelsky
 */
public class BATableExperiments {
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

    pen.println("ASCII -> Braille");
    pen.println("----------------");
    for (char letter = 'A'; letter <= 'Z'; ++letter) {
      try {
        pen.println(letter + " -> " + BrailleAsciiTables.toBraille(letter));
      } catch (Exception e) {
        pen.println("Could not convert " + letter + " to braille because "
            + e.getMessage());
      } // try/catch
    } // for

    String[] brailleCharacters = new String[] {
        "100000", "110000", "100100", "100110", "100010", "110100", "110110",
        "110010", "010100", "010110", "101000", "111000", "101100", "101110",
        "101010", "111100", "111110", "111010", "011100", "011110", "101001",
        "111001", "101101", "101111", "101011", "010111", "000000"};

    pen.println();
    pen.println("Braille -> ASCII");
    pen.println("----------------");
    for (String bits : brailleCharacters) {
      try {
        pen.println(bits + " -> '" + BrailleAsciiTables.toAscii(bits) + "'");
      } catch (Exception e) {
        pen.println("Could not convert " + bits + " to ASCII because "
            + e.getMessage());
      } // try/catch
    } // for

    pen.println();
    pen.println("Braille -> Unicode");
    pen.println("------------------");
    for (String bits : brailleCharacters) {
      try {
        pen.println(bits + " -> '" + BrailleAsciiTables.toUnicode(bits) + "'");
      } catch (Exception e) {
        pen.println("Could not convert " + bits + " to unicode because "
            + e.getMessage());
      } // try/catch
    } // for

    pen.close();
  } // main(String[])

} // class BitTreeExperiments
