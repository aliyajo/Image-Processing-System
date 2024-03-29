package commands;

import java.util.Objects;
import java.util.Scanner;

import model.IImageDataBase;
import model.IImageState;
import model.transformations.BrightenTransformation;
import model.transformations.ITransformation;

/**
 * This class utilizes the Brightened transformation when the command is called.
 * It extends the ICommand.
 */
public class BrightenCommand implements ICommand {

  @Override
  public void run(Scanner scanner, IImageDataBase model) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(scanner);
    if (!scanner.hasNextInt()) {
      throw new IllegalStateException("Second argument must be integer.");
    }
    int value = scanner.nextInt();
    if (!scanner.hasNext()) {
      throw new IllegalStateException("Third argument must be image ID.");
    }
    String sourceImageId = scanner.next();

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Fourth argument must be image ID.");
    }
    String destinationImage = scanner.next();
    //Get the image with the source ID.
    IImageState sourceImage = model.get(sourceImageId);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified id doesn't exist.");
    }
    //Brighten the image
    ITransformation brightenTransformation = new BrightenTransformation(value);
    IImageState brightenedImage = brightenTransformation.run(sourceImage);
    //Save it back to the database
    model.add(destinationImage, brightenedImage);
  }
}
