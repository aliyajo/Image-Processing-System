package commands.greyscalecommands;

import model.transformations.ITransformation;
import model.transformations.component.LumaTransformation;

/**
 * This class utilizes the Luma Transformation when command called.
 * It extends the AbstractComponentCommand.
 */
public class LumaComponentCommand extends AbstractComponentCommand {

  @Override
  protected ITransformation getTransformation() {
    return new LumaTransformation();
  }
}
