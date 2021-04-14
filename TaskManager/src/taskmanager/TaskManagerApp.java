/**
 * Task Management Program
 */
package taskmanager;

import taskmanager.data.TaskFileController;
import taskmanager.view.TaskView;

/**
 * @author Jose A. Mouriño
 *
 */
public class TaskManagerApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//crate the file if not exist
		TaskFileController.createFile(TaskFileController.getPath());

		TaskView taskView = new TaskView();

		taskView.showInitialView();

		System.out.println("Finalizando la ejecución del programa");

	}// main

}// TaskManagerApp
