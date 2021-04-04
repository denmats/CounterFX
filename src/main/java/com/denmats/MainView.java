package com.denmats;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainView extends VBox {

    private Button increase;
    private Label label;
    private Button decrease;
    private TextField textField;
    private Button save;
    private Button revert;
    private Canvas canvas;
    private Counter counter;
    private Keeper keeper;

    public Label getLabel() {
        return label;
    }

    public Counter getCounter() {
        return counter;
    }


    public MainView() {

        this.increase = new Button("Increase");
        this.label = new Label("0");
        this.decrease = new Button("Decrease");
        this.textField = new TextField();
        this.save = new Button("Save Current Value");
        this.revert = new Button("Upload Saved Value");

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(this.increase,this.label,this.decrease, this.textField, this.save, this.revert);
        this.canvas = new Canvas(800,30);
        this.counter = new Counter();
        this.keeper = new Keeper("sample.txt");
        
        this.increase.setOnAction(this::handleIncrease);
        this.decrease.setOnAction(this::handleDecrease);
        this.textField.setOnAction(this::handleTextFieldChanges);
        this.save.setOnAction(this::handleSaveToFileCurrentValue);
        this.revert.setOnAction(this::handleImportSavedNumberToApplication);

        this.getChildren().addAll(hBox,this.canvas);
    }

    private void handleImportSavedNumberToApplication(ActionEvent actionEvent) {
        keeper.readFromFile(this);
    }

    private void handleSaveToFileCurrentValue(ActionEvent actionEvent) {
        keeper.createFile();
        keeper.writeToFile(this);
    }

    private void handleTextFieldChanges(ActionEvent actionEvent) {
        textField.selectAll();
        textField.copy();
        int number;
        try{
            number = Integer.parseInt(String.valueOf(textField.getText()));
        }catch (NumberFormatException e){
            number = counter.getCount();
        }
        counter.setCount(number);
        label.setText(String.valueOf(number));
    }

    private void handleDecrease(ActionEvent actionEvent) {
        counter.decrement();
        label.setText(String.valueOf(counter.getCount()));
    }

    private void handleIncrease(ActionEvent actionEvent) {
        counter.increment();
        label.setText(String.valueOf(counter.getCount()));
    }

}
