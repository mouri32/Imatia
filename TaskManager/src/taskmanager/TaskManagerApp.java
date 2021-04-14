/**
 * Task Management Program
 */
package taskmanager;

import java.util.List;
import java.util.Scanner;

import taskmanager.data.TaskDataManager;
import taskmanager.tools.ToolsTaskManager;

/**
 * @author Jose A. Mouriño
 *
 */
public class TaskManagerApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String path = "./data.txt";
		TaskDataManager.createFile(path);

		// variable where we store the value selected from the menu
		int option = 0;
		// data entry by keyboard
		Scanner sc = new Scanner(System.in);
		// loop to show the menu
		do {

			// print the menu by console
			System.out.println("\n");
			System.out.println("***** GESTOR DE TAREAS *****");
			System.out.println("1. Crear Tarea");
			System.out.println("2. Listar Tareas");
			System.out.println("3. Borrar Tarea");
			System.out.println("0. Salir del programa");
			System.out.print("Introduzca una opción válida: ");

			// get the option
			option = ToolsTaskManager.getOption();

			// if the option is less than 0 or greater than 3, it is not a
			// valid option
			if (option < 0 || option > 3) {
				System.out.println("Opción no válida. Vuelva a escoger");
			} else if (option != 0) {

				System.out.println("\n");

				switch (option) {

				case 1: // Create Task
					System.out.print("Introduzca la tarea que desea guardar: ");
					String task = sc.nextLine();
					// save the task in the text file
					TaskDataManager.saveTask(task, path);
					break;
				case 2: // List Tasks
					// get the list of tasks
					List<String> tasks = TaskDataManager.getTask(path);
					// print the list of tasks if has elements
					if(tasks.size()>1) {
						TaskDataManager.printTask(tasks);
					}
					else {
						System.out.println("La lista de tareas está vacía");
					}

					break;

				case 3: // Delete Task
					System.out.print("Introduzca la posicion de la tarea que desea borrar: ");
					// we obtain the position that we want to delete
					// start at 0
					int position = Integer.parseInt(sc.nextLine());
					// delete the task in position
					TaskDataManager.deleteTask(position, path);
					break;

				}// switch

			}

		} while (option != 0); // option == 0 exit the program

		// we are not going to read any more values
		sc.close();

		System.out.println("Finalizando la ejecución del programa");

	}// main

}// TaskManagerApp
