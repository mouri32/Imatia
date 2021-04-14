/**
 * 
 */
package taskmanager.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * class to implement task controller methods
 * 
 * @author Jose Mouriño
 *
 */
public class TaskFileController {

	/**
	 * method to get the path of the text file
	 * 
	 * @return string with path
	 */
	public static String getPath() {

		return "./data.txt";

	}

	/**
	 * method to create the file
	 * 
	 * @param path
	 */
	public static void createFile(String path) {

		File f = new File(path);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			saveFile("LISTA DE TAREAS:\n", path);
			System.out.println("El fichero se ha creado correctamente");
		}

	}

	/**
	 * method to save a string of tasks in a text file
	 * 
	 * @param task
	 * @param path
	 */
	public static void saveFile(String task, String path) {

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
	 * method to read the content of a text file
	 * 
	 * @param path
	 * @return string data
	 */
	public static String readFile(String path) {

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

}
