/**
Name: Nyi Htet
Course: CS125-01
Lab#: Extra-credit
Lab due date: 12/1/2022
Submission Date: 11/29/2022
Description: driver class for RecursiveMultiply class
*/
import java.security.Timestamp;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RecursiveMultiplyApp extends Application{  //driver class
	private TextField first;    
	private TextField second;
	private TextField result;
	public static void main(String[] args) {  //main method
		launch(args);
	}   //end of main method
	@Override
	public void start(Stage primaryStage) {   //start method 
		Label firstLabel = new Label("first number");   //label for first num
		Label secondLabel = new Label("second number"); //label for second num
		first = new TextField();    //text field for first num
		second = new TextField();   //text field for second num
		result = new TextField();   //text field for result
		result.setEditable(false);  //setting result as uneditable text field
		HBox hbox1 = new HBox(10, firstLabel, first);     //hbox layout for label and text field of first num
		HBox hbox2 = new HBox(10, secondLabel, second);   //hbox layout for label and text field of second num
		hbox1.setPadding(new Insets(10));  //hbox1 set padding
		hbox2.setPadding(new Insets(10));  //hbox2 set padding
		Button okay = new Button("OK");    //button for okay
		okay.setOnAction(new OkayButtonHandler());  //register the event handler
		Button clear = new Button("Clear"); //button for clear
		clear.setOnAction(new ClearButtonHandler()); //register the event handler
		Button exit = new Button("Exit");   //button for exit
		exit.setOnAction(new ExitButtonHandler());   //register the event handler
		
		HBox hbox3 = new HBox(10,okay,clear,exit);   //horizontal control layout for buttons
		VBox vbox = new VBox(10,hbox1, hbox2,hbox3, result);  //vertical control layout
		vbox.setPadding(new Insets(10));   //vbox set padding
		Scene scene = new Scene(vbox);     //create scene
		primaryStage.setScene(scene);      //set scene
		primaryStage.show();   //open the window
	}  //end of start method

	class OkayButtonHandler implements EventHandler<ActionEvent>{ //event handler class for okay button
        @Override
		public void handle(ActionEvent event) {   //handle method
        	try {   //try block
        		int x = Integer.parseInt(first.getText());   //getting first num
    			int y = Integer.parseInt(second.getText());  //getting second num
    			if (x<0 && y<0) {     //checking whether both are negatives
    				throw new NegativeDoubleException(x,y);
    			}
    			if (x<0) {            //checking whether first num negative 
    				throw new NegativeDoubleException(x);
    			} 
    			if (y<0) {            //checking whether first num negative 
    				throw new NegativeDoubleException(y);
    			}
    			result.setText(RecursiveMultiply.add(x, y)+"");  //call the static method of RecursiveMultiply class
        	}  //end of try block
        	catch(NullPointerException e) {    //catch block in case user didn't assign any input
				JOptionPane.showMessageDialog(null, "must enter a positive real number!");  //show message using JOptionPane
			}    //end of catch block
			catch(NumberFormatException e) {   //catch block in case user put letters
				JOptionPane.showMessageDialog(null, "A positive real number, not letter");  //show message using JOptionPane
				first.setText("");   //resetting data in first num
				second.setText("");  //resetting data in second num
			}   //end of catch block
			catch(NegativeDoubleException e) {   //catch block in case user assign negative data
				JOptionPane.showMessageDialog(null, e.getMessage()); //show message using JOptionPane
				first.setText("");   //resetting data in first num
				second.setText("");  //resetting data in second num
			}  //end of catch block
			
		} //end of handle method 
	} //end of event handler
	class ClearButtonHandler implements EventHandler<ActionEvent>{  //Event handler for clear button
        @Override
		public void handle(ActionEvent event) { //handle method
			first.setText("");   //resetting first num
			second.setText("");  //resetting second num
			result.setText("");  //resetting result
		} //end of handle method
	} //end of event handler
	class ExitButtonHandler implements EventHandler<ActionEvent>{  //Event handler for exit button
        @Override
		public void handle(ActionEvent event) {  //handle method
			Platform.exit(); //close the window
		}  //end of handle method 
	} //end of event handler
}  //end of driver class

