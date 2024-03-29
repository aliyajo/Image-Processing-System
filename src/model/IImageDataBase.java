package model;

/**
 * This interface is for the methods maintained by a model.
 */
public interface IImageDataBase {

  /**
   * This method is able to add images to a database.
   *
   * @param id    String id of the image.
   * @param image image object.
   */
  void add(String id, IImageState image);

  /**
   * This is a getter method of retrieving image from database.
   *
   * @param id String id of the image.
   * @return image with given string id.
   */
  IImageState get(String id);
}
