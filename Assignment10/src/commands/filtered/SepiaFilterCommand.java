package commands.filtered;

import model.transformations.ITransformation;
import model.transformations.filter.SepiaFilterTransformation;

/**
 * This class utilizes the SepiaFilterTransformation class when sepia command asked.
 * It extends the AbstractFilterCommand abstract class.
 */
public class SepiaFilterCommand extends AbstractFilterCommand {

  @Override
  protected ITransformation getFilterTransformation() {
    return new SepiaFilterTransformation();
  }
}
