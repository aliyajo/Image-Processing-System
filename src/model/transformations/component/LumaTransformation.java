package model.transformations.component;

import model.IImage;
import model.IImageState;
import model.ImageImpl;
import model.transformations.AbstractTransformation;

/**
 * This class extends the AbstractTransformation which implements the ITransformation Interface.
 * It is able to implement a run method to accurately cause a greyscale image through
 * the luma weighted equation sum.
 */
public class LumaTransformation extends AbstractTransformation {

  @Override
  public IImageState run(IImageState sourceImage) {
    //create new image
    IImage newImage = new ImageImpl(sourceImage.getWidth(), sourceImage.getHeight());
    //Going to make each pixel brighten in the image
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        //Use the weighted sum equation to determine Luma value.
        double weightedR = (0.216) * (clamp(sourceImage.getRedChannel(col, row)));
        double weightedG = (0.7152) * clamp(sourceImage.getGreenChannel(col, row));
        double weightedB = (0.0722) * clamp(sourceImage.getBlueChannel(col, row));
        int weightedSum = (int) Math.round(weightedR + weightedB + weightedG);
        //Accurately have the current pixel represent the luma value in RBG values.
        newImage.setPixel(col, row, weightedSum, weightedSum, weightedSum);
      }
    }
    return newImage;
  }
}
