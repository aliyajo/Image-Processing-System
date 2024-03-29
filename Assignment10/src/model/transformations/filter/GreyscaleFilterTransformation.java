package model.transformations.filter;

/**
 * This class is to implement a run method to greyscale an image.
 * It extends the AbstractTransformation abstract class.
 */
public class GreyscaleFilterTransformation extends AbstractFilterTransformation {

  //The kernel the greyscale filter utilizes.
  private static final double[][] greyscaleKernel = {
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722},
  };

  /**
   * This constructor creates the filter transformation based on the passed in kernel.
   */
  public GreyscaleFilterTransformation() {
    super(greyscaleKernel);
  }

}
