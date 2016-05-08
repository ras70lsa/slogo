## SLogo Addition

 * Estimation:
 
    * The feature doesn't seem hard to implement, it would probably take around one to two hours maximum. I am familiar with this 
    implementation in terms of he front end border cases and the backend storage of lines. However, I am unfamiliar with the aspect that 
    has to do with commands so it will probably take a while for me too go through the classes and figure it out. 
    * I would need to modify the classes that are associated with commands and defintely View, Viewmodel. I would need to add two more 
    classes, one for each feature (wrap is already implemented). I would also need to add three more classes, one for each command (wrap
    was not a command). The new command will also need to be added in IView and ISlogoModelActioonsExtended.

* Review:
    * It took me around 2-3 hours to implement this feature. 
    * I ended up updating 14 files. In terms of classes related to commands, I updated the BackendtestNullModelActor and CommandFactory to
    set up for parsing. I also added CmdFence, CmdWindow, CmdWrap, for the commmands themselves. In terms of drawing the line itself, I
    added DrawLine as a super class for the newly created classes FenceDrawLine and WindowDrawLine classes, along with the old class
    WrapAroundDrawLine. I also ended ucreating a new interface call IMyDrawer to better oraganize these classes. I also added the new 
    commands into IView and ISlogoModelActioonsExtended.
    * I pretty much got it complete right on the first try.

* Analysis:
   * Our original code was pretty extendible and thus it was fairly easy to implement these additional features. When I first wrote 
   the wrap around class, I already had the idea of considering multiple border cases in my mind.
   * However, now that when I revisied the code again, I notice many flaws within the original code. The View class and View Model class
   is a mess in the sense that there are so many methods that can be regrouped and refactored out. In addition, the drawing methods
   could have been better passed around and organized as interfaces.
   * The backend classes regarding commands took me a while to figure out what's going on. The classes regarding line drawing is relatively
   easy to underestand. However, this could be improved by better documentation so others would be able to use our code easily. 
