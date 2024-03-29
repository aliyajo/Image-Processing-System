package io;

import model.IImageState;

/**
 * This Interface is for loading an Image.
 */
public interface IImageLoader {

  /**
   * This method is run the loader accurately by loading the image.
   *
   * @return the loaded Image.
   */
  IImageState run();
}
