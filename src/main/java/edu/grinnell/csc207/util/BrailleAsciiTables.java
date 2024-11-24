package edu.grinnell.csc207.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;

/**
 *
 *
 * @author Nicole Moreno Gonzalez.
 * @author Samuel A. Rebelsky
 */
public class BrailleAsciiTables {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * Conversions from braille to ASCII.
   */
  static final String b2a = 
      """
      100000,A
      110000,B
      100100,C
      100110,D
      100010,E
      110100,F
      110110,G
      110010,H
      010100,I
      010110,J
      101000,K
      111000,L
      101100,M
      101110,N
      101010,O
      111100,P
      111110,Q
      111010,R
      011100,S
      011110,T
      101001,U
      111001,V
      101101,X
      101111,Y
      101011,Z
      010111,W
      000000, 
      """;

  // +---------------+-----------------------------------------------
  // | Static fields |
  // +---------------+

  /**
   *
   */
  static BitTree a2bTree = null;

  /**
   *
   */
  static BitTree b2aTree = null;

  /**
   *
   */
  static BitTree b2uTree = null;

  // +-----------------------+---------------------------------------
  // | Static helper methods |
  // +-----------------------+

  // +----------------+----------------------------------------------
  // | Static methods |
  // +----------------+

  /**
   *
   */
  String toBraille(char letter) {
    return "";  // STUB
  } // toBraille(char)

  /**
   *
   */
  String toAscii(String bits) {
    // Make sure we've loaded the braille-to-ASCII tree.
    if (null == b2aTree) {
      b2aTree = new BitTree(6);
      InputStream b2aStream = new ByteArrayInputStream(b2a.getBytes());
      b2aTree.load(b2aStream);
      try {
        b2aStream.close();
      } catch (IOException e) {
        // We don't care if we can't close the stream.
      } // try/catch
    } // if
    return "";  // STUB
  } // toAscii(String)

  /**
   *
   */
  String toUnicode(String bits) {
    return "";  // STUB
  } // toUnicode(String)
} // BrailleAsciiTables
