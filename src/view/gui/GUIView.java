package view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

/**
 * This class is going to create the view object of the gui.
 * It extends JFrame class to create the visual window.
 * It also implements the ActionListener Interface.
 */
public class GUIView extends JFrame implements ActionListener, IGUIView {

  private final Histogram histogram;
  private final List<ViewListener> listenersToNotify;
  private final JLabel mainPage;

  /**
   * This creates a GUIView constructor.
   */
  public GUIView() {
    this.listenersToNotify = new ArrayList<>();
    //Setting up window:
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Close on exit.
    setTitle("Image Processing System");
    ImageIcon image = new ImageIcon("res/TitlePage.png"); //Adding custom image as main.
    mainPage = new JLabel(image);
    JScrollPane scrollingWindow = new JScrollPane(mainPage);
    scrollingWindow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollingWindow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    add(scrollingWindow, BorderLayout.CENTER);
    pack();
    //Creating menu
    JMenuBar menuBar = new JMenuBar();
    //For the file option of the menu
    JMenu file = new JMenu("File");
    menuBar.add(file);
    // Save, Load, and Undo options.
    JMenuItem save = new JMenuItem("Save");
    JMenuItem load = new JMenuItem("Load");
    JMenuItem undo = new JMenuItem("Undo");
    // Add the options to the file menu.
    file.add(undo);
    file.add(save);
    file.add(load);
    //For the edit portion of the menu
    //Includes all the editing image methods.
    JMenu edit = new JMenu("Edit");
    menuBar.add(edit);
    JMenuItem brighten = new JMenuItem("Change Brightness");
    edit.add(brighten);
    //All the greyscale visualize methods.
    JMenuItem red = new JMenuItem("Visualize Red Component");
    JMenuItem green = new JMenuItem("Visualize Green Component");
    JMenuItem blue = new JMenuItem("Visualize Blue Component");
    JMenuItem value = new JMenuItem("Visualize With Maximum RBG Value");
    JMenuItem intensity = new JMenuItem("Visualize With The Average Of RBG Values");
    JMenuItem luma = new JMenuItem("Visualize With The Weighted Sum");
    JMenu greyscale = new JMenu("Convert To Greyscale");
    // Add the greyscale options to the edit menu.
    edit.add(greyscale);
    greyscale.add(red);
    greyscale.add(green);
    greyscale.add(blue);
    greyscale.add(value);
    greyscale.add(intensity);
    greyscale.add(luma);
    //Blur and sharpen methods.
    JMenuItem blur = new JMenuItem("Blur Image");
    JMenuItem sharpen = new JMenuItem("Sharpen Image");
    // Add the blur and sharpen options to the edit menu.
    edit.add(blur);
    edit.add(sharpen);
    //Color transformation filter methods
    JMenu colorTransformation = new JMenu("Color Transformation");
    edit.add(colorTransformation);
    JMenuItem sepia = new JMenuItem("Sepia Filter");
    JMenuItem grey = new JMenuItem("Greyscale Filter");
    colorTransformation.add(sepia);
    colorTransformation.add(grey);
    //Help menu bar
    JMenuItem help = new JMenuItem("Help");
    menuBar.add(help);
    setJMenuBar(menuBar);
    //Add action Listener
    load.addActionListener(this);
    save.addActionListener(this);
    brighten.addActionListener(this);
    red.addActionListener(this);
    green.addActionListener(this);
    blue.addActionListener(this);
    value.addActionListener(this);
    intensity.addActionListener(this);
    luma.addActionListener(this);
    blur.addActionListener(this);
    sharpen.addActionListener(this);
    sepia.addActionListener(this);
    grey.addActionListener(this);
    undo.addActionListener(this);
    help.addActionListener(this);
    //Set up the histogram.
    histogram = new Histogram();
    histogram.setPreferredSize(new Dimension(scrollingWindow.getWidth(), 50));
    add(histogram, BorderLayout.SOUTH);

  }

  /**
   * This method is to set the image of the GUIView.
   * @param image BufferedImage to be set.
   */
  @Override
  public void setImage(BufferedImage image) {
    ImageIcon currentImage = new ImageIcon(image);
    this.mainPage.setIcon(currentImage);
    pack();
  }

  /**
   * This method is to add a listener to the GUIView.
   * @param listener ViewListener to be added.
   */
  @Override
  public void addViewListener(ViewListener listener) {
    this.listenersToNotify.add(listener);
  }

