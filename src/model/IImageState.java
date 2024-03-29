package model;

/**
 * This interface maintains the Image state.
 */
public interface IImageState {

  /**
   * This is a getter method to return height of an image.
   *
   * @return height integer of the image.
   */
  int getHeight();

  /**
   * This is a getter method to return width of an image.
   *
   * @return width integer of the image.
   */
  int getWidth();

  /**
   * This is a getter method to return the red channel value at given coordinate.
   *
   * @param x x coordinate.
   * @param y y coordinate.
   * @return red channel value at coordinate.
   */
  int getRedChannel(int x, int y);

  /**
   * This is a getter method to return the blue channel value at given coordinate.
   *
   * @param x x coordinate.
   * @param y y coordinate.
   * @return blue channel value at coordinate.
   */
  int getBlueChannel(int x, int y);

  /**
   * This is a getter method to return the green channel value at given coordinate.
   *
   * @param x x coordinate.
   * @param y y coordinate.
   * @return green channel value at coordinate.
   */
  int getGreenChannel(int x, int y);

}
