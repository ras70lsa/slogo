Ryan St.Pierre
ras70

Estimation 
===

I think it will take me an hour and I will have to modify 5 classes and add 2. I know I will have to add the view class and estimate I will have to add another class where something is lacking.  I assume I will have to modify classes to give the accesses I need. 

Review
===

It only took me 30 minutes to implement this feature. 

I only needed to modify **1** class (Display.java).  This modification was a two line addition, adding my new View feature to the display.  
In addition to modifying 1 class I altered one property file (adding one line to the EnglishUI language properties file).

I only needed to add **1** class, the *TurtleImageFeature* that drives the view.  This view class includes the ListView display of all the turtle images.  An interface already existed to give me proper access to the list of actors in the backend (IView) which I passed to my view.  In addition to this class I created 2 other classes, ImageCell.java and FileFactory.java.  The ImageCell is the cell factory for the ListView in the TurtleImageFeature class, changing the image of the item, which in this case is the Actor class (which has the turtle image). The FileFactory.java class is a couple method class that makes grabbing the image from the FileChooser easier.  This removes JavaFx dependency from the ImageCell class.  Lastly, I added a properties file to define the filters that the FileChooser in the FileFactory class should use.  

All the exception classes I needed were already in place.   

I was able to get it right on the first try.  I spent a lot of time designing a modular approach to the front-end and designing most of the features.  Thus, I was comfortable and able to get it right on the first try. 

Analysis
===

Extending my code was as easy as I remember.  I remember it was very easy to add a small amounts of lines to the Display class to add a new feature by adding a view class and putting it in the Accordion.  In addition, I remember creating the TitlePaneFeature class, which most views in the project extend and which I extended to add this feature.  This class gives generic functionality to make defining a new view pretty bare.  Doing such just requires adding the right access. Also, I remember that using properties and observable lists made it easy to add features, since a view, controller, and communication doesn't have to be defined.   Rather, the updating and communication is gotten "for free" by tapping into the right backend list.  Thus, to create the feature I just had to identify what list I wanted to watch and then focus solely on the view code. 

 However, given all this, I was still surprised how easy it was.  
 
There is still lots of room for this code to be improved.  I was still forced to add the new view feature to the Display class in a hard-coded manner.  I had to go the the 'makeLeft()' method and add the view there.  I think this project would benefit greatly from data driven design which was explored in great detail in my VoogaSalad project.  Properties files should be used to define the display view.  If this was in place I could have added my new feature (view) to a properties file and automatically have it appear rather then add it in the code itself. This would follow the open-close principle more closely.  I think we nailed the open for extension.  However, the close on modification is not complete, since a couple line change needed to take place in the Display class. 

If I was not familiar with the code at all I think it would have taken much more time.  I wouldn't have known to add the view feature class in the display class.  However, I do think my package names are somewhat helpful.  For example, I was able to look at other feature implementations, which was extremely helpful, by finding the 'frontend.features' package.  The 'exception' package also made me easily find previously defined exceptions and prevented me from re-writing pre-existing code. 