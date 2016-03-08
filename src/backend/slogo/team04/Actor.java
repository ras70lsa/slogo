package backend.slogo.team04;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import frontend.features.TurtleShape;
import backend.structures.Pen;
import backend.structures.RGBColor;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import model.ModelLine;
import model.ModelLine.Style;
import properties.ImageProperty;
import utilities.Angle;

/**
 * Class for manipulating the Turtle in the model
 * 

 * @author Ryan St Pierre 
 * @author Sophie Guo

 *
 */

public class Actor {

	public static final String DEFAULT_PATH = "images/slogoTurtle.png";
	private DoubleProperty xLocation;
	private DoubleProperty yLocation;
	private BooleanProperty active;
	private DoubleProperty heading;
	private BooleanProperty penIsDown;
	private BooleanProperty showing;
	//Should be simple image? Decide later
	private ImageProperty image;
	private Stack<ModelLine> myLines;
	private Pen pen;
	private TurtleShape myShape;

	public Actor(double x, double y, double heading, boolean penIsDown) {
		xLocation = new SimpleDoubleProperty();
		xLocation.set(x);
		yLocation = new SimpleDoubleProperty();
		yLocation.set(y);
		showing = new SimpleBooleanProperty();
		showing.set(true);
		this.heading = new SimpleDoubleProperty();
		this.heading.set(heading);
		this.penIsDown = new SimpleBooleanProperty();
		this.penIsDown.set(penIsDown);
		pen = new Pen(new RGBColor(0,0,0));
		image = new ImageProperty();
		image.set(getImage(DEFAULT_PATH));
		active = new SimpleBooleanProperty(true);
		myLines = new Stack<ModelLine>();
	}
	
	public Actor(Actor save) {
		this(save.getXLocation(), save.getYLocation(), save.getHeading(), save.penIsDown.get());
		myLines.addAll(save.getMyLines());
	}

	
	private Image getImage(String path){
		return new Image(getClass().getClassLoader().getResourceAsStream(path));
	}

	public ModelLine forward(double pixels) {
		double angle = getHeadingInRadians();
		ModelLine newLine = setxy(getXLocation() + Math.cos(angle) * pixels,
				getYLocation() + Math.sin(angle) * pixels);
		if (penIsDown.get()) {
			return newLine;		
		}
		return null;
	}
	
	public void setXLocation(double x){
		xLocation.set(x);
	}
	
	public void setYLocation(double y){
		yLocation.set(y);
	}
	
	public ModelLine setxy(double x, double y) {
		if(penIsDown.get()){
			ModelLine newLine = new ModelLine(getXLocation(), getYLocation(), x, y, pen);
			xLocation.set(x);
			yLocation.set(y);
			myLines.add(newLine);
			return newLine;
		}else{
			return null;
		}
	}

	public Image getImageResult(){
		return image.getValue();
	}
	public double getXLocation() {
		return xLocation.get();
	}
	
	public DoubleProperty getXProperty() {
		return xLocation;
	}
	
	public DoubleProperty getYProperty(){
		return yLocation;
	}

	public double getYLocation() {
		return yLocation.get();
	}

	public double getHeading() {
		return heading.get();
	}

	public DoubleProperty getHeadingProperty(){
		return heading;
	}
	
	public void setHeading(double heading) {
		this.heading.set(Angle.mod360(heading));
	}

	public void rotateClockwise(double degrees) {
		this.heading.set(Angle.mod360(this.heading.get() - degrees));
	}

	public void rotateCounterClockwise(double degrees) {
		this.heading.set(Angle.mod360(this.heading.get() + degrees));
	}

	public double getHeadingInRadians() {
		return Math.toRadians(getHeading());
	}

	public void setPenDown(boolean down) {
		// TODO Auto-generated method stub
		penIsDown.set(down);
	}

	public void setShowing(boolean showing) {
		this.showing.set(showing);
		
	}

	public int getPenDown() {
		return (penIsDown.get()) ? 1: 0;
	}
	
	public BooleanProperty getPenDownProperty(){
		return penIsDown;
	}
	
	public boolean getVisible() {
		return showing.get();
	}
	
	public BooleanProperty getVisibileProperty(){
		return showing;
	}

	public void setImageProperty(Image updatedImage) {
		image.set(updatedImage);
	}
	
	public String toString() {
		return  "Actor" + xLocation;
		
	}

	public void toggleActive() {
		active.set(!active.get());
	}
	
	public BooleanProperty getActive() {
		return active;
	}

	public List<ModelLine> getMyLines() {
		return myLines;
	}

	public Image getImage() {
		return image.get();
	}
	
	public void setPenColor(RGBColor color) {
		pen.setPenColor(color);
	}
	
	public void setShape(TurtleShape shape){
		myShape = shape;
		Image newImage = null;
		switch (shape) {
		case RECTANGLE:
			image.set(getImage("images/rectangle.png"));
			break;
		case CIRCLE:
			image.set(getImage("images/circle.png"));
			break;
		case ELLIPSE:
			image.set(getImage("images/ellipse.png"));;
			break;
		default:	
			
		}
	}
	
	public TurtleShape getShape(){
		return myShape;
	}
	
	public void setPenStyle(String selectedItem) {
		pen.setStyle(selectedItem);
	}
	
	public void setPenWidth(double d) {
		pen.setLineWidth(d);
	}
}

