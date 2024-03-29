package commands.greyscalecommands;

import model.transformations.ITransformation;
import model.transformations.component.IntensityTransformation;

/**
 * This class utilizes the intensity transformation when command called.
 * It extends the AbstractComponentCommand.
 */
public class IntensityComponentCommand extends AbstractComponentCommand {

  @Override
  protected ITransformation getTransformation() {
    return new IntensityTransformation();
  }
}
