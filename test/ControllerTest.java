import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import controller.text.ITextController;
import controller.text.TextController;
import model.IImage;
import model.IImageDataBase;
import model.IImageState;
import model.ImageDataBase;
import model.ImageImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class tests the controller.
 */
public class ControllerTest {

  private IImageDataBase mockImageData;
  private IImage image;
  private StringWriter output;

  @Before
  public void setUp() {
        /**
         * This sets up the mockImageData and the image.
         */
    mockImageData = new ImageDataBase();
    int width = 3;
    int height = 3;
    // Create an Image with the following pixel values
    image = new ImageImpl(width, height);
    image.setPixel(0, 0, 100, 150, 200);
    image.setPixel(0, 1, 50, 100, 150);
    image.setPixel(0, 2, 200, 100, 50);
    image.setPixel(1, 0, 75, 125, 175);
    image.setPixel(1, 1, 125, 175, 225);
    image.setPixel(1, 2, 175, 225, 50);
    image.setPixel(2, 0, 5, 75, 125);
    image.setPixel(2, 1, 150, 200, 250);
    image.setPixel(2, 2, 100, 50, 150);
    // Create a mock image database with the previously loaded image
    mockImageData = new ImageDataBase();
    mockImageData.add("testImage", image);
    // Create a StringWriter to capture the output
    output = new StringWriter();
    //Setup another Image
    IImage image2 = new ImageImpl(2, 1);
    image2.setPixel(0, 0, 0, 0, 0);
    image2.setPixel(1, 0, 1, 1, 0);
    mockImageData.add("testImage2", image);
  }


  @Test
  public void testPPMCommandSave() {
    /**
     * This tests the save command for PPM type images.
     */
    // Mocking a given command text line
    String command = "save testImage.ppm testImage";
    // Create a mock image database with the previously loaded image
    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    // Create a ByteArrayOutputStream to capture the output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    // Create a PrintStream to capture the output
    PrintStream customPrintStream = new PrintStream(outputStream);
    // Save the original System.out
    PrintStream originalPrintStream = System.out;
    // Set the System.out to the customPrintStream
    System.setOut(customPrintStream);
    // Start the controller
    controller.start();
    // Restore the original System.out
    System.setOut(originalPrintStream);
    String expectedOutput = "P3\n"
            + "3 3\n"
            + "255\n"
            + "100 150 200\n"
            + "75 125 175\n"
            + "5 75 125\n"
            + "50 100 150\n"
            + "125 175 225\n"
            + "150 200 250\n"
            + "200 100 50\n"
            + "175 225 50\n"
            + "100 50 150\n"
            + "Image saved successfully.";
    assertEquals(expectedOutput, outputStream.toString().trim());
  }

