package taskmanager.view;

import java.util.List;
import java.util.Scanner;
import taskmanager.data.TaskDataController;
import taskmanager.data.TaskFileController;
import taskmanager.tools.ToolsTaskManager;

/**
 * class to implement the menu and the initial view
 * @author Jose Mouriño
 *
 */
public class TaskView {
	
	//get the path of the text file with data
	String path = TaskFileController.getPath();
	
	Scanner sc = new Scanner(System.in);
	// loop to show the menu

	public void showMenuView() {
		//variable where we store the position of the task
		int position;
		//variable where we store a task
		String task;	

		// variable where we store the value selected from the menu
		int userOption = 0;
		// data entry by keyboard
		
		do {

			// print the menu by console
			System.out.println("\n");
			System.out.println("***** MENU *****");
			System.out.println("1. Crear Tarea");
			System.out.println("2. Listar Tareas");
			System.out.println("3. Modificar Tarea");
			System.out.println("4. Borrar Tarea");
			System.out.println("0. Salir del programa");
			System.out.print("Introduzca una opción válida: ");

			// get the option
			userOption = ToolsTaskManager.getNumber();

			// if the option is less than 0 or greater than 3, it is not a
			// valid option
			if (userOption < 0 || userOption > 4) {
				System.out.println("Opción no válida. Vuelva a escoger");
			} else if (userOption != 0) {

				Option option = Option.getOption(userOption);

				System.out.println("\n");

				switch (option) {

				case CREATE: // Create Task
					System.out.print("Introduzca la tarea que desea guardar: ");
					task = sc.nextLine();
					// save the task in the text file
					TaskDataController.saveTask(task, path);
					break;
				case LIST: // List Tasks
					// get the list of tasks
					List<String> tasks = TaskDataController.getTask(path);
					// print the list of tasks if has elements
					if (tasks.size() > 1) {
						TaskDataController.printTask(tasks);
					} else {
						System.out.println("La lista de tareas está vacía");
					}

					break;

				case DELETE: // Delete Task
					System.out.print("Introduzca la posicion de la tarea que desea borrar: ");
					// we obtain the position that we want to delete
					position = ToolsTaskManager.getNumber();
					// delete the task in position
					TaskDataController.deleteTask(position, path);
					break;

				case MODIFY:
					System.out.print("Introduzca la posicion de la tarea que desea modificar: ");
					// we obtain the position that we want to modify
					position = ToolsTaskManager.getNumber();
					System.out.print("Introduzca la nueva tarea: ");
					task = sc.nextLine();
					// modify the task in position
					TaskDataController.modifyTask(position, task, path);
					break;

				}// switch

			}

		} while (userOption != 0); // option == 0 exit the program

		// we are not going to read any more values
		sc.close();

	}
	/**
	 * method to show the initial view of the program with the list of task
	 */
	public void showInitialView() {
		
		// get the list of tasks
		List<String> tasks = TaskDataController.getTask(path);
		// print the list of tasks if has elements
		if (tasks.size() > 1) {
			TaskDataController.printTask(tasks);
		} else {
			System.out.println("La lista de tareas está vacía");
		}
		System.out.println("\n");
		//show a message to go to the menu
		System.out.printf("Pulse cualquier tecla para ir al menu principal: ");
		if(sc.hasNextLine()) {
			showMenuView();
			
		}
	}

}
