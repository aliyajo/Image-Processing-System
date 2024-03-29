package model.gui;

import java.util.Objects;

/**
 * This class represents the model for a GUI.
 */
public class Model implements IModel {
  private String data;

  /**
   * This constructor creates a new Model object.
   */
  public Model() {
    this.data = "";
  }

  @Override
  public String getData() {
    return this.data.toUpperCase();
  }

  @Override
  public void setData(String data) {
    this.data = Objects.requireNonNull(data);
  }
}
