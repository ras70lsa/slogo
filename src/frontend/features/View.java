package frontend.features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import backend.slogo.team04.Actor;
import backend.structures.RGBColor;
import constants.CSSPathConstants;
import constants.DisplayConstants;
import frontend.slogo.team04.State;
import frontend.slogo.team04.VisualTurtle;
import interfaces.slogo.team04.IView;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.ModelLine;
import utilities.Angle;
import visual.states.GuiUserOption;
import visual.states.ViewUIState;

public class View extends StaticPane implements Observer {

	private double scaleFactor = 1;
	public static final double DARKEN_FACTOR = -.75;
	public static final double FADE_FACTOR = .5;
	private ViewUIState visuals;
	private IView model;
	private Pen pen;
	private Line TopLine;
	private Line RightLine;
	private Line LeftLine;
	private Line BottomLine;
	
	public static final double ACTOR_WIDTH = 50;
	public static final double ACTOR_HEIGHT = 60;

	private static final double TURTLE_INITIAL_ANGLE = Angle.HALF_CIRCLE / 2;

	public View(IView model) {
		this.model = model;
		addCSS(CSSPathConstants.DEFAULT_VIEW);
		pen = new Pen(Color.BLACK);
		addSilentListeners();
		model.addObserver(this);
		TopLine = new Line(0, 0, DisplayConstants.VIEW_WIDTH, 0);
		RightLine = new Line(DisplayConstants.VIEW_WIDTH, 0, DisplayConstants.VIEW_WIDTH, DisplayConstants.VIEW_HEIGHT);
		LeftLine = new Line(0, 0, 0, DisplayConstants.VIEW_HEIGHT);
		BottomLine = new Line(0, DisplayConstants.VIEW_HEIGHT, DisplayConstants.VIEW_WIDTH, DisplayConstants.VIEW_HEIGHT);
		
	}

	private void addSilentListeners() {

		model.getBackgroundColor().addListener((a,b,c) -> updateColor(c));
		model.getPenColor().addListener((a,b,c) -> updatePenColor(c)); 

	}

