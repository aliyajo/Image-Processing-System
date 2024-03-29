import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import controller.gui.GUIController;
import controller.gui.IGUIController;
import controller.text.TextController;
import model.IImageDataBase;
import model.ImageDataBase;
import view.gui.GUIView;
import view.text.TextView;

/**
 * This class if for the main function.
 */
public class IMEMain {
  /**
   * This is the main function.
   *
   * @param args String argument.
   */
  public static void main(String[] args) {
    IImageDataBase model = new ImageDataBase();
    System.out.println("Hello! Welcome to the Image Processing System\n");
    System.out.println("Please select which mode you would like to work on:");
    System.out.println("-text: Opens the interactive text mode");
    System.out.println("-file <path-of-script-file>: Opens the given script file");
    System.out.println(" : Opens the gui mode");
    System.out.println("Which would you like to do: ");

    // Read user input
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine().trim();
    // In the case of the user wanting to run the gui mode
    if (input.isEmpty()) {
      // Launch the gui mode
      SwingUtilities.invokeLater(() -> {
        GUIView view = new GUIView();
        IGUIController controller = new GUIController(model, view);
        controller.run();
      });
    // In the case of the user wanting to run the text mode
    } else if (input.equals("-text")) {
      // Launch the interactive text mode
      TextController controller = new TextController(
              new InputStreamReader(System.in), model, System.out
      );
      TextView view = new TextView(new Scanner(System.in), controller);
      view.start();
    // In the case of the user wanting to run a script file
    } else if (input.startsWith("-file ")) {
      // Launch the script file mode
      String scriptFilename = input.substring(6);
      try {
        FileReader fileReader = new FileReader(scriptFilename);
        TextController controller = new TextController(fileReader, model, System.out);
        controller.start();
        System.exit(0);
      // Catch invalid script file
      } catch (Exception e) {
        System.err.println("Error reading the script file: " + e.getMessage());
      }
    // Catch invalid input
    } else {
      System.err.println("Invalid input\n");
      System.exit(0);
    }
  }
}


