package commands.filtered;

import java.util.Objects;
import java.util.Scanner;

import commands.ICommand;
import model.IImageDataBase;
import model.IImageState;
import model.transformations.ITransformation;

/**
 * This is an abstract class for filter commands.
 * It implements the ICommand Interface.
 */
public abstract class AbstractFilterCommand implements ICommand {

  /**
   * This method if able to determine which filter transformation to call.
   *
   * @return which ITransformation is being used.
   */
  protected abstract ITransformation getFilterTransformation();

  @Override
  public void run(Scanner scanner, IImageDataBase model) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(scanner);

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Second argument must be image ID.");
    }
    String sourceImageId = scanner.next();

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Third argument must be new image ID.");
    }
    String destinationImage = scanner.next();

    //Get the image with the source ID.
    IImageState sourceImage = model.get(sourceImageId);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified id doesn't exist.");
    }
    //Apply transformation.
    ITransformation transformation = getFilterTransformation();
    IImageState transformedFilterImage = transformation.run(sourceImage);
    //Save it back to the database
    model.add(destinationImage, transformedFilterImage);
  }
}
