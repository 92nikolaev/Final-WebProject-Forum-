package by.epam.training.helper.bean;



/**
 * 
 * @author Nikolaev Ilya
 * 
 *	This class is used to generalize all classes of entities. Used to betray between layers of information
 */
public class Entity {

	private int id;

	public Entity() {
		super();
	}
	public Entity(int id) {
		super();
		this.id = id;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Entity [id=" + id + "]";
	}
}
