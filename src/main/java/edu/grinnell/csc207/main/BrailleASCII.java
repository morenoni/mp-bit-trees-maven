package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.BrailleAsciiTables;

/**
 * A program to translate between ASCII, Braille, and Unicode.
 *
 * This program accepts two command-line arguments:
 * 1. The target character set (braille, ascii, or unicode).
 * 2. The source characters to translate.
 *
 * @author Nicole Moreno Gonzalez
 */
public class BrailleASCII {

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * The main method that processes input and performs translation.
   *
   * @param args Command-line arguments:
   *             - args[0]: Target character set ("braille", "ascii", "unicode").
   *             - args[1]: Source characters to translate.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    if (args.length != 2) {
      pen.println("Usage: ba <target> <source>");
      pen.close();
      return;
    } // if

    String target = args[0].toLowerCase();
    String source = args[1];

    try {
      switch (target) {
        case "braille":
          StringBuilder brailleResult = new StringBuilder();
          for (char c : source.toCharArray()) {
            brailleResult.append(BrailleAsciiTables.toBraille(c));
          } // for
          pen.println(brailleResult.toString());
          break;

        case "ascii":
          StringBuilder asciiResult = new StringBuilder();
          boolean foundError = false;
          for (int i = 0; i + 6 <= source.length(); i += 6) {
            String bits = source.substring(i, i + 6);
            try {
              asciiResult.append(BrailleAsciiTables.toAscii(bits));
            } catch (RuntimeException e) {
              foundError = true;
              pen.println("Trouble translating chunk '" + bits + "' because " + e.getMessage());
            } // try/catch
          } // for

          int leftover = source.length() % 6;
          if (leftover != 0) {
            String remainingBits = source.substring(source.length() - leftover);
            pen.println("Invalid bits: '" + remainingBits + "' (length: " + leftover + ")");
            foundError = true;
          } // if
          if (!foundError || asciiResult.length() > 0) {
            pen.println("Result: " + asciiResult.toString());
          } // if
          break;

        case "unicode":
          StringBuilder unicodeResult = new StringBuilder();
          for (char c : source.toCharArray()) {
            String brailleBits = BrailleAsciiTables.toBraille(c);
            unicodeResult.append(BrailleAsciiTables.toUnicode(brailleBits));
          } // for
          pen.println(unicodeResult.toString());
          break;

        default:
          pen.println("Unsupported target: " + target);
      } // switch
    } catch (RuntimeException e) {
      pen.println("Trouble translating because " + e.getMessage());
    } // try/catch
    pen.close();
  } // main(String[]
} // class BrailleASCII
