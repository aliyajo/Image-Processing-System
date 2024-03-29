import org.junit.Before;
import org.junit.Test;

import controller.gui.GUIController;
import model.IImageDataBase;
import model.IImageState;
import model.ImageDataBase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This is to test the gui system.
 */
public class ViewTest {
  IImageDataBase model;
  MockView mockView;
  GUIController controller;


  @Before
  public void setUp() {
    model = new ImageDataBase();
    mockView = new MockView();
    controller = new GUIController(model, mockView);
  }

  @Test
  public void testLoadEvent() {
    /*
     * This is to test the load event.
     */
    controller.handleLoadEvent("res/image.png");
    assertNotNull(model.get("image.png"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadEventError() {
    /*
     * This is to test the load event.
     * This is to test the error case.
     */
    controller.handleLoadEvent("res/script.txt");
  }

  @Test
  public void testSaveEvent() {
    /*
     * This is to test the save event.
     */
    controller.handleLoadEvent("res/png_test.png");
    // Get the initial image from the model
    IImageState initialImage = model.get("png_test.png");
    // Perform save event
    controller.handleSaveEvent();
    // Get the saved image from the model (you'll need to implement a way to retrieve it)
    IImageState savedImage = model.get("png_test.png"); // Replace this with your implementation
    assertEquals(initialImage.getWidth(), savedImage.getWidth());
    assertEquals(initialImage.getHeight(), savedImage.getHeight());
  }

  @Before
  public void setUp2() {
    /*
     * This is to test the transformation events.
     */
    controller.handleLoadEvent("res/png_test.png");
  }

  @Test
  public void testRedTransformationEvent() {
    /*
     * This is to test the red transformation event.
     */
    // Perform red transformation event
    controller.handleRedTransformationEvent();
    IImageState transformedImage = model.get("png_test.png");
    assertEquals(237, transformedImage.getBlueChannel(2, 1));
    assertEquals(237, transformedImage.getRedChannel(2, 1));
    assertEquals(237, transformedImage.getGreenChannel(2, 1));
  }

  @Test
  public void testGreenTransformationEvent() {
    /*
     * This is to test the green transformation event.
     */
    // Perform green transformation event
    controller.handleGreenTransformationEvent();
    IImageState transformedImage = model.get("png_test.png");
    assertEquals(28, transformedImage.getBlueChannel(2, 1));
    assertEquals(28, transformedImage.getRedChannel(2, 1));
    assertEquals(28, transformedImage.getGreenChannel(2, 1));
  }

  @Test
  public void testBlueTransformationEvent() {
    /*
     * This is to test the blue transformation event.
     */
    // Perform blue transformation event
    controller.handleBlueTransformationEvent();
    IImageState transformedImage = model.get("png_test.png");
    assertEquals(36, transformedImage.getBlueChannel(2, 1));
    assertEquals(36, transformedImage.getRedChannel(2, 1));
    assertEquals(36, transformedImage.getGreenChannel(2, 1));
  }

  @Test
  public void testValueEvent() {
    /*
     * This is to test the value transformation event.
     */
    // Perform value transformation event
    controller.handleValueEvent();
    IImageState transformedImage = model.get("png_test.png");
    assertEquals(237, transformedImage.getBlueChannel(2, 1));
    assertEquals(237, transformedImage.getRedChannel(2, 1));
    assertEquals(237, transformedImage.getGreenChannel(2, 1));
  }

  @Test
  public void testIntensityEvent() {
    /*
     * This is to test the intensity transformation event.
     */
    // Perform intensity transformation event
    controller.handleIntensityEvent();
    IImageState transformedImage = model.get("png_test.png");
    assertEquals(100, transformedImage.getBlueChannel(2, 1));
    assertEquals(100, transformedImage.getRedChannel(2, 1));
    assertEquals(100, transformedImage.getGreenChannel(2, 1));
  }

  @Test
  public void testLumaEvent() {
    /*
     * This is to test the luma transformation event.
     */
    // Perform luma transformation event
    controller.handleLumaEvent();
    IImageState transformedImage = model.get("png_test.png");
    assertEquals(74, transformedImage.getBlueChannel(2, 1));
    assertEquals(74, transformedImage.getRedChannel(2, 1));
    assertEquals(74, transformedImage.getGreenChannel(2, 1));
  }
}