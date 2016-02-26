package frontend_features;

import java.util.List;

import frontend_slogo_team04.GuiUserOption;
import interfaces_slogo_team04.ICommands;
import javafx.scene.Node;
import javafx.scene.control.ListView;

public class CommandFeature extends StaticPane {
	
	public static final double LABEL_HEIGHT = 25;
	public static final double SCROLL_BAR = 3.9;
	ICommands model;
	private ListView<String> commands;
	
	public CommandFeature(ICommands model) {
		this.model = model;
		addCSS("visual_resources/DefaultCommands.css");
		createListView();
		addListeners();
	}

	private void createListView() {
		commands = new ListView<String>();
		commands = new ListView<String>();
		commands.setVisible(true);
		commands.setItems(model.getCommands());
		commands.setOnMouseClicked(e -> print());
		add(commands, 0, 0);
		
	}
	
	private void addListeners() {
		getWidth().addListener((a,b,w) -> resize(w.doubleValue(), getHeight().get()));
		getHeight().addListener((a,b,h) -> resize(getWidth().get(),h.doubleValue()));
		
	}
	
	private void resize(double width, double height) {
		commands.setPrefSize(width, height- LABEL_HEIGHT - SCROLL_BAR);
	}

	private void print() {
		System.out.println(commands.getSelectionModel().getSelectedItem());
		
	}

	protected List<Node> getReleventProperties(GuiUserOption factory) {
		return null;
	}

}