  /**
   * This method to ensure the correct action is taken when a button is pressed.
   * @param e ActionEvent to be taken.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      switch (e.getActionCommand()) {
        case "Undo":
          emitUndoEvent();
          break;
        case "Save":
          emitSaveEvent();
          break;
        case "Load":
          emitLoadEvent();
          break;
        case "Change Brightness":
          emitBrightnessEvent();
          break;
        case "Visualize Red Component":
          emitRedTransformationEvent();
          break;
        case "Visualize Green Component":
          emitGreenTransformationEvent();
          break;
        case "Visualize Blue Component":
          emitBlueTransformationEvent();
          break;
        case "Visualize With Maximum RBG Value":
          emitValueTransformationEvent();
          break;
        case "Visualize With The Average Of RBG Values":
          emitIntensityTransformationEvent();
          break;
        case "Visualize With The Weighted Sum":
          emitLumaTransformationEvent();
          break;
        case "Blur Image":
          emitBlurFilterEvent();
          break;
        case "Sharpen Image":
          emitSharpenFilterEvent();
          break;
        case "Sepia Filter":
          emitSepiaFilterEvent();
          break;
        case "Greyscale Filter":
          emitGreyscaleFilterEvent();
          break;
        case "Help":
          emitHelpEvent();
          break;
        default:
          //Default will result in nothing. No other way to press a button not initialized.
          break;
      }
    // Catch any errors that may occur.
    } catch (Exception err) {
      JOptionPane.showMessageDialog(this, "Error: " + err.getMessage(),
              "ERROR", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * This helper function for when the undo button is pressed.
   */
  private void emitUndoEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleUndoEvent();
    }
  }

  /**
   * This is a helper function for when the save button is pressed.
   */
  private void emitSaveEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleSaveEvent();
    }
  }

  /**
   * This is a helper function for when the load button is pressed.
   */
  private void emitLoadEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleLoadEvent(selectLoadLocation());
    }
  }

  /**
   * This helper function helps the user determine which image to load.
   * @return The string of the file path.
   */
  private String selectLoadLocation() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      return selectedFile.getAbsolutePath();
    } else {
      throw new IllegalArgumentException("Invalid file chosen");
    }
  }

  /**
   * This is a helper function for when the brightness button is pressed.
   */
  private void emitBrightnessEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleBrightnessEvent(brightnessValue());
    }
  }

  /**
   * This helper function is to allow the user to pick a brightnessValue.
   *
   * @return Integer of the brightness value.
   */
  private int brightnessValue() {
    //Allow user to choose the brightness value
    String stringBrightness = JOptionPane.showInputDialog(this,
            "Change brightness by: ", null);
    if (stringBrightness != null) {
      //Convert string to an integer.
      return Integer.parseInt(stringBrightness);
      //Error if value not given.
    } else {
      throw new IllegalArgumentException("No value given");
    }
  }

  /**
   * This is a helper function for when the red transformation button is pressed.
   */
  private void emitRedTransformationEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleRedTransformationEvent();
    }
  }

  /**
   * This is a helper function for when the green transformation button is pressed.
   */
  private void emitGreenTransformationEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleGreenTransformationEvent();
    }
  }

  /**
   * This is a helper function for when the blue transformation button is pressed.
   */
  private void emitBlueTransformationEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleBlueTransformationEvent();
    }
  }

  /**
   * This is a helper function for when the value transformation button is pressed.
   */
  private void emitValueTransformationEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleValueEvent();
    }
  }


  /**
   * This is a helper function for when the intensity transformation button is pressed.
   */
  private void emitIntensityTransformationEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleIntensityEvent();
    }
  }

  /**
   * This is a helper function for when the luma transformation button is pressed.
   */
  private void emitLumaTransformationEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleLumaEvent();
    }
  }


  /**
   * This is a helper function for when the blur filter button is pressed.
   */
  private void emitBlurFilterEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleBlurEvent();
    }
  }

  /**
   * This is a helper function for when the sharpen filter button is pressed.
   */
  private void emitSharpenFilterEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleSharpenEvent();
    }
  }

  /**
   * This is a helper function for when the sepia filter button is pressed.
   */
  private void emitSepiaFilterEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleSepiaFilterEvent();
    }
  }

  /**
   * This is a helper function for when the greyscale filter button is pressed.
   */
  private void emitGreyscaleFilterEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleGreyscaleFilterEvent();
    }
  }

  /**
   * This is a helper function for when the help button is pressed.
   */
  private void emitHelpEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleHelpEvent();
    }
  }

  /**
   * This method is to maintain the image of the histogram.
   * @param image Buggered Image that histogram is based off of.
   */
  public void updateHistogram(BufferedImage image) {
    histogram.updateHistogramData(image);
  }
}