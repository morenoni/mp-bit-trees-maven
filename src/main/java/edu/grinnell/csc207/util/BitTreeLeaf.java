package edu.grinnell.csc207.util;

/**
 * A class representing a leaf node in a BitTree.
 *
 * @author Nicole Moreno Gonzalez
 */
public class BitTreeLeaf implements BitTreeNode {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The value stored in this leaf node.
   */
  private String value;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructs an empty leaf node with no value.
   */
  public BitTreeLeaf() {
    this.value = null;
  } // BitTreeLeaf

  /**
   * Constructs a leaf node with the specified value.
   *
   * @param value the value to store in this leaf node.
   */
  public BitTreeLeaf(String value) {
    this.value = value;
  } // BitTreeLeaf

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Indicates whether this node is a leaf.
   *
   * @return true, as leaf nodes are leaves.
   */
  @Override
  public boolean isLeaf() {
    return true;
  } // isLeaf

  /**
   * Gets the value stored in this leaf node.
   *
   * @return the value stored in this node.
   */
  @Override
  public String getValue() {
    return this.value;
  } // getValue

  /**
   * Sets the value stored in this leaf node.
   *
   * @param value the value to store.
   */
  @Override
  public void setValue(String value) {
    this.value = value;
  } // setValue
} // class BitTreeLeaf
