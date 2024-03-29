package controller.text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import commands.BrightenCommand;
import commands.ICommand;
import commands.LoadCommand;
import commands.SaveCommand;
import commands.filtered.BlurFilterCommand;
import commands.filtered.GreyscaleFilterCommand;
import commands.filtered.SepiaFilterCommand;
import commands.filtered.SharpenFilterCommand;
import commands.greyscalecommands.BlueComponentCommand;
import commands.greyscalecommands.GreenComponentCommand;
import commands.greyscalecommands.IntensityComponentCommand;
import commands.greyscalecommands.LumaComponentCommand;
import commands.greyscalecommands.RedComponentCommand;
import commands.greyscalecommands.ValueComponentCommand;
import model.IImageDataBase;

/**
 * This class creates a gui object.
 * It implements the ITextController interface.
 */
public class TextController implements ITextController {
  private final Readable input;
  private final IImageDataBase model;
  private final Appendable appendable;
  private Map<String, ICommand> commandMap;

  /**
   * This constructor creates a controller object.
   *
   * @param input      input given to the controller from text input.
   * @param model      ImageDataBase data that it uses.
   * @param appendable what will be printed out.
   */
  public TextController(Readable input, IImageDataBase model, Appendable appendable) {
    this.input = Objects.requireNonNull(input);
    this.model = Objects.requireNonNull(model);
    this.appendable = Objects.requireNonNull(appendable);
    this.commandMap = new HashMap<String, ICommand>();
    this.commandMap.put("brighten", new BrightenCommand());
    this.commandMap.put("value-component", new ValueComponentCommand());
    this.commandMap.put("red-component", new RedComponentCommand());
    this.commandMap.put("green-component", new GreenComponentCommand());
    this.commandMap.put("blue-component", new BlueComponentCommand());
    this.commandMap.put("intensity-component", new IntensityComponentCommand());
    this.commandMap.put("luma-component", new LumaComponentCommand());
    this.commandMap.put("blur", new BlurFilterCommand());
    this.commandMap.put("sharpen", new SharpenFilterCommand());
    this.commandMap.put("sepia", new SepiaFilterCommand());
    this.commandMap.put("greyscale", new GreyscaleFilterCommand());
    this.commandMap.put("save", new SaveCommand());
    this.commandMap.put("load", new LoadCommand());
  }

  /**
   * This method writes out the message given to it.
   *
   * @param message String message to be printed out.
   */
  private void write(String message) {
    try {
      this.appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Writing to the appendable failed");
    }
  }

  @Override
  public void start() {
    Scanner scanner = new Scanner(this.input);

    while (scanner.hasNext()) {
      String command = scanner.nextLine();
      processCommand(command);
    }
  }

  /**
   * This is a helper function that is able to process the command given.
   *
   * @param command String command.
   */
  public void processCommand(String command) {
    Scanner scanner = new Scanner(command);

    if (!scanner.hasNext()) {
      write("Invalid command.");
      return;
    }

    String action = scanner.next();

    ICommand commandToRun = this.commandMap.getOrDefault(action, null);
    if (commandToRun == null) {
      write("Invalid command.");
      return;
    }

    try {
      commandToRun.run(scanner, this.model);
    } catch (IllegalStateException e) {
      write(e.getMessage());
    }
  }
}