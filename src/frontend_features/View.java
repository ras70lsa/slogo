package frontend_features;


import java.util.List;

import backend_slogo_team04.Action;
import constants.DisplayConstants;
import frontend_slogo_team04.GuiUserOption;
import frontend_slogo_team04.State;
import frontend_slogo_team04.TestingState;
import frontend_slogo_team04.VisualTurtle;
import frontend_slogo_team04.VisualizationAction;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import model.Controller;

public class View extends StaticPane implements VisualizationAction {

	private Controller myController;
	private VisualTurtle turtle;
	private TestingState state;
	private boolean penDown = true;
	private boolean isShowing = true;
	private double scaleFactor = 1;

	public View(TestingState state) {
		this.state = state;
		addListeners();
		addCSS("visual_resources/DefaultView.css");
	}

	private void addListeners() {
		state.getColorProperty().addListener((a, b, newValue) -> updateColor(newValue));
	}

	public void draw() {

	}

	public void setUp() {
		// TODO Auto-generated method stub
		Pane newDisplay = new Pane();
		newDisplay.setStyle(state.getBackgroundColor());
		newDisplay.setPrefSize(DisplayConstants.VIEW_WIDTH, DisplayConstants.VIEW_HEIGHT);
		setPane(newDisplay);
		setTurtleImage(new Image(getClass().getClassLoader().getResourceAsStream("/slogo_team04/images/turtle.jpg")));
		add(turtle, getCenterXCor(),  getCenterYCor());
	}


	private void setTurtleImage(Image i) {
		turtle.setImage(i);
		
	}

	public double getMaxWidth() {
		return DisplayConstants.VIEW_WIDTH;
	}

	public double getMaxHeight() {
		return DisplayConstants.VIEW_HEIGHT;
	}

	@Override
	public double forward(double pixels) {
		// TODO Auto-generated method stub
		return 0;
	}


	public double getScaleFactor() {
		return scaleFactor;
	}

	@Override
	public double back(double pixels) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double left(double degrees) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isPenDown() {
		// TODO Auto-generated method stub
		return penDown;
	}

	@Override
	public boolean isShowing() {
		// TODO Auto-generated method stub
		return isShowing;
	}

	public State getState() {
		return state;
	}


	@Override
	public double right(double pixels) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setHeading(double degrees) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double towards(double x, double y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setxy(double x, double y) {
		// TODO Auto-generated method stub
		turtle.setTranslateX(x);
		turtle.setTranslateY(y);
		return 0;
	}

	@Override
	public double penDown() {
		// TODO Auto-generated method stub
		penDown = true;
		return 1;
	}

	@Override
	public double penUp() {
		// TODO Auto-generated method stub
		penDown = false;
		return 0;
	}

	@Override
	public double showTurtle() {
		turtle.showTurtle();
		return 1;
	}

	@Override
	public double hideTurtle() {
		turtle.hideTurtle();
		return 0;
	}

	@Override
	public double home() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double clearScreen() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double xCor() {
		return turtle.getTranslateX() - getCenterXCor();
	}
	
	@Override
	public double yCor() {
		return getCenterYCor() - turtle.getTranslateY();
	}

	public double getCenterXCor(){
		return DisplayConstants.VIEW_WIDTH/2;
	}
	
	public double getCenterYCor(){
		return DisplayConstants.VIEW_HEIGHT/2;
	}
	
	@Override
	public double heading() {
		return turtle.getHeading();
	}
	
	@Override
	public void updateHistory(List<Action> history) {
		// TODO Auto-generated method stub
		
	}

	protected List<Node> getReleventProperties(GuiUserOption factory) {
		return (List<Node>) state.getStageNodes();
	}

}

