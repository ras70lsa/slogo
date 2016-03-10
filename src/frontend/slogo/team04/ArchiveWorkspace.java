package frontend.slogo.team04;
import java.util.ArrayList;

import java.util.List;

import backend.slogo.team04.Actor;
import backend.slogo.team04.INonLinearCommand;
import backend.slogo.team04.Variable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.Model;
import model.ModelLine;
public class ArchiveWorkspace {

	private List<ModelLine> savedLines;
	private ObservableList<Actor> savedActors;
	private ListProperty<Variable> savedVariables;
	private ListProperty<String> savedCommands;
	private ListProperty<INonLinearCommand> savedCommandNode;
	private ListProperty<String> savedHistory;
	
	public ArchiveWorkspace(Workspace active) {
		savedLines = new ArrayList<>(active.getModel().getViewInterface().getLines());
		makeSavedActors(active.getModel().getViewInterface().getActorProperty());
		makeSavedHistory(active.getModel());
	}
	
	private void makeSavedHistory(Model model) {
		savedVariables = new SimpleListProperty<>(FXCollections.observableArrayList());
		savedVariables.addAll(model.getExecutionState().getVariables());
		savedCommands = new SimpleListProperty<>(FXCollections.observableArrayList());
		savedCommands.addAll(model.getExecutionState().getCommands());
		savedCommandNode = new SimpleListProperty<>(FXCollections.observableArrayList());
		savedCommandNode.addAll(model.getExecutionState().getCommandNodes());
		savedHistory = new SimpleListProperty<>(FXCollections.observableArrayList());
		savedHistory.addAll(model.getHistory().getCommandList());
	}

	private void makeSavedActors(ListProperty<Actor> listProperty) {
		savedActors = FXCollections.observableArrayList();
		for(Actor save: listProperty.get()) {
			savedActors.add(new Actor(save));
		}
	}

	public Workspace getNew(WorkspaceManager workspaceManager, Stage mainStage) {
		Workspace workspace = new Workspace(workspaceManager, mainStage);
		workspace = alterActor(workspace);
		workspace = alterHistory(workspace);
		return workspace;
		
	}

	private Workspace alterHistory(Workspace workspace) {
		for(String history: savedHistory) {
			workspace.getModel().getHistory().add(history);
		}
		workspace.getModel().getExecutionState().getVariables().set(savedVariables);
		workspace.getModel().getExecutionState().getCommands().set(savedCommands);
		workspace.getModel().getExecutionState().getCommandNodes().set(savedCommandNode);
		return workspace;
	}

	private Workspace alterActor(Workspace workspace) {
		workspace.getModel().getViewInterface().getLines().clear();
		workspace.getModel().getViewInterface().getLines().addAll(savedLines);
		workspace.getModel().getViewInterface().getActorProperty().set(savedActors);
		workspace.getModel().getViewInterface().update();
		return workspace;
		
	}
}
