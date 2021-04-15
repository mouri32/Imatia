/**
 * 
 */
package taskmanager.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * class to implement task controller methods
 * 
 * @author Jose A. Mouriño
 *
 */
public class TaskDataController {

	private static Scanner sc;

	/**
	 * method to add a task to a text file
	 * 
	 * @param task
	 * @param path
	 */
	public static void saveTask(String task, String path) {

		String taskTitle = TaskFileController.readFile(path) + task;

		try {
			File file = new File(path);
			FileWriter fw = new FileWriter(file);
			// add a mark to separate each task in the string
			fw.write(taskTitle + "\n");
			fw.flush();
			fw.close();
			System.out.println("La tarea se ha guardado correctamente");
		} catch (IOException e) {
			System.out.printf("\nHa ocurrido un error:\n%s", e.getMessage());

		}
	}

	/**
	 * method to get a list of tasks
	 * 
	 * @param path
	 * @return
	 */
	public static List<String> getTask(String path) {

		List<String> taskList = new ArrayList<>();
		// get in an array of strings the tasks saved in the text file
		String[] tasks = TaskFileController.readFile(path).split("\n");
		//  get the list of tasks from the array to make it mutable
		taskList.addAll(Arrays.asList(tasks));

		return taskList;

	}
	

	/**
	 * method to print a list of tasks indicating the position
	 * 
	 * @param task
	 */
	public static void printTask(List<String> task) {
		// we create a variable to display an identifier in each task to better identify
		// the tasks
		int position = 1;
		
		System.out.println("\n"+task.get(0)+"\n");
		
		for (int i = 1; i < task.size(); i++) {

			System.out.println(position + ". " + task.get(i));
			position++;

		}

	}

	/**
	 * method to delete a task
	 * 
	 * @param position
	 * @param path
	 */
	public static void deleteTask(int position, String path) {

		List<String> taskList = new ArrayList<>();
		//  get the list of tasks
		taskList = getTask(path);
		
		// delete selected task if position is correct and the list has tasks
		if (position >= 1 && position < taskList.size() && taskList.size() > 1) {
			taskList.remove(position);
			System.out.println("La tarea se ha borrado correctamente");

		} else {
			System.out.println("El valor de la posicion no es válido o la lista está vacía");

		}
		// persist the list of tasks with the modifications
		saveListTaskModifications(taskList, path);

	}

	/**
	 * method to modify a task by making a substitution
	 * 
	 * @param position
	 * @param task
	 * @param path
	 */
	public static void modifyTask(int position, String task, String path) {

		List<String> taskList = new ArrayList<>();
		//  get the list of tasks
		taskList = getTask(path);
		// modify selected task if position is correct and the list has tasks
		if (position >= 1 && position < taskList.size() && taskList.size() > 1) {
			
			taskList.set(position, task);
			System.out.println("La tarea se ha modificado correctamente");

		} else {
			System.out.println("\n");
			System.out.println("El valor de la posicion no es válido o la lista está vacía");

		}
		// persist the list of tasks with the modifications
		saveListTaskModifications(taskList, path);

	}

	/**
	 * method to save the modifications of the task list in the file
	 * 
	 * @param taskList
	 * @param path
	 */
	private static void saveListTaskModifications(List<String> taskList, String path) {

		String taskTitle = "";
		// rebuild the task string
		for (String task : taskList) {
			taskTitle += task + "\n";

		}
		// save the task string with the changes in the text file
		TaskFileController.saveFile(taskTitle, path);

	}
	
	public static void orderTask(int position, int newPosition, String path) {
		
		List<String> taskList = new ArrayList<>();
		String task="";
		String taskChanged="";

		//  get the list of tasks
		taskList = getTask(path);
		// modify selected task if position is correct and the list has tasks
		if (position >= 1 && position < taskList.size() && taskList.size() > 1 
				&& newPosition >=1 && newPosition < taskList.size()) {
			task = taskList.get(position);
			taskChanged = taskList.get(newPosition);
			taskList.set(newPosition, task);
			taskList.set(position, taskChanged);
			System.out.println("La tarea se ha insertado en la posicion seleccionada correctamente");

		} else {
			System.out.println("\n");
			System.out.println("El valor de la posicion no es válido o la lista está vacía");

		}
		// persist the list of tasks with the modifications
		saveListTaskModifications(taskList, path);
			
	}
	
	public static int selectTask(String path) {

		sc = new Scanner(System.in);
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
	
	
	
}// TaskDataManager
