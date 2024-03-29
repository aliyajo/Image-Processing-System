package view.text;

import java.util.Objects;
import java.util.Scanner;

import controller.text.TextController;

/**
 * This class is for creating a view for the project.
 */
public class TextView implements ITextView {
  private final Scanner scanner;
  private final TextController controller;

  /**
   * This constructor creates a view object given the input and controller.
   *
   * @param scanner    Scanner object of the input given.
   * @param controller gui object.
   */
  public TextView(Scanner scanner, TextController controller) {
    this.scanner = Objects.requireNonNull(scanner);
    this.controller = Objects.requireNonNull(controller);
  }

  /**
   * This method starts the view.
   */
  @Override
  public void start() {
    displayMessage("Welcome to the Interactive Text Mode for the Image Processing System");
    displayMessage("Enter commands or 'exit' to quit.");
    while (true) {
      displayMessage("Enter a command: ");
      String command = readCommand();
      if (command.equalsIgnoreCase("exit")) {
        displayMessage("Exiting the Image Processing System. Goodbye!");
        break;
      }
      controller.processCommand(command);
    }
  }

  /**
   * This method reads the command.
   */
  @Override
  public String readCommand() {
    return scanner.nextLine();
  }

  /**
   * This method displays the message.
   * @param message message to be displayed.
   */
  @Override
  public void displayMessage(String message) {
    System.out.println(message);
  }
}