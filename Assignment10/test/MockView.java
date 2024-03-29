import java.awt.image.BufferedImage;

import view.gui.GUIView;
import view.gui.IGUIView;
import view.gui.ViewListener;

/**
 * This class is to create a mock view for the GUIView.
 */
class MockView extends GUIView implements IGUIView {
  public BufferedImage imageToTest;

  @Override
  public void setImage(BufferedImage image) {
    this.imageToTest = image;
  }

  @Override
  public void addViewListener(ViewListener listener) {
    //Leaving this blank since don't need a reaction from the system.
  }

}