/**
Name: Nyi Htet
Course: CS125-01
Lab#: Extra-credit
Lab due date: 12/1/2022
Submission Date: 11/29/2022
Description: Recursive Multiply Operation class
*/
import java.util.Scanner;

public class RecursiveMultiply {     //operation class
	public static int add(int x, int y) {  //static method
		if (x==0) {                  //base case
			return 0;
		}
		else {                 
			return y+add(x-1,y);     //recursive adding method
		}
	}  //end of method
}   //end of class
