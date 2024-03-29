package model.transformations.component;

import model.IImage;
import model.IImageState;
import model.ImageImpl;
import model.transformations.AbstractTransformation;

/**
 * This class extends the AbstractTransformation which implements the ITransformation Interface.
 * It is able to implement a run method to accurately cause a greyscale image through
 * determining intensity value.
 */
public class IntensityTransformation extends AbstractTransformation {

  @Override
  public IImageState run(IImageState sourceImage) {
    //create new image
    IImage newImage = new ImageImpl(sourceImage.getWidth(), sourceImage.getHeight());
    //Going to make each pixel brighten in the image
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        //Find average out of the three RBG values.
        int sum = clamp(sourceImage.getRedChannel(col, row))
                + clamp(sourceImage.getBlueChannel(col, row))
                + clamp(sourceImage.getGreenChannel(col, row));
        int intensity = sum / 3;
        //Set the current pixel RBG values be the average of the three.
        newImage.setPixel(col, row, intensity, intensity, intensity);
      }
    }
    return newImage;
  }
}
