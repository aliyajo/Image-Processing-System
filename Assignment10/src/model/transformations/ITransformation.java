package model.transformations;

import model.IImageState;

/**
 * This interface includes a strategy plan of the method utilized by each transformation
 * for the corresponding command.
 */
public interface ITransformation {

  /**
   * This method is able to alter the pixel dependent on the corresponding transformation.
   *
   * @param sourceImage Image that we want to alter.
   * @return the altered Image.
   */
  IImageState run(IImageState sourceImage);

}
