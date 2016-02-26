##API Review

##Part 1

1) First, the modular approach to the front end makes the design flexible.  There is one class, called Display, that is in charge of controlling the whole screen.  This Display screens contains features. Each feature is its own class, extending the Module interface.  This means that to add a new feature to the front end all that needs to be done is write a new Module.  This means that each feature, once written is closed.  In other words, if a History and Variable feature are already implemented this code does not need to be altered if a new feature is desired. Second, I think the way in which the user options screen is generated makes the design flexible.  Each Module has its own state, which tracks its properties.  In the options section of the menu bar on the UI display there is the ability to alter any of these modules.  Upon clicking on a given module's options a new screen appears in which the user can alter the Module's state.  This screen is automatically generated based on the Modules state (specifically property types within the state).  This means, to allow the user to alter something about a module's appearance nothing needs to be added to this editing screen.  Rather, this ability through the editing screen can be achieved by simply adding the desired editable property to the module's state.

2) The API hides implementation decisions in two ways.  First, the Display is unaware of each feature's implementation.  This is due to the fact that each feature is its own module and the Display simply rearranges these features.  In addition, each feature is unaware of the implementation of all the other features. Breaking the screen up in this manner ensures more proper encapsulation, allowing features to hide information as they so chose.  Second, encapsulation is achieved between the front and back end through the Controller.  It is the job of the Controller to alter the states of the feature modules on the front end (through properties/bindings).  Thus, there is no true public connection between the front and back end.

3) Errors will be thrown mostly in the back end. It will be the job of the front end to realize these exceptions have been thrown and display the proper message to the user (most likely in the form of a Java FX alert).  These exceptions caught in the back end will relate to proper inputted text structure.  It is the job of the backend to ensure the input matches the specified model requirements. 

4) I think my design is *good* because it is **extendable** and considers well the **open/close** principle.  The code is extendable because it is easy to add a new feature by adding a new module.  The open/close principle is utilized because each module is responsible for rearranging itself, making the Display class closed when a feature needs to be changed.  Changing a feature is done *internally*, which obeys the open/close principle well.  Lastly, to make my code better I want to remove all hard coded values.  This includes abstracting the positioning process done in the Display class.

## Part 2

2) 
a) Changing background color

	User clicks option and the proper Module:
	
	In Display: 
	
	view.update(); 	//This automatically display the stage that allows the user to select the background color
					//Clicking on the color picker will change the view's color property 
	
	In GuiOptionMaker:
	
	get(ColorProperty color);
					//Generates the automated stage
					
	In View
	
	getColorProperty.setSilentListener((a,b,newValue) -> updateColor(newValue));
					//This sets the listener to change the value when it is altered by the user
b) Entering a command

	User hits go:
	
	In UserTextInput:
	
		controller.parse(textArea.getText());

c) Catching an Error 
	
	Show alert from backend

d) Updating the variables

	Plan on adding a TableCell that will allows the user to update the information.  
	This will be driven by a map that holds properties of the current variables.

3) I'm most excited to work on the variables feature.  I want to get it such that the variables update upon input (looking into table view)

4) I am not as excited to work on the view.  I am not as comfortable with Java FX canvas, which I think would be helpful.

