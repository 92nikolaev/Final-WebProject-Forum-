/**
 * 
 */
package by.epam.training.helper.dao.pool;

import java.util.ResourceBundle;

/**
 * @author Nikolaev Ilya
 *
 */
public class ResourceManagerBD {
	private static ResourceManagerBD instance;
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("DBResource");
	
	public ResourceManagerBD() {}


	public static ResourceManagerBD getInstance() {
		if(instance == null){
			instance = new ResourceManagerBD();
		}
		return instance;
	}
	
	public String getValue(String key){
		return resourceBundle.getString(key);
	}

}
