package commands.greyscalecommands;

import model.transformations.ITransformation;
import model.transformations.component.BlueTransformation;

/**
 * This class is for utilizing the BlueTransformation when called.
 * It extends the AbstractComponentCommand.
 */
public class BlueComponentCommand extends AbstractComponentCommand {

  @Override
  protected ITransformation getTransformation() {
    return new BlueTransformation();
  }
}
