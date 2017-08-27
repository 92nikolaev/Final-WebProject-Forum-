/**
 * 
 */
package by.epam.training.helper.dao.pool;

import java.util.ResourceBundle;

/**
 * Provides access to a file(*.properties) with database settings.
 * @author Nikolaev Ilya
 *
 */
public class ResourceManagerDB {
	private final String resource = "DBResource";
	private static ResourceManagerDB instance;
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(resource);
	private ResourceManagerDB() {}
	public static ResourceManagerDB getInstance() {
		if(instance == null){
			instance = new ResourceManagerDB();
		}
		return instance;
	}
	public String getValue(String key){
		return resourceBundle.getString(key);
	}
}
