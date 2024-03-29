package model;

/**
 * The class implements an image.
 * This class implements the IImage Interface.
 */
public class ImageImpl implements IImage {
  private final int width;
  private final int height;
  private final IPixel[][] data;

  /**
   * This constructor creates a new image.
   *
   * @param width  integer of the width of the image.
   * @param height integer of the height of the image.
   */
  public ImageImpl(int width, int height) {
    this.width = width;
    this.height = height;
    // Initialize the data array with empty pixels
    this.data = new IPixel[width][height];
  }

  /**
   * This method is a getter method for the height of the image.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * This method is a getter method for the width of the image.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Pulls the red channel value from the pixel at the given coordinates.
   * @param x the x coordinate of the pixel
   * @param y the y coordinate of the pixel
   * @return the red channel value of the pixel
   * @throws IllegalArgumentException if the coordinates are out of bounds
   */
  @Override
  public int getRedChannel(int x, int y) {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      throw new IllegalArgumentException("x or y outside of the picture");
    }
    return this.data[x][y].getR();
  }

  /**
   * Pulls the green channel value from the pixel at the given coordinates.
   * @param x the x coordinate of the pixel
   * @param y the y coordinate of the pixel
   * @return the green channel value of the pixel
   * @throws IllegalArgumentException if the coordinates are out of bounds
   */
  @Override
  public int getGreenChannel(int x, int y) {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      throw new IllegalArgumentException("x or y outside of the picture");
    }
    return this.data[x][y].getG();
  }

  /**
   * Pulls the blue channel value from the pixel at the given coordinates.
   * @param x the x coordinate of the pixel
   * @param y the y coordinate of the pixel
   * @return the blue channel value of the pixel
   * @throws IllegalArgumentException if the coordinates are out of bounds
   */
  @Override
  public int getBlueChannel(int x, int y) {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      throw new IllegalArgumentException("x or y outside of the picture");
    }
    return this.data[x][y].getB();
  }

  /**
   * Sets the pixel values at the given coordinates.
   * @param x the x coordinate of the pixel
   * @param y the y coordinate of the pixel
   * @param r the red channel value of the pixel
   * @param g the green channel value of the pixel
   * @param b the blue channel value of the pixel
   * @throws IllegalArgumentException if the coordinates are out of bounds
   */
  @Override
  public void setPixel(int x, int y, int r, int g, int b) {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      throw new IllegalArgumentException("x or y outside of the picture");
    }
    this.data[x][y] = new Pixel(r, g, b);
  }

}
