package edu.grinnell.csc207.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Trees intended to be used in storing mappings between fixed-length
 * sequences of bits and corresponding values.
 *
 * @author Nicole Moreno Gonzalez
 */
public class BitTree {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The root of the tree.
   */
  private final BitTreeNode root;

  /**
   * The length of bit sequences stored in the tree.
   */
  private final int bitLength;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructs a new BitTree for sequences of a given length.
   *
   * @param n the length of bit sequences.
   */
  public BitTree(int n) {
    this.bitLength = n;
    this.root = new BitTreeInteriorNode();
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+


  /**
   * A recursive helper method for setting a value in the tree.
   *
   * @param node the current node being processed.
   * @param bits the remaining bits to process.
   * @param value the value to set.
   */
  private void setHelper(BitTreeNode node, String bits, String value) {
    if (bits.isEmpty()) {
      if (node instanceof BitTreeLeaf bitTreeLeaf) {
        bitTreeLeaf.setValue(value);
      } else {
        throw new IllegalStateException("Expected a leaf node.");
      } // else
      return;
    } // if

    BitTreeInteriorNode interior = (BitTreeInteriorNode) node;
    char bit = bits.charAt(0);

    switch (bit) {
      case '0' -> {
        if (interior.getLeft() == null) {
          interior.setLeft(bits.length() == 1 ? new BitTreeLeaf() : new BitTreeInteriorNode());
        } // if
        setHelper(interior.getLeft(), bits.substring(1), value);
      } // case
      case '1' -> {
        if (interior.getRight() == null) {
          interior.setRight(bits.length() == 1 ? new BitTreeLeaf() : new BitTreeInteriorNode());
        } // if
        setHelper(interior.getRight(), bits.substring(1), value);
      } // case
      default -> throw new IllegalArgumentException("Invalid bit: " + bit);
    } // switch
  } // setHelper

  /**
   * A recursive helper method for retrieving a value from the tree.
   *
   * @param node the current node being processed.
   * @param bits the remaining bits to process.
   * @return the stored value, if found.
   */
  private String getHelper(BitTreeNode node, String bits) {
    if (node == null) {
      throw new IndexOutOfBoundsException("Path does not exist.");
    } // if
    if (bits.isEmpty()) {
      if (node instanceof BitTreeLeaf bitTreeLeaf) {
        return bitTreeLeaf.getValue();
      } // if
      throw new IllegalStateException("Path does not lead to a value.");
    } // if

    BitTreeInteriorNode interior = (BitTreeInteriorNode) node;
    char bit = bits.charAt(0);

    switch (bit) {
      case '0' -> {
        return getHelper(interior.getLeft(), bits.substring(1));
      } // case
      case '1' -> {
        return getHelper(interior.getRight(), bits.substring(1));
      } // case
      default -> throw new IllegalArgumentException("Invalid bit: " + bit);
    } // switch
  } // getHelper

  /**
   * A recursive helper method for dumping the tree contents.
   *
   * @param node the current node being processed.
   * @param prefix the current bit path.
   * @param pen the writer to output the tree's contents.
   */
  private void dumpHelper(BitTreeNode node, String prefix, PrintWriter pen) {
    if (node == null) {
      return;
    } // if
    if (node.isLeaf()) {
      pen.println(prefix + "," + node.getValue());
    } else {
      BitTreeInteriorNode interior = (BitTreeInteriorNode) node;
      dumpHelper(interior.getLeft(), prefix + "0", pen);
      dumpHelper(interior.getRight(), prefix + "1", pen);
    } // else
  } // dumpHelper

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sets the value for a specific bit sequence.
   *
   * @param bits the bit sequence.
   * @param value the value to set.
   */
  public void set(String bits, String value) {
    if (bits.length() != this.bitLength) {
      throw new IndexOutOfBoundsException("Bit length mismatch.");
    } // if
    setHelper(this.root, bits, value);
  } // set(String, String)

  /**
   * Gets the value for a specific bit sequence.
   *
   * @param bits the bit sequence.
   * @return the corresponding value.
   */
  public String get(String bits) {
    if (bits.length() != this.bitLength) {
      throw new IndexOutOfBoundsException("Bit length mismatch.");
    } // if
    return getHelper(this.root, bits);
  } // get(String, String)

  /**
   * Dumps the tree contents in CSV format.
   *
   * @param pen the writer to output to.
   */
  public void dump(PrintWriter pen) {
    dumpHelper(this.root, "", pen);
  } // dump(PrintWriter)

  /**
   * Loads mappings from an input stream.
   *
   * @param source the input stream containing bit-value pairs.
   * @throws RuntimeException if an error occurs while reading the stream.
   */
  public void load(InputStream source) {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(source))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",", 2);
        if (parts.length == 2) {
          set(parts[0], parts[1]);
        } // if
      } // while
    } catch (IOException e) {
      throw new RuntimeException("Error reading from input stream.", e);
    } // try/catch
  } // load(InputStream)
} // class BitTree
