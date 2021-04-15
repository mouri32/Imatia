package taskmanager.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import taskmanager.data.TaskDataController;
import taskmanager.data.TaskFileController;

/**
 * class to implement the menu and the initial view
 * 
 * @author Jose Mouri�o
 *
 */
public class TaskView {

	// get the path of the text file with data
	String path = TaskFileController.getPath();

	
	// loop to show the menu

	public void showMenuView() {
		
		Scanner sc = new Scanner(System.in);
		// variable where we store the position of the task
		int position, newPosition;
		// variable where we store a task
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
			System.out.println("5. Cambiar Tarea De Posicion");
			System.out.println("0. Salir del programa");
			System.out.print("Introduzca una opci�n v�lida: ");

			// get the option
			userOption = ToolsTaskManager.getNumber();

			// if the option is less than 0 or greater than 3, it is not a
			// valid option
			if (userOption < 0 || userOption > 5) {
				System.out.println("Opci�n no v�lida. Vuelva a escoger");
			} else if (userOption != 0) {

				Option option = Option.getOption(userOption);

				System.out.println("\n");

				switch (option) {

				case CREATE: // Create Task
					System.out.print("Introduzca la tarea que desea guardar: ");
					task = sc.nextLine();
					// save the task in the text file
					TaskDataController.saveTask(task, path);
					showInitialView();
					break;
				case LIST: // List Tasks
					showInitialView();
					break;

				case DELETE: // Delete Task
					
					// we obtain the position that we want to delete
					position = selectTask(path);
					// delete the task in position
					boolean confirmationDelete = ToolsTaskManager.getConfirmation("borrar");
					// get the confirmation of the delete
					if(confirmationDelete == true) {
						TaskDataController.deleteTask(position, path);
						showInitialView();
					}
					break;

				case MODIFY:
					// we obtain the position that we want to modify
					position = selectTask(path);
					System.out.print("Introduzca la nueva tarea: ");
					task = sc.nextLine();
					boolean confirmationModify = ToolsTaskManager.getConfirmation("modificar");
					// get the confirmation of the delete
					if(confirmationModify == true) {
						// modify the task in position
						TaskDataController.modifyTask(position, task, path);
						showInitialView();
					}
					
					break;

				case ORDER:
					// we obtain the position that we want to modify
					position = selectTask(path);
					System.out.print("Ahora introduzca la nueva posicion para la tarea: ");
					newPosition = ToolsTaskManager.getNumber();

					TaskDataController.orderTask(position, newPosition, path);
					showInitialView();
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

		Scanner sc = new Scanner(System.in);
		// get the list of tasks
		List<String> tasks = TaskDataController.getTask(path);
		// print the list of tasks if has elements
		if (tasks.size() > 1) {
			TaskDataController.printTask(tasks);
		} else {
			System.out.println("La lista de tareas est� vac�a");
		}
		System.out.println("\n");
		// show a message to go to the menu
		System.out.println("Pulse la tecla enter para ir al menu.");
		String enterkey = sc.nextLine();
		if (enterkey.isEmpty()) {
			showMenuView();

		}
	}

	public static int selectTask(String path) {

		Scanner sc = new Scanner(System.in);
		List<String> taskList = new ArrayList<>();
		String title = "";
		int position = 0;

		// get the list of tasks
		taskList = TaskDataController.getTask(path);

		System.out.print("Introduzca la posicion o el titulo de la tarea que desea seleccionar: ");
		if (sc.hasNextInt()) {
			position = sc.nextInt();
		} else {
			title = sc.nextLine();
		}

		if (title != "") {

			position = taskList.indexOf(title);
		}
		//sc.close();
		return position;
	}

}
