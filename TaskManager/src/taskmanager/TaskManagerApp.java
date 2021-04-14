/**
 * Task Management Program
 */
package taskmanager;

import taskmanager.data.TaskFileController;
import taskmanager.view.TaskView;

/**
 * @author Jose A. Mouri�o
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

		System.out.println("Finalizando la ejecuci�n del programa");

	}// main

}// TaskManagerApp
