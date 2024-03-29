package model.transformations.filter;

/**
 * This class creates a Sepia filter with the correct kernel.
 * It extends the AbstractFilterTransformation abstract class.
 */
public class SepiaFilterTransformation extends AbstractFilterTransformation {

  //This is the kernel for the sepia filter.
  private static final double[][] sepiaKernel = {
          {0.393, 0.769, 0.189},
          {0.349, 0.689, 0.168},
          {0.272, 0.534, 0.131}
  };

  /**
   * This constructor creates a sharpened filter that takes in the sepia kernel.
   */
  public SepiaFilterTransformation() {
    super(sepiaKernel);
  }
}
