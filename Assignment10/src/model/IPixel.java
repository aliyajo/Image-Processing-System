package model;

/**
 * This interface allows the mutation of the components of a pixel.
 * It extends the PixelState Interface.
 */
public interface IPixel extends IPixelState {

  /**
   * This is a mutator method for the red component.
   *
   * @param val integer that sets the red component.
   */
  void setR(int val);

  /**
   * This is a mutator method for the green component.
   *
   * @param val integer that sets the green component.
   */
  void setG(int val);

  /**
   * This is a mutator method for the blue component.
   *
   * @param val integer that sets the blue component.
   */
  void setB(int val);
}
