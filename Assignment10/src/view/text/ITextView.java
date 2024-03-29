package view.text;

/**
 * This interface is for view objects.
 */
public interface ITextView {

  /**
   * This method is for the starting of the main.
   * It will provide a more aesthetically text-based user.
   */
  void start();

  /**
   * This method is able to read the command given in the text.
   *
   * @return String of what command is wanted.
   */
  String readCommand();

  /**
   * This method is for displaying the message.
   *
   * @param message String message wanted to be displayed.
   */
  void displayMessage(String message);
}
