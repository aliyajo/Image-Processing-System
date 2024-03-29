package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents the model for an Image.
 */
public class ImageDataBase implements IImageDataBase {
  // The map of images in the database
  private final Map<String, IImageState> images;

  /**
   * This constructor creates new ImageDataBase object.
   */
  public ImageDataBase() {
    this.images = new HashMap<String, IImageState>();
  }

  /**
   * This method adds an image to the database.
   * @param id the id of the image
   * @param image the image to add
   * @throws IllegalArgumentException if the id or image is null
   */
  @Override
  public void add(String id, IImageState image) {
    if (id == null || image == null) {
      throw new IllegalArgumentException("id or image is null");
    }
    this.images.put(id, image);
  }

  /**
   * This method gets an image from the database.
   * @param id the id of the image
   * @return the image with the given id
   */
  @Override
  public IImageState get(String id) {
    Objects.requireNonNull(id);
    return this.images.get(id);
  }

  /**
   * This method gets data from the database.
   * @return the data from the database
   */
  public Map<String, IImageState> getData() {
    return images;
  }
}
