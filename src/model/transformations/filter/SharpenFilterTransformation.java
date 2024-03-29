package model.transformations.filter;

/**
 * This class is to implement a run method to sharpen an image.
 * It extends the AbstractTransformation abstract class.
 */
public class SharpenFilterTransformation extends AbstractFilterTransformation {

  //kernel that the sharpen filter follows.
  private static final double[][] sharpenKernel = {
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
  };

  /**
   * This constructor creates the filter transformation based on the passed in kernel.
   */
  public SharpenFilterTransformation() {
    super(sharpenKernel);
  }
}