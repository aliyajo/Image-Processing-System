package commands.filtered;

import model.transformations.ITransformation;
import model.transformations.filter.GreyscaleFilterTransformation;

/**
 * This class utilizes the GreyscaleFilterTransformation class when greyscale command asked.
 * It extends the AbstractFilterCommand abstract class.
 */
public class GreyscaleFilterCommand extends AbstractFilterCommand {
  @Override
  protected ITransformation getFilterTransformation() {
    return new GreyscaleFilterTransformation();
  }
}
