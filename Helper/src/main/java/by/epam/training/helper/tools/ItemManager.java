package by.epam.training.helper.tools;

import java.util.List;

/**
 * @author Nikolaev Ilya
 *	The class is used to get the list of items and the number of pages
 */
public final class ItemManager<E> {
	private List<E> items;
    private int pageCount;
	
	public ItemManager (List<E> items, int pageCount) {
		this.items = items;
		this.pageCount = pageCount;
	}
	public List<E> getItems() {
		return items;
	}
	public void setItems(List<E> items) {
		this.items = items;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
