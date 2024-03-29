package commands.filtered;

import model.transformations.ITransformation;
import model.transformations.filter.BlurFilterTransformation;

/**
 * This class utilizes the BlurFilterTransformation class when blur command asked.
 * It extends the AbstractFilterCommand abstract class.
 */
public class BlurFilterCommand extends AbstractFilterCommand {

  @Override
  protected ITransformation getFilterTransformation() {
    return new BlurFilterTransformation();
  }
}