  @Test
  public void testPNGCommandSave() {
    /**
     * This tests the save command for PNG type images.
     */
    // Mocking a given command text line
    String command = "save testImage.png testImage";
    // Create a mock image database with the previously loaded image
    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    // Create a ByteArrayOutputStream to capture the output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    // Create a PrintStream to capture the output
    PrintStream customPrintStream = new PrintStream(outputStream);
    // Save the original System.out
    PrintStream originalPrintStream = System.out;
    // Set the System.out to the customPrintStream
    System.setOut(customPrintStream);
    // Start the controller
    controller.start();
    // Restore the original System.out
    System.setOut(originalPrintStream);
    // Expected output
    String expectedOutput = "png\r\n"
            + "3 3\r\n"
            + "255\r\n"
            + "100 150 200\r\n"
            + "75 125 175\r\n"
            + "5 75 125\r\n"
            + "50 100 150\r\n"
            + "125 175 225\r\n"
            + "150 200 250\r\n"
            + "200 100 50\r\n"
            + "175 225 50\r\n"
            + "100 50 150\r\n"
            + "Image saved successfully.\r\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void testJPGCommandSave() {
        /**
         * This tests the save command for JPG type images.
         */
    // Mocking a given command text line
    String command = "save testImage.jpeg testImage";
    // Create a mock image database with the previously loaded image
    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    // Create a ByteArrayOutputStream to capture the output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    // Create a PrintStream to capture the output
    PrintStream customPrintStream = new PrintStream(outputStream);
    // Save the original System.out
    PrintStream originalPrintStream = System.out;
    // Set the System.out to the customPrintStream
    System.setOut(customPrintStream);
    // Start the controller
    controller.start();
    // Restore the original System.out
    System.setOut(originalPrintStream);
    // Expected output
    String expectedOutput = "jpeg\r\n"
            + "3 3\r\n"
            + "255\r\n"
            + "100 150 200\r\n"
            + "75 125 175\r\n"
            + "5 75 125\r\n"
            + "50 100 150\r\n"
            + "125 175 225\r\n"
            + "150 200 250\r\n"
            + "200 100 50\r\n"
            + "175 225 50\r\n"
            + "100 50 150\r\n"
            + "Image saved successfully.\r\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void testPPMImageLoader() {
    String command = "load res/test_image.ppm test_image";
    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream customPrintStream = new PrintStream(outputStream);
    PrintStream originalPrintStream = System.out;
    System.setOut(customPrintStream);
    controller.start();
    System.setOut(originalPrintStream);
    String expectedOutput = "Width of image: 3\r\n"
            + "Height of image: 3\r\n"
            + "Maximum value of a color in this file (usually 255): 255\r\n"
            + "Color of pixel (0,0): 100,150,200\r\n"
            + "Color of pixel (0,1): 50,100,150\r\n"
            + "Color of pixel (0,2): 200,100,50\r\n"
            + "Color of pixel (1,0): 75,125,175\r\n"
            + "Color of pixel (1,1): 125,175,225\r\n"
            + "Color of pixel (1,2): 175,225,50\r\n"
            + "Color of pixel (2,0): 5,75,125\r\n"
            + "Color of pixel (2,1): 150,200,250\r\n"
            + "Color of pixel (2,2): 100,50,150\r\n";

    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void testPNGLoadCommand() {
    /**
     * This tests the load command for PNG type images.
     */
    // Mocking a given command text line
    String command = "load res/png_test.png png_test";

    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream customPrintStream = new PrintStream(outputStream);
    PrintStream originalPrintStream = System.out;
    System.setOut(customPrintStream);
    controller.start();
    System.setOut(originalPrintStream);
    String expectedOutput = "Image format: png\r\n"
            + "Width of the image: 3\r\n"
            + "Height of the image: 4\r\n"
            + "Color of pixel (0,0): 237,28,36\r\n"
            + "Color of pixel (0,1): 237,28,36\r\n"
            + "Color of pixel (0,2): 237,28,36\r\n"
            + "Color of pixel (1,0): 237,28,36\r\n"
            + "Color of pixel (1,1): 237,28,36\r\n"
            + "Color of pixel (1,2): 237,28,36\r\n"
            + "Color of pixel (2,0): 237,28,36\r\n"
            + "Color of pixel (2,1): 237,28,36\r\n"
            + "Color of pixel (2,2): 237,28,36\r\n"
            + "Color of pixel (3,0): 237,28,36\r\n"
            + "Color of pixel (3,1): 237,28,36\r\n"
            + "Color of pixel (3,2): 237,28,36\r\n";

    assertEquals(expectedOutput, outputStream.toString());

    //Test#2 for png saving.
    String command2 = "load res/png_test2.png png_test2";
    ITextController controller2 = new TextController(new StringReader(command2),
            mockImageData, new PrintWriter(output));
    ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
    PrintStream customPrintStream2 = new PrintStream(outputStream2);
    PrintStream originalPrintStream2 = System.out;
    System.setOut(customPrintStream2);
    controller2.start();
    System.setOut(originalPrintStream2);
    String expectedOutput2 = "Image format: png\r\n"
            + "Width of the image: 3\r\n"
            + "Height of the image: 1\r\n"
            + "Color of pixel (0,0): 0,0,0\r\n"
            + "Color of pixel (0,1): 255,255,255\r\n"
            + "Color of pixel (0,2): 0,0,0\r\n";

    assertEquals(expectedOutput2, outputStream2.toString());
  }

  @Test
  public void testJPEGLoadCommand() {
    /**
     * This tests the load command for JPEG type images.
     */
    String command = "load res/jpeg_test.jpg jpeg_test";
    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream customPrintStream = new PrintStream(outputStream);
    PrintStream originalPrintStream = System.out;
    System.setOut(customPrintStream);
    controller.start();
    System.setOut(originalPrintStream);
    String expectedOutput = "Image format: jpg\r\n"
            + "Width of the image: 5\r\n"
            + "Height of the image: 5\r\n"
            + "Color of pixel (0,0): 255,201,14\r\n"
            + "Color of pixel (0,1): 234,200,64\r\n"
            + "Color of pixel (0,2): 110,134,102\r\n"
            + "Color of pixel (0,3): 226,255,255\r\n"
            + "Color of pixel (0,4): 242,255,255\r\n"
            + "Color of pixel (1,0): 214,206,82\r\n"
            + "Color of pixel (1,1): 195,209,124\r\n"
            + "Color of pixel (1,2): 214,255,255\r\n"
            + "Color of pixel (1,3): 200,255,255\r\n"
            + "Color of pixel (1,4): 216,255,255\r\n"
            + "Color of pixel (2,0): 57,157,159\r\n"
            + "Color of pixel (2,1): 43,153,168\r\n"
            + "Color of pixel (2,2): 26,155,197\r\n"
            + "Color of pixel (2,3): 23,156,212\r\n"
            + "Color of pixel (2,4): 27,153,204\r\n"
            + "Color of pixel (3,0): 8,163,185\r\n"
            + "Color of pixel (3,1): 8,164,187\r\n"
            + "Color of pixel (3,2): 9,167,194\r\n"
            + "Color of pixel (3,3): 7,165,194\r\n"
            + "Color of pixel (3,4): 13,168,196\r\n"
            + "Color of pixel (4,0): 20,179,115\r\n"
            + "Color of pixel (4,1): 24,178,116\r\n"
            + "Color of pixel (4,2): 27,172,117\r\n"
            + "Color of pixel (4,3): 35,176,123\r\n"
            + "Color of pixel (4,4): 28,169,117\r\n";
    assertEquals(expectedOutput, outputStream.toString());

    String command2 = "load res/jpeg_test2.jpg jpeg_test2.jpg";
    ITextController controller2 = new TextController(new StringReader(command2),
            mockImageData, new PrintWriter(output));
    ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
    PrintStream customPrintStream2 = new PrintStream(outputStream2);
    PrintStream originalPrintStream2 = System.out;
    System.setOut(customPrintStream2);
    controller2.start();
    System.setOut(originalPrintStream2);
    String expectedOutput2 = "Image format: jpg\r\n"
            + "Width of the image: 1\r\n"
            + "Height of the image: 1\r\n"
            + "Color of pixel (0,0): 255,255,255\r\n";
    assertEquals(expectedOutput2, outputStream2.toString());
  }


  @Test
  public void testBlueCommand() {
    /*
     * This tests the blue command.
     */
    String command = "blue-component testImage blueImage";

    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    controller.start();

    IImageState blueImage = mockImageData.get("blueImage");
    // Assert the resulting pixel values after red transformation.
    assertEquals(150, blueImage.getRedChannel(0, 1));
    assertEquals(150, blueImage.getGreenChannel(0, 1));
    assertEquals(150, blueImage.getBlueChannel(0, 1));
  }

  @Test
  public void testRedCommand() {
    /*
     * This tests the red command.
     */
    String command = "red-component testImage redImage";

    // Create a mock image database with the previously loaded image
    IImageDataBase mockImageData = new ImageDataBase();
    mockImageData.add("testImage", image);

    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    controller.start();

    IImageState redImage = mockImageData.get("redImage");
    // Assert the resulting pixel values after red transformation.
    assertEquals(150, redImage.getRedChannel(2, 1));
    assertEquals(150, redImage.getGreenChannel(2, 1));
    assertEquals(150, redImage.getBlueChannel(2, 1));
  }

  @Test
  public void testGreenCommand() {
    /*
     * This tests the green command.
     */
    String command = "green-component testImage greenImage";

    // Create a mock image database with the previously loaded image
    IImageDataBase mockImageData = new ImageDataBase();
    mockImageData.add("testImage", image);

    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    controller.start();

    IImageState greenImage = mockImageData.get("greenImage");
    assertEquals(50, greenImage.getRedChannel(2, 2));
    assertEquals(50, greenImage.getGreenChannel(2, 2));
    assertEquals(50, greenImage.getBlueChannel(2, 2));
  }

  @Test
  public void testIntensityCommand() {
    /*
     * This tests the intensity command.
     */
    String command = "intensity-component testImage intensityImage";

    // Create a mock image database with the previously loaded image
    IImageDataBase mockImageData = new ImageDataBase();
    mockImageData.add("testImage", image);

    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    controller.start();

    IImageState intensityImage = mockImageData.get("intensityImage");
    assertEquals(100, intensityImage.getRedChannel(0, 1));
    assertEquals(100, intensityImage.getGreenChannel(0, 1));
    assertEquals(100, intensityImage.getBlueChannel(0, 1));
  }

  @Test
  public void testLumaCommand() {
    /*
     * This tests the luma command.
     */
    String command = "luma-component testImage lumaImage";

    // Create a mock image database with the previously loaded image
    IImageDataBase mockImageData = new ImageDataBase();
    mockImageData.add("testImage", image);

    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    controller.start();

    IImageState lumaImage = mockImageData.get("lumaImage");
    assertEquals(64, lumaImage.getRedChannel(2, 0));
    assertEquals(64, lumaImage.getGreenChannel(2, 0));
    assertEquals(64, lumaImage.getBlueChannel(2, 0));
  }

  @Test
  public void testValueCommand() {
    /*
     * This tests the value command.
     */
    String command = "value-component testImage valueImage";

    // Create a mock image database with the previously loaded image
    IImageDataBase mockImageData = new ImageDataBase();
    mockImageData.add("testImage", image); // image is the one loaded in the previous test

    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    controller.start();

    IImageState valueImage = mockImageData.get("valueImage");
    assertEquals(225, valueImage.getRedChannel(1, 2));
    assertEquals(225, valueImage.getGreenChannel(1, 2));
    assertEquals(225, valueImage.getBlueChannel(1, 2));
  }

  @Test
  public void testIncreaseBrightenCommand() {
    /*
     * This tests the brighten command.
     * Specifically, it tests the brighten command with a positive value.
     */
    String command = "brighten 50 testImage brightenImage";

    // Create a mock image database with the previously loaded image
    IImageDataBase mockImageData = new ImageDataBase();
    mockImageData.add("testImage", image); // image is the one loaded in the previous test

    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    controller.start();

    IImageState brightenedImage = mockImageData.get("brightenImage");
    assertEquals(150, brightenedImage.getRedChannel(0, 0));
    assertEquals(200, brightenedImage.getGreenChannel(0, 0));
    assertEquals(250, brightenedImage.getBlueChannel(0, 0));
  }

  @Test
  public void testDecreaseBrightnessCommand() {
    /*
     * This tests the brighten command.
     * Specifically, it tests the brighten command with a negative value.
     */
    String command = "brighten -50 testImage brightenImage";

    // Create a mock image database with the previously loaded image
    IImageDataBase mockImageData = new ImageDataBase();
    mockImageData.add("testImage", image);

    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    controller.start();

    IImageState brightenedImage = mockImageData.get("brightenImage");
    assertEquals(50, brightenedImage.getRedChannel(0, 0));
    assertEquals(100, brightenedImage.getGreenChannel(0, 0));
    assertEquals(150, brightenedImage.getBlueChannel(0, 0));
  }

  @Test
  public void testInvalidCommand() {
     /*
      * This tests an invalid command.
      */
    // Create a mock IImageDataBase
    IImageDataBase mockModel = new ImageDataBase();
    // Create a StringWriter to capture the output
    StringWriter output = new StringWriter();
    // Create the text
    TextController controller = new TextController(
            new java.io.StringReader(""), mockModel, output);
    // Provide an invalid command (e.g., "invalid-command")
    String invalidCommand = "invalid-command";
    // Call processCommand with the invalid command
    controller.processCommand(invalidCommand);
    // Verify if the correct error message is written to the output (StringWriter)
    assertEquals("Invalid command.", output.toString().trim());
  }

  /**
   * These test the filtering commands.
   */

  @Test
  public void BlurFilterCommand() {
    /*
     * This tests the blur command.
     */
    String command = "blur testImage blurImage";

    // Create a mock image database with the previously loaded image
    IImageDataBase mockImageData = new ImageDataBase();
    mockImageData.add("testImage", image);

    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    controller.start();

    IImageState blurImage = mockImageData.get("blurImage");
    assertEquals(48, blurImage.getRedChannel(0, 0));
    assertEquals(76, blurImage.getGreenChannel(0, 0));
    assertEquals(104, blurImage.getBlueChannel(0, 0));
  }

  @Test
  public void GreyScaleFilterCommand() {
    /*
     * This tests the greyscale command.
     */
    String command = "greyscale testImage greyImage";

    // Create a mock image database with the previously loaded image
    IImageDataBase mockImageData = new ImageDataBase();
    mockImageData.add("testImage", image);

    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    controller.start();

    IImageState greyImage = mockImageData.get("greyImage");
    assertEquals(121, greyImage.getRedChannel(0, 0));
    assertEquals(200, greyImage.getGreenChannel(0, 0));
    assertEquals(255, greyImage.getBlueChannel(0, 0));
  }

  @Test
  public void SepiaFilterCommand() {
    /**
     * This tests the sepia command.
     */
    // Mocking a given command text line
    String command = "sepia testImage sepiaImage";
    // Create a mock image database with the previously loaded image
    IImageDataBase mockImageData = new ImageDataBase();
    // Add the image to the database
    mockImageData.add("testImage", image);
    // Create controller to process the command
    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    // start the controller
    controller.start();
    // Check the image is in the database
    IImageState sepiaImage = mockImageData.get("sepiaImage");
    // Ensure the pixel's values are as expected
    assertEquals(124, sepiaImage.getRedChannel(0, 0));
    assertEquals(200, sepiaImage.getGreenChannel(0, 0));
    assertEquals(255, sepiaImage.getBlueChannel(0, 0));
  }

  @Test
  public void testSharpenCommand() {
    /**
     * This tests the sharpen command.
     */
    // Mocking a given command text line
    String command = "sharpen testImage sharpImage";
    // Create a mock image database with the previously loaded image
    IImageDataBase mockImageData = new ImageDataBase();
    // Add the image to the database
    mockImageData.add("testImage", image);
    // Create controller to process the command
    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    // start the controller
    controller.start();
    // Check the image is in the database
    IImageState sharpImage = mockImageData.get("sharpImage");
    // Ensure the pixel's values are as expected
    assertEquals(83, sharpImage.getRedChannel(0, 0));
    assertEquals(168, sharpImage.getGreenChannel(0, 0));
    assertEquals(255, sharpImage.getBlueChannel(0, 0));
  }

  @Test
  public void testFileFormats() {
        /**
         * This tests the file formats.
         * Testing saving these different image formats.
         */
    // Mocking a given command text line
    String command = "load res/jpeg_test.jpg test";
    // Create a mock image database
    IImageDataBase mockImageData = new ImageDataBase();
    // Create controller to process the command
    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    // start the controller
    controller.start();
    assertNotNull(mockImageData.get("test"));
    // Mocking a given command text line
    String command2 = "save res/test.png test";
    // Create controller to process the command
    ITextController controller2 = new TextController(new StringReader(command2),
            mockImageData, new PrintWriter(output));
    controller2.start();
    // Check the image is in the database
    assertNotNull(mockImageData.get("test"));
  }

  @Test
  public void testScript() {
    /**
     * This tests the script command.
     * Scripts are text files that contain a sequence of commands.
     */
    // Mocking a given command text line
    String command = "-file res/script.txt";

    // Create a mock image database with the previously loaded image
    IImageDataBase mockImageData = new ImageDataBase();
    // Create controller to process the command
    ITextController controller = new TextController(new StringReader(command),
            mockImageData, new PrintWriter(output));
    // Create a ByteArrayOutputStream to capture the output
    ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
    PrintStream customPrintStream2 = new PrintStream(outputStream2);
    // Read what is printed to the console
    PrintStream originalPrintStream2 = System.out;
    // Set the System.out to the customPrintStream
    System.setOut(customPrintStream2);
    // Start the controller
    controller.start();
    // Restore the original System.out
    System.setOut(originalPrintStream2);
    String expectedOutput = "";
    assertEquals(expectedOutput, outputStream2.toString());
  }
}