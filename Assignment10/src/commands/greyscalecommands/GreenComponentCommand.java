package commands.greyscalecommands;

import model.transformations.ITransformation;
import model.transformations.component.GreenTransformation;

/**
 * This class is to utilize the Green Transformation when command called.
 * It extends the AbstractComponentCommand.
 */
public class GreenComponentCommand extends AbstractComponentCommand {

  @Override
  protected ITransformation getTransformation() {
    return new GreenTransformation();
  }
}
