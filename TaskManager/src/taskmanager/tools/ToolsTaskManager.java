/**
 * 
 */
package taskmanager.tools;

import java.util.Scanner;

/**
 * utility class, for example to get numerical values
 * 
 * @author Jose A. Mouriño
 *
 */
public class ToolsTaskManager {

	/**
	 * method to get the option and make it a numeric value
	 * 
	 * @return an int which is the option
	 */
	public static int getOption() {

		int option = 0;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean CorrectValue = true;

		// Loop for get a number
		do {

			if (sc.hasNextInt()) { // check that you enter a number
				CorrectValue = true;
				option = sc.nextInt();
			} else {
				CorrectValue = false;
				sc.next();
				// message in case you do not enter a number
				System.out.print("Debe de introducir una opción válida: ");
			}

		} while (!CorrectValue);

		// sc.close();

		return option;

	}

}// ToolsTaskManager
