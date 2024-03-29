package io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import model.IImageState;
import model.Pixel;

import static io.ImageUtil.getFormat;

/**
 * This class is to accurately save the Image in PPM format.
 * It implements the IImageSaver interface.
 */
public class ImageSaver implements IImageSaver {
  private final String pathToSave;
  private final IImageState image;
  private Appendable output;

  /**
   * This constructor holds the information needed to save an image.
   *
   * @param pathToSave String of where to save the image.
   * @param image      Image that is wanting to be saved.
   */
  public ImageSaver(String pathToSave, IImageState image) {
    this.pathToSave = Objects.requireNonNull(pathToSave);
    this.image = Objects.requireNonNull(image);
    this.output = System.out;
  }

  /**
   * This helper function aids in writing the messages.
   *
   * @param message String message wanting to be written out.
   */
  private void write(String message) {
    try {
      this.output.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("writing failed.");
    }
  }

  @Override
  public void run() {
    try {
      File file = new File(pathToSave);

      // Get the image format based on the file extension
      String imageType = getFormat(pathToSave);
      System.out.println(imageType);

      // Create a BufferedImage to hold the image data
      BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(),
              BufferedImage.TYPE_INT_RGB);

      int height = image.getHeight();
      int width = image.getWidth();
      write(image.getWidth() + " " + image.getHeight() + "\n");
      System.out.println("255");
      // Populate the BufferedImage with pixel data from the image
      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          int red = image.getRedChannel(col, row);
          int green = image.getGreenChannel(col, row);
          int blue = image.getBlueChannel(col, row);
          //Use bitwise operations to accurately depict the RGB channels.
          int rgb = (red << 16) | (green << 8) | blue;
          bufferedImage.setRGB(col, row, rgb);
          Pixel pixel = new Pixel(red, green, blue);
          write(pixel + "\n");
        }
      }

      // Save the image using ImageIO
      ImageIO.write(bufferedImage, imageType, file);

      System.out.println("Image saved successfully.");
    } catch (IOException e) {
      throw new IllegalStateException("Could not save file: " + e.getMessage());
    }
  }
}
