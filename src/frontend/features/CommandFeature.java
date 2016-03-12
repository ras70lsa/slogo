package frontend.features;

import backend.slogo.team04.INonLinearCommand;
import constants.ResourceConstants;
import interfaces.slogo.team04.ICommands;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.util.Callback;

public class CommandFeature extends InteractionListView {
	
	public static final String CSS = "-fx-font-size: 24px;";
	ICommands model;
	
	public CommandFeature(ICommands model) {
		super(model.getCommands(), ResourceConstants.COMMAND_KEY);
		this.model = model;
		setHover();
	}
	
	private void setHover() {
		
		getList().setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
		     @Override 
		     public ListCell<String> call(ListView<String> list) {
		    	 HoverListCell hoverable = new HoverListCell();
		    	 hoverable.setStyle(CSS);
		    	 return hoverable;
		     }
		});
	}

	public class HoverListCell extends ListCell<String> {
		
		public static final String EMPTY = "";
		public static final double OFFSET = 50;
		public static final double CELL_HEIGHT = 75;
		public static final double TEXT_WIDTH = 200;
		public static final String HBOX_CSS = "-fx-background-color: #FFFFFF;";
				
		Popup popup;
		Label label;
		
		public HoverListCell() {
			
			createPopUp();
			setAction();
			this.setPrefHeight(CELL_HEIGHT);
		}

		private void createPopUp() {
			popup = new Popup();
			HBox hbox = new HBox();
			label = new Label(EMPTY);
			hbox.getChildren().add(label);
			hbox.setStyle(HBOX_CSS);
			label.setPrefWidth(TEXT_WIDTH);
			label.setWrapText(true);
			popup.getContent().add(hbox);
		}

		private void setAction() {
			this.setOnMouseEntered(e -> open(e.getSceneX(), e.getSceneY()));
			this.setOnMouseExited(e -> close());
		}
		
		private void open(double x, double y) {
			if(!label.getText().equals(EMPTY)) {
				popup.show(getList(), x - OFFSET, y + OFFSET);
			}
		}
		
		private void close() {
			popup.hide();
		}
		
		private void setContext(String input) {
			if(input!=null) {
				label.setText(addNewLines(input));
			}
		}
	
		private String addNewLines(String input) {
			return input;
		}

		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			setText(item);
			int index = model.getCommands().indexOf(item);
			if(index != -1) {
				INonLinearCommand head = model.getCommandNodes().get(index);
				setContext(head.parsableRepresentation());
			}
		}
	}
}