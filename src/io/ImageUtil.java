package io;

import java.awt.image.BufferedImage;

import model.IImageState;

/**
 * This class stores the methods used across the project of processing images.
 */
public class ImageUtil {

  /**
   * This is a helper function to determine the image format.
   *
   * @param filePath File that format is being determined.
   * @return String that is the file type.
   */
  public static String getFormat(String filePath) {
    String format = "";
    //Determine file type by the filePath given.
    int dotIndex = filePath.lastIndexOf('.');
    if (dotIndex > 0 && dotIndex < filePath.length() - 1) {
      format = filePath.substring(dotIndex + 1).toLowerCase();
    }
    return format;
  }

  /**
   * This method is to convert a given image into a buffered image.
   *
   * @param image Image going to be converted.
   * @return buffered image.
   */
  public static BufferedImage convertToBufferedImage(IImageState image) {
    BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    // Populate the BufferedImage with pixel data from the image
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        int red = image.getRedChannel(col, row);
        int green = image.getGreenChannel(col, row);
        int blue = image.getBlueChannel(col, row);
        //Use bitwise operations to accurately depict the RGB channels.
        int rgb = (red << 16) | (green << 8) | blue;
        bufferedImage.setRGB(col, row, rgb);
      }
    }
    return bufferedImage;
  }
}
