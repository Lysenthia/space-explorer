package commandLine;
import java.util.Scanner;
import backend.*;

public class GameEnviroment {
	
	public static boolean hasInteger(String s) {
		Scanner reader = new Scanner(s.trim());
		boolean hasInt = reader.hasNextInt();
		reader.close();
		if (hasInt) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int extractInt(String s) {
		Scanner reader = new Scanner(s.trim());
		int value = reader.nextInt();
		reader.close();
		return value;
	}

	public static void main(String[] args) {
		int days = 0;
		String line;
		Scanner input = new Scanner(System.in);
		System.out.println("Hello!");
		System.out.println("Welcome to Space Explorers!");			
		System.out.println("How many days would you like to play for? (between 3 and 10 days): ");
		line = input.nextLine();
		if (hasInteger(line)) {
			days = extractInt(line);
		}
		while (days < 3 || days > 10) {
			System.out.println("Please enter an integer between 3 and 10 inclusive: ");
			line = input.nextLine();
			if (hasInteger(line)) {
				days = extractInt(line);
			}
		}
		System.out.println(days);
		input.close();
		System.out.println("Done");
	}
}
