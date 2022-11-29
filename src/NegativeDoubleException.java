/**
Name: Nyi Htet
Course: CS125-01
Lab#: Extra-credit
Lab due date: 12/1/2022
Submission Date: 11/29/2022
Description: custom-designed exception class for validation
*/
public class NegativeDoubleException extends Exception{  //custom-designed exceptional class
	NegativeDoubleException(){   //constructor  
		super("A positive real number, not negative");  //override superclass(exception)'s message
	} //end of constructor
	NegativeDoubleException(double r){  //overloaded constructor with one parameter
		super("A positive real number, not negative ("+r+")"); //override superclass(exception)'s message
	} //end of constructor
	NegativeDoubleException(double r, double z){  //overloaded constructor with two parameter
		super("positive real numbers, not negative numbers("+r+" and "+z+")"); //override superclass(exception)'s message
	} //end of constructor
}    //end of class
