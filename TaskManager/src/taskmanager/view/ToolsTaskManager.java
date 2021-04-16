package taskmanager.view;

import java.util.Scanner;

/**
* utility class, for example to get numerical values
* 
* @author Jose A. Mouriño
*
*/
public class ToolsTaskManager {
	
	private static Scanner sc;

	/**
	 * method to get the option and make it a numeric value
	 * 
	 * @return an int which is the option
	 */
	
	
	public static int getNumber() {

		int option = 0;
		sc = new Scanner(System.in);
		
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
	
	/**
	 * method to confirm the delete or the modify of task
	 * @param action
	 * @return boolean
	 */
	public static boolean getConfirmation(String action) {
		
		Boolean confirmation  = false;
		sc = new Scanner(System.in);
		
		System.out.println("¿Esta seguro de "+action+" la tarea?");
		System.out.print("Escriba  Si/No para confirmar: ");
		
		String request = sc.nextLine();
		
		if(request.equalsIgnoreCase("si")) {
			confirmation = true;
		}
		if(request.equalsIgnoreCase("no")) {
			confirmation = false;
		}
		
		 return confirmation;
	}


}
