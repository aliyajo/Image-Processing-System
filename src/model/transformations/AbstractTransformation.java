package model.transformations;

/**
 * This is an abstract class that implements the ITransformation interface.
 * It provides a common method used by the transformations.
 */
public abstract class AbstractTransformation implements ITransformation {

  /**
   * This method is a helper function that makes sure the values given don't exceed
   * the parameters needed of either the red, blue, or green channel.
   *
   * @param value integer value that is made sure to in certain parameters.
   * @return the value.
   */
  protected int clamp(int value) {
    if (value < 0) {
      return 0;
    } else if (value > 255) {
      return 255;
    } else {
      return value;
    }
  }
}
