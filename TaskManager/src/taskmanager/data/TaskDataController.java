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

/**
 * class to implement task controller methods
 * 
 * @author Jose A. Mouriño
 *
 */
public class TaskDataController {

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
		// get the list of tasks from the array
		taskList = Arrays.asList(tasks);

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
		int position = 0;

		for (String tarea : task) {

			System.out.println(position + ". " + tarea);
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

		// get in an array of strings the tasks saved in the text file
		String[] tasks = TaskFileController.readFile(path).split("\n");
		// get the list of tasks from the array to make it mutable
		taskList.addAll(Arrays.asList(tasks));
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

		// get in an array of strings the tasks saved in the text file
		String[] tasks = TaskFileController.readFile(path).split("\n");
		// get the list of tasks from the array to make it mutable
		taskList.addAll(Arrays.asList(tasks));
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
}// TaskDataManager
