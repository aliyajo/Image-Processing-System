package commands;

import java.util.Objects;
import java.util.Scanner;

import io.ImageLoader;
import io.ImageUtil;
import io.PPMImageLoader;
import model.IImageDataBase;
import model.IImageState;

/**
 * This class utilizes the LoadPPM function  when the command is called.
 * It extends the ICommand.
 */
public class LoadCommand implements ICommand {

  @Override
  public void run(Scanner scanner, IImageDataBase model) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(scanner);

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Second argument must be image path.");
    }
    String sourceImagePath = scanner.next();
    String imageType = ImageUtil.getFormat(sourceImagePath);
    if (!scanner.hasNext()) {
      throw new IllegalStateException("Third argument must be image ID.");
    }
    String imageId = scanner.next();

    if (imageType.equals("ppm")) {
      PPMImageLoader imageLoad = new PPMImageLoader(sourceImagePath, imageId);
      IImageState loadImage = imageLoad.run();
      //Save it back to the database
      model.add(imageId, loadImage);
    } else {
      ImageLoader imageLoad = new ImageLoader(sourceImagePath, imageId);
      IImageState loadImage = imageLoad.run();
      //Save it back to the database
      model.add(imageId, loadImage);
    }
  }
}
