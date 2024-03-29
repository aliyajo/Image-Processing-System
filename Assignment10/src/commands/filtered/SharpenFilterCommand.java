package commands.filtered;

import model.transformations.ITransformation;
import model.transformations.filter.SharpenFilterTransformation;

/**
 * This class utilizes the SharpenFilterTransformation class when sharpen command asked.
 * It extends the AbstractFilterCommand abstract class.
 */
public class SharpenFilterCommand extends AbstractFilterCommand {
  @Override
  protected ITransformation getFilterTransformation() {
    return new SharpenFilterTransformation();
  }
}