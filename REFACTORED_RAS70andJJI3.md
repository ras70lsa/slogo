##File Option

We added a Map that contains all of the MenuItem titles and EventHandlers to the top of the class.  This way it is easier for the reader to identify the main component of the class and add a MenuItem easily if desired.

## Menu Feature

Tied the commonality of all menus into one super class called MenuFeature.  This is able to create menuItems and has access to the resource bundle being used.

Please see the code and comments in the menu.contents package.  We refactored the one SlogoMenu class into 4 classes. This made sense to provide each sub-menu greater functionality and greater extensibility.