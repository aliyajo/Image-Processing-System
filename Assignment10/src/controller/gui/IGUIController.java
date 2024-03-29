package controller.gui;

import view.gui.ViewListener;

/**
 * This interface is for the gui Controller classes.
 * It extends the ViewListener interface.
 */
public interface IGUIController extends ViewListener {

  /**
   * This method is to initiate the controller for the gui system.
   */
  void run();

}
