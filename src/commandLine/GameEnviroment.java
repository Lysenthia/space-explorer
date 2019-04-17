package commandLine;
import java.util.Scanner;
import backend.*;

public class GameEnviroment {

	public static void main(String[] args) {
		Scanner user_input = new Scanner(System.in);
		System.out.println("Hello!");
		System.out.println("Welcome to Space Explorers!");
		int numDays;
				
		System.out.println("How many days would you like to play for? (between 3 and 10 days): ");
		numDays = user_input.nextInt();
		while (numDays < 3 || numDays > 10) {
			System.out.println("How many days would you like to play for? (between 3 and 10 days): ");
		}
		System.out.println("You have chosen "+numDays+" Days.");
		
	}
}
