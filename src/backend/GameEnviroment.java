package backend;
import java.util.Scanner;

public class GameEnviroment {

	public static void main(String[] args) {
		System.out.println("Hello!");
		System.out.println("Welcome to Space Explorers!");
		
		Scanner user_input = new Scanner(System.in);
		System.out.println("How many days would you like to play for? (between 3 and 10 days): ");
		int numDays = Integer.parseInt(user_input.next());
//		while (numDays < 3 || numDays > 10) {
//			System.out.println("Please enter a number between 3 and 10: ");
//			System.out.println("How many days would you like to play for? (between 3 and 10 days): ");
//		}
		System.out.println("You have chosen "+numDays+" Days.");
		
	}
}
