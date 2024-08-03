package model.transformations.component;

import model.IImage;
import model.IImageState;
import model.ImageImpl;
import model.transformations.AbstractTransformation;

/**
 * This class extends the AbstractTransformation which implements the ITransformation Interface.
 * It is able to implement a run method to accurately cause a greyscale image through
 * the max value of the RBG values.
 */
public class ValueTransformation extends AbstractTransformation {

  @Override
  public IImageState run(IImageState sourceImage) {
    //create new image
    IImage newImage = new ImageImpl(sourceImage.getWidth(), sourceImage.getHeight());
    //Going to make each pixel to transform via setting pixels to the max pixel value
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        //Determining max out of the three RBG values.
        int max = Math.max(clamp(sourceImage.getRedChannel(col, row)),
                Math.max(clamp(sourceImage.getBlueChannel(col, row)),
                        clamp(sourceImage.getGreenChannel(col, row))));
        //Make this pixel accurately hold this max value.
        newImage.setPixel(col, row, max, max, max);
      }
    }
    return newImage;
  }
}
