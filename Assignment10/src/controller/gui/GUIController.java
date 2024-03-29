package controller.gui;

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;


import io.IImageSaver;
import io.ImageLoader;
import io.ImageSaver;
import io.ImageUtil;
import model.IImageDataBase;
import model.IImageState;
import model.transformations.BrightenTransformation;
import model.transformations.component.BlueTransformation;
import model.transformations.component.GreenTransformation;
import model.transformations.component.IntensityTransformation;
import model.transformations.component.LumaTransformation;
import model.transformations.component.RedTransformation;
import model.transformations.component.ValueTransformation;
import model.transformations.filter.BlurFilterTransformation;
import model.transformations.filter.GreyscaleFilterTransformation;
import model.transformations.filter.SepiaFilterTransformation;
import model.transformations.filter.SharpenFilterTransformation;
import view.gui.GUIView;


/**
 * This class is for the gui Controller implementation.
 * It implements the IGUIController Interface.
 */
public class GUIController extends Component implements IGUIController {
  private final IImageDataBase model;
  private final GUIView view;
  private IImageState currentImage;
  private String currentImageID;
  private final Stack<IImageState> imageStack;

  /**
   * This constructor creates a gui object.
   *
   * @param model IImageDataBase model.
   * @param view  GUIView view
   */
  public GUIController(IImageDataBase model, GUIView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.view.addViewListener(this);
    imageStack = new Stack<>();
  }

  @Override
  public void run() {
    view.setVisible(true);
  }

  @Override
  public void handleUndoEvent() {
    //Going to use stack objects.
    if (!imageStack.isEmpty()) {
      //Use the built-in method of pop() to get previous image.
      currentImage = imageStack.pop();
      this.view.setImage(ImageUtil.convertToBufferedImage(currentImage));
    }
  }


  @Override
  public void handleLoadEvent(String filePath) {
    String imageID = extractImageId(filePath);
    currentImageID = imageID;
    IImageState imageFromModel = this.model.get(imageID);

    //Make sure it is appropriate image type.
    if (!isFileAnImage(filePath)) {
      throw new IllegalArgumentException("Invalid image type.");
    }
    //Check if image is in the model or not.
    if (imageFromModel == null) {
      ImageLoader loadImage = new ImageLoader(filePath, imageID);
      IImageState loadedImage = loadImage.run();

      //Add image to the model, and make it the background screen.
      if (loadedImage != null) {
        this.model.add(imageID, loadedImage);
        this.currentImage = loadedImage;
        imageStack.push(currentImage);
        this.view.setImage(ImageUtil.convertToBufferedImage(loadedImage));
        this.view.updateHistogram(ImageUtil.convertToBufferedImage(currentImage));
        //If image is null, then cannot be loaded.
      } else {
        throw new IllegalArgumentException("The image was not able to be loaded!");
      }
      //If the image is in the model, then make it the background screen.
    } else {
      this.view.setImage(ImageUtil.convertToBufferedImage(imageFromModel));
    }
  }


  /**
   * This is a helper function to extract the ImageId of the given image.
   *
   * @param filePath String of the given file path.
   * @return String of the imageId.
   */
  private String extractImageId(String filePath) {
    Path path = Paths.get(filePath);
    //Use built-in function of getting the fileName.
    return path.getFileName().toString();

  }

  /**
   * This is a helper function to determine if the given image is the correct type of file.
   *
   * @param filePath String of the filepath of the given file.
   * @return boolean if the file is an image or not.
   */
  private boolean isFileAnImage(String filePath) {
    //Current images the gui can interpret.
    String[] validImageFormat = {"jpg", "jpeg", "png", "ppm"};
    //Utilize ImageUtil.getFormat method which determines extension of the file.
    String imageFormat = ImageUtil.getFormat(filePath);
    return Arrays.asList(validImageFormat).contains(imageFormat.toLowerCase());
  }

  @Override
  public void handleSaveEvent() {
    if (currentImage == null) {
      throw new IllegalArgumentException("There is no image to save");
    }
    String filePath = selectSaveLocation(currentImageID);
    try {
      IImageSaver imageSaver = new ImageSaver(filePath, this.currentImage);
      imageSaver.run();
      JOptionPane.showMessageDialog(this.view, "Image saved successfully to: "
              + filePath);
    } catch (Exception e) {
      throw new IllegalArgumentException("Image cannot be saved.");
    }
  }

