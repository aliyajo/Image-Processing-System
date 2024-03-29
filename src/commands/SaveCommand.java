package commands;

import java.util.Objects;
import java.util.Scanner;

import io.ImageSaver;
import io.ImageUtil;
import io.PPMImageSaver;
import model.IImageDataBase;
import model.IImageState;

/**
 * This class utilizes the SavePPM function  when the command is called.
 * It extends the ICommand.
 */
public class SaveCommand implements ICommand {

  @Override
  public void run(Scanner scanner, IImageDataBase model) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(scanner);

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Second argument must be image ID.");
    }
    String destinationPath = scanner.next();
    String imageType = ImageUtil.getFormat(destinationPath);

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Third argument must be image ID.");
    }
    String sourceImageId = scanner.next();

    IImageState sourceImage = model.get(sourceImageId);

    if (imageType.equals("ppm")) {
      PPMImageSaver ppmImageSaver = new PPMImageSaver(destinationPath, sourceImage, System.out);
      ppmImageSaver.run();
      model.add(sourceImageId, sourceImage);
    } else {
      ImageSaver ppmImageSaver = new ImageSaver(destinationPath, sourceImage);
      ppmImageSaver.run();
      model.add(sourceImageId, sourceImage);
    }
  }
}
