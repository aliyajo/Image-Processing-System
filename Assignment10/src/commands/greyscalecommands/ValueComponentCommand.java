package commands.greyscalecommands;

import model.transformations.ITransformation;
import model.transformations.component.ValueTransformation;

/**
 * This class utilizes the Abstract Transformation when the command is called.
 * It extends the AbstractComponentCommand.
 */
public class ValueComponentCommand extends AbstractComponentCommand {

  @Override
  protected ITransformation getTransformation() {
    return new ValueTransformation();
  }
}
