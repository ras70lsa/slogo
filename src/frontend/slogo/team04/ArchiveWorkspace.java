package frontend.slogo.team04;
import java.util.ArrayList;
import java.util.List;

import backend.slogo.team04.Actor;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.ModelLine;
public class ArchiveWorkspace {

	private List<ModelLine> savedLines;
	private ObservableList<Actor> savedActors;
	
	public ArchiveWorkspace(Workspace active) {
		savedLines = new ArrayList<>(active.getModel().getViewInterface().getLines());
		makeSavedActors(active.getModel().getViewInterface().getActorProperty());
	}
	
	private void makeSavedActors(ListProperty<Actor> listProperty) {
		savedActors = FXCollections.observableArrayList();
		for(Actor save: listProperty.get()) {
			savedActors.add(new Actor(save));
		}
	}

	public void print() {
		System.out.println(savedLines);
	}

	public Workspace getNew(WorkspaceManager workspaceManager, Stage mainStage) {
		print();
		Workspace workspace = new Workspace(workspaceManager, mainStage);
		workspace.getModel().getViewInterface().getLines().clear();
		workspace.getModel().getViewInterface().getLines().addAll(savedLines);
		workspace.getModel().getViewInterface().getActorProperty().set(savedActors);
		workspace.getModel().getViewInterface().update();
		return workspace;
		
	}
}
