package view.gui;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.JPanel;

/**
 * This class is to create a histogram.
 * It extends the JPanel.
 */
public class Histogram extends JPanel {
  private int[] redHistogram;
  private int[] greenHistogram;
  private int[] blueHistogram;
  private int[] intensityHistogram;

  /**
   * This creates a histogram object.
   */
  public Histogram() {
    redHistogram = new int[256];
    greenHistogram = new int[256];
    blueHistogram = new int[256];
    intensityHistogram = new int[256];
  }


  /**
   * This method is to update the histogram data of the given image.
   * @param image Buffered Image which we are measuring the channels and intensity.
   */
  public void updateHistogramData(BufferedImage image) {
    // Reset histograms
    Arrays.fill(redHistogram, 0);
    Arrays.fill(greenHistogram, 0);
    Arrays.fill(blueHistogram, 0);
    Arrays.fill(intensityHistogram, 0);

    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        int rgb = image.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        int intensity = (red + green + blue) / 3;
        //Increment the individual section based on when it encounters the channel.
        redHistogram[red]++;
        greenHistogram[green]++;
        blueHistogram[blue]++;
        intensityHistogram[intensity]++;
      }
    }

    //Updates the current histogram panel.
    repaint();
  }

  /**
   * This method is to paint the histogram graph.
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    int barWidth = getWidth() / 256;
    int maxFrequency = getMax();

    for (int i = 0; i < 256; i++) {
      int redHeight = scaleValue(redHistogram[i], maxFrequency);
      int greenHeight = scaleValue(greenHistogram[i], maxFrequency);
      int blueHeight = scaleValue(blueHistogram[i], maxFrequency);
      int intensityHeight = scaleValue(intensityHistogram[i], maxFrequency);

      //Draws the bars for each of the channels & intensity.
      g.setColor(Color.RED);
      g.fillRect(i * barWidth, getHeight()
              - redHeight, barWidth, redHeight);

      g.setColor(Color.GREEN);
      g.fillRect(i * barWidth, getHeight()
              - greenHeight - redHeight, barWidth, greenHeight);

      g.setColor(Color.BLUE);
      g.fillRect(i * barWidth, getHeight()
              - greenHeight - redHeight - blueHeight, barWidth, blueHeight);

      g.setColor(Color.BLACK);
      g.fillRect(i * barWidth, getHeight()
              - greenHeight - redHeight - blueHeight - intensityHeight, barWidth, intensityHeight);
    }
  }

  /**
   * This helper function makes sure that the lines don't go above the highest value.
   * @return
   */
  private int getMax() {
    int maxFrequency = 0;
    for (int i = 0; i < 256; i++) {
      maxFrequency = Math.max(maxFrequency,
              Math.max(intensityHistogram[i], Math.max(redHistogram[i],
                      Math.max(greenHistogram[i], blueHistogram[i]))));
    }
    return maxFrequency;
  }

  /**
   * This private function is to maintain the height of the bars in the histogram.
   * @param value integer value of the histogram bar.
   * @param maxValue maximum value of all of them.
   * @return the highest value the height should be.
   */
  private int scaleValue(int value, int maxValue) {
    return (int) ((double) value / maxValue * getHeight());
  }
}