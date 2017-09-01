package by.epam.training.helper.utils;

public class PaginationUtils {

	public static int pageCounting(int amontItems, int itemOnPage) {
		return(amontItems != 0 && amontItems % itemOnPage == 0 )?(amontItems / itemOnPage):(amontItems / itemOnPage + 1);
	}
}
