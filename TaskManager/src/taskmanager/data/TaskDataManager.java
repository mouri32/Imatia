/**
 * 
 */
package taskmanager.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * class to implement task manager methods
 * 
 * @author Jose A. Mouriño
 *
 */
public class TaskDataManager {
	
	
	/**
	 * method to create the file
	 * @param path
	 */
	public static void createFile(String path) {

		File f = new File(path);
		if (!f.exists()) {
			try {
				f.createNewFile();
				saveFile("Lista de ficheros:\n", path);
				System.out.println("El fichero se ha creado correctamente");
			} catch (IOException e) {
				System.out.printf("\nHa ocurrido un error al crear el fichero:\n%s", e.getMessage());

			}
		}
			
	}

	/**
	 * method to save a string of tasks in a text file
	 * 
	 * @param task
	 * @param path
	 */
	private static void saveFile(String task, String path) {

		File file = new File(path);
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			fw.write(task);
			fw.flush();
			fw.close();
		} catch (IOException e) {

			System.out.printf("\nHa ocurrido un error:\n%s", e.getMessage());

		}

	}
	

	/**
	 * method to add a task to a text file
	 * 
	 * @param task
	 * @param path
	 */
	public static void saveTask(String task, String path) {

		String taskTitle = readFile(path) + task;

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
	 * method to read the content of a text file
	 * 
	 * @param path
	 * @return
	 */
	private static String readFile(String path) {

		String data = "";
		int fact;
		
		try {
			FileReader fr = new FileReader(path);
			while ((fact = fr.read()) != -1) {
				data = data + (char) fact;
			}

			fr.close();
		} catch (FileNotFoundException e) {
				System.out.printf("\nHa ocurrido un error. No se ha encontrado el fichero:\n%s", e.getMessage());

		} catch (IOException e) {
				System.out.printf("\nHa ocurrido un error:\n%s", e.getMessage());

		}
		
		return data;

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
		String[] tasks = readFile(path).split("\n");
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

		for (String tarea : task) {

			System.out.println(tarea);

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
		String taskTitle = "";

		// get in an array of strings the tasks saved in the text file
		String[] tasks = readFile(path).split("\n");
		// get the list of tasks from the array to make it mutable
		taskList.addAll(Arrays.asList(tasks));
		// delete selected task if position is correct and the list has tasks
		if (position >= 1 && position < taskList.size() && taskList.size() >1){
			taskList.remove(position);
			System.out.println("La tarea se ha borrado correctamente");

		} else {
			System.out.println("El valor de la posicion no es válido o la lista está vacía");

		}
		// rebuild the task string
		for (String task : taskList) {
			taskTitle += task + "\n";
	
		}
		//save the task string with the changes in the text file
		saveFile(taskTitle, path);

	}
	
	

}// TaskDataManager
