#Slogo Maintenance : JJI3
Backend extensions
Estimation: before looking at the old code:
how long do you think it will take you to complete this new feature? 3-4 hours
how many files will you need to add or update? Why? ~8 , one for each new language element (3), one to update the main recursive class to be aware of these new language elements (1), one to update the property files of the language to key conversions (1), one new class for each new draw mode in the model class (3)

Review: after completing the feature:
how long did it take you to complete this new feature? 2 hours and 22 minutes

how many files did you need to add or update? Why? 23 files were changed or added
I remembered where most of the changes would need to take place, I simply forgot about the major back front api (our model actions interface), and the language property changes. I also ended up removing/ canabalizng code that existed to implement the wrap feature as it was and was forced to call my new code in the view class itself. Overall, I predicted most of the changes. The backend test null class is simply a dummy null class for independent backend testing that implements the frontend backend api using print statements. Updated it as it broke when I added the new language calls to the interface. 
The following files were changed 
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   src/backend/slogo/team04/BackendTestNullModelActor.java
	modified:   src/backend/slogo/team04/CommandFactory.java
	modified:   src/frontend/features/View.java
	modified:   src/frontend/features/WrapAroundDrawLine.java
	modified:   src/interfaces/slogo/team04/ISlogoModelActionsExtended.java
	modified:   src/interfaces/slogo/team04/IView.java
	modified:   src/model/ViewModel.java
	modified:   src/resources/languages/Chinese.properties
	modified:   src/resources/languages/English.properties
	modified:   src/resources/languages/French.properties
	modified:   src/resources/languages/German.properties
	modified:   src/resources/languages/Italian.properties
	modified:   src/resources/languages/Portuguese.properties
	modified:   src/resources/languages/Russian.properties
	modified:   src/resources/languages/Spanish.properties

Untracked files:
  (use "git add <file>..." to include in what will be committed)

	src/backend/slogo/team04/CmdFence.java
	src/backend/slogo/team04/CmdWindow.java
	src/backend/slogo/team04/CmdWrap.java
	src/model/ActorDrawer.java
	src/model/FenceDrawer.java
	src/model/IActorDrawer.java
	src/model/WindowDrawer.java
	src/model/WrapDrawer.java
did you get it completely right on the first try?
The language extension was done properly first try, as that was my domain during the initial implementation, the addition of the new screen turtle behavior however took longer. 
Analysis: what do you feel this exercise reveals about your project's design and documentation?
Our design was very good, the only problems exist in poor documentation. I was forced to add some additional classes and interfaces to help structure how we stored different notions of wrapping, but that was to be expected as that was not initially designed as a modular feature. I was forced to stumble around using some Eclipse search tools to find the classes that I needed to modify. 
was it as good (or bad) as you remembered?
The code is about as good as I remembered it to be. Adding the new language commands in the parser took literally 10 minutes. Simply required creating new command tree classes, implementing the call on the newly added interface
what could be improved?
I noticed inconsistencies in style and implementation standard (lack of interface usage) when going through classes in the front end that I know were built by different people. Need to make sure there are more stringent code reivews before acceptance into the main code base to ensure that things are uniform and up to code. 
what would it have been like if you were not familiar with the code at all?
Was at first lost going through the front end classes, I could only imagine what trying to understand the recursive parsing classes in the back end would be like without a guide. Need to write better javadoc comments over interfaces, and in core classes themselves if they are sufficiently complex, I think someone trying to extend my backend parsing classes without such a guide would have a very hard time indeed. 