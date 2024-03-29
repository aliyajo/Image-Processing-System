package view.gui;

import java.awt.image.BufferedImage;

/**
 * This interface is for the GUIView classes.
 */
public interface IGUIView {
  /**
   * This method is for the ability to set the current background to the given image.
   *
   * @param image Given image that is wanting to be the background.
   */
  void setImage(BufferedImage image);

  /**
   * This method is to allow the buttons to be listened.
   *
   * @param listener View listener.
   */
  void addViewListener(ViewListener listener);

}
