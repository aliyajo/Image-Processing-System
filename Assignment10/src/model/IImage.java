package model;

/**
 * This interface is for creating an Image object.
 */
public interface IImage extends IImageState {

  /**
   * This is a mutator method of setting a pixel given the coordinate and rbg values.
   *
   * @param x x coordinate.
   * @param y y coordinate.
   * @param r red channel value.
   * @param g green channel value.
   * @param b blue channel value.
   */
  void setPixel(int x, int y, int r, int g, int b);
}
