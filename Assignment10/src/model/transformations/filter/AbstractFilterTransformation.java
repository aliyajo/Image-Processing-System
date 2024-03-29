package model.transformations.filter;

import model.IImage;
import model.IImageState;
import model.ImageImpl;
import model.transformations.AbstractTransformation;

/**
 * This abstract class is to provide generalization of methods for filter transformations.
 * It extends the AbstractTransformation abstract class.
 */
public abstract class AbstractFilterTransformation extends AbstractTransformation {
  protected final double[][] kernel;

  /**
   * This constructor creates the filter transformation based on the passed in kernel.
   */
  public AbstractFilterTransformation(double[][] kernel) {
    this.kernel = kernel;
  }

  @Override
  public IImageState run(IImageState image) {
    int width = image.getWidth();
    int height = image.getHeight();
    IImage transformedImage = new ImageImpl(width, height);

    // Iterate through the image
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        // Based on the kernel, apply the filter to each channel and sum up the results.
        double newRedChannel = 0;
        double newGreenChannel = 0;
        double newBlueChannel = 0;

        // Iterate through the kernel.
        for (int kernelR = 0; kernelR < kernel.length; kernelR++) {
          for (int kernelC = 0; kernelC < kernel[kernelR].length; kernelC++) {
            int totalRow = row + kernelR - (kernel.length / 2);
            int totalCol = col + kernelC - (kernel[kernelR].length / 2);

            // Perform the arithmetic equation to determine channel values if in bounds.
            if (totalRow >= 0 && totalRow < height && totalCol >= 0 && totalCol < width) {
              double kernelValue = kernel[kernelR][kernelC];
              int redChannel = image.getRedChannel(totalCol, totalRow);
              int greenChannel = image.getGreenChannel(totalCol, totalRow);
              int blueChannel = image.getBlueChannel(totalCol, totalRow);

              newRedChannel += kernelValue * redChannel;
              newGreenChannel += kernelValue * greenChannel;
              newBlueChannel += kernelValue * blueChannel;
            }
          }
        }
        // Create the image based on these new channel values.
        int newR = clamp((int) newRedChannel);
        int newG = clamp((int) newGreenChannel);
        int newB = clamp((int) newBlueChannel);
        transformedImage.setPixel(col, row, newR, newG, newB);
      }
    }
    return transformedImage;
  }
}



