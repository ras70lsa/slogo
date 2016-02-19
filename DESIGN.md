## Introduction

The intent of this program is to create an interactive IDE that allows users to execute code in the Slogo language. The goals of the project are as follows:

1. Create an easy to use interface that can easily be extended to include new features.  
2. Create a system that is capable of parsing String representing Slogo language into a Java structure in which Eclipse (or an other Java IDE) can interpret and execute. 
3. Off a parsing model that offers easy extendibility for commands/code following the Slogo read-eval-print style.
4. Create a communication system, through the controller, that connects the front and back end in a way that is efficient but still allows the front and back end to maintain their distinct purposes. 

Open: 
*Modules for adding to the display
*Commands (Linear and nonlinear) in the backend

Closed: 
*The main display 
*Parser that generates the Tree command structure 
*Interpreter that executes the tree of commands the parser generates 
*Controller that maintains the communication between the front and back end

## User Interface


## Team Responsibilities

Jonathan Im (jji93):  
* Parser 
* Factory 

Ryan St Pierre (ras70) 
* Interpreter 
* Controller