# Part 1
1. What about your API/design is intended to be flexible?
  * Our backend design is centered around using APIs tied directly to the functionality required by the slogo language itself. To expound further, each "actionable" thing in the slogo language, something that we perceive as requiring a response on the part of the model is associated with the inclusion of a simiarily named method in our ISlogoModelAction interface. 
  * A specific example is the forward command in the slogo language which is supported by the existence of:
  ```java
  public interface ISlogoModelActions {
    ...
    public double forward(double pixels);
    ...
  }
  ```
  * To perform parsing, an interface and recursive trees of nodes. Our interface is called INonLinearCommand and this implements two methods, one to assist in construction of the tree of overall commands as a result of parsing a piece of text, and the other to actually execute the action associatd with the code.
  * Our CommandTreeNode class implements the aforementioned interface and supports the required state for the tree itself. 
  * Thus each slogo command will be associated with a subclass of this command tree node class, which will know how to parse what it needs to be "complete" and the ability to cause this command to be executed by calling the associated method in our ISlogoModelAction interface.
  * Also, as a consequence, we can radically and rapidly redesign our program as needed, we can cut out or add middleman components by simply re-implementing these interfaces properly 
* How is your API/design encapsulating your implementation decisions?
  * Our implementation discussions are now focused around the distinct functionalities required by the language specification, as this is made evident by the unimplemented methods sitting in our abstract interfaces
* What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
  * Logic and userinput exceptions, logic encapsulating non sensical inputs like negative values when positive are requested, and user input exceptions where the user has input something that does not match the expected pattern of inputs, like a command call with a mispelled command name 
* Why do you think your API/design is good (also define what your measure of good is)?
  * I think that our API is good because we are thinking in terms of actual behavoirs in regards to our public interfaces, thus we will classes that are easier for others to implement and use, and that nicely abstract away any internal operations that need to happen in order to get their work done.
  
# Part II
1. Code was demoed
* 4 use cases
  1. Parse user input and run the code on the screen
  * Store the history of code run
  * Most excited to complete the implementation of the parse tree, recursion is always fun
  * Most worried about the extensions in the next sprint, our teams has considered various scenarios that would be difficult to implement, unless we strictly implement a model view controller 
*
*
