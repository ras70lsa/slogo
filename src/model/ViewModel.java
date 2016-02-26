package model;

import java.util.Observer;

import backend_slogo_team04.Action;
import backend_slogo_team04.Actor;
import constants.DisplayConstants;

import java.util.List;
import java.util.Observable;

import interfaces_slogo_team04.ISlogoModelActions;
import interfaces_slogo_team04.IView;

public class ViewModel extends Observable implements IView, ISlogoModelActions {
	private Actor turtle;
	private boolean penDown = true;
	private boolean isShowing = true;
	
	
	@Override
	public double forward(double pixels) {
		// TODO Auto-generated method stub
		
		setChanged();
		notifyObservers();
		return 0;
	}

	@Override
	public double back(double pixels) {
		// TODO Auto-generated method stub
		setChanged();
		
		notifyObservers();
		return 0;
	}

	@Override
	public double left(double pixels) {
		// TODO Auto-generated method stub
		setChanged();
		
		notifyObservers();
		return 0;
	}

	@Override
	public double right(double pixels) {
		// TODO Auto-generated method stub
		setChanged();
		
		notifyObservers();
		return 0;
	}

	@Override
	public double setHeading(double degrees) {
		// TODO Auto-generated method stub
		setChanged();
		
		notifyObservers();
		return 0;
	}

	@Override
	public double towards(double x, double y) {
		// TODO Auto-generated method stub
		setChanged();
		
		notifyObservers();
		return 0;
	}

	@Override
	public double setxy(double x, double y) {
		// TODO Auto-generated method stub
		setChanged();
		return 0;
	}

	@Override
	public double penDown() {
		setChanged();
		
		notifyObservers();
		return 1;
	}

	@Override
	public double penUp() {
		// TODO Auto-generated method stub
		setChanged();
		
		notifyObservers();
		return 0;
	}

	@Override
	public double showTurtle() {
		setChanged();
		isShowing = true;
		notifyObservers();
		return 0;
	}

	@Override
	public double hideTurtle() {
		isShowing = false;
		setChanged();
		notifyObservers();
		return 0;
	}

	@Override
	public double home() {
		// TODO Auto-generated method stub
		setChanged();
		
		notifyObservers();
		return 0;
	}

	@Override
	public double xCor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double yCor() {
		return turtle.getYLocation();
	}

	@Override
	public double heading() {
		return turtle.getHeading();
	}

	@Override
	public double isPenDown() {
		return (penDown)? 1:0;
	}

	@Override
	public double isShowing() {
		return (isShowing)? 1:0;
	}

	@Override
	public List<Action> getHistory() {
		// TODO Auto-generated method stub
		return null;
	}

}
