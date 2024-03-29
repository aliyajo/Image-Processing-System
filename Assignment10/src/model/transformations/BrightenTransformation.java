package model.transformations;

import model.IImage;
import model.IImageState;
import model.ImageImpl;

/**
 * This class extends the AbstractTransformation which implements the ITransformation Interface.
 * It is able to implement a run method to accurately brighten an image.
 */
public class BrightenTransformation extends AbstractTransformation {
  private final int brightenValue;

  /**
   * This constructor is to hold the brightened value.
   *
   * @param brightenValue integer determining in altering the brightness level.
   */
  public BrightenTransformation(int brightenValue) {
    this.brightenValue = brightenValue;
  }

  @Override
  public IImageState run(IImageState sourceImage) {
    //create new image
    IImage newImage = new ImageImpl(sourceImage.getWidth(), sourceImage.getHeight());
    //Going to make each pixel brighten in the image
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        int newR = clamp(sourceImage.getRedChannel(col, row) + brightenValue);
        int newG = clamp(sourceImage.getGreenChannel(col, row) + brightenValue);
        int newB = clamp(sourceImage.getBlueChannel(col, row) + brightenValue);
        newImage.setPixel(col, row, newR, newG, newB);
      }
    }
    return newImage;
  }
}
