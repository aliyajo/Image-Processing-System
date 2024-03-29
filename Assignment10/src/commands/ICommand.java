package commands;

import java.util.Scanner;

import model.IImageDataBase;

/**
 * This interface is for the commands going ot be used to alter an image.
 */
public interface ICommand {

  /**
   * This method is to be able to accurately alter an image.
   *
   * @param scanner The image that is given in scanner format.
   * @param model   the imageDataBase data.
   */
  void run(Scanner scanner, IImageDataBase model);

}
