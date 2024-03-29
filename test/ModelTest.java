import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import io.ImageLoader;
import io.ImageSaver;
import io.ImageUtil;
import io.PPMImageSaver;
import model.IImage;
import model.IImageState;
import model.IPixel;
import model.ImageDataBase;
import model.ImageImpl;
import model.Pixel;
import model.transformations.BrightenTransformation;
import model.transformations.component.BlueTransformation;
import model.transformations.component.GreenTransformation;
import model.transformations.component.IntensityTransformation;
import model.transformations.component.LumaTransformation;
import model.transformations.component.RedTransformation;
import model.transformations.component.ValueTransformation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the model.
 */
public class ModelTest {
  private IImage image;

  /**
   * Testing the Pixel.
   */

  @Test
  public void pixelConstructorTest() {
    /*
     * Establishing a pixel object and testing the getter methods.
     */
    IPixel pixelTest = new Pixel(2, 3, 4);
    assertEquals(2, pixelTest.getR());
    assertEquals(3, pixelTest.getG());
    assertEquals(4, pixelTest.getB());
    String expected = "2 3 4";
    assertEquals(expected, pixelTest.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void setRBGExceptionTest() {
    /*
     * Testing the exceptions for the Pixel class.
     * Ensure that the code does not allow for out of range values.
     */
    //Testing out of range red channel.
    IPixel pixelTestR = new Pixel(-2, 3, 40);
    IPixel pixelTestRR = new Pixel(265, 3, 40);
    //Testing out of range green channel.
    IPixel pixelTestG = new Pixel(2, -44, 30);
    IPixel pixelTestGG = new Pixel(2, 256, 3);
    //Testing out of range blue channel.
    IPixel pixelTestB = new Pixel(2, 44, -30);
    IPixel pixelTestBB = new Pixel(2, 44, 300);
  }

  @Test
  public void testSettingPixel() {
    /*
     * Testing the setter methods for the Pixel class.
     */
    IPixel pixel = new Pixel(0, 0, 0);
    pixel.setR(50);
    assertEquals(50, pixel.getR());
    pixel.setB(75);
    assertEquals(75, pixel.getB());
    pixel.setG(56);
    assertEquals(56, pixel.getG());
    String expected = "50 56 75";
    assertEquals(expected, pixel.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSettingPixelException() {
    /*
     * Testing the exceptions for the Pixel class.
     */
    IPixel pixel = new Pixel(0, 0, 0);
    pixel.setR(265);
    pixel.setR(-28);
    pixel.setG(665);
    pixel.setG(-15);
    pixel.setB(300);
    pixel.setB(-33);
  }

  /**
   * Testing ImageImpl.
   */

  @Test
  public void ImageImplRBGTest() {
    /*
     * Testing the ImageImpl class.
     */
    IImage image = new ImageImpl(3, 4);
    //Testing getter methods for image dimensions.
    assertEquals(4, image.getHeight());
    assertEquals(3, image.getWidth());
    image.setPixel(2, 3, 100, 100, 100);
    //Testing RBG channel getter methods.
    assertEquals(100, image.getRedChannel(2, 3));
    assertEquals(100, image.getBlueChannel(2, 3));
    assertEquals(100, image.getGreenChannel(2, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void ImageImplRBGExceptionTest() {
    /*
     * Testing the exceptions for the ImageImpl class.
     */
    IImage image = new ImageImpl(4, 5);
    //Testing red channel exceptions.
    image.getRedChannel(-1, 2);
    image.getRedChannel(2, -3);
    image.getRedChannel(5, 4);
    image.getRedChannel(4, 6);
    //Testing green channel exceptions.
    image.getGreenChannel(-1, 2);
    image.getGreenChannel(2, -3);
    image.getGreenChannel(5, 4);
    image.getGreenChannel(4, 6);
    //Testing blue channel exceptions.
    image.getBlueChannel(-1, 2);
    image.getBlueChannel(2, -3);
    image.getBlueChannel(5, 4);
    image.getBlueChannel(4, 6);
  }

  @Test
  public void ImageDataBaseConstructorTest() {
    /*
     * Testing the ImageDataBase constructor.
     */
    ImageDataBase data = new ImageDataBase();
    assertTrue(data.getData().isEmpty());
  }

  @Test
  public void ImageDataBaseTest() {
    /*
     * Testing the ImageDataBase add and get methods.
     */
    ImageDataBase imageData = new ImageDataBase();
    IImageState image = new ImageImpl(4, 5);
    imageData.add("image1", image);
    assertEquals(image, imageData.get("image1"));
    //Another test for imageDataBase add and get method
    IImageState image2 = new ImageImpl(43, 53);
    imageData.add("image!!!!", image2);
    assertEquals(image2, imageData.get("image!!!!"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void ImageDataBaseExceptionTest() {
    /*
     * Testing the exceptions for the ImageDataBase class.
     */
    ImageDataBase imageData = new ImageDataBase();
    IImageState image = new ImageImpl(4, 5);
    imageData.add(null, image);
    imageData.add("image", null);
  }

  /**
   * Testing the transformations.
   */

  @Before
  public void setUp() {
    //Setup one image
    int width = 3;
    int height = 3;
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

    //Setup another Image
    IImage image2 = new ImageImpl(2, 1);
    image2.setPixel(0, 0, 0, 0, 0);
    image2.setPixel(1, 0, 1, 1, 0);
  }

  @Test
  public void BrightnessPPMTest() {
    /*
     * Testing the BrightenTransformation.
     */
    // Case #1 for testing brightness.
    BrightenTransformation transformation = new BrightenTransformation(50);
    IImageState brightenedImage = transformation.run(image);
    // Assert the resulting pixel values after brightening
    assertEquals(150, brightenedImage.getRedChannel(0, 0));
    assertEquals(200, brightenedImage.getGreenChannel(0, 0));
    assertEquals(250, brightenedImage.getBlueChannel(0, 0));
    // Case #2 for testing brightness.
    BrightenTransformation transformation2 = new BrightenTransformation(50);
    IImageState brightenedImage2 = transformation2.run(image);
    assertEquals(200, brightenedImage2.getRedChannel(2, 1));
    assertEquals(250, brightenedImage2.getGreenChannel(2, 1));
    //Showing the clamping working for 255.
    assertEquals(255, brightenedImage2.getBlueChannel(2, 1));
    // Case #3 for testing brightness.
    BrightenTransformation transformation3 = new BrightenTransformation(-50);
    IImageState brightenedImage3 = transformation3.run(image);
    assertEquals(0, brightenedImage3.getRedChannel(2, 0));
    assertEquals(25, brightenedImage3.getGreenChannel(2, 0));
    //Showing the clamping working for 0.
    assertEquals(75, brightenedImage3.getBlueChannel(2, 0));
  }


  @Test
  public void BluePPMTest() {
    /*
     * Testing the BlueTransformation.
     */
    // Apply the BlueTransformation.
    BlueTransformation transformation = new BlueTransformation();
    IImageState blue = transformation.run(image);

    // Assert the resulting pixel values after blue transformation.
    assertEquals(150, blue.getRedChannel(0, 1));
    assertEquals(150, blue.getGreenChannel(0, 1));
    assertEquals(150, blue.getBlueChannel(0, 1));
  }

  @Test
  public void GreenPPMTest() {
    /*
     * Testing the GreenTransformation.
     */
    // Apply the GreenTransformation.
    GreenTransformation transformation = new GreenTransformation();
    IImageState green = transformation.run(image);

    // Assert the resulting pixel values after green transformation.
    assertEquals(50, green.getRedChannel(2, 2));
    assertEquals(50, green.getGreenChannel(2, 2));
    assertEquals(50, green.getBlueChannel(2, 2));
  }

  @Test
  public void RedPPMTest() {
    /*
     * Testing the RedTransformation.
     */
    // Apply the RedTransformation.
    RedTransformation transformation = new RedTransformation();
    IImageState red = transformation.run(image);

    // Assert the resulting pixel values after red transformation.
    assertEquals(150, red.getRedChannel(2, 1));
    assertEquals(150, red.getGreenChannel(2, 1));
    assertEquals(150, red.getBlueChannel(2, 1));
  }

  @Test
  public void IntensityPPMTest() {
    /*
     * Testing the IntensityTransformation.
     */
    // Apply the IntensityTransformation.
    IntensityTransformation transformation = new IntensityTransformation();
    IImageState intensity = transformation.run(image);

    // Assert the resulting pixel values after intensity transformation.
    assertEquals(100, intensity.getRedChannel(0, 1));
    assertEquals(100, intensity.getGreenChannel(0, 1));
    assertEquals(100, intensity.getBlueChannel(0, 1));
  }

  @Test
  public void LumaPPMTest() {
    /*
     * Testing the LumaTransformation.
     */
    // Apply the LumaTransformation.
    LumaTransformation transformation = new LumaTransformation();
    IImageState luma = transformation.run(image);

    // Assert the resulting pixel values after luma transformation.
    assertEquals(64, luma.getRedChannel(2, 0));
    assertEquals(64, luma.getGreenChannel(2, 0));
    assertEquals(64, luma.getBlueChannel(2, 0));
  }

  @Test
  public void ValuePPMTest() {
    /*
     * Testing the ValueTransformation.
     */
    // Apply the ValueTransformation.
    ValueTransformation transformation = new ValueTransformation();
    IImageState value = transformation.run(image);

    // Assert the resulting pixel values after value transformation.
    assertEquals(225, value.getRedChannel(1, 2));
    assertEquals(225, value.getGreenChannel(1, 2));
    assertEquals(225, value.getBlueChannel(1, 2));
  }

  @Test
  public void testSavePPM() {
    /*
     * Testing the PPMImageSaver.
     */
    StringBuilder stringBuilder = new StringBuilder();
    PPMImageSaver ppmImageSaver = new PPMImageSaver("res/image.ppm",
            image, stringBuilder);
    ppmImageSaver.run();

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
            + "100 50 150";
    assertEquals(expectedOutput, stringBuilder.toString().trim());
  }

  @Test
  public void testPNGSave() {
    /*
     * Testing the ImageSaver for PNG.
     */
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream customPrintStream = new PrintStream(outputStream);

    // Redirect System.out to customPrintStream
    PrintStream originalSystemOut = System.out;
    System.setOut(customPrintStream);
    ImageSaver imageSaver = new ImageSaver("res/image.png", image);
    // Save the image
    imageSaver.run();
    System.setOut(originalSystemOut);
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
            + "Image saved successfully.";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void testJPEGSave() {
    /*
     * Testing the ImageSaver for JPEG.
     */
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream customPrintStream = new PrintStream(outputStream);

    // Redirect System.out to customPrintStream
    PrintStream originalSystemOut = System.out;
    System.setOut(customPrintStream);
    ImageSaver imageSaver = new ImageSaver("res/image.jpeg", image);
    // Save the image
    imageSaver.run();
    System.setOut(originalSystemOut);
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
  public void testLoadPPM() {
    /*
     * Testing the ImageLoader for PPM.
     */
    String filePath = "res/test_image.ppm";
    String fileName = "test_image";
    io.ImageLoader imageLoader = new io.ImageLoader(filePath, fileName);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    imageLoader.run();
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
  public void testLoadPNG() {
    /*
     * Testing the ImageLoader for PNG.
     */
    String filePath = "res/png_test.png";
    String fileName = "png_test.png";
    ImageLoader imageLoader = new ImageLoader(filePath, fileName);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    imageLoader.run();
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
    String filePath2 = "res/png_test2.png";
    String fileName2 = "png_test2.png";
    ImageLoader imageLoader2 = new ImageLoader(filePath2, fileName2);
    ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream2));
    imageLoader2.run();
    String expectedOutput2 = "Image format: png\r\n"
            + "Width of the image: 3\r\n"
            + "Height of the image: 1\r\n"
            + "Color of pixel (0,0): 0,0,0\r\n"
            + "Color of pixel (0,1): 255,255,255\r\n"
            + "Color of pixel (0,2): 0,0,0\r\n";

    assertEquals(expectedOutput2, outputStream2.toString());
  }

  @Test
  public void testJPEGLoad() {
    /*
     * Testing the ImageLoader for JPEG.
     */
    String filePath = "res/jpeg_test.jpg";
    String fileName = "jpeg_test.jpg";
    ImageLoader imageLoader = new ImageLoader(filePath, fileName);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    imageLoader.run();
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

    String filePath2 = "res/jpeg_test2.jpg";
    String fileName2 = "jpeg_test2.jpg";
    ImageLoader imageLoader2 = new ImageLoader(filePath2, fileName2);
    ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream2));
    imageLoader2.run();
    String expectedOutput2 = "Image format: jpg\r\n"
            + "Width of the image: 1\r\n"
            + "Height of the image: 1\r\n"
            + "Color of pixel (0,0): 255,255,255\r\n";

    assertEquals(expectedOutput2, outputStream2.toString());
  }

  @Test
  public void testImageUtil() {
    /*
     * Testing the ImageUtil class.
     */
    String filePath = "path/to/file.ppm";
    assertEquals("ppm", ImageUtil.getFormat(filePath));
  }
}
