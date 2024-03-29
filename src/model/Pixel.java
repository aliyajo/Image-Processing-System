package model;

/**
 * This class is to create a Pixel object.
 * It implements the IPixel Interface.
 */
public class Pixel implements IPixel {
  private int r;
  private int g;
  private int b;

  /**
   * This constructor creates the Pixel constructor given the three components.
   *
   * @param r integer of the red component.
   * @param g integer of the green component.
   * @param b integer of the blue component.
   */
  public Pixel(int r, int g, int b) {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Pixel values out of bounds.");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * This method is a getter method for the red component.
   * @return integer of the red component.
   */
  @Override
  public int getR() {
    return this.r;
  }

  /**
   * This method is a setter method for the red component.
   * @param val integer of the red component.
   * @throws IllegalArgumentException if the value is out of bounds.
   */
  @Override
  public void setR(int val) {
    if (val < 0 || val > 255) {
      throw new IllegalArgumentException("Channel value is invalid");
    }
    this.r = val;
  }

  /**
   * This method is a getter method for the green component.
   * @return integer of the green component.
   */
  @Override
  public int getG() {
    return this.g;
  }

  /**
   * This method is a setter method for the green component.
   * @param val integer of the green component.
   * @throws IllegalArgumentException if the value is out of bounds.
   */
  @Override
  public void setG(int val) {
    if (val < 0 || val > 255) {
      throw new IllegalArgumentException("Channel value is invalid");
    }
    this.g = val;
  }

  /**
   * This method is a getter method for the blue component.
   * @return integer of the blue component.
   */
  @Override
  public int getB() {
    return this.b;
  }

  /**
   * This method is a setter method for the blue component.
   * @param val integer of the blue component.
   * @throws IllegalArgumentException if the value is out of bounds.
   */
  @Override
  public void setB(int val) {
    if (val < 0 || val > 255) {
      throw new IllegalArgumentException("Channel value is invalid");
    }
    this.b = val;
  }

  /**
   * This method is to make the Pixel object into a string.
   */
  @Override
  public String toString() {
    return this.r + " " + this.g + " " + this.b;
  }
}
