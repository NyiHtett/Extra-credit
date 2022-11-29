/**
Name: Nyi Htet
Course: CS125-01
Lab#: Extra-credit
Lab due date: 12/1/2022
Submission Date: 11/29/2022
Description: Moving banner application
*/
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MovingBanner extends Application{  //Moving banner driver class
	private Text info;        //text for moving banner
	private TextField input;  //text field for input 
	public static void main(String[] args) { //main method
		launch(args);
	}//end of method 
	@Override
	public void start(Stage primaryStage) {	//start method	
		info = new Text(200,55,"Hello world ");  //setting the original text for moving banner
		info.setFont(new Font("Cursive", 26));   //set font
		Rectangle rect = new Rectangle(0,0,500,100);  //rectangle for background
		rect.setFill(Color.ANTIQUEWHITE);  //color for rectangle
		Pane pane = new Pane(rect,info);   //pane control for background and text
		
		Label color = new Label("Select color");   //label for instruction to choose color
		ComboBox<String> colorComboBox = new ComboBox<>();   //combo box for color
		colorComboBox.getItems().addAll("red","green","blue","brown","purple");  //input 5 colors
		colorComboBox.setOnAction(event->{   //setting method for colorComboBox using lambda expression
			String colorResult = colorComboBox.getValue(); //get the selected item
			switch(colorResult) {    //compare the selected data with followings
			case "red":
				info.setFill(Color.RED);  //setting the font color red if red is selected
				break;
			case "green":
				info.setFill(Color.GREEN);  //setting the font color green if green is selected
				break;
			case "blue":
				info.setFill(Color.BLUE);  //setting the font color blue if blue is selected
				break;
			case "brown":
				info.setFill(Color.BROWN);  //setting the font color brown if brown is selected
				break;
			case "purple":
				info.setFill(Color.PURPLE);  //setting the font color purple if purple is selected
				break;
			default:
				info.setFill(Color.BLACK);  //black font color for default setting
			}   //end of switch						
		});  //end of setOnAction
		
		Label content = new Label("content"); //label for inputting text
		input = new TextField();              //text field for input 
		HBox inputHbox = new HBox(10,content, input); //horizontal control layout
		inputHbox.setAlignment(Pos.CENTER);   //setting position in center
		
		Button okay = new Button("Okay");     //button for okay
		okay.setOnAction(new OkayButtonHandler());  //register the event handler
		Button exit = new Button("Exit");     //button for exit
		exit.setOnAction(new ExitButtonHandler());  //register the event handler
		HBox buttonHbox = new HBox(10,okay, exit);  //hbox for buttons 
        buttonHbox.setAlignment(Pos.CENTER);  //setting position in center 
        buttonHbox.setPadding(new Insets(10)); //setting padding
        
        Label listView = new Label("Select font size");  //label for instruction for listView
        ListView<String> fontSizeList = new ListView<>(); //listView for font size
		fontSizeList.setPrefHeight(150);   //setting height for listView
		fontSizeList.getItems().addAll("20","23","26","30","33","36");  //inputting 6 font sizes
		fontSizeList.getSelectionModel().selectedItemProperty().addListener(event->{   //lambda expression event listener for being selected in listView
			int size = Integer.parseInt(fontSizeList.getSelectionModel().getSelectedItem());  //get the selected item
			info.setFont(new Font("Cursive",size)); //change the font size
		}); //end of setOnAction
			
		
		VBox vbox = new VBox(10,pane,color,colorComboBox,inputHbox,buttonHbox,listView,fontSizeList); //vbox for all data
		vbox.setPadding(new Insets(10)); //set padding
		Scene scene = new Scene(vbox);   //create scene
		primaryStage.setScene(scene);    //set scene
		primaryStage.show();   //open the window
		Thread taskThread = new Thread(new Runnable() { //create thread
		      @Override
		      public void run() {    //run method
		        double progress = 0; //initiating progress
		        while(true) {  //while loop
		          try {  //try block
		            Thread.sleep(300);  //sleep for some time
		          }  //end of try block
		          catch (InterruptedException e) { //catch block
		            e.printStackTrace(); 
		          }  //end of catch block
		          progress += 0.1;   //increasing progress
		          final double reportedProgress = progress;

		          Platform.runLater(new Runnable() {  //run later method
		            @Override
		            public void run() {  //run method
		              String str = info.getText();  //get the string
		              String first = str.substring(0,1);  //take first character
		              String result = str.substring(1) + first;  //omit first character and concatenate the first character behind
		              info.setText(result);  //set the text
		              }   //end of run method 
		          });  //end of run later method
		        } //end of while loop
		      }  //end of run method
		    });  //end of creating thread
		    taskThread.start();  //start the thread
	}  //end of start method
	public class OkayButtonHandler implements EventHandler<ActionEvent>{ //event handler for okay button
		@Override
		public void handle(ActionEvent event) { //handle method
			String str = input.getText();  //get the input string
			info.setText(str);  //set the string
		} //end of handle method
	} //end of event handler
	public class ExitButtonHandler implements EventHandler<ActionEvent>{  //event handler for exit button
		@Override
		public void handle(ActionEvent event) {  //handle method 
			Platform.exit(); //close the window
		} //end of handle method
	} //end of event handler
}   //end of driver class for application