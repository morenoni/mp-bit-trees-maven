package edu.grinnell.csc207.util;

/**
 * An interface for nodes in a BitTree.
 *
 * @author Nicole Moreno Gonzalez
 */
public interface BitTreeNode {

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Indicates whether the node is a leaf.
   *
   * @return true if the node is a leaf, false otherwise.
   */
  boolean isLeaf();

  /**
   * Returns the value of the node if it is a leaf.
   *
   * @return the value stored in the node, or null if the node is not a leaf.
   */
  String getValue();

  /**
   * Sets the value of the node if it is a leaf.
   *
   * @param value the value to set.
   * @throws UnsupportedOperationException if the node is not a leaf.
   */
  void setValue(String value);
} // interace BitTreeNode
