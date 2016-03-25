// This entire file is part of my masterpiece.
// YAQI GUO


package backend.slogo.team04;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import backend.slogo.team04.Actor;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ModelLine;

public class Actors {
	private ListProperty<Actor> actors;

	public Actors() {
		ObservableList<Actor> list = FXCollections.observableArrayList();
		actors = new SimpleListProperty<Actor>(list);
	}

	public ListProperty<Actor> getActors() {
		return actors;
	}

	public void addActor(Actor newActor) {
		actors.add(newActor);
	}

	public boolean elementIsNull(int index) {
		return actors.get(index) == null;
	}

	public void actOnEachElement(Consumer<Actor> action) {
		actors.stream().forEach(action);
	}

	public List<ModelLine> getLines() {
		ArrayList<ModelLine> lines = new ArrayList<ModelLine>();
		for (Actor actor : actors) {
			lines.addAll(actor.getMyLines());
		}
		return lines;
	}

	public List<Boolean> getCurrentActiveTurtles() {
		List<Boolean> currentActive = new ArrayList<Boolean>();
		for (Actor act : actors) {
			currentActive.add(act.getActive().get());
		}
		return currentActive;
	}

	public List<Actor> mapEachElement(UnaryOperator<Actor> action) {
		return actors.stream().map(action).collect(Collectors.toList());
	}

	public List<Actor> filter(Predicate<Actor> action) {
		return actors.stream().filter(action).collect(Collectors.toList());
	}

	public int getSize() {
		return actors.size();
	}

	public void clear() {
		actors.clear();
	}
}
