Image-Processing-System.js

## Project Status ##
Completed

Authors: 
  - Aliya Jordan

## Project Description ##
This project is an image processing application. This program has text-based, script-based, 
and interfactive GUI-based user interfaces to run the program. It performs image processing operations onto the given loaded image based on the given command from the user. It is able to support
multiple image file formats (ie. PNG, JPEG, ppm , etc). These operations include: filtering transformations, 
color transformations, and brightness transformations.

## Built with ##
- IntelliJ IDEA v.2023.3.6

## Usage Overview ##
Once started with running the IMEMain.java file, the program will and the first thing it will ask it which program you want to run the Image Processing System.
  ![image](https://github.com/aliyajo/Image-Processing-System/assets/133428097/6b4e01c1-e301-47a8-8bdd-77436cfabbe9)

> -text: To use the interactive command-line text-mode.  

![image](https://github.com/aliyajo/Image-Processing-System/assets/133428097/f2daf275-360e-4623-a544-742928aecd0e)

  - brighten <increment> <filename> : This command increases the brightness of the entire image.
  - value-component <fileName> <newImageName>: This command converts the image to greyscal using the value component.
  - red-component<fileName> <newImageName>: This command converts the image to greyscale using the red component.
  - green-component<fileName> <newImageName>: This command converts the image to greyscale using the green component.
  - blue-component<fileName> <newImageName>: This command converts the image to greyscale using the blue component.
  - intensity-component<fileName> <newImageName>: This command converts the image to greyscale using the intensity component.
  - luma-component<fileName> <newImageName>: This command converts the image to greyscale using the luma component.
  - sharpen <fileName> <newImageName>: This command sharpens the image.
  - sepia <fileName> <newImageName>: This command sepia filters the image.
  - greyscale <fileName> <newImageName>: This command greyscale filters the image.
  - blur <fileName> <newImageName>: This command blurs an image.
  - load <filePath > <filename>: This command loads an image from a file in PPM format. Replace <filename> with the name of the file to load.
  - save <destinationPath> <filename>: This command loads an image from a file in PPM format. Replace <filename> with the name of the file to load.

> -file <script filePath> : To run a script text file

  This allows you to load up a text file that has commands line-by-line. It will
  execute the commands and exit the program. To make sure it worked, look
  at where you wanted the altered image to be saved at.

> space/pressing enter without input: To use GUI mode

![image](https://github.com/aliyajo/Image-Processing-System/assets/133428097/6c884cc5-1bdf-4ac2-b2b6-5af452549a49)

  Refer to the USEME file that goes through how to use this mode.
  
  USEME: https://github.com/aliyajo/Image-Processing-System/blob/03f540c61dba28849fcc5e258746e3626d0d532a/USEME.pdf 

## Design ##
This project has a MVC design pattern.
Because of this, this was how the project was broken down to ensure the program ran the efficiently. 
- Implementing the model
   This establishes the image database. This serves as the cache of the images being altered, saved, and loaded.
   Establishes a pixel database. This serves as the database where the pixel data for the image is being stored.
  
- Implementing the view
  This establishes what the user is seeing.
  Here it is able to show the user two different forms: text and gui based
  It efficiently communicates the commands given to it to the controller for processing.
  
- Implementing the controller
  This ensures that the commands for altering an image is established.

  Able to interpret commands from both a gui and text based input.

When it came to implementing the different forms of interacting with this (text, file, or gui based), it involved coding for these seperately. Because of this, the project was broke down as such when it came to addressing the different formats of running the program.
 - Implementing command-line input
    - Establishing a view for the text-based commands.
    - Establishing a controller for the text-based commands.

 - Implementing file-based input
    - Ensuring that the file given is read from the main function, and then calls the text-based interpretation of the commands in the file

  - Implementing gui-based input
     - Ensuring a view for the gui-based commands.
     - Ensuring a controller for the text-based commands
   

## Citations ##
The image used in the USEME, included in the res folder for testing (Godzilla.jpg)

https://www.peakpx.com/en/hd-wallpaper-desktop-aktix 

  

