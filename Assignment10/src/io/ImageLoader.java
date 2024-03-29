package io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import model.IImage;
import model.IImageState;
import model.ImageImpl;

/**
 * This class is to accurately load the Image.
 * It implements the IImageLoader interface.
 */
public class ImageLoader implements IImageLoader {
  private final String filePath;
  private final String fileName;

  /**
   * This constructor holds the information needed to load an image.
   *
   * @param filePath String that is where the file is.
   * @param fileName String of what the image is called.
   */
  public ImageLoader(String filePath, String fileName) {
    this.filePath = Objects.requireNonNull(filePath);
    this.fileName = Objects.requireNonNull(fileName);
  }

  @Override
  public IImageState run() {
    try {
      File file = new File(filePath);

      if (!file.exists()) {
        System.out.println("File " + fileName + " not found!");
      }
      //Read the file and set as an image.
      BufferedImage loadedImage = ImageIO.read(file);
      Objects.requireNonNull(loadedImage);

      System.out.println("Image format: " + ImageUtil.getFormat(filePath));
      int width = loadedImage.getWidth();
      System.out.println("Width of the image: " + width);
      int height = loadedImage.getHeight();
      System.out.println("Height of the image: " + height);

      IImage image = new ImageImpl(width, height);
      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          int rgb = loadedImage.getRGB(col, row);
          int r = (rgb >> 16) & 0xFF;
          int g = (rgb >> 8) & 0xFF;
          int b = rgb & 0xFF;
          image.setPixel(col, row, r, g, b);
          System.out.println("Color of pixel (" + row + "," + col + "): " + r + "," + g + "," + b);
        }
      }

      return image;

    } catch (IOException e) {
      System.out.println("Error loading image: " + e.getMessage());
      return null;
    }
  }


}