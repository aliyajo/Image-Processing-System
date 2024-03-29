package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import model.IImage;
import model.IImageState;
import model.ImageImpl;

/**
 * This class is to accurately load the Image.
 * It implements the IImageLoader interface.
 */
public class PPMImageLoader implements io.IImageLoader {
  private final String filePath;
  private final String fileName;

  /**
   * This constructor holds the information needed to load an image.
   *
   * @param filePath String that is where the file is.
   * @param fileName String of what the image is called.
   */
  public PPMImageLoader(String filePath, String fileName) {
    this.filePath = Objects.requireNonNull(filePath);
    this.fileName = Objects.requireNonNull(fileName);
  }

  @Override
  public IImageState run() {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(this.filePath));
    } catch (FileNotFoundException e) {
      System.out.println("File " + fileName + " not found!");
    }

    StringBuilder builder = new StringBuilder();
    // Read the file line by line and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (!s.startsWith("#")) { // Use startsWith instead of charAt(0) for comment lines
        builder.append(s).append(System.lineSeparator());
      }
    }

    // Now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
    IImage image = new ImageImpl(width, height);
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        image.setPixel(col, row, r, g, b);
        System.out.println("Color of pixel (" + row + "," + col + "): " + r + "," + g + "," + b);
      }
    }
    return image;
  }
}