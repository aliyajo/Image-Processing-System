package model.transformations.component;

import model.IImage;
import model.IImageState;
import model.ImageImpl;
import model.transformations.AbstractTransformation;

/**
 * This class extends the AbstractTransformation which implements the ITransformation Interface.
 * It is able to implement a run method to accurately cause a greyscale image through
 * the red channel integer.
 */
public class RedTransformation extends AbstractTransformation {

  @Override
  public IImageState run(IImageState sourceImage) {
    //create new image
    IImage newImage = new ImageImpl(sourceImage.getWidth(), sourceImage.getHeight());
    //Going to make each pixel brighten in the image
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        int newR = clamp(sourceImage.getRedChannel(col, row));
        int newG = clamp(sourceImage.getRedChannel(col, row));
        int newB = clamp(sourceImage.getRedChannel(col, row));
        newImage.setPixel(col, row, newR, newG, newB);
      }
    }
    return newImage;
  }
}