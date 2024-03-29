package commands.greyscalecommands;

import java.util.Objects;
import java.util.Scanner;

import commands.ICommand;
import model.IImageDataBase;
import model.IImageState;
import model.transformations.ITransformation;

/**
 * This is an abstract class for greyscale component commands.
 * It implements the ICommand Interface.
 */
public abstract class AbstractComponentCommand implements ICommand {

  protected abstract ITransformation getTransformation();

  @Override
  public void run(Scanner scanner, IImageDataBase model) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(scanner);

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Second argument must be image ID.");
    }
    String sourceImageId = scanner.next();

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Third argument must be image ID.");
    }
    String destinationImage = scanner.next();

    //Get the image with the source ID.
    IImageState sourceImage = model.get(sourceImageId);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified id doesn't exist.");
    }
    //Apply transformation.
    ITransformation transformation = getTransformation();
    IImageState transformedImage = transformation.run(sourceImage);
    //Save it back to the database
    model.add(destinationImage, transformedImage);
  }
}
