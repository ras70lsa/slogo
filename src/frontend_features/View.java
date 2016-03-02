package frontend_features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import Utilities.Angle;
import backend_slogo_team04.Actor;
import backend_structures.RGBColor;
import constants.DisplayConstants;
import frontend_slogo_team04.State;
import frontend_slogo_team04.VisualTurtle;
import interfaces_slogo_team04.IView;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import visual_states.GuiUserOption;
import visual_states.ViewUIState;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.ModelLine;

public class View extends StaticPane implements Observer {

	private double scaleFactor = 1;
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
		addCSS("visual_resources/DefaultView.css");
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
		if(model.getActor().getVisible()) {
			draw(model.getActor());
		}
		for(ModelLine line: model.getLines()) {
			draw(line);
		}
		
	}
	
	private void draw(Actor turtle) {
		
		ImageView view = new ImageView(turtle.getImageProperty().get());
		view.setFitWidth(ACTOR_WIDTH);
		view.setFitHeight(ACTOR_HEIGHT);
		view.setTranslateX(makeXCorrection(turtle.getXLocation()) - view.getFitWidth()/2);
		view.setTranslateY(makeYCorrection(turtle.getYLocation()) - view.getFitHeight()/2);
		view.setRotate(translateToTurtleAngle(turtle.getHeading()));
		add(view);
	}
	
	private void draw(ModelLine line) {
		drawLine(makeXLineCorrection(line.getStartX()), 
				makeYLineCorrection(line.getStartY()), 
				makeXLineCorrection(line.getEndX()), 
				makeYLineCorrection(line.getEndY()));
	}

	public Line drawLine(double startX, double startY, double endX, double endY) {
//		double sX, sY, eX, eY;
//		
//		if(isInBounds(startX, startY )){
//			sX = startX;
//			sY = startY;
//			eX = endX;
//			eY = endY;
//		}else{
//			double numTimesXWidthRemoved = Math.floor((startX/DisplayConstants.VIEW_WIDTH));
//			double numTimesYWidthRemoved = Math.floor((startY/DisplayConstants.VIEW_HEIGHT));
//			sX = startX % DisplayConstants.VIEW_WIDTH;
//			sY = startY % DisplayConstants.VIEW_HEIGHT;
//			eX = endX - (numTimesXWidthRemoved * DisplayConstants.VIEW_WIDTH);
//			eY = endY - (numTimesYWidthRemoved * DisplayConstants.VIEW_HEIGHT);
//		}
		
		Line n = new Line();
		n.setStroke(pen.getPenColor());
		
//		if(isInBounds(eX, eY)){
////			addLine(n, startX, startY, endX, endY);
//			addLine(n, sX, sY, eX, eY);
//		}else{
//			double[] newStartPoints = getCollisionPointWithBoundaryForLine(sX, sY, eX, eY);
////			addLine(n, startX, startY, newStartPoints[0], newStartPoints[1]);
//			addLine(n, sX, sY, newStartPoints[2], newStartPoints[3]);
//			drawLine(newStartPoints[0], newStartPoints[1], newStartPoints[4], newStartPoints[5]);
//		}
		addLine(n, startX, startY, endX, endY);
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
	private double[] getCollisionPointWithBoundaryForLine(double startX, double startY, double endX, double endY ){
		double[] loc = new double[6];
		loc[0] = adjustStartX(endX)[0];
		loc[1] = adjustStartY(endY)[0];
		loc[2] = adjustEndX(endX) + calcX(endY);
		loc[3] = adjustEndY(endY);
		loc[4] = adjustStartX(endX)[1];
		loc[5] = adjustStartY(endY)[1];
		return loc;
	}
	
	private boolean checkXBounds(double xLoc){
		return !(xLoc<0 || xLoc> DisplayConstants.VIEW_WIDTH);
	}
	
	private boolean checkYBounds(double yLoc){
		return !(yLoc<0 || yLoc> DisplayConstants.VIEW_HEIGHT);
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
	
//	private double adjustX(double x){
//		if(x<0){
//			return DisplayConstants.VIEW_WIDTH+x;
//		}else if(x>DisplayConstants.VIEW_WIDTH){
//			return x-DisplayConstants.VIEW_WIDTH;
//		}else{
//			return x;
//		}
//	}
//	
//	private double adjustY(double y){
//		if(y<0){
//			return DisplayConstants.VIEW_HEIGHT+y;
//		}else if(y>DisplayConstants.VIEW_HEIGHT){
//			return y-DisplayConstants.VIEW_HEIGHT;
//		}else{
//			return y;
//		}
//	}
	
	private double[] adjustStartX(double x){
		double[] line = new double[2];
		if(x<0){
			line[0] = DisplayConstants.VIEW_WIDTH;
			line[1] = x+DisplayConstants.VIEW_WIDTH;
			return line;
		}else if(x>DisplayConstants.VIEW_WIDTH){
			line[0] = 0;
			line[1] = x-DisplayConstants.VIEW_WIDTH;
			return line;
		}else{
			line[0] = x;
			line[1] = x;
			return line;
		}
	}
	
	private double[] adjustStartY(double y){
		double[] line = new double[2];
		if(y<0){
			line[0] = DisplayConstants.VIEW_HEIGHT;
			line[1] = y + DisplayConstants.VIEW_HEIGHT;
			return line;
		}else if(y>DisplayConstants.VIEW_HEIGHT){
			line[0] = 0;
			line[1] = y - DisplayConstants.VIEW_HEIGHT;
			return line;
		}else{
			line[0] = y;
			line[1] = y;
			return line;
		}
	}
	
	private double adjustEndX(double x){
		if(x<0){
			return 0;
		}else if(x>DisplayConstants.VIEW_WIDTH){
			return DisplayConstants.VIEW_WIDTH;
		}else{
			return x;
		}
	}
	
	private double adjustEndY(double y){
		if(y<0){
			return 0;
		}else if(y>DisplayConstants.VIEW_HEIGHT){
			return DisplayConstants.VIEW_HEIGHT;
		}else{
			return y;
		}
	}
	
	private double calcX(double y){
		double hyp = y/Math.sin(model.getActor().getHeadingInRadians());
		return Math.sqrt(hyp*hyp-y*y);
	}

	private double makeXCorrection(double x) {
		if(x<(-DisplayConstants.VIEW_WIDTH/2)){
			return DisplayConstants.VIEW_WIDTH + translateToGridX(x);
		}
		else if(x>DisplayConstants.VIEW_WIDTH/2){
			return x-DisplayConstants.VIEW_WIDTH/2;
		}
		return x + DisplayConstants.VIEW_WIDTH /2;
	}

	private double makeYCorrection(double y) {
		if(y<(-DisplayConstants.VIEW_HEIGHT)/2){
			return -(y+DisplayConstants.VIEW_HEIGHT/2);
		}
		else if(y>DisplayConstants.VIEW_HEIGHT/2){
			return DisplayConstants.VIEW_HEIGHT-(y-DisplayConstants.VIEW_HEIGHT/2);
		}
		return DisplayConstants.VIEW_HEIGHT /2 - y; 
	}
	
	private double translateToGridX(double x){
		return x + DisplayConstants.VIEW_WIDTH/2;
	}
	
	private double translateToGridY(double y){
		return DisplayConstants.VIEW_HEIGHT/2 -y ;
	}
	

	public double translateToTurtleAngle(double angle) {
		return -(angle - TURTLE_INITIAL_ANGLE);
	}
	
	private double[] getLineIntersction(Line line1, Line line2){
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

