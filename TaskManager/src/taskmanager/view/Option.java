/**
 * 
 */
package taskmanager.view;

/**
 * @author USER
 *
 */
public enum Option {
	
	CREATE, LIST, MODIFY, DELETE, ORDER;
	
	public static Option getOption(int userOption) {
		
		Option option=null;
		
		if(userOption == 1) {
			option = CREATE;
		}
		if(userOption == 2) {
			option = LIST;
		}
		if(userOption == 3) {
			option = MODIFY;
		}
		if(userOption == 4) {
			option = DELETE;
		}
		if(userOption == 5) {
			option = ORDER;
		}
		
		
		return option;
		
		
	}

}

	
