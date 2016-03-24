API Changes
===

## Sprint 2

Our initial APIs were inadequate. At the time we did not fully understand the full relationship, communication, needed from the front to backend. In the original design we included a very simple interface that had a string parsing method that initially served to be the communication between the view input and the backend execution. In addition we had an interface that allowed the back end tree executor to call simple methods on the model, which contained the turtle. It is clear this level of communication is not sufficient.

We upgraded our APIs to include far more numerous interfaces/APIs. First, we split the backend (model) into smaller components (sub-models). We created individual model classes each responsible for tracking a different part of the model. For example, there are submodels for the variables, user defined commands, and view (turtle) model. We expanded to have an interface for communication between each front-end feature and its necessary components of the backend (necessary submodel). For example we created an interface called IVariables. The VariableModel implements this interface. The VariableFeature (front-end view) takes in the IVariables interface and is able to query the IVariableModel for the information needed for display. All of these interfaces have the proper functions for the front-end views to access the proper properties in the model.

In addition, we made the interface between the turtle view feature in the front-end and the view-model more robust. We decided to give the turtle model more robustness in the backend, holding instances of actors and non-UI specific lines (defined in our own class). We did this to better separate the model from the view and allow for easy swapping of the UI of choice (JavaFX for Swing for example). This move forced us to create an interface/API for this interaction (IView) that describes what the turtle view can ask for from the turtle model in order to properly render.

## Sprint 3

Change: ISlogoModelActionExtended

Reason: In the first sprint we did not consider the importance of Turtle ID. This information was lost in our current communication construct. To handle this change we needed to alter the API to allow the Interpreter access to the active turtles (this could no longer be handled solely through the model) and tell the model the ID of the turtle that needs to execute. In other words, our previous API was lacking strong enough communication between the Interpreter and the model.