package commands.greyscalecommands;

import model.transformations.ITransformation;
import model.transformations.component.RedTransformation;

/**
 * This class utilizes the Red Transformation when the command is called.
 * It extends the AbstractComponentCommand.
 */
public class RedComponentCommand extends AbstractComponentCommand {

  @Override
  protected ITransformation getTransformation() {
    return new RedTransformation();
  }
}

