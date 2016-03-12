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
import javafx.scene.text.Text;
import javafx.util.Callback;

public class CommandFeature extends InteractionListView {
	
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
		    	 return hoverable;
		     }
		});
	}

	public class HoverListCell extends ListCell<String> {
		
		public static final String EMPTY = "";
		ContextMenu menu;
		MenuItem item;
		
		public HoverListCell() {
			menu = new ContextMenu();
			menu.setMaxWidth(50);
			item = new MenuItem();
			item.setText(EMPTY);
			menu.getItems().add(item);
			setContext(null);
			setAction();
			this.setPrefHeight(150);
		}
		
		private void setAction() {
			this.setOnMouseEntered(e -> open(e.getSceneX(), e.getSceneY()));
			this.setOnMouseExited(e -> close());
		}
		
		private void open(double x, double y) {
			if(!item.getText().equals(EMPTY)) {
				menu.show(getList(), x - 50, y + 50);
			}
		}
		
		private void close() {
			menu.hide();
		}
		
		private void setContext(String input) {
			if(input!=null) {
				item.setText(addNewLines(input));
			}
		}
	
		private String addNewLines(String input) {
			String [] words = input.split(" ");
			for(String str: words) {
				if(!str.equals(" ")) {
				System.out.println(str);
				}
			}
			String newLines = input.replaceAll("((?:\\w+\\s){2}\\w+)(\\s)", "$1\n");
			return newLines;
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
