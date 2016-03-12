package frontend.features;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import constants.CSSPathConstants;
import exceptions.LogicException;
import exceptions.UserInputException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import model.Controller;

/**
 * Text input feature (for code)
 * @author Ryan St Pierre
 */

public class UserTextInput extends VPane {

    public static final double HBOX_SPACING = 5;
    public static final boolean DEBUGGING = true;
    private TextArea textArea;
    private Controller controller;
    private HBox hbox;
    private Button step;

    /**
     * All will be buttons
     */
    Map<String, EventHandler<ActionEvent>> hboxItems = new TreeMap<>();{
        hboxItems.put(getString("Go"), e-> inputEntered());
        hboxItems.put(getString("AddActor"), e-> addActor());
        hboxItems.put(getString("Debug"), e->debug());
    };

    public UserTextInput(Controller controller) {
        this.controller = controller;
        setUp();
        addCSS(CSSPathConstants.INPUT);
    }

    private void createTextField() {
        textArea = new TextArea();
        textArea.getStyleClass().add(CSSPathConstants.TEXT_AREA);
        add(textArea);
    }

    public void setUp() {
        createButtons();
        createTextField();
    }

    private void createButtons() {
        hbox = new HBox(HBOX_SPACING);
        for(Entry<String, EventHandler<ActionEvent>> entry: hboxItems.entrySet()) {
            hbox.getChildren().add(createButton(entry.getKey(), entry.getValue()));
        }
        step = createButton(getString("Step"), e->step());
        step.setVisible(!DEBUGGING);
        hbox.getChildren().add(step);
        add(hbox);
    }

    private void step() {
        try {
            controller.step();
        }  catch (UserInputException|InterruptedException e) {
            AlertMessage alert = new AlertMessage(e.getMessage());
            alert.displayError();

        }
    }

    private void debug() {
        try {
            controller.debug(textArea.getText());
            textArea.clear();
        } catch (UserInputException|LogicException e) {
            AlertMessage alert = new AlertMessage(e.getMessage());
            alert.displayError();
        } 

        step.setVisible(DEBUGGING);

    }

    private void addActor() {
        controller.addActor();
    }

    private Button createButton(String name, EventHandler<ActionEvent> event) {
        Button button = new Button(name);
        button.setOnAction(event);
        return button;
    }

    public void inputEntered() {
        step.setVisible(!DEBUGGING);
        try {
            controller.parseString(textArea.getText());
            textArea.clear();
        } catch (UserInputException|LogicException e) {
            AlertMessage alert = new AlertMessage(e.getMessage());
            alert.displayError();
        } 
    }

    public void setText(String selectedText) {
        textArea.setText(selectedText);
    }

    public void append(String text) {
        if(text == null) {
            return;
        }
        textArea.setText(textArea.getText() + " " + text);
    }

    public void disableStep () {
        step.setVisible(!DEBUGGING);

    }


}
