package edu.grinnell.csc207.util;

/**
 * A class representing an interior node in a BitTree.
 *
 * @author Nicole Moreno Gonzalez
 */
public class BitTreeInteriorNode implements BitTreeNode {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The left child of this interior node.
   */
  private BitTreeNode left;

  /**
   * The right child of this interior node.
   */
  private BitTreeNode right;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructs an empty interior node with no children.
   */
  public BitTreeInteriorNode() {
    this.left = null;
    this.right = null;
  } // BitTreeInteriorNode

  /**
   * Constructs an interior node with specified left and right children.
   *
   * @param left the left child of this node.
   * @param right the right child of this node.
   */
  public BitTreeInteriorNode(BitTreeNode left, BitTreeNode right) {
    this.left = left;
    this.right = right;
  } // BitTreeInteriorNode

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Gets the left child of this interior node.
   *
   * @return the left child, or null if no child exists.
   */
  public BitTreeNode getLeft() {
    return this.left;
  } // getLeft

  /**
   * Sets the left child of this interior node.
   *
   * @param left the left child to set.
   */
  public void setLeft(BitTreeNode left) {
    this.left = left;
  } // setLeft

  /**
   * Gets the right child of this interior node.
   *
   * @return the right child, or null if no child exists.
   */
  public BitTreeNode getRight() {
    return this.right;
  } // getRight

  /**
   * Sets the right child of this interior node.
   *
   * @param right the right child to set.
   */
  public void setRight(BitTreeNode right) {
    this.right = right;
  } // setRight

  /**
   * Indicates whether this node is a leaf.
   *
   * @return false, as interior nodes are not leaves.
   */
  @Override
  public boolean isLeaf() {
    return false;
  } // isLeaf

  /**
   * Gets the value of this node.
   *
   * @return nothing, as interior nodes do not store values.
   * @throws UnsupportedOperationException
   */
  @Override
  public String getValue() {
    throw new UnsupportedOperationException("Interior nodes do not have values.");
  } // getValue

  /**
   * Sets the value of this node.
   *
   * @param value the value to set (ignored, as this node does not store values).
   * @throws UnsupportedOperationException
   */
  @Override
  public void setValue(String value) {
    throw new UnsupportedOperationException("Interior nodes do not store values.");
  } // getValue
} // class BitTreeInteriorNode
