package frontend.features;


import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public enum TurtleShape {
	RECTANGLE(new Rectangle(20,10)),
	CIRCLE(new Circle(10)),
	ELLIPSE(new Ellipse(10,8));
	
	Shape myShape;

    TurtleShape (Shape shape) {
        myShape = shape;
    }
    
    public Shape getShape() {
        return myShape;
    }
}
