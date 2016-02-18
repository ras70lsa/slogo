
#BackEnd:
Jonathan Im (jji93)
Ryan St.Pierre (ras70)

Below is the internal backend API:

![header](images/backEndInternalAPI2.png "Backend Internal API")

The external API will be driven by the controller 

![header](images/backEndExternal2.png "Backend External API")


#Use Cases: 

1. The user types 'fd 50' in the command window, sees the turtle move in the display window leaving a trail, and has the command added to the environment's history.

In GamePane:

	Controller.parse(Input) //where input is the String receive from the user
	updateHistory() {
		convertStackToListofText(controller.getHistory);
	}

In Controller:

	parse(String input) {
		Collection<LinearCommand> commands = Parse.parse(input);
		int counter;
		for (LinearCommand command: commands) {
			counter = counter + command.execute();
		}
		Action action = new Action(commands, GUI.getTurleAttributes(), counter);
		history.add(action);
	}

In Parser: 

	parse(String input){ 
	
		return Interpretter.flatten(makeTree(input));
	
	}
	
2. The user types '50 fd' in the command window and sees an error message that the command was not formatted correctly.

In Controller:

	try{ 
		Parser.parse(input);
	} catch(CommandException e) {
		GamePane.handleError(e.message());
	}
	
In GamePane

	handleError(String input) {
		showAlert(input);
	}
	
In Backend: throw exception when necessary

3. The user types 'pu fd 50 pd fd 50' in the command window and sees the turtle move twice (once without a trail and once with a trail)

The same flow will hold with that in case 1.  Whether or not the trail is drawn is determined by the LinearCommands the Controller is given. Specified linear commands upon execution can changed the stored state in the Controller of whether or not the pen is up or done. Every time the front end goes to draw they will access this stored value in the Controller to determine whether or not the pen is up or down.

4. The user changes the color of the environment's background.

	Color color = startColorChooser();
	setBackground(color);







