package view.gui;

/**
 * This is an interface for the view to accurately listen
 * to certain events.
 */
public interface ViewListener {

  /**
   * This method is for when the event of using the undo method.
   */
  void handleUndoEvent();

  /**
   * This method is for when the event of using the load method.
   *
   * @param filePath String of the file path the image should be saved at.
   */
  void handleLoadEvent(String filePath);

  /**
   * This method is for when the event of using the save method.
   */
  void handleSaveEvent();

  /**
   * This method is for when the event of using the brightness method.
   *
   * @param brightnessValue integer that is level of brightness change.
   */
  void handleBrightnessEvent(int brightnessValue);

  /**
   * This method is for when the event of using the red transformation method.
   */
  void handleRedTransformationEvent();

  /**
   * This method is for when the event of using the green transformation method.
   */
  void handleGreenTransformationEvent();

  /**
   * This method is for when the event of using the blue transformation method.
   */
  void handleBlueTransformationEvent();

  /**
   * This method is for when the event of using the value transformation method.
   */
  void handleValueEvent();

  /**
   * This method is for when the event of using the intensity transformation method.
   */
  void handleIntensityEvent();

  /**
   * This method is for when the event of using the luma transformation method.
   */
  void handleLumaEvent();


  /**
   * This method is for when the event of using the blur filter method.
   */
  void handleBlurEvent();

  /**
   * This method is for when the event of using the sharpen filter method.
   */
  void handleSharpenEvent();

  /**
   * This method is for when the event of using the sepia filter method.
   */
  void handleSepiaFilterEvent();

  /**
   * This method is for when the event of using the greyscale filter method.
   */
  void handleGreyscaleFilterEvent();

  /**
   * This method is for when the event of using the help button.
   */
  void handleHelpEvent();
}