	private void updatePenColor(RGBColor c) {
		pen.setPenColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 1.0));

	}
	
	public double getMaxWidth() {
		return DisplayConstants.VIEW_WIDTH;
	}

	public double getMaxHeight() {
		return DisplayConstants.VIEW_HEIGHT;
	}

	public double getScaleFactor() {
		return scaleFactor;
	}

	public double getCenterXCor(double imageWidth) {
		return DisplayConstants.VIEW_WIDTH / 2 - (imageWidth / 2);
	}

	public double getCenterYCor(double imageHeight) {
		return DisplayConstants.VIEW_HEIGHT / 2 - (imageHeight / 2);

	}

	protected List<Node> getReleventProperties(GuiUserOption factory) {
		List<Node> list = new ArrayList<Node>();
		list.add(factory.get(model.getImageProperty(), "Choose Actor Image"));
		list.add(factory.get(model.getBackgroundColor(), "Background Color"));
		list.add(factory.get(model.getPenColor(), "Pen Color"));
		return list;
	}

	@Override
	public void update(Observable o, Object arg) {
		clear();
	
		for(ModelLine line: model.getLines()) {
			draw(line);
		}
		
		for(Actor actor: model.getActorProperty()) {
			if(actor.getVisible()) {
				draw(actor);
			}
		}
		
	}
	
	private void draw(Actor turtle) {
		
		ImageView view = new ImageView(turtle.getImageProperty().get());
		view.setOnMouseClicked(e -> turtle.toggleActive());
		turtle.getActive().addListener((a,b,current) -> handleImage(view, current));
		view.setFitWidth(ACTOR_WIDTH);
		view.setFitHeight(ACTOR_HEIGHT);
		view.setTranslateX(makeXCorrection(turtle.getXLocation()) - view.getFitWidth()/2);
		view.setTranslateY(makeYCorrection(turtle.getYLocation()) - view.getFitHeight()/2);
		view.setRotate(translateToTurtleAngle(turtle.getHeading()));
		add(view);
		handleImage(view, turtle.getActive().get());
	}
	
	private void handleImage(ImageView view, Boolean active) {
		ColorAdjust adjust = new ColorAdjust();
		if(!active) {
			adjust.setBrightness(DARKEN_FACTOR);
			view.setOpacity(FADE_FACTOR);
		} else {
			adjust.setBrightness(0);
			view.setOpacity(1);
		}
		view.setEffect(adjust);
	}

	private void draw(ModelLine line) {
		drawLine(makeXLineCorrection(line.getStartX()), 
				makeYLineCorrection(line.getStartY()), 
				makeXLineCorrection(line.getEndX()), 
				makeYLineCorrection(line.getEndY()));
	}

	public Line drawLine(double startX, double startY, double endX, double endY) {
		double sX, sY, eX, eY;
		
		if(isInBounds(startX, startY)){
			sX = startX;
			sY = startY;
			eX = endX;
			eY = endY;
		}else{
			double numTimesXWidthRemoved = Math.floor((startX/DisplayConstants.VIEW_WIDTH));
			double numTimesYWidthRemoved = Math.floor((startY/DisplayConstants.VIEW_HEIGHT));
			sX = Math.abs(startX) % DisplayConstants.VIEW_WIDTH;
			sY = Math.abs(startY) % DisplayConstants.VIEW_HEIGHT;
			if(startX<0){
				sX = DisplayConstants.VIEW_WIDTH-sX;
				eX = endX+(numTimesXWidthRemoved * DisplayConstants.VIEW_WIDTH);
			}else{
				eX = endX-(numTimesXWidthRemoved * DisplayConstants.VIEW_WIDTH);
			}
			if(startY<0){
				sY = DisplayConstants.VIEW_HEIGHT-sY;
				eY = endY+(numTimesYWidthRemoved * DisplayConstants.VIEW_HEIGHT);
			}else{
				eY = endY-(numTimesYWidthRemoved * DisplayConstants.VIEW_HEIGHT);
			}
//			eX = endX - (numTimesXWidthRemoved * DisplayConstants.VIEW_WIDTH);
//			eY = endY - (numTimesYWidthRemoved * DisplayConstants.VIEW_HEIGHT);
		}
		
		Line n = new Line();
		n.setStroke(pen.getPenColor());
		
		if(isInBounds(eX, eY)){
			addLine(n, sX, sY, eX, eY);
		}else{
			Line n2 = new Line();
			n2.setStartX(sX);
			n2.setStartY(sY);
			n2.setEndX(eX);
			n2.setEndY(eY);
			double[] originalNewEndPoints = adjustEnd(n2);
			addLine(n, sX, sY, originalNewEndPoints[0], originalNewEndPoints[1]);
			System.out.println("Original Starting X " + sX);
			System.out.println("Original Starting Y " + sY);
			System.out.println("Original Ending X/New Starting X " + originalNewEndPoints[0]);
			System.out.println("Original Ending Y/New Starting Y " + originalNewEndPoints[1]);
			double[] newLine = adjustLine(originalNewEndPoints[0], originalNewEndPoints[1], eX, eY);
			System.out.println("New Ending X " + newLine[2]);
			System.out.println("New Ending Y " + newLine[3]);
			System.out.println("");
			drawLine(newLine[0], newLine[1], newLine[2], newLine[3]);
		}
//		addLine(n, startX, startY, endX, endY);
		return n;
	}
		/**
		 * 
		 * @param startX
		 * @param startY
		 * @param endX
		 * @param endY
		 * @return Array of doubles, index 0 is the x location, 1 = y of the intersection point 
		 */
	
	private boolean checkXBounds(double xLoc){
		return !(xLoc<0 || xLoc> DisplayConstants.VIEW_WIDTH);
	}
	
	private boolean checkYBounds(double yLoc){
		return !(yLoc<0 || yLoc> DisplayConstants.VIEW_HEIGHT);
	}
	
	private boolean checkTop(double yLoc){
		return yLoc>0;
	}
	
	private boolean checkBottom(double yLoc){
		return yLoc<DisplayConstants.VIEW_HEIGHT;
	}
	
	private boolean checkRight(double xLoc){
		return xLoc<DisplayConstants.VIEW_WIDTH;
	}
	
	private boolean checkLeft(double xLoc){
		return xLoc>0;
	}
	
	private boolean isInBounds(double xLoc, double yLoc){
		return (checkXBounds(xLoc)&&checkYBounds(yLoc));
	}
	
	private double makeXLineCorrection(double x){
		return x + DisplayConstants.VIEW_WIDTH /2;
	}
	
	private double makeYLineCorrection(double y){
		return DisplayConstants.VIEW_HEIGHT /2 - y; 
	}
	
	private double[] adjustLine(double sx, double sy, double ex, double ey){
		double original[] = new double[4];
		original[0] = sx;
		original[1] = sy;
		original[2] = ex;
		original[3] = ey;
		
		if(Math.floor(sy) == 0){
			original[1] = DisplayConstants.VIEW_HEIGHT;
			original[3] = ey + DisplayConstants.VIEW_HEIGHT;
		}
		if(Math.floor(sy) == DisplayConstants.VIEW_HEIGHT){
			original[1] = 0;
			original[3] = ey - DisplayConstants.VIEW_HEIGHT;
		}
		if(Math.floor(sx) == 0){
			original[0] = DisplayConstants.VIEW_WIDTH;
			original[2] = ex + DisplayConstants.VIEW_WIDTH;
		}
		if(Math.floor(sx) == DisplayConstants.VIEW_WIDTH){
			original[0] = 0;
			original[2] = ex - DisplayConstants.VIEW_WIDTH;
		}
		return original;
	}
	
	private double[] adjustEnd(Line n){
		double original[] = new double[2];
		original[0] = n.getEndX();
		original[1] = n.getEndY();
		
		if(checkTop(n.getEndY())==false){
			System.out.println("Intercept top");
			return getLineIntersection(n, TopLine);
			
		}
		else if(checkBottom(n.getEndY())==false){
			System.out.println("Intercept bottom");
			return getLineIntersection(n, BottomLine);
		}else if(checkRight(n.getEndX())==false){
			System.out.println("Intercept right");
			return getLineIntersection(n, RightLine);
		}else if(checkLeft(n.getEndX())==false){
			System.out.println("Intercept left");
			return getLineIntersection(n, LeftLine);
		}else{
			return original;
		}
	}
	
	private double[] adjustStart(Line n){
		double original[] = new double[2];
		original[0] = n.getStartX();
		original[1] = n.getStartY();
		original[2] = n.getEndX();
		original[3] = n.getEndY();
		if(checkTop(n.getEndY())==false){
			System.out.println("Intercept top");
			original[1] = original[1] + DisplayConstants.VIEW_HEIGHT;
			original[3] = original[3] + DisplayConstants.VIEW_HEIGHT;
		}
		else if(checkBottom(n.getEndY())==false){
			System.out.println("Intercept bottom");
			original[1] = original[1] - DisplayConstants.VIEW_HEIGHT;
			original[3] = original[3] - DisplayConstants.VIEW_HEIGHT;
			
		}else if(checkRight(n.getEndX())==false){
			System.out.println("Intercept right");
			original[0] = original[0] + DisplayConstants.VIEW_WIDTH;
			original[2] = original[2] + DisplayConstants.VIEW_WIDTH;
			
		}else if(checkLeft(n.getEndX())==false){
			System.out.println("Intercept left");
			original[0] = original[0] - DisplayConstants.VIEW_WIDTH;
			original[2] = original[2] - DisplayConstants.VIEW_WIDTH;
		}
			return original;
	}
	
	private double makeXCorrection(double x) {
		x = x - DisplayConstants.VIEW_WIDTH/2;
		double xd = Math.abs(x)%(DisplayConstants.VIEW_WIDTH);
		if(x<0){
			return DisplayConstants.VIEW_WIDTH-xd;
		}
		else if(x>DisplayConstants.VIEW_WIDTH){
			return xd;
		}
		return x;
	}

	private double makeYCorrection(double y) {
		y = -y - DisplayConstants.VIEW_HEIGHT/2;
		double yd = Math.abs(y)%(DisplayConstants.VIEW_HEIGHT);
		if(y<0){
			return DisplayConstants.VIEW_HEIGHT - yd;
		}
		else if(y>DisplayConstants.VIEW_HEIGHT){
			return yd;
		}
		return y;
	}

	public double translateToTurtleAngle(double angle) {
		return -(angle - TURTLE_INITIAL_ANGLE);
	}
	
	private double[] getLineIntersection(Line line1, Line line2){
		double x1 = line1.getStartX();
		double x2 = line1.getEndX();
		double y1 = line1.getStartY();
		double y2 = line1.getEndY();
		
		double x3 = line2.getStartX();
		double x4 = line2.getEndX();
		double y3 = line2.getStartY();
		double y4 = line2.getEndY();
		
		double[] ans = new double[2];
		
		double det1And2 = det(x1, y1, x2, y2);
	      double det3And4 = det(x3, y3, x4, y4);
	      double x1LessX2 = x1 - x2;
	      double y1LessY2 = y1 - y2;
	      double x3LessX4 = x3 - x4;
	      double y3LessY4 = y3 - y4;
	      double det1Less2And3Less4 = det(x1LessX2, y1LessY2, x3LessX4, y3LessY4);
	      if (det1Less2And3Less4 == 0){
	         // the denominator is zero so the lines are parallel and there's either no solution 
	    	 //(or multiple solutions if the lines overlap) so return null.
	         return null;
	      }
	      double x = (det(det1And2, x1LessX2,
	            det3And4, x3LessX4) /
	            det1Less2And3Less4);
	      double y = (det(det1And2, y1LessY2,
	            det3And4, y3LessY4) /
	            det1Less2And3Less4);
	      ans[0] = x;
	      ans[1] = y;
	      return ans;
		
	}
	
	private double det(double a, double b, double c, double d){
		return a * d - b * c;
	}
	
	public void getOptions() {

		List<Node> properties = getReleventProperties(new GuiUserOption());
		Stage stage = getEmptyStage();
		VBox box = new VBox();
		Group myGroup = (Group) stage.getScene().getRoot();
		myGroup.getChildren().add(box);
		for (Node node : properties) {
			box.getChildren().add(node);
		}
		stage.show();

	}
}

