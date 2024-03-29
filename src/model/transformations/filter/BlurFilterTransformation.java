package model.transformations.filter;

/**
 * This class implements a run method to blur an image.
 * It extends the AbstractTransformation Abstract class.
 */
public class BlurFilterTransformation extends AbstractFilterTransformation {
  //Matrix that the blur filter follows.
  private static final double[][] blurKernel = {
          {1.0 / 16, 1.0 / 8, 1.0 / 16},
          {1.0 / 8, 1.0 / 4, 1.0 / 8},
          {1.0 / 16, 1.0 / 8, 1.0 / 16}
  };

  /**
   * This constructor creates the filter transformation based on the passed in kernel.
   */
  public BlurFilterTransformation() {
    super(blurKernel);
  }
}
