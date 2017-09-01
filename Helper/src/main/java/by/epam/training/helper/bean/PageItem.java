package by.epam.training.helper.bean;

import java.util.List;

/**
 *  The class is used to get a list of items and the number of pages.
 *	It is used to receive all the data on the service layer and send them to the command layer.
 *	Used for patination
 * 	@author Nikolaev Ilya
 *  @param <E> 
 */
public final class PageItem<E extends BaseEntity> {
	private List<E> items;
    private int pageCount;
	
	public PageItem (List<E> items, int pageCount) {
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