  /**
   * This helper method is to allow the user to select the save location.
   *
   * @param currentImageID String for the currentImage ID
   * @return String of the filePath.
   */
  private String selectSaveLocation(String currentImageID) {
    JFileChooser fileChooser = new JFileChooser();
    //Set the default file name as the currentImageID variable
    fileChooser.setSelectedFile(new File(currentImageID));
    int result = fileChooser.showSaveDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      return selectedFile.getAbsolutePath();
    } else {
      throw new IllegalArgumentException("Invalid location chosen");
    }
  }


  @Override
  public void handleBrightnessEvent(int brightnessValue) {
    if (currentImageID == null) {
      throw new IllegalArgumentException("No image to alter");
    }
    try {
      //Use brighten transformation class to alter image.
      BrightenTransformation brightened = new BrightenTransformation(brightnessValue);
      //Pushing the image before the transformation so undo option will work.
      imageStack.push(currentImage);
      currentImage = brightened.run(currentImage);
      //Add this new current image to the model.
      this.model.add(currentImageID, currentImage);
      //Set the background as the now altered image.
      this.view.setImage(ImageUtil.convertToBufferedImage(currentImage));
      //Reset the histogram.
      this.view.updateHistogram(ImageUtil.convertToBufferedImage(currentImage));
      //Catch exceptions that involve number format error, and if image cannot be brightened.
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid brightness value");
    } catch (Exception e) {
      throw new IllegalArgumentException("Image cannot be brightened.");
    }
  }

  @Override
  public void handleRedTransformationEvent() {
    if (currentImageID == null) {
      throw new IllegalArgumentException("No image to alter");
    }
    try {
      //Use red transformation class to alter image.
      RedTransformation redTransformation = new RedTransformation();
      //Pushing the image before the transformation so undo option will work.
      imageStack.push(currentImage);
      currentImage = redTransformation.run(currentImage);
      //Add this new current image to the model.
      this.model.add(currentImageID, currentImage);
      //Set the background as the now altered image.
      this.view.setImage(ImageUtil.convertToBufferedImage(currentImage));
      //Reset the histogram.
      this.view.updateHistogram(ImageUtil.convertToBufferedImage(currentImage));
      //Catch exceptions that involve number format error, and if image cannot be brightened.
    } catch (Exception e) {
      throw new IllegalArgumentException("Image cannot be altered.");
    }
  }

  @Override
  public void handleGreenTransformationEvent() {
    if (currentImageID == null) {
      throw new IllegalArgumentException("No image to alter");
    }
    try {
      //Use green transformation class to alter image.
      GreenTransformation greenTransformation = new GreenTransformation();
      //Pushing the image before the transformation so undo option will work.
      imageStack.push(currentImage);
      currentImage = greenTransformation.run(currentImage);
      //Add this new current image to the model.
      this.model.add(currentImageID, currentImage);
      //Set the background as the now altered image.
      this.view.setImage(ImageUtil.convertToBufferedImage(currentImage));
      //Reset the histogram.
      this.view.updateHistogram(ImageUtil.convertToBufferedImage(currentImage));
      //Catch exceptions that involve number format error, and if image cannot be brightened.
    } catch (Exception e) {
      throw new IllegalArgumentException("Image cannot be altered.");
    }
  }

  @Override
  public void handleBlueTransformationEvent() {
    if (currentImageID == null) {
      throw new IllegalArgumentException("No image to alter");
    }
    try {
      //Use blue transformation class to alter image.
      BlueTransformation blueTransformation = new BlueTransformation();
      //Pushing the image before the transformation so undo option will work.
      imageStack.push(currentImage);
      currentImage = blueTransformation.run(currentImage);
      //Add this new current image to the model.
      this.model.add(currentImageID, currentImage);
      //Set the background as the now altered image.
      this.view.setImage(ImageUtil.convertToBufferedImage(currentImage));
      //Reset the histogram.
      this.view.updateHistogram(ImageUtil.convertToBufferedImage(currentImage));
      //Catch exceptions that involve number format error, and if image cannot be brightened.
    } catch (Exception e) {
      throw new IllegalArgumentException("Image cannot be altered.");
    }
  }

  @Override
  public void handleValueEvent() {
    if (currentImageID == null) {
      throw new IllegalArgumentException("No image to alter");
    }
    try {
      //Use value transformation class to alter image.
      ValueTransformation valueTransformation = new ValueTransformation();
      //Pushing the image before the transformation so undo option will work.
      imageStack.push(currentImage);
      currentImage = valueTransformation.run(currentImage);
      //Add this new current image to the model.
      this.model.add(currentImageID, currentImage);
      //Set the background as the now altered image.
      this.view.setImage(ImageUtil.convertToBufferedImage(currentImage));
      //Reset the histogram.
      this.view.updateHistogram(ImageUtil.convertToBufferedImage(currentImage));
      //Catch exceptions that involve number format error, and if image cannot be brightened.
    } catch (Exception e) {
      throw new IllegalArgumentException("Image cannot be altered.");
    }
  }

  @Override
  public void handleIntensityEvent() {
    if (currentImageID == null) {
      throw new IllegalArgumentException("No image to alter");
    }
    try {
      //Use intensity transformation class to alter image.
      IntensityTransformation intensityTransformation = new IntensityTransformation();
      //Pushing the image before the transformation so undo option will work.
      imageStack.push(currentImage);
      currentImage = intensityTransformation.run(currentImage);
      //Add this new current image to the model.
      this.model.add(currentImageID, currentImage);
      //Set the background as the now altered image.
      this.view.setImage(ImageUtil.convertToBufferedImage(currentImage));
      //Reset the histogram.
      this.view.updateHistogram(ImageUtil.convertToBufferedImage(currentImage));
      //Catch exceptions that involve number format error, and if image cannot be brightened.
    } catch (Exception e) {
      throw new IllegalArgumentException("Image cannot be altered.");
    }
  }

  @Override
  public void handleLumaEvent() {
    if (currentImageID == null) {
      throw new IllegalArgumentException("No image to alter");
    }
    try {
      //Use luma transformation class to alter image.
      LumaTransformation lumaTransformation = new LumaTransformation();
      //Pushing the image before the transformation so undo option will work.
      imageStack.push(currentImage);
      currentImage = lumaTransformation.run(currentImage);
      //Add this new current image to the model.
      this.model.add(currentImageID, currentImage);
      //Set the background as the now altered image.
      this.view.setImage(ImageUtil.convertToBufferedImage(currentImage));
      //Reset the histogram.
      this.view.updateHistogram(ImageUtil.convertToBufferedImage(currentImage));
      //Catch exceptions that involve number format error, and if image cannot be brightened.
    } catch (Exception e) {
      throw new IllegalArgumentException("Image cannot be altered.");
    }
  }

  @Override
  public void handleBlurEvent() {
    if (currentImageID == null) {
      throw new IllegalArgumentException("No image to alter");
    }
    try {
      //Use blur filter transformation class to alter image.
      BlurFilterTransformation blurFilterTransformation = new BlurFilterTransformation();
      //Pushing the image before the transformation so undo option will work.
      imageStack.push(currentImage);
      currentImage = blurFilterTransformation.run(currentImage);
      //Add this new current image to the model.
      this.model.add(currentImageID, currentImage);
      //Set the background as the now altered image.
      this.view.setImage(ImageUtil.convertToBufferedImage(currentImage));
      //Reset the histogram.
      this.view.updateHistogram(ImageUtil.convertToBufferedImage(currentImage));
      //Catch exceptions that involve number format error, and if image cannot be brightened.
    } catch (Exception e) {
      throw new IllegalArgumentException("Image cannot be altered.");
    }
  }

  @Override
  public void handleSharpenEvent() {
    if (currentImageID == null) {
      throw new IllegalArgumentException("No image to alter");
    }
    try {
      //Use sharpen filter transformation class to alter image.
      SharpenFilterTransformation sharpenFilterTransformation = new SharpenFilterTransformation();
      //Pushing the image before the transformation so undo option will work.
      imageStack.push(currentImage);
      currentImage = sharpenFilterTransformation.run(currentImage);
      //Add this new current image to the model.
      this.model.add(currentImageID, currentImage);
      //Set the background as the now altered image.
      this.view.setImage(ImageUtil.convertToBufferedImage(currentImage));
      //Reset the histogram.
      this.view.updateHistogram(ImageUtil.convertToBufferedImage(currentImage));
      //Catch exceptions that involve number format error, and if image cannot be brightened.
    } catch (Exception e) {
      throw new IllegalArgumentException("Image cannot be altered.");
    }
  }

  @Override
  public void handleSepiaFilterEvent() {
    if (currentImageID == null) {
      throw new IllegalArgumentException("No image to alter");
    }
    try {
      //Use sepia filter transformation class to alter image.
      SepiaFilterTransformation sepiaFilterTransformation = new SepiaFilterTransformation();
      currentImage = sepiaFilterTransformation.run(currentImage);
      //Add this new current image to the model.
      this.model.add(currentImageID, currentImage);
      //Set the background as the now altered image.
      this.view.setImage(ImageUtil.convertToBufferedImage(currentImage));
      //Reset the histogram.
      this.view.updateHistogram(ImageUtil.convertToBufferedImage(currentImage));
      //Catch exceptions that involve number format error, and if image cannot be brightened.
    } catch (Exception e) {
      throw new IllegalArgumentException("Image cannot be altered.");
    }
  }

  @Override
  public void handleGreyscaleFilterEvent() {
    if (currentImageID == null) {
      throw new IllegalArgumentException("No image to alter");
    }
    try {
      //Use greyscale filter transformation class to alter image.
      GreyscaleFilterTransformation greyscaleFilterTransformation =
              new GreyscaleFilterTransformation();
      //Pushing the image before the transformation so undo option will work.
      imageStack.push(currentImage);
      currentImage = greyscaleFilterTransformation.run(currentImage);
      //Add this new current image to the model.
      this.model.add(currentImageID, currentImage);
      //Set the background as the now altered image.
      this.view.setImage(ImageUtil.convertToBufferedImage(currentImage));
      //Reset the histogram.
      this.view.updateHistogram(ImageUtil.convertToBufferedImage(currentImage));
      //Catch exceptions that involve number format error, and if image cannot be brightened.
    } catch (Exception e) {
      throw new IllegalArgumentException("Image cannot be altered.");
    }
  }

  @Override
  public void handleHelpEvent() {
    String helpText = "res/USEME.pdf";
    try {
      File helpFile = new File(helpText);
      Desktop.getDesktop().open(helpFile);

    } catch (IOException e) {
      JOptionPane.showMessageDialog(this.view, "Help Document unavailable at the time",
              "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}