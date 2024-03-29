package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import model.IImageState;
import model.Pixel;

/**
 * This class is to accurately save the Image in PPM format.
 * It implements the IImageSaver interface.
 */
public class PPMImageSaver implements IImageSaver {
  private final String pathToSave;
  private final IImageState image;
  private final Appendable output;

  /**
   * This constructor holds the information needed to save an image.
   *
   * @param pathToSave String of where to save the image.
   * @param image      Image that is wanting to be saved.
   * @param output     conversion to the PPM format.
   */
  public PPMImageSaver(String pathToSave, IImageState image, Appendable output) {
    this.pathToSave = Objects.requireNonNull(pathToSave);
    this.image = Objects.requireNonNull(image);
    this.output = output;
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
    try (
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathToSave))) {
      //Writing type of PPM file.
      write("P3\n");
      //Provide the width and height of the image.
      write(image.getWidth() + " " + image.getHeight() + "\n");
      //Writing maximum value of the color.
      write("255\n");
      for (int row = 0; row < image.getHeight(); row++) {
        for (int col = 0; col < image.getWidth(); col++) {
          Pixel pixel = new Pixel(image.getRedChannel(col, row), image.getGreenChannel(col, row),
                  image.getBlueChannel(col, row));
          write(pixel + "\n");
        }
      }
      writer.write(output.toString());
      System.out.println("Image saved successfully.");
    } catch (IOException e) {
      throw new IllegalStateException("Could not save file");
    }
  }
}
